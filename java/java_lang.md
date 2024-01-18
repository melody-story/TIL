# java.lang 패키지

java. lang 패키지에서 제공하는 `인터페이스`, `클래스`, `예외 클래스` 등은 다음과 같이 분류할 수 있다.

- 언어 관련 기본
- 문자열 관련
- 기본 자료형 및 숫자 관련
- 쓰레드 관련
- 예외 관련
- 런타임 관련

### 언어 관련 기본

| 타입      | 항목들                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 인터페이스   | Cloneable, Comparable, Iterable, Readable                                                                                                                                                                                                                                                                                                                                                                                                                  |
| 클래스     | Class, ClassLoader, Compiler, Enum, Object, Package, SecurityManager, StackTraceElement, System, Void                                                                                                                                                                                                                                                                                                                                                      |
| 예외 및 에러 | ArrayIndexOutOfBoundsException, ArrayStoreException ClassCastException, ClassNotFoundException CloneNotSupportedException, EnumConstantNotPresentException, IllegalAccessException IllegalArgumentException, IndexOutOfBoundsException, InstantiationException, NegativeArraySizeException, NoSuchFieldException, NoSuchMethodException, NullPointerException, RuntimeException, SecurityException, TypeNotPresentException, UnsupportedOperationException |

### 문자열 관련

| 타입      | 항목들                                 |
|---------|-------------------------------------|
| 인터페이스   | Appendable, CharSequence            | 
| 클래스     | String, StringBuffer, StringBuilder |
| 예외 및 에러 | StringIndexOutOfBoundsException     |

### 기본 자료형 및 숫자 관련

| 타입      | 항목들                                                                                                                                |
|---------|------------------------------------------------------------------------------------------------------------------------------------|
| 클래스     | Boolean, Byte, Character, Character.Subset, Character, UnicodeBlock, Double, Float, Integer, Long, Math, Number, Short, StrictMath |
| 예외 및 에러 | ArithmeticException, NumberFormatException                                                                                         |

### 쓰레드 관련

| 타입      | 항목들                                                                              |
|---------|----------------------------------------------------------------------------------|
| 인터페이스   | Runnable, Thread.UncaughtExceptionHandler                                        |
| 클래스     | InheritableThreadLocal, Thread, ThreadGroup, ThreadLocal, Thread.state(Enum 타입임) |
| 예외 및 에러 | IllegalMonitorStateException, IllegalThreadStateException, InterruptedException  |

### 예외 관련

| 타입      | 항목들       |
|---------|-----------|
| 클래스     | Throwable |
| 예외 및 에러 | Exception |

### 런타임 관련

| 타입      | 항목들                                                 |
|---------|-----------------------------------------------------|
| 클래스     | Process, ProcessBuilder, Runtime, RuntimePermission |
| 예외 및 에러 | IllegalStateException                               |

## 숫자를 처리하는 클래스들

- 자바의 힙 Heap이라는 영역에 저장되지 않고, `스택 Stack`이라는 영역에 저장되어 관리
- 계산할 때 보다 빠른 처리가 가능

- 기본 자료형의 `숫자를 객체로 처리해야 할 필요`가 있을 수도 있다.
- 따라서, 자바에는 다음과 같이 `기본 자료형으로 선언되어 있는 타입의 클래스`들이 선언되어 있다.

    - `Wrapper클래스`라고 불리며, `Number`라는 `abstract 클래스`를 확장한다.
    - 참조 자료형이지만 자바 컴파일러에서 자동으로 형변환을 해주기 때문에 기본자료형처럼 사용할 수 있다.
        - Byte
        - Short
        - Integer
        - Long
        - Float
        - Double

    - Character : `Character` 클래스를 제외하고 모두 공통적인 메소드를 제공한다. `parse타입이름()`, `valueOf()`
    - Boolean

