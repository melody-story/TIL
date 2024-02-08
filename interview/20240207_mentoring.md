## 멘토링 피드백

[프로젝트]

- 기술스택
    - [x] 프로젝트 기술스택 21로 수정 - 마지막 LTS
    - [x] Object Storage 로 변경
    - 프로젝트의 기술 스택 선택 기준은 무엇인가요?
    - Spring boot 버전 정하기
    - 빌드와 배포 툴 결정
    - Mysql 버전 정하기
- ERD
    - [x] 멤버 아이디 -> spaceUser로 변경
    - [x] 수정자, 생성자, String 타입으로 변경
    - dbdiagram으로 ERD 다시 작성해보기
    - db column snake case로 수정, 타입 수정하기
    - audit column 기본적으로 넣기(is_deleted 등)
    - 이력 관리
        - delete 이력 관리는 필요있는 것만

## 공부할내용

- 자바 11과 var 사용에 대한 장단점을 명확히 이해하기 이를 컨벤션에 적용할때고려사항
- JPA와 Query DSL 선택 이유를 명확히 하고, 이를 면접에서 설명할 수 있도록 준비하기
- java 21의 장점, 특징 공부
- gadle VS maven
- Spring boot 버전에 따른 차이와 장단점
- JPA의 장단점
- Spring Data JPA VS JPA
- JPA VS QueryDSL
    - JPA와 마이바티스 중 JPA를 선택한 이유
    - Query DSL을 사용하는 주된 이유
- Mybatis VS JPA
- Spok VS Junit
- CI/CD 파이프라인에 대한 이해를 깊게 하고, 각 도구의 역할과 사용 방법을 숙지하기
    - CI/CD 파이프라인을 구성할 때 고려해야 할 주요 요소
- jenkins vs git Action
- SRE Devops
- 데이터 호출 시 Enum VS 테이블 장단점 비교
- Object Storage URL VS id
- History table은 실무에서 FK를 걸지 않는다. FK를 거는 것과 걸지 않는 것의 장단점
- delete 이력관리를 한테이블에서 하는 것과 기존 테이블에서 하는 것의 장단점.

## 주제

1. 프로젝트 기술스택 선정하고 해당 기술 스택을 선정한 이유
    - 공식문서, 커뮤니티 등 수치에 따른 근거 제시
2. 대용량 트래픽이 발생하는 이벤트가 예정되어있는 있을때 Young 영역과 Old 영역의 비율은 어떻게 하는게 좋을지