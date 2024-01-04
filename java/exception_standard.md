# 예외 처리 전략 및 표준

- 예외에는 크게 3종류가 있다.
- Error, checked Exception, unchecked Exception(Runtime Exception)
- 예외가 항상 발생하지 않고, 실행시에 발생할 확률이 높은 경우, `런타임 예외`로 만드는 것이 나을 수 있다.
- 즉, 클래스 선언시 Exception이 아닌, RuntimeException을 상속받아 선언. 

<br>

### 주의
- 항상 예외를 던지는 메소드를 사용하더라도 컴파일할때는 문제가 발생하지 않으나, 예외가 발생할 확률은 높다.
- 따라서 예외가 발생할 경우 해당 클래스를 호출하는 다른 클래스에서 예외를 처리하도록 구조적인 안전 장치가 되어 있어야만 한다.
- 여기서 안전 장치라고 하는 것은 `try-catch로 묶지 않은 메소드를 호출하는 메소드`에서 예외를 처리하는 try-catch가 되어 있는 것을 말한다.

```java
public void methodCaller() {
    try {
        methodCallee();
    } catch (Exception e) {
        // 예외처리
    }
}

public void methodCallee() {
 // RuntimeException 예외 발생 가능성 있는 부분
}
```

<br>

### catch 문장에서 피해야하는 것.
```java
try {
    // 예외 발생 가능한 코드    
} catch(SomeException e) {
    // 여기 아무 코드 없음
}
```

- 위에서 `SomeException`이라는 예외를 잡도록 처리하였다.
- `catch` 문장내에 아무런 처리를 하지 않는것을 볼 수있다. 아무런 작업이 일어나지 않기 때문에 문제가 어디서 발생했는지 전혀 찾을 수 없다.
- 따라서 개발 표준을 잡을 때에는 `catch`문 내에서 어떻게 처리할지를 명시적으로 선언해 두어야만 한다.
- 다시 말해서 `catch`문에서 로그를 남기는 등의 작업을 하고, 예외를 `throw`를 이용하여 던져주어야 문제가 발생한 정확한 원인을 찾을 수 있다.

<br>

## 정리

#### 임의의 예외클래스 만들때
1. 반드시 try~catch로 묶어줄 필요가 있을 경우에만 Exception클래스를 확장한다.
2. 일반적으로 실행시 예외를 처리할 수 있는 경우에는 RuntimeException 클래스를 확장하는 것을 권장한다.

#### catch문 내에 아무런 작업없이 공백을 놔둔경우
- 예외 분석이 어려워지므로 꼭 로그 처리와 같은 예외 처리를 해줘야만 한다.

<br>

#### 더 많은 전략 검색 키워드 
 **# Java Exception Strategy**