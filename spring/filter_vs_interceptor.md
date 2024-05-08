# 필터(Filter)와 인터셉터(Interceptor)

- 필터(Filter)와 인터셉터(Interceptor)는 웹 애플리케이션에서 요청과 응답에 대한 처리를 담당하는 기능이지만, 각각의 역할, 동작 방식, 그리고 사용하는 시점에서 차이가 있습니다.

## 필터(Filter)

- 서블릿 컨테이너에서 요청과 응답을 처리하기 전후에 특정 작업을 수행하기 위한 객체입니다.
- 'HTTP 요청과 응답을 변경할 수 있는 재사용 가능한 클래스'이다. 객체의 형태로 존재하며 클라이언트에서 오는 요청(request)과 최종 자원 사이에 위치하여 클라이언트의 요청 정보를 알맞게 변경할 수 있다.
  한 개의 필터만 존재할 수 있는 것은 아니며, 여러 개의 필터가 모여 하나의 필터 체인을 형성할 수 있다.
- filter는 스프링에서 관리되지 않고 톰캣과 같은 웹 컨테이너에 의해 관리가 된다. 필터를 추가하기 위해서는 Filter 인터페이스를 구현해주어야 한다.

- 역할
    - 클라이언트로부터 들어오는 요청(Request)과 서버로부터 나가는 응답(Response)에 대한 처리를 담당합니다.
      주로 요청/응답 데이터의 변형, 인코딩, 보안 관련 작업 등을 수행합니다.
    - public void init(FilterConfig filterConfig) : 필터를 초기화 할 때 호출된다.
    - public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) : 필터 기능을 수행한다. chain을 통해
      다음 필터로 처리한 결과를 전달할 수 있다.
    - public void destroy() : 필터를 웹 컨테이너에서 삭제할 때 호출된다.

- 장점
    - Servlet API에 근간을 두고 있어서 특정한 프레임워크에 종속되지 않습니다.
      모든 URL 패턴에 대해 동작하므로, 특정 URL 패턴에 대한 처리가 필요할 때 유용합니다.
- 단점
    - Spring 컨텍스트에 직접 접근할 수 없으며, 서블릿 컨텍스트만 접근 가능합니다.
      필터 체인의 순서가 고정되어 있어서 순서를 조정하기 어렵습니다.

## 인터셉터(Interceptor)

- 스프링 MVC 프레임워크에서 컨트롤러의 호출 전후에 특정 작업을 수행하기 위한 객체입니다.
- Spring에서 제공하는 기술로, Dispatcher Servlet에서 controller를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공한다. Spring context 내부에
  존재하므로 Spring의 모든 bean 객체에 접근이 가능하다.

- 역할
    - 핸들러(Controller)의 호출 전후에 로직을 수행하여, 요청과 응답을 가로채고 처리할 수 있습니다.
      주로 권한 검사, 로깅, 캐시 처리 등을 수행합니다.
- 장점
    - 스프링 컨텍스트에 직접 접근하여 스프링 빈을 주입받을 수 있습니다.
      특정 핸들러(Controller)에만 적용할 수 있어서 필터보다 세밀한 제어가 가능합니다.
- 단점
    - Spring MVC에 종속되어 있어서 스프링 MVC 내에서만 동작합니다.
      컨트롤러 이전에만 동작하기 때문에 컨트롤러 이후의 처리를 변경할 수 없습니다.
      1~2번 과정에서 Dispatcher Servlet은 Handler Mapping을 통해 요청에 대해 적절한 Controller를 찾는데 그 결과로 실행 체인(HandlerExecutionChain)을
      얻는다. 이 실행 체인은 1개 이상의 Interceptor가 등록되어 있을 경우 순차적으로 Interceptor들을 거쳐 controller가 실행되도록 하고, Interceptor가 없을 경우 바로
      Controller를 실행한다.

![img_1.png](img_1.png)

- 1~2번 과정에서 Dispatcher Servlet은 Handler Mapping을 통해 요청에 대해 적절한 Controller를 찾는데 그 결과로 실행 체인(HandlerExecutionChain)을 얻는다.
  이 실행 체인은 1개 이상의 Interceptor가 등록되어 있을 경우 순차적으로 Interceptor들을 거쳐 controller가 실행되도록 하고, Interceptor가 없을 경우 바로 Controller를
  실행한다.

- 3번 과정에서 Dispatcher Servlet은 실행할 Controller 정보를 Handler Adapter에게 전달한다. Handler Adapter는 전달받은 Controller를 실행하는데, 실행하기
  이전에 Interceptor들을 먼저 실행한다. Handler Adapter가 Controller 메소드 혹은 interceptor를 실행하는 도중 HttpSession을 이용해야 한다면, Servlet
  Container가 Session Storage를 확인하여 session을 새로 발급하거나 기존의 session을 매핑시켜 준다.

- 4~5번 과정에서는 controller를 실행하기 전 처리해야 할 전처리 작업이나 요청 정보를 가공하거나 추가하는 작업을 진행한다.

- 14~15번 과정에서는 controller를 실행한 후 처리해야 할 후처리 작업이 이루어진다.
  Interceptor를 추가하기 위해서는 org.springframework.web.servlet의 HandlerInterceptor 인터페이스를 구현해야 한다.


  - preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    - preHandler 역할을 한다. 3번째 파라미터인
      handler는 Handler Mapping이 찾아준 Controller bean에 매핑되는 Handler Method라는 새로운 타입의 객체로, @RequestMapping이 붙은 메소드의 정보를 추상화한
      객체이다. return 값이 false일 경우 controller로 흐름이 넘어가지 않고 중단된다.

  - postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView
    modelAndView) 
    - postHandler 역할을 한다. 4번째 파라미터인 modelAndView 타입의 정보는 controller로부터 반환된 값이다. 하지만 최근에는 Json 형태로 데이터를 제공하는
      RestAPI 기반의 controller를 만들어서 자주 사용되지 않는다.

  - afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex)
    - 모든 작업이 완료된 후에 실행된다. 요청 처리 중 사용한 리소스를 반환할 때 사용하기에 적합하다.

## 차이점

1. 적용 시점

- 필터(Filter): 서블릿 컨테이너의 요청 전후에 동작합니다.
- 인터셉터(Interceptor): 스프링 MVC의 컨트롤러 호출 전후에 동작합니다.

2. 적용 대상

- 필터(Filter): 모든 요청에 대해 적용됩니다.
- 인터셉터(Interceptor): 특정 컨트롤러나 핸들러에만 적용됩니다.

3. 스프링 관련 기능 사용 여부:

- 필터(Filter): 스프링 빈을 주입받을 수 없으며, 스프링 컨텍스트에 직접 접근할 수 없습니다.
- 인터셉터(Interceptor): 스프링 빈을 주입받을 수 있으며, 스프링 컨텍스트에 직접 접근할 수 있습니다.

Filter는 Spring과 무관한 작업들 처리가 가능하다. 보안 관련 작업이 대표적인 예시인데, 보안검사를 하여 올바른 요청이 아닐 경우 Spring container까지 요청이 전달되지 않게 하여 안정성을 높일 수 있다.
ServletRequest와 ServletResponse 객체를 조작할 수 있다.

Interceptor는 Filter와 달리 ServletRequest와 ServletResponse를 조작할 수는 없다. 대신, 객체가 내부적으로 갖는 값은 조작이 가능하다. 따라서 controller로 넘겨주기 위한 정보를 가공하는 데 사용된다. 예를 들어, JWT 토큰 정보를 파싱해서 controller에게 사용자의 정보를 제공할 수 있도록 가공하는 것이다.

토큰에는 권한 정보만 지니고 있고, 권한으로 무엇을 할 수 있는지에 대한 정보는 DB에 저장되어 있다고 가정한다. 사용자의 권한에 따라 페이지에 대한 접근을 막고자 할 때에는 Filter를 사용해야 하는가? Interceptor를 사용해야 하는가?
답은 Interceptor이다. Filter에서 접근을 막게 되면 DB Connection Pool이 2개가 생겨버려 비효율적인 코드가 된다. Spring에서는 어차피 controller에서 요청을 처리할 때 DB connection이 필요해서 connection pool이 생길 것이다. 그런데 Filter에서 권한에 따른 페이지에 대한 접근을 막기 위해 DB를 조회하게 되어 Connection Pool이 하나 더 생겨버리는 것이다.

IP에 따른 제한을 두려면 Filter를 사용해야 하는가? Interceptor를 사용해야 하는가?
IP 정보는 ServletRequest로부터 제공되므로 IP에 따른 제한을 하는 작업은 Spring 무관하게 처리할 수 있다. 물론, Interceptor로 구현을 해도 된다. 하지만 이 경우 Filter에 걸어 Spring Dispatcher Servlet까지 요청이 도달하는 것을 막는 것이 안정적인 측면에서도, 속도적인 측면에서도 이득이다. 따라서 Filter에 구현해볼 만 한 경우라 할 수 있다.

괜히 Filter와 Interceptor가 각각 존재하는 것이 아니다. 각각의 특징을 잘 알고 어느 때에 Filter를 사용하는지, 어느 때에 Interceptor를 사용해야 하는지, 어떻게 해야 정확하고 효율적인 프로그램이 될 지 항상 고민해야 한다.



> ### 결론
>
> 필터는 요청과 응답에 대한 전반적인 처리를 담당하며, 모든 URL 패턴에 대해 동작합니다.
> 인터셉터는 주로 스프링 MVC의 컨트롤러 호출 전후에 특정 작업을 수행하며, 특정 컨트롤러에만 적용됩니다. 스프링의 빈을 주입받을 수 있어서 스프링 컨텍스트와 관련된 로직을 처리할 수 있습니다.

![img.png](img.png)


[참고](https://velog.io/@guswlsapdlf/Filter-vs-Interceptor)