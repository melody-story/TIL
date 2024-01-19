# java.lang.system

## 각종 정보를 확인하기 위한 System 클래스

- System 클래스는 이름 그대로 `시스템에 대한 정보를 확인`하는 클래스이며, 이 클래스에서 제공되는 메소드를 분류해보면 다음과 같이 다양한 역할을 한다는 것을 알 수 있다.
    - 시스템 속성(Property)값 관리
    - 시스템 환경(Environment)값 조회
    - GC 수행 : 절대로 수행해서는 안 되는 메소드
    - JVM 종료 : 절대로 수행해서는 안 되는 메소드
    - 현재 시간 조회
    - 기타 관리용 메소드들

### `System.out.println()`

- System : 클래스
- out : Printstream 타입을 가진 변수
- println() : Printstream 클래스의 메소드
    - 출력을 위한 부분들은 out과 err로 선언된 `Printstream`과 연관되어 있다.

- 가장 큰 특징 : 생성자가 없다는 것
    - System 클래스의 `3개`의 `static 변수`가 선언 되어 있다. 선언되어 있는 타입과 변수 명은 다음과 같다.

| 선언 및 리턴 타입         | 변수명 | 설명                   |
|--------------------|-----|----------------------|
| static Printstream | err | 에러 및 오류를 출력할 때 사용한다. |
| static Inputstream | in  | 입력값을 처리할 때 사용한다.     |
| static Printstream | out | 출력값을 처리할 때 사용한다.     |

- `Inputstream`, `Printstream` : 모두 `java.io` 패키지에 선언됨.

## System 클래스의 메소드

### 1. 시스템 속성(Property)값 관리

| 리턴 타입             | 메소드 이름 및 매개 변수                        | 설명                                                            |
|-------------------|---------------------------------------|---------------------------------------------------------------|
| static String     | clearProperty(String key)             | key에 지정된 시스템 속성을 제거한다.                                        |
| static Properties | getProperties()                       | 현재 시스템 속성을 Properties 클래스 형태로 제공한다.                           |
| static String     | getProperty(String key)               | key에 지정된 문자열로 된 시스템 속성값(value)을 얻는다.                          | 
| static String     | getProperty(String key, String def)   | key에 지정된 문자열로된 시스템 속성값(value)을 얻고, 만약 없으면, def 에 지정된 값을 리턴한다. |
| static void       | setProperties(Properties props)       | Properties 타입으로 넘겨주는 매개변수에 있는 값들을 시스템 속성에 넣는다.                |
| static String     | setProperty(String key, String value) | key에 지정된 시스템 속성의 값을 value로 대체한다.                              |

#### `Properties` 클래스

- java.util 패키지에 속한다.
- Hashtable의 상속을 받는다.
- properies 클래스의 객체는 key-value로 묶여 있는 여러 개의 데이터를 제공
- 원하는 값을 찾으려면 key 값만 알고 있으면 된다.
- 필요 여부와 상관 없이 자바 프로그램을 실행하면 Properties 객체가 생성되며, 그 값은 언제, 어디서 든지 같은 JVM 내에서는 꺼내서 사용할 수 있다.

```java
public void systemPropertiesCheck(){
        System.out.printIn("java.version = "+System.getProperty("java.version"));
}

// 출력
// java.version = 1.8.0_211 
// JDK 8의 update211이라는 버전을 사용하고 있다는 말
```

> Hashtable이란?
> - key와 value의 쌍으로 이루어진 여러 개의 값을 갖는 Map 형태의 자료구조
> - Map 형태의 자료구조는 순서는 거의 없고, key-value 쌍으로 되어 있다.
> - 이 객체에서 원하는 값을 찾으려고 할 때에는 0, 5, 7번째와 같은 위치로 찾는 것이 아니라,
> - key로 찾는다.

### 2. 시스템 환경(Environment) 값 조회

| 리턴 타입                      | 메소드 이름 및 매개 변수      | 설명                              |
|----------------------------|---------------------|---------------------------------|
| static Map <String,String> | getenv()            | 현재 시스템 환경에 대한 Map 형태의 리턴값을 받는다. |
| static String              | getenv(String name) | 지정한 name에 해당하는 값을 받는다.          |

- Properties라는 것은 추가할 수도 있고, 변경도 할 수 있다. 하지만, 환경값 env라 는 것은 변경하지 못하고 읽기만 할 수 있다.
- OS나 장비와 관련된 것들

```java
System.out.println("JAVA_HOME = "+System.getenv("JAVA _HOME"));

// 출력
// JAVA_HOME = C: \jdk1.8.92
```

- `JAVA_HOME`
    - 자바를 사용할 때 JAVA_HOME 이라는 값은 `JDK가 설치되어 있는 경로`를 말한다.
    - java.exe나 java라는 명령어가 있는 위치가 아닌 `자바의 가장 상위 디렉터리`를 의미
    - JAVA_HOME이 설정되어 있지 않다면, `null`로 나올 수도 있다.

### 3. GC 수행

| 리턴 타입       | 메소드 이름 및 매개 변수    | 설명                                           |
|-------------|-------------------|----------------------------------------------|
| static void | gc()              | 가비지 컬렉터를 실행한다.                               |
| static void | runFinalization() | GC 처리를 기다리는 모든 객체에 대하여 finalize() 메소드를 실행한다. |

- <span style="color:red">절대 호출되면 안 되는 것 중 하나</span>
- 자바는 메모리 처리를 개발자가 별도로 하지 않는다.
- 따라서, `System.gc()`라는 메소드를 호출 하면 가비지 컬렉션을 명시적으로 처리하도록 할 수 있다.
- 그리고, `Object 클래스`에 선언되어 있는 `finalize()` 메소드를 명시적으로 수행하도록 하는 `runFinalization()` 메소드가 있다.
- 이 두 개의 메소드들을 여러분들이 호출하지 않아도 알아서 JVM이 더 이상 필요 없는 객체를 처리하는 GC 작업과 finalization 작업을 실행한다.
- 만약 여러분들이 명시적으로 이 메소드들 을 호출하는 코드를 집어 넣으면, 시스템은 하려던 일들을 멈추고 이 작업을 실행한다.
- GC는 JVM이 알아서 필요할 때한다. JVM한테 GC를 하라고 강요하지 말 것.

### 4. JVM 종료

- <span style="color:red">절대 호출되면 안 되는 것 중 하나</span>
- 해당 애플리케이션의 JVM이 죽어버린다.
- status가 0일 경우에만 정상적인 종료를 의미하고, 그 외의 숫자는 비정상적인 종료를 의미 한다.

| 리턴 타입       | 메소드 이름 및 매개 변수   | 설명                |
|-------------|------------------|-------------------|
| static void | exit(int status) | 현재 수행중인 JVM을 멈춘다. |

### 5. 현재 시간 조회

- currentTimeMillis() : 현재 시간을 확인하기 위한 메소드
- nanoTime() : 시간의 차이를 측정하기위한 메소드

| 리턴 타입       | 메소드 이름 및 매개 변수      | 설명                                                        |
|-------------|---------------------|-----------------------------------------------------------|
| static long | currentTimeMillis() | 현재 시간을 밀리초 단위로 리턴한다. <br/> 밀리초 = 1/1000 초, 1,000ms ==  1초 |
| static long | nanoTime()          | 현재 시간을 나노초 단위로 리턴한다. <br/> 나노초 = 1/1,000,000,000초         |

```java
public void numberMinMaxElapsedCheck(){
        JavaLangNumber numberSample=new JavaLangNumber();
        long startTime=System.currentTimeMillis();
        long startNanoTime=System.nanoTime();
        numberSample.numberMinMaxCheck(); //  해당 메소드를 수행하는데 결리는 시간 측정
        System.out.printIn("Milli second="+(System.currentTimeMillis()-startTime));
        System.out.printIn("Nano second="+(System.nanoTime()-startNanoTime));
}
```

- 출력 결과
    - ```shell
    Milli second=0
    Nano second=513650 =>  1/1,000,000으로 나누면 0.5ms다.
    ```

> 그밖에도 많은 관리형 메소드들이 있으나, 위 5가지가 가장 많이 사용된다.

---

## `System.out`을 살펴보자

- out(System.out)과 err(System.err) 변수는 `PrintStream`이라는 동일한 클래스의 객체다.
- 단지, `정상적인 출력`인지, `에러가 났을 때의 출력` 결과인지의 차이만 존재
- `Printstream 클래스`는 `static`하게 사용하므로, 지금은 생성자를 살펴볼 필요가 없다.
- 대신 이 클래스에 선언되어 있는 `출력을 위한 메소드`들에는 어떤 것들이 있는지 알아보자.

### `PrintStream 클래스`의 출력을 위한 주요 메소드

- ⭐️print()
    - 기본 자료형과 참조 자료형 모두 매개 변수로 사용 가능
    - 매개 변수에 있는 내용들을 출력하고 줄 바꿈을 하지 않음
- ⭐️println()
    - 기본 자료형과 참조 자료형 모두 매개 변수로 사용 가능
    - 매개 변수에 있는 내용들을 출력하고 줄바꿈 처리를 함
    - 매개 변수가 없는 메소드가 존재
        - 줄바꿈만 처리하고 싶을 때 : `println("");` 대신 `println();`을 사용하여 불필요한 String 객체가 생성된는 것을 방지.

> print() println() 모두 byte 타입이나 short 타입을 매개 변수로 받는 메소드가 선언되어 있지 않다.<br/>
> 하지만, 두 개 모두 `정수형`이기 때문에 전혀 문제 없이 출력된다. </br>
> `byte`나 `short` 타입을 `print()`나 `printin()` 메소드에 넘겨주면 
> `int 타입을 매개 변수로 받는 메소드에서 알아서 처리`해주기 때문이다.
> 
>```java
>package d.lang;
>
>public class JavaLangSystemPrint {
>   public static void main(String args[]) {
>       JavaLangSystemPrint sample = new JavaLangSystemPrint();
>       sample. printStreamCheck();
>   }
>   public void printStreamCheck() {
>      byte b=127; 
>      short s=32767;
>      System.out.println(b);
>      System.out.println(s);
>      printInt(b);
>      printInt(s);
>   }
>   public void printInt(int value) {
>      System.out.println(value);
>   }
>}
>```
>- 출력
>
>```shell
>127
>32767
>127
>32767
>```

#### Object가 null일 경우

```java
public void printNull(){
    Object obj=null;
    System.out.println(obj);
    System.out.println(obj + " is object's value");
}
```
- print()메소드와 printin()메소드에서는 단순히 Object 클래스의 toString() 메소드를 사용하여 결과를 출력하지 않는다.
  - String 클래스의 valueOf()라는 static 메소드를 호출하여 결과를 받은 후 출력한다. 즉,`String.valueOf(object)`가 호출된 것이다. 그러므로,아무런 이상 없이 결과가 출력된 것이다.
    - `java.lang.System`
      - ```java
        public void print(Object obj) {
          write(String.valueOf(obj));
        }
        
        public void println(Object x) {
            String s = String.valueOf(x);
            synchronized (this) {
                print(s);
                newLine();
            }
        }
        ```

- `object.toString()`
  - `java.lang.Object`
    - ```java
      public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
      }
      ```

- `String.valueOf(object)`
  - `java.lang.String`
    - ```java
      public static String valueOf(Object obj) {
            return (obj == null) ? "null" : obj.toString();
      }
      ```

- null과 문자열을 더했는데 예외가 발생하지 않는 이유
  - 컴파일러에서 이 더하기 문장을 `StringBuilder`로 변환하기 때문이다.
  - ```java
        /// obj + " is object's value" 이 연산은 아래와 동일하다.
        new StringBuilder().append(obj).append(" is object's value");
    ```
    

>️⭐️객체를 출력할 때에는 toString()을 사용하는 것보다 `valueOf()` 메소드를 사용하는 것이 훨씬 안전하다!!

- format()과 printf() : 메소드 이름만 다르고 처리하는 것은 동일.
  - ```java
    System.out.printf("문자: %c 정수 : %d  실수 : %f",'A',123,5.222);
    ```
  - 출력
    - ```shell
      문자: A 정수 : 123 실수 5.222
      ```
- write() : byte를 출력하거나, 아스키 코드 등을 출력할때 쓰임.
  - ```java
    System.out.write(65); // A의 아스키 코드 값 65를 입력. 자바 버퍼안에 데이터로 저장되어있는 상태
    System.out.flush(); // flush를 해주어야 콘솔에 출력됨.
    ```


















