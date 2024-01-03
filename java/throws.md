# throws

- 예외를 발생시킴

## throw

- try 블록 내에서 throw 라고 명시한 후 개발자가 예외 클래스의 객체를 생성.
- 다른 예외가 발생한 상황과 동일하게 throw한 문장 이후에 있는 모든 try 블록 내의 문장들은 수행이 되지 않고, catch 블록으로 이동.
- catch 블록 중에 throw한 예외와 동일하거나 상속 관계에 있는 예외가 있다면 그 블록에서 에 의를 처리할 수 있다.
- 만약 해당하는 예외가 없다면 예외가 발생된 메소드를 호출한 메소드로 던진다.
- 이럴 때 사용하는 것이 throws 구문이다.

```java
package c.exception;

public class ThrowSample {
    public static void main(String args[]) {
        ThrowSample sample = new ThrowSample();
        sample.throwException(13);
    }

    public void throwException(int number) {
        try {
            if (number > 12) {
                throw new Exception("Number is over than 12");
            }
            System.out.printIn("Number is " + number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## throws

- throws 구문은 다음과 같이 `메소드 선언`할 때 사용
- 예외가 발생했을 때 try-catch로 묶어주지 않아도 그 메소드를 호출한 메소드로 예외처리를 위임하는 것이기 때문에 전혀 문제가 되지 않는다.
- try-catch 블록으로 묶지 않아도 에외를 throw한다고 할지라도, throws가 선언되어 있기 때문에 전혀 문제없이 컴파일 및 실행이 가능.
- 주의
    - throws 로 메소드를 선언할시 `throwsException()` 메소드를 호출한 메소드에서는 반드시 try~catch 블록으로 `throwsException()`메소드를 감싸주어야함.

```java
public void throwsException(int number) throws Exception {
    if (number > 12) {
        throw new Exception("Number is over than 12");
    }
    System.out.println("Number is " + number);
}
```

따라서 아래 코드를 실행하면 `error: unreported exception Exception; must be caught or declared to be thrown`컴파일 오류가 난다.

```java
public static void main(String args[]) {
    ThrowSample sample = new ThrowSample();
    sample.throwException(13);
    sample.throwsException(13);
}
```

### 해결

1. try-catch로 묶기

- 가장 좋은 방법

```java

public static void main(String args[]) {
    Throwsample sample = new Throwsample();
    sample.throwException(13);
    try {
        sample.throwsException(13);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
```

- 출력결과

```shell
Exception in thread "main" java.lang.RuntimeException: java.lang.Exception: Number is over than 12
	at ThrowSample.main(ThrowSample.java:10)
Caused by: java.lang.Exception: Number is over than 12
	at ThrowSample.throwsException(ThrowSample.java:27)
	at ThrowSample.main(ThrowSample.java:8)
```

2. 호출한 메소드(여기서는 main() 메소드)에서도 다시 throws 선언하기

- 컴파일하고 실행하는데 전혀 문제없으나, 이미 throw한것을 다시 throws하는 것은 좋은 습관이 아니다.

```java
public static void main(String args[]) throws Exception {
    ThrowSample sample = new ThrowSample();
    sample.throwException(13);
    sample.throwsException(13);
}
```

- 출력결과

```shell
Exception in thread "main" java. lang. Exception: Number is over than 12 
  at c.exception. ThrowSample. throwsException(ThrowSample. java:24) 
  at c.exception. ThrowSample. main(ThrowSample. java: 10)
```

## 정리
- 메소드를 선언할 때 매개 변수 소괄호 뒤에 throws라는 예약어를 적어 준 뒤 예외를 선언하면,
해당 메소드에서 선언한 예외가 발생했을 때 호출한 메소드로 예외가 전달된다.
만약 메소드에서 두 가지 이상의 예외를 던질 수 있다면, implements 처럼 콤마로 구분하여 예외 클래스 이름을 적어주면 된다.
```java
public void multiThrows() throws NullPointerException, Exception {
}
```

- try 볼록 내에서 예외를 발생시길 경우에는 throw라는 예약어를 적어 준 뒤 예외 객체를 생성하거나, 생성되어있는 객체를 명시해준다.
throw한 예외 클래스가 catch 블록에 선언되어 있지 않거나, throws 선언에 포함되어 있지 않으면 컴파일 에러가 발생한다.
- catch 블록에서 예외를 throw할 경우에도 메소드 선언의 throws 구문에 해당 예외가 정의되어 있어야만 한다.
