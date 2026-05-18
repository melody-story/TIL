"""
의약품 정보 조회 프록시 서버
─────────────────────────────────────────────────────
- 클라이언트(노트북)는 X-API-Key 헤더로 인증
- 서버가 식약처 e약은요 공공 API를 대신 호출
- 동일 약품명은 lru_cache 로 캐싱
"""

import os
import httpx
from functools import lru_cache
from fastapi import FastAPI, HTTPException, Security
from fastapi.security import APIKeyHeader
from fastapi.middleware.cors import CORSMiddleware

# ────────────────────────────────────────────────────
# 환경 변수
# ────────────────────────────────────────────────────
DATA_GO_KR_KEY = os.environ['DATA_GO_KR_API_KEY']
CLIENT_KEYS = {
    k.strip() for k in os.environ['CLIENT_API_KEYS'].split(',') if k.strip()
}

UPSTREAM = (
    'https://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList'
)

# ────────────────────────────────────────────────────
# FastAPI
# ────────────────────────────────────────────────────
app = FastAPI(title='Medication Info Proxy', version='1.0.0')

app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_methods=['GET'],
    allow_headers=['*'],
)

api_key_header = APIKeyHeader(name='X-API-Key', auto_error=False)


def verify_key(key: str | None = Security(api_key_header)) -> str:
    if not key or key not in CLIENT_KEYS:
        raise HTTPException(status_code=401, detail='Invalid or missing X-API-Key')
    return key


# ────────────────────────────────────────────────────
# 공공 API 호출 + 캐싱
# ────────────────────────────────────────────────────
@lru_cache(maxsize=500)
def _fetch_from_public_api(name: str) -> dict:
    params = {
        'serviceKey': DATA_GO_KR_KEY,
        'itemName': name,
        'type': 'json',
        'numOfRows': 1,
    }
    r = httpx.get(UPSTREAM, params=params, timeout=10)
    payload = r.json()
    body = payload.get('body') or payload.get('response', {}).get('body', {})
    items = body.get('items', [])
    if not items:
        return {}
    item = items[0] if isinstance(items, list) else items
    return {
        'name'        : item.get('itemName', '') or '',
        'company'     : item.get('entpName', '') or '',
        'efficacy'    : (item.get('efcyQesitm') or '').strip(),
        'usage'       : (item.get('useMethodQesitm') or '').strip(),
        'precaution'  : (item.get('atpnQesitm') or '').strip(),
        'side_effects': (item.get('seQesitm') or '').strip(),
        'storage'     : (item.get('depositMethodQesitm') or '').strip(),
    }


# ────────────────────────────────────────────────────
# 엔드포인트
# ────────────────────────────────────────────────────
@app.get('/health')
def health() -> dict:
    return {'status': 'ok'}


@app.get('/api/medication')
def get_medication(name: str, _: str = Security(verify_key)) -> dict:
    """약품명으로 의약품 정보 조회 (요청: ?name=에어클란정)."""
    if not name.strip():
        raise HTTPException(status_code=400, detail='name 파라미터가 비어있습니다')
    data = _fetch_from_public_api(name.strip())
    if not data:
        raise HTTPException(status_code=404, detail=f"'{name}' 약품 정보 없음")
    return data
