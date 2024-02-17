# Spring Data JPA

- Spring Framework와의 통합
  - Spring Data JPA는 Spring Framework와 밀접하게 통합되어 있습니다. 이는 Spring 애플리케이션 내에서 JPA를 쉽게 사용할 수 있도록
    도와줍니다. 반면에 순수한 JPA는 프레임워크와 직접적인 관련이 없습니다.
- Repository 인터페이스
  - Spring Data JPA는 JpaRepository와 같은 Repository 인터페이스를 제공하여 개발자가 데이터 액세스 계층을 쉽게 작성할 수 있도록 도와줍니다. 이러한
    Repository 인터페이스는 CRUD 작업을 수행하는 메서드들을 자동으로 생성해줍니다. 반면에 순수한 JPA에서는 Repository 인터페이스를 제공하지 않으며, 개발자가 직접 Repository를 구현해야
    합니다.
- 쿼리 메서드
  - Spring Data JPA는 메서드 이름을 기반으로 쿼리를 자동으로 생성할 수 있는 쿼리 메서드 기능을 제공합니다. 이는 개발자가 별도의 JPQL(QueryDSL, Criteria API 등)을
    작성하지 않고도 간단한 쿼리를 만들 수 있도록 도와줍니다. 순수한 JPA에서는 이러한 기능을 제공하지 않습니다.
- 페이징 및 정렬
  - Spring Data JPA는 페이징 및 정렬과 관련된 기능을 제공합니다. Pageable 인터페이스를 통해 페이징 및 정렬 조건을 지정할 수 있으며, 이를 사용하여 쉽게 페이징된 결과를 가져올
    수 있습니다. 순수한 JPA에서는 이러한 기능을 직접 구현해야 합니다.
- 동적 쿼리 생성
  - Spring Data JPA는 QueryDSL을 지원하여 동적으로 쿼리를 생성하고 실행할 수 있습니다. 이는 복잡한 동적 쿼리를 쉽게 작성할 수 있도록 도와줍니다. 반면에 순수한 JPA에서는
    동적 쿼리를 작성하려면 Criteria API나 JPQL을 사용해야 합니다.
>이러한 차이점들은 Spring Data JPA가 개발자에게 데이터 액세스를 더 쉽고 편리하게 만들어주는데 기여하고 있습니다.

[JPA, Hibernate, Spring Data JPA 참고1](https://jhyonhyon.tistory.com/35)<br>
[JPA, Hibernate, Spring Data JPA 참고2](https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/)
