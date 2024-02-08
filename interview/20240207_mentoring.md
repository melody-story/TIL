## 멘토링 피드백

1. 기술스택을 왜 사용하는 지에 대한 고찰 하기
2. 프로젝트 기술스택 21로 수정
4. Spring boot 버전 정하기
4. Mysql 버전 정하기
5. 첫 프로젝트로 mybatis 사용을 권장
6. Object Storage 용어로 변경
7. 빌드와 배포 툴 결정
8. navercloud로 어플리케이션 서버띄우고 외부에서 접속가능하게 하기
9. dbdiagram으로 ERD 다시 작성해보기
10. db column snake case로 수정, 타입 수정하기
11. 나중에 추가하려면 비용이드므로 테이블 내에 audit column 기본적으로 넣기(is_deleted 등)
    - 수정자, 생성자, String 타입으로 변경
12. 멤버 아이디 -> spaceUser로 변경
13. 이력 관리
    - delete 이력 관리는 필요있는 것만

## 공부할내용

1. java 11부터 등장한 var의 장단점과 이를 컨벤션에 적용할때고려사항
2. java 21의 장점, 특징 공부
3. gadle VS maven
4. Spring boot 버전에 따른 차이와 장단점
5. JPA의 장단점
6. Spring Data JPA VS JPA
7. JPA VS QueryDSL
8. Mybatis의 장단점
9. Spok VS Junit
10. CI/CD란?
11. CI의 개념
12. jenkins vs git Action
13. SRE Devops
14. 데이터 호출 시 Enum VS 테이블 장단점 비교
15. Object Storage URL VS id
16. History table은 실무에서 FK를 걸지 않는다. FK를 거는 것과 걸지 않는 것의 장단점
17. delete 이력관리를 한테이블에서 하는 것과 기존 테이블에서 하는 것의 장단점.

## 주제

1. 프로젝트 기술스택 선정하고 해당 기술 스택을 선정한 이유
   - 공식문서, 커뮤니티 등 수치에 따른 근거 제시
2. 대용량 트래픽이 발생하는 이벤트가 예정되어있는 있을때 Young 영역과 Old 영역의 비율은 어떻게 하는게 좋을지