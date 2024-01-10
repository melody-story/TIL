# 싱글톤 패턴(Singleton Pattern)

- **단 하나의 유일한 객체**를 만들기 위한 코드 패턴

## 싱글톤 패턴의 사용하는 이유

1. 메모리 측면 : 최초 한번의 new 연산자를 통해서 고정된 메모리 영역을 사용하기 때문에 추후 해당 객체에 접근할 때 메모리 낭비를 방지
    - 따라서 보통 싱글톤 패턴이 적용된 객체가 필요한 경우는 그 객체가 **리소스를 많이 차지하는 역할을 하는 무거운 클래스일때 적합**하다.
    - 대표적으로 데이터베이스 연결 모듈을 예로 들 수 있다. 데이터베이스에 접속하는 작업(I/O 바운드)은 그 자체로 무거운 작업에 속하며 또한 한번만 객체를 생성하고 돌려쓰면 되지 굳이 여러번 생성할 필요가
      없기 때문이다.
    - 그밖의 예) 디스크 연결, 네트워크 통신, DBCP 커넥션풀, 스레드풀, 캐시, 로그 기록 객체 등
2. 속도 측면 : **이미 생성된 인스턴스를 활용**하니 속도가 빠름
3. 데이터 공유가 쉬움 : 싱글톤 인스턴스가 전역으로 사용되는 인스턴스이기 때문에 다른 클래스의 인스턴스들이 접근하여 사용할 수 있음
    - 여러 클래스의 인스턴스에서 싱글톤 인스턴스의 데이터에 동시에 접근하게 되면 동시성 문제가 발생할 수 있음.
4. 도메인 관점에서 인스턴스가 한 개만 존재하는 것을 보증하고 싶은 경우 싱글톤 패턴을 사용하기도 함.

## 싱글톤 패턴의 문제점

1. 싱글톤 패턴을 구현하는 코드 자체가 많이 필요
    - 정적 팩토리 메서드에서 객체 생성을 확인하고 생성자를 호출하는 경우에 멀티스레딩 환경에서 발생할 수 있는 동시성 문제 해결을 위해 `syncronized` 키워드를 사용
2. 테스트하기 어렵다.
    - 싱글톤 인스턴스는 자원을 공유하고 있기 때문에 테스트가 결정적으로 격리된 환경에서 수행되려면 매번 인스턴스의 상태를 초기화시켜주어야 한다.
3. 의존 관계상 클라이언트가 구체 클래스에 의존하게 된다.
    - new 키워드를 직접 사용하여 클래스 안에서 객체를 생성하고 있으므로, 이는 `SOLID` 원칙 중 `DIP `를 위반하게 되고 `OCP` 원칙 또한 위반할 가능성이 높다.

    * `의존관계 역전 원칙 (DIP, Dependency inversion principle)` : 추상화에 의존해야지, 구체화에 의존하면 안된다.
    * `개방-폐쇄 원칙 (OCP, Open/closed principle)` : 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
4. 자식클래스를 만들수 없다.
5. 내부 상태를 변경하기 어렵다.
6. 따라서 유연성이 많이 떨어짐.

## 싱글톤 패턴의 구현 방법

1. Eager Initialization

- 한번만 미리 만들어두는, 가장 직관적이면서도 심플한 기법
- `static final` 이라 멀티 쓰레드 환경에서도 안전함
- 그러나 `static` 멤버는 당장 객체를 사용하지 않더라도 메모리에 적재하기 때문에 만일 리소스가 큰 객체일 경우, 공간 자원 낭비가 발생함
- 예외 처리를 할 수 없음
- 만일 싱글톤을 적용한 객체가 그리 크지 않은 객체라면 이 기법으로 적용해도 무리는 없다.

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static final Singleton INSTANCE = new Singleton();

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

2. Static block initialization

- `static block`을 이용해 예외를 잡을 수 있음
- 그러나 여전히 static 의 특성으로 사용하지도 않는데도 공간을 차지함
- static block : 클래스가 로딩되고 클래스 변수가 준비된 후 자동으로 실행되는 블록

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static Singleton instance;

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {
    }

    // static 블록을 이용해 예외 처리
    static {
        try {
            instance = new Singleton();
        } catch (Exception e) {
            throw new RuntimeException("싱글톤 객체 생성 오류");
        }
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```

3. Lazy initialization

- 객체 생성에 대한 관리를 내부적으로 처리
- 메서드를 호출했을 때 인스턴스 변수의 `null` 유무에 따라 초기화 하거나 있는 걸 반환하는 기법
- **위의 미사용 고정 메모리 차지의 한계점을 극복**
- 그러나 쓰레드 세이프(Thread Safe) 하지 않는 치명적인 단점을 가지고 있음 (아래 설명)

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static Singleton instance;

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {
    }

    // 외부에서 정적 메서드를 호출하면 그제서야 초기화 진행 (lazy)
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // 오직 1개의 객체만 생성
        }
        return instance;
    }
}
```

4. Thread safe initialization

- synchronized 키워드를 통해 메서드에 쓰레드들을 하나하나씩 접근하게 하도록 설정한다. (동기화)
- 하지만 여러개의 모듈들이 매번 객체를 가져올 때 synchronized 메서드를 매번 호출하여 동기화 처리 작업에 overhead가 발생해 성능 하락이 발생한다.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    // synchronized 메서드
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

5. Double-Checked Locking

- 매번 `synchronized` 동기화를 실행하는 것이 문제라면, 최초 초기화할때만 적용하고 이미 만들어진 인스턴스를 반환할때는 사용하지 않도록 하는 기법
- 이때 인스턴스 필드에 `volatile` 키워드를 붙여주어야 I/O 불일치 문제를 해결 할 수 있다.
- 그러나 `volatile` 키워드를 이용하기위해선 **JVM 1.5이상**이어야 되고, JVM에 대한 심층적인 이해가 필요하여, JVM에 따라서 여전히 쓰레드 세이프 하지 않는 경우가 발생하기 때문에 사용하기를
  지양하는 편이다.
```java
class Singleton {
    private static volatile Singleton instance; // volatile 키워드 적용

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
        	// 메서드에 동기화 거는게 아닌, Singleton 클래스 자체를 동기화 걸어버림
            synchronized (Singleton.class) { 
                if(instance == null) { 
                    instance = new Singleton(); // 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를 최소화
                }
            }
        }
        return instance; // 최초 초기화가 되면 앞으로 생성된 인스턴스만 반환
    }
}
```
#### volatile 키워드
- Java에서는 쓰레드를 여러개 사용할경우, 성능을 위해서 각각의 쓰레드들은 변수를 메인 메모리(RAM)으로부터 가져오는 것이 아니라 캐시(Cache) 메모리에서 가져오게 된다.문제는 비동기로 변수값을 캐시에
  저장하다가, 각 쓰레드마다 할당되어있는 캐시 메모리의 변수값이 일치하지 않을수 있다는 점이다.그래서 volatile 키워드를 통해 이 변수는 캐시에서 읽지 말고 메인 메모리에서 읽어오도록 지정해주는 것이다.

6. Bill Pugh Solution (LazyHolder)
- 권장되는 두가지 방법중 하나
- 멀티쓰레드 환경에서 안전하고 `Lazy Loading`(나중에 객체 생성) 도 가능한 완벽한 싱글톤 기법
- **클래스 안에 내부 클래스(holder)를 두어 JVM의 클래스 로더 매커니즘과 클래스가 로드되는 시점을 이용한 방법 (스레드 세이프함)**
- `static` 메소드에서는 `static` 멤버만을 호출할 수 있기 때문에 내부 클래스를 `static`으로 설정이밖에도 내부 클래스의 치명적인 문제점인 메모리 누수 문제를 해결하기 위하여 내부 클래스를 `static`으로 설정
- **다만 클라이언트가 임의로 싱글톤을 파괴할 수 있다는 단점을 지님 (Reflection API, 직렬화/역직렬화를 통해)**
```java
class Singleton {

    private Singleton() {}

    // static 내부 클래스를 이용
    // Holder로 만들어, 클래스가 메모리에 로드되지 않고 getInstance 메서드가 호출되어야 로드됨
    private static class SingleInstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }
}
```
1) 우선 내부클래스를 `static`으로 선언하였기 때문에, 싱글톤 클래스가 초기화되어도 `SingleInstanceHolder` 내부 클래스는 메모리에 로드되지 않음
2) 어떠한 모듈에서 getInstance() 메서드를 호출할 때, `SingleInstanceHolder` 내부 클래스의 static 멤버를 가져와 리턴하게 되는데, 이때 내부 클래스가 한번만 초기화되면서 싱글톤 객체를 최초로 생성 및 리턴하게 된다.
3) 마지막으로 `final` 로 지정함으로서 다시 값이 할당되지 않도록 방지한다.


#### 참고) 직렬화/역직렬화

- **데이터 직렬화** : 메모리를 디스크에 저장하거나, 네트워크 통신에 사용하기 위한 형식으로 변환하는 것이다.
- **데이터 역직렬화** : 디스크에 저장한 데이터를 읽거나, 네트워크 통신으로 받은 데이터를 메모리에 쓸 수 있도록 변환하는 것이다.

<br>

- **Java 직렬화**
  - 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트 형태로 데이터 변환하는 기술이다.
  - JVM의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술이다.
- **Java 역직렬화**
  - 바이트로 변환된 데이터를 다시 객체로 변환하는 기술이다.
  - 직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 기술이다.

#### 참고) Reflection
- 리플렉션이란 객체를 통해 클래스의 정보를 분석해 내는 프로그램 기법
- reflection 은 자바의 특징이다. 실행중인 자바프로그램 내부를 검사하고 내부의 속성을 수정할 수 있도록 한다. 예를 들어, 어떤 자바클래스가 가진 모든 멤버의 이름을 얻거나 보여줄 수 있다.자바에서 클래스가 그 자신을 조사하고 수정하는 것이  많다고 할수는 없으나 다른 언어에서는 볼수 없는 특징이다.reflection 이 구체적인 쓰임중에 하나가 빌더툴을 이용해서 소프트웨어 콤포넌트를 만드는 곳에서 이다. 툴은 reflection 을 사용해서 동적으로 로딩되는 자바 콤포넌트(클래스)의 속성을 얻을 수 있다.
- `BeanFactory`는 어플리케이션이 실행한 후 객체가 호출 될 당시 객체의 인스턴스를 생성하게되는데, 이때 필요한 기술.

7. Enum 이용
- 권장되는 두가지 방법중 하나
- **enum은 애초에 멤버를 만들때 private로 만들고 한번만 초기화 하기 때문에 thread safe함.**
- enum 내에서 상수 뿐만 아니라, 변수나 메서드를 선언해 사용이 가능하기 때문에, 이를 이용해 싱글톤 클래스 처럼 응용이 가능
- 위의 Bill Pugh Solution 기법과 달리, 클라이언트에서 Reflection을 통한 공격에도 안전
- **하지만 만일 싱글톤 클래스를 멀티톤(일반적인 클래스)로 마이그레이션 해야할때 처음부터 코드를 다시 짜야 되는 단점이 존재한다. (개발 스펙은 언제어디서 변경 될수 있기 때문에)**
- **클래스 상속이 필요할때, enum 외의 클래스 상속은 불가능하다.**

```java
enum SingletonEnum {
    INSTANCE;

    private final Client dbClient;

    SingletonEnum() {
        dbClient = Database.getClient();
    }

    public static SingletonEnum getInstance() {
        return INSTANCE;
    }

    public Client getClient() {
        return dbClient;
    }
}

public class Main {
    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.getInstance();
        singleton.getClient();
    }
}
```

## 최종 정리
- 싱글톤 패턴 클래스를 만들기 위해서는 `Bill Pugh Solution` 기법을 사용하거나 Enum으로 만들어 사용하면 된다.
- 다만, 이 둘의 사용 선택은 자신의 싱글톤 클래스의 목적에 따라 갈리게 된다고 보면 된다.

<br> 

- LaszHolder : 성능이 중요시 되는 환경
- Enum : 직렬화, 안정성 중요시 되는 환경


#### **참고**
- https://inpa.tistory.com/entry/GOF-💠-싱글톤Singleton-패턴-꼼꼼하게-알아보자
- https://steady-coding.tistory.com/576
- https://gyrfalcon.tistory.com/entry/Java-Reflection