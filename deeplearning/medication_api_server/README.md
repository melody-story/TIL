# Medication Info Proxy API

식약처 e약은요 공공 API를 클라이언트 인증키 기반으로 중계하는 FastAPI 서버.

```
[Colab 노트북] --X-API-Key 헤더--> [본 서버] --serviceKey--> [e약은요 공공 API]
```

## 1. 사전 준비

```bash
# 공공데이터포털 인증키 발급
# https://www.data.go.kr/data/15075057/openapi.do → 활용신청

# 클라이언트 키 생성
python -c "import secrets; print(secrets.token_urlsafe(32))"
```

## 2. 로컬 개발

```bash
cd medication_api_server
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt

cp .env.example .env
# .env 편집: DATA_GO_KR_API_KEY, CLIENT_API_KEYS 입력

# 환경변수 로드 후 실행
set -a; source .env; set +a
uvicorn server:app --reload --port 8000
```

테스트:
```bash
curl -H "X-API-Key: 클라이언트키" \
  'http://localhost:8000/api/medication?name=에어클란정'
```

## 3. Docker 배포 (권장)

```bash
cd medication_api_server

# 환경 변수 파일 준비
cp .env.example .env && nano .env

# 빌드 + 실행
docker compose up -d --build

# 로그 확인
docker compose logs -f

# 헬스체크
curl http://localhost:8000/health
```

업데이트:
```bash
git pull && docker compose up -d --build
```

> Nginx 리버스 프록시는 호스트에서 그대로 사용 (컨테이너는 `127.0.0.1:8000` 만 노출).

## 4. systemd + venv 배포 (Docker 없이)

```bash
# 코드 배포
sudo mkdir -p /opt/medication_api_server
sudo cp -r ./* /opt/medication_api_server/
cd /opt/medication_api_server

# 가상환경 + 의존성
sudo python3 -m venv .venv
sudo .venv/bin/pip install -r requirements.txt

# 환경 변수 파일
sudo cp .env.example .env
sudo nano .env   # 실제 키 입력
sudo chmod 600 .env

# systemd 등록
sudo cp medication-api.service /etc/systemd/system/
sudo nano /etc/systemd/system/medication-api.service   # User=YOUR_USER 수정
sudo systemctl daemon-reload
sudo systemctl enable --now medication-api
sudo systemctl status medication-api

# Nginx 리버스 프록시
sudo cp nginx.conf.example /etc/nginx/sites-available/medication-api
sudo nano /etc/nginx/sites-available/medication-api   # server_name, ssl 경로 수정
sudo ln -s /etc/nginx/sites-available/medication-api /etc/nginx/sites-enabled/
sudo nginx -t && sudo systemctl reload nginx
```

## 5. 엔드포인트

| Method | Path | 인증 | 설명 |
|--------|------|------|------|
| GET | `/health` | 없음 | 헬스체크 |
| GET | `/api/medication?name=약품명` | `X-API-Key` 헤더 | 약품 정보 조회 |

### 응답 예시

```json
{
  "name": "에어클란정",
  "company": "...",
  "efficacy": "이 약은 ...",
  "usage": "...",
  "precaution": "...",
  "side_effects": "...",
  "storage": "..."
}
```

### 에러 코드

| 코드 | 원인 |
|------|------|
| 400 | `name` 파라미터 누락 |
| 401 | `X-API-Key` 누락 또는 미등록 |
| 404 | 해당 약품 정보 없음 |

## 6. 클라이언트 키 회전

`.env` 의 `CLIENT_API_KEYS` 를 수정한 뒤 재시작:

- Docker: `docker compose restart`
- systemd: `sudo systemctl restart medication-api`

콤마 구분으로 여러 키를 동시에 활성화할 수 있어 무중단 회전이 가능합니다.
