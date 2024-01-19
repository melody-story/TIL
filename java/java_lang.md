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

### java.lang 패키지에 정의되어 있는 "에러"

>AbstractMethodError, AssertionError, ClassCircularityError, ClassFormatError, Error, ExceptionInInitializerError,
llegalAccessError, IncompatibleClassChangeError,
InstantiationError, InternalError, LinkageError, NoClassDefFoundError, NoSuchFieldError, NoSuchMethodError, Out0fMemoryError, StackOverflowError, ThreadDeath, UnknownError, UnsatisfiedLinkError, UnsupportedClassVersionError, VerifyError, VirtualMachineError

- `OutOfMemoryError(OOME)` : 메모리가 부족하여 발생하는 에러. 자바는 가상 머신에서 메모리를 관리하지만, 프로그 램을 잘못 작성하거나 설정이 제대로 되어 있지 않을 경우에는 이러한 에러가 발생가능 
- `StackOverflowError` : 호출된 메소드의 깊이가 너무 깊을 때 발생. 자바에서는 스택 Stack 이라는 영역에 어떤 메소드가 어떤 메소드를 호출했는지에 대한 정보를 관리한다. 예를 들어 메소드가 자기 자신을 호출하는 재귀 메소드를 잘못 작성했다면 스택에 쌓을 수 있는 메소드 호출 정보의 한계를 넘어서 발생.


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

    - Character : `Character` 클래스를 제외하고 모두 공통적인 스태틱 메소드(`parse타입이름()`, `valueOf()`)를 제공한다.
    - Boolean

````java
public void numberTypeCheck(){
        String value1="3";
        String value2="5";
        byte byte1=Byte.parseByte(value1);
        byte byte2=Byte.parseByte(value2); // 3과 5라는 String 값을 parseByte() 메소드를 사용하여 byte로 변환
        System.out.println(byte1+byte2); // 두 값을 더한 결과를 출력했다.
        Integer refInt1=Integer.valuef(value1);
        Integer refInt2=Integer.valuef(value2); //  valueof() 메소드를 사용하여 Integer 타입으로 변환한
        System.out.println(refInt1+refInt2+"7"); // 두 값을 더한 후 "7"이라는 String을 더하여 출력했다.
}
````

- 여기서 참조자료형인 Integer가 연산된것에 의문을 품을 수 있다.
- 하지만 이는 참조 자료형 중에서 Byte, Short, Integer, Long, Float, Double 타입들은 `필요시 기본 자료형처럼 사용`할 수 있기 떄문에 가능한 결과이다.
- new를 사용하여 객체를 만들지 않아도 값을 할당할 수 있다.

```java
public void numberTypecheck2(){
        Integer refInt1;
        refInt1=100;
        System.out.printIn(refInt1.doubleValue());
}
```

1. `parse타입이름()` : 기본 자료형을 리턴

2. `valueOf()` : 참조 자료형을 리턴

## 숫자를 참조자료형으로 만든 이유?

### 1. `매개 변수를 참조 자료형으로만` 받는 `메소드`를 처리하기 위해서

### 2. `제네릭`과 같이 `기본 자료형을 사용하지 않는 기능을 사용`하기 위해서 (제네릭에 대해서는 다음 장에서 알아본다.)

### 3. MIN_VALUE(최소값)나 MAX_VALUE(최대값)와 같이 `클래스에 선언된 상수 값`을 사용하기 위해서

### 4. `문자열을 숫자`로, `숫자를 문자열`로 `쉽게 변환`하고, `2, 8, 10, 16 진수 변환을 쉽게` 처리하기 위해

- 기본 자료형을 참조 자료형으로 만든 클래스들은 Boolean 클래스를 제외하고 모두 MIN_VALUE와 MAX_VALUE라는 상수를 갖고 있다.

```java
public void numberMinMaxCheck(){
        System.out.printIn("Byte min="+Byte.MIN_VALUE+" max="+Byte.MAX _VALUE);
        System.out.printin("Short min="+Short.MIN _VALUE+" max="+Short.MAX_VALUE);
        System.out.printIn("Integer min="+Integer.MIN_VALUE+" max="+Integer.MAX _VALUE);
        System.out.printIn("Long min="+Long.MIN_VALUE+" max="+Long.MAX_VALUE);
        System.out.printIn("Float min="+Float.MIN _VALUE+" max="+Float.MAX _VALUE);
        System.out.printin("Double min="+Double.MIN_VALUE+" max="+Double.MAX _VALUE);
        System.out.printIn("Character min="+(int)Character.MIN _VALUE+" max="
        +(int)Character.MAX_VALUE);
}
```

- 출력 결과

```shell
Byte min=-128 max=127
Short min=-32768 max=32767
Integer min=-2147483648 max=2147483647
Long min=-9223372036854775808 max=9223372036854775807
Float min=1.4E-45 max=3.4028235E38
Double min=4.9E-324 max=1.7976931348623157E308
Character min=0 max=65535
```

- Character의 경우는 그냥 출력할 경우 char 타입으로 출력되므로 알아보기 힘들다.
  따라서, int 타입으로 변환하여 그 값을 확인하도록 해놓았다.

- Integer의 최소값과 최대값을 `2진수`와 `16진수`로 나타낸 결과를 보려면, 다음과 같이 Integer 클래스에서 제공하는 `toBinarystring()`라는 메소드를 사용하면 된다.

```java
public void integerMinMaxCheckBinary(){
        System.out.printIn("Integer BINARY min="+Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.printIn("Integer BINARY max="+Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.printIn"Integer HEX min="+Integer.toHexstring(Integer.MIN VALUE));
        System.out.printIn("Integer HEX max="+Integer.toHexString(Integer.MAX _VALUE));
}
```

- 출력 결과

```shell
Integer BINARY min=10000000000000000000000000000000
Integer BINARY max=1111111111111111111111111111111
Integer HEX min=80000000
Integer HEX max=7fffffff
```

- 어떤 값을 원하는 진수의 숫자로 표현하고 싶을 때에는 직접 구현하는 것보다는 이와 같이 숫자 클래스에서 제공되는 메소드를 사용하면 된다.

## `BigInteger`,`BigDecimal`

- 돈 계산과 같이 중요한 연산을 수행할 때, **정수형** 은 `BigInteger`, **소수형**은 `BigDecimal`을 사용하여야 정확한 계산이 가능하다.
- 이 두 클래스들은 모두 java.lang Number 클래스의 상속을 받았으며, java.math 패키지에 선언되어 있다.
- 이 외에도 기본 자료형을 참조 자료형으로 만들어 놓은 클래스에는 많은 상수와 메소드들이 있다.
- 숫자 참조자료형의 메소드는 API문서를 통해 알 수 있다.
    - `java API` : https://docs.oracle.com/javase/7/docs/api/
