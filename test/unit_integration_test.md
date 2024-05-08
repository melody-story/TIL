- 통합 테스트와 단위 테스트는 소프트웨어 테스트의 두 가지 주요 유형으로, 각각 다른 단계에서 다른 측면을 검증합니다.

## 단위 테스트(Unit Test)

단위 테스트는 코드의 각각의 구성 요소, 주로 클래스 또는 메소드를 개별적으로 테스트합니다.
테스트 대상이 되는 각 구성 요소는 가능한 한 독립적으로 테스트되어야 합니다. 따라서 외부 의존성은 목 객체(Mock Object)로 대체하여 테스트를 수행합니다.
단위 테스트는 주로 코드의 기능이 예상대로 작동하는지를 검증하고, 각각의 기능에 대한 세부적인 동작을 확인합니다.
단위 테스트는 보통 개발자가 코드를 작성하는 동시에 작성되며, 빠르게 실행되어야 하므로 경량화되고 빠른 테스트가 필요합니다.

1. **작은 범위**: 단위 테스트는 개별적인 메서드, 함수 또는 클래스와 같이 가능한 한 작은 범위의 코드를 대상으로 합니다. 예를 들어, 단일 메서드의 입력과 출력을 검증하는 것이 일반적입니다.

   ```java
   public class Calculator {
       public int add(int a, int b) {
           return a + b;
       }
   }

   import org.junit.jupiter.api.Test;
   import static org.junit.jupiter.api.Assertions.assertEquals;

   public class CalculatorTest {
       @Test
       public void testAdd() {
           Calculator calculator = new Calculator();
           assertEquals(5, calculator.add(2, 3));
       }
   }
   ```
2. **독립성**: 단위 테스트는 다른 코드와 완전히 독립적으로 실행되어야 합니다. 즉, 외부 의존성을 최소화하고, 단위 테스트의 결과가 외부 요인에 의해 영향을 받지 않도록 해야 합니다. 이를 위해 목 객체(Mock Object)나 스텁(Stub)과 같은 가짜 객체를 사용하여 외부 의존성을 대체하는 것이 일반적입니다.

```java
public class Service {
    public int calculate(int a, int b) {
        ExternalDependency externalDependency = new ExternalDependency();
        int result = externalDependency.process(a, b);
        return result * 2;
    }
}

public class ExternalDependency {
    public int process(int a, int b) {
        // 외부 의존성에 대한 작업
        return a + b;
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {
    @Test
    public void testCalculate() {
        ExternalDependency mockDependency = mock(ExternalDependency.class);
        when(mockDependency.process(2, 3)).thenReturn(5);

        Service service = new Service();
        int result = service.calculate(2, 3);
        assertEquals(10, result);
    }
}

```

3. **자동화 가능**: 단위 테스트는 자동화되어야 하며, 빠르게 실행되어야 합니다. 개발자가 코드를 변경할 때마다 쉽게 실행하여 결과를 확인할 수 있어야 합니다.

```java
public class StringUtils {
    public static boolean isPalindrome(String str) {
        // 문자열이 회문인지 확인하는 코드
        return true;
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {
    @Test
    public void testIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("radar"));
    }
}

```

4. **커버리지**: 단위 테스트는 코드 커버리지를 향상시키는 데 기여합니다. 즉, 테스트 스위트가 모든 코드 경로를 실행하고 검증하여 코드의 안정성을 높입니다.

```java
public class MathUtils {
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        return dividend / divisor;
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathUtilsTest {
    @Test
    public void testDivide() {
        assertEquals(2, MathUtils.divide(10, 5));
    }
  
    @Test
    public void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.divide(10, 0));
    }
}

```

5. **예외 처리**: 예외 상황에 대한 처리를 포함하여 모든 코드 경로를 테스트해야 합니다. 올바른 입력 및 잘못된 입력에 대한 처리가 예상대로 동작하는지를 확인해야 합니다.

```java
public class ArrayUtils {
    public static int getAtIndex(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayUtilsTest {
    @Test
    public void testGetAtIndex() {
        int[] array = {1, 2, 3, 4, 5};
        assertEquals(3, ArrayUtils.getAtIndex(array, 2));
    }
  
    @Test
    public void testInvalidIndex() {
        int[] array = {1, 2, 3, 4, 5};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> ArrayUtils.getAtIndex(array, 10));
    }
}

```

7. **읽기 쉬움**: 단위 테스트는 다른 개발자가 코드를 쉽게 이해하고 수정할 수 있도록 명확하고 읽기 쉬운 방식으로 작성되어야 합니다. 테스트 케이스의 이름은 테스트가 하는 일을 명확하게 설명해야 합니다.

```java
public class StringUtils {
    public static boolean isPalindrome(String str) {
        // 문자열이 회문인지 확인하는 코드
        return true;
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {
    @Test
    public void testIsPalindrome() {
        // 'radar'는 회문이므로 예상대로 동작하는지 확인
        assertTrue(StringUtils.isPalindrome("radar"));
    }
}

```

## 통합 테스트(Integration Test)

통합 테스트는 여러 개의 구성 요소가 함께 작동하는지를 검증합니다. 이는 단위 테스트에서 검증된 각각의 구성 요소가 상호작용하는 방식을 확인하는 것을 의미합니다.
통합 테스트는 종종 실제 데이터베이스나 외부 서비스와의 상호작용을 포함합니다. 따라서 통합 테스트는 시스템의 실제 환경과 더 밀접하게 연관되어 있습니다.
통합 테스트는 보통 단위 테스트 이후에 수행되며, 시스템이 완성된 후에 전체 시스템의 동작을 검증하는 데 사용됩니다.
통합 테스트는 단위 테스트보다 실행 시간이 오래 걸릴 수 있으며, 시스템 전체를 대상으로 하므로 테스트가 복잡할 수 있습니다.

1. 여러 컴포넌트 간의 상호 작용 검증

```java
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(new User(1L, "John Doe"));

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("John Doe"));
    }
}

```

2. 외부 서비스와의 통합 검증

```java
@SpringBootTest
public class ExternalServiceIntegrationTest {

    @Autowired
    private ExternalServiceClient externalServiceClient;

    @Test
    public void testExternalServiceCall() {
        String response = externalServiceClient.callExternalService();

        assertNotNull(response);
        assertEquals("Expected response", response);
    }
}

```

3. 전체 시스템의 통합 검증

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullSystemIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFullSystemIntegration() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/users", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}

```

4. 환경 설정 및 배포 관리 검증

```java
@SpringBootTest
public class EnvironmentConfigurationIntegrationTest {

    @Autowired
    private Environment environment;

    @Test
    public void testEnvironmentConfiguration() {
        assertEquals("Expected value", environment.getProperty("key"));
    }
}

```

5. 비즈니스 로직의 검증

```java
@SpringBootTest
public class BusinessLogicIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testBusinessLogic() {
        User user = userRepository.save(new User("John Doe"));
  
        assertNotNull(user.getId());
        assertEquals("John Doe", user.getName());
    }
}

```

> 간단히 말해, 단위 테스트는 개별 구성 요소의 동작을 검증하고,
>
> 통합 테스트는 이러한 구성 요소들이 함께 작동하는 방식을 검증합니다.
>
> 두 가지 유형의 테스트는 함께 사용되어 소프트웨어의 신뢰성과 품질을 보장하는 데 도움이 됩니다.
