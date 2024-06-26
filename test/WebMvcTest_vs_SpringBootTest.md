# `@SpringBootTest`와 `@WebMvcTest`의 차이

**테스트 대상 범위:**

- `@SpringBootTest`는 스프링 애플리케이션 전체를 로드하여 테스트하는 데 사용됩니다. 즉, 애플리케이션의 모든 구성 요소를 로드하고 테스트할 수 있습니다. 이는 통합 테스트를 수행하는 데 주로
  사용됩니다.
- `@WebMvcTest`는 웹 계층 컴포넌트인 컨트롤러를 테스트하는 데 사용됩니다. 스프링 MVC 컨트롤러와 관련된 빈만 로드하여 테스트하기 때문에 테스트가 더 빠르고 가볍습니다. 이는 단위 테스트나 통합
  테스트에서
  컨트롤러를 테스트할 때 주로 사용됩니다.

**로드되는 빈의 범위:**

- `@SpringBootTest`는 스프링 애플리케이션 전체 컨텍스트를 로드하므로, 실제 애플리케이션과 동일한 방식으로 빈을 로드합니다.
- `@WebMvcTest`는 웹 계층에 관련된 빈만 로드합니다. 따라서 컨트롤러, 컨트롤러 어드바이스, 필터 등과 관련된 빈만 로드됩니다.

**테스트 속도**

- `@SpringBootTest`는 전체 애플리케이션 컨텍스트를 로드하므로 테스트의 시작과 실행에 시간이 더 걸릴 수 있습니다.
- `@WebMvcTest`는 웹 계층에 관련된 빈만 로드하므로 테스트가 더 빠르게 실행될 수 있습니다.

**사용 사례**

- `@SpringBootTest`는 애플리케이션의 전반적인 동작을 테스트하거나 통합 테스트를 수행할 때 사용됩니다.
- `@WebMvcTest`는 단일 또는 여러 개의 컨트롤러를 테스트하고자 할 때 사용됩니다.

## 정리

1. 로드하는 빈의 범위

- `@WebMvcTest`: 웹 계층 컴포넌트인 컨트롤러와 관련된 빈만 로드한다.
- `@SpringBootTest`: 스프링 애플리케이션 전체를 로드한다.

2. 테스트 속도

- `@WebMvcTest`: 웹 계층과 관련된 빈만 로드하므로 테스트가 빠르다.
- `@SpringBootTest`: 전체 애플리케이션 컨텍스트를 로드하므로 테스트 시작과 실행에 더 많은 시간이 걸린다.

3. 사용되는 상황

- `@WebMvcTest`: 단일 또는 여러 개의 컨트롤러를 테스트할 때 사용된다.
- `@SpringBootTest`: 애플리케이션 전체의 동작을 테스트하거나 통합 테스트를 수행할 때 사용된다.
