# Default methods

- Java8에서 인터페이스에 디폴트 메소드(Default methods)라는 것이 추가되었습니다.
- 인터페이스는 메소드 정의만 할 수 있고 구현은 할 수 없었습니다만,
- Java8부터 디폴트 메소드라는 개념이 생겨 구현내용도 인터페이스에 포함시킬 수 있었습니다.

`Default methods`에 대해서 알아보겠습니다.

1. Default methods를 정의하는 방법
   다음은 일반적으로 인터페이스를 구현한 코드입니다.

```java
public interface Vehicle {
    public void doSomething(int n);
}
}
```

디폴트 메소드를 사용하면 구현내용도 인터페이스에 포함시킬 수 있습니다. 아래 코드처럼 메소드 이름 앞에 default 키워드를 입력하고 구현 내용을 추가하시면 됩니다.

```java
public interface Vehicle {
    public default void doSomething(int n) {
        System.out.println("doSomething(Vehicle)");
    }
}
```

디폴트 메소드가 구현된 인터페이스도 상속받을 수 있습니다. 다음 코드는 VehicleChild가 Vehicle를 상속받는 코드입니다.

```java
public interface Vehicle {
    public default void doSomething(int n) {
        System.out.println("doSomething(Vehicle)");
    }
}

public interface VehicleChild extends Vehicle {
}
```

Vehicle는 VehicleChild의 디폴트 메소드 구현도 함께 상속받게 됩니다.

2. 클래스가 인터페이스를 implements할 때
   클래스가 디폴트 메소드가 정의된 인터페이스를 implements하면 어떻게 될까요? 예상하시는 것처럼 인터페이스의 디폴트 메소드가 클래스에 자동으로 구현이 됩니다.

다음은 디폴트 메소드가 정의된 Vehicle 인터페이스를 Car 클래스가 implements하는 코드입니다.

```java
public interface Vehicle {
    public default void doSomething(int n) {
        System.out.println("doSomething(Vehicle)");
    }
}

public static class Car implements Vehicle {
}

    public static void main(String args[]) {
        Car car = new Car();
        car.doSomething(10);
    }
// 실행 결과
// doSomething(Vehicle)
```

코드 실행 결과를 보시면 implements했을 때 디폴트 메소드에 구현된 것도 함께 클래스에 적용된 다는 것을 알 수 있습니다.

3. (다중상속개념) 클래스가 두개의 인터페이스를 implements 했을 때
   어떤 클래스가 두개의 인터페이스를 implements하면 어떻게 될까요? 그냥 인터페이스도 아니고 동일한 메소드 이름(Signature)으로 디폴트 메소드를 구현한 인터페이스입니다.

개념적으로 다중상속이기 때문에 컴파일러는 어떤 인터페이스의 메소드를 상속받아야 할 지 헷갈릴 수 있습니다. 결과를 먼저 말씀드리면 컴파일 에러가 발생합니다.

다음 코드는 Car 클래스가 동일한 메소드를 디폴트 메소드로 구현한 Vehicle과 Movable을 implements했습니다

```java
public interface Vehicle {
    public default void doSomething(int n) {
        System.out.println("doSomething(Vehicle)");
    }
}

public interface Movable {
    public default void doSomething(int n) {
        System.out.println("doSomething(Movable)");
    }
}

public class Car implements Vehicle, Movable {
}
```

3.1 왜 등장했는가?

- 인터페이스에 새로운 메서드를 구현하는 경우를 생각해보자. 만약 인터페이스를 구현한 구현체가 수십개라면? 생각도 하기가 싫다. 
- 인터페이스를 구현하는 모든 클래스에는 새로 추가된 메서드를 구현해야한다. 
- 기존의 구현을 고치지 않고도 이미 공개된 인터페이스를 변경할 방법은 없을까? 라는 아이디어에서 default method가 등장하게 되었다.
- 즉, default method는 구현체에서 반드시 구현하지 않아도 되는 메서드를 인터페이스에서 구현해둘수가 있게 되었다. 
- 구현체가 로드될때는 인터페이스에 default키워드가 붙어 있는 메서드가 로드되면서 컴파일시에도 아무 문제없이 실행이 가능하다.
- 즉, 기존의 코드를 최대한 수정하지 않으면서 설계된 인터페이스에 새로운 확장을 가능하게 만들기 위해서 등장한 기술이다.

- Java8의 List 인터페이스에는 sort메서드가 default method로 선언되어 있다. 즉, sort를 구현하지 않더라도 Collections의 sort를 이용해서 정렬을 진행하도록 선언되어 있는것이다.

default void sort(Comparator<? superE> c) { Collections.sort(this,c); }
 