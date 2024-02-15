## 멘토링 피드백

- 업데이트가 많이 일어나는 인증관련 데이터는 User테이블에서 분리하여 관리

[프로젝트]

- 기술스택
    - 프로젝트 기술스택 21로 선정한 이유? 1-2가지 정도 알고 있기
    - 그밖의 프로젝트의 기술 스택 선택 기준 정리해보기
- 컨벤션
    - 깃 브랜치 전략
    - 자바 코드 컨벤션(들여쓰기 등)
        - [네이버핵데이](https://naver.github.io/hackday-conventions-java/)
        - [구글](https://google.github.io/styleguide/javaguide.html)
        - .editorconfig
        - save action plugin
    - git, github convention
        - commit message
            - 한글혼용, 이슈번호
        - pr-template
        - issue-template
    - 객체지향 체조원칙 3가지 정하기
        - 참고 : https://jamie95.tistory.com/99
    - 디렉터리구조/아키텍쳐 정하기
        - 참고 : https://github.com/cheese10yun/spring-guide/blob/master/docs/directory-guide.md
- 처음 등장하는 어노테이션에 주석으로 설명달기
    - @Service
    - @Controller
    - @Test
    - @SpringBootTest
    - @Slf4j 등

[mission 2]

- ci 적용 (optional cd)

- [기타]
    - 블로그 리뷰 반영(플랫폼 변경, minorGC 처리 속도에 대한 고민 추가하기, GC 내용 분리, '정산' 단어 프로젝트 네임에 들어가도록)
    - 코테풀때 시간복잡도 공간복잡도도 함께 생각해보기

## 공부할내용

- java 21
- 깃 브랜치 전략
- git rebase vs merge
- git reset vs revert
- 인증/인가
- oauth 2.0, SSO
- System.gc()와 System.exit()를 사용하면 안되는 이유
- System.out.println()과 System.out.print()를 사용하면 안되는 이유
- 로깅하는 법
- SL4J 를 사용하는 이유와 작동원리
- logback vs log4j
- 제네릭복습
- ArrayList vs LinkedList 어떤상황에서 어떤게 유리한가(검색, 삭제, 삽입)
- ArrayList 크기?

## 주제

1. 프로젝트 기술스택 선정하고 해당 기술 스택을 선정한 이유
    - 공식문서, 커뮤니티 등 수치에 따른 근거 제시