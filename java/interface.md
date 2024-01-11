# 인터페이스(Interface)

- 메소드 내용이 없다.
자바에서 .class 파일을 생성할 수 있는 것에는 `클래스` 뿐만아니라, `인터페이스`와 `추상클래스` 라는 것이 있다.

- 이를 이해하기 위해서는 시스템을 만드는 `방법론`을 이해해야한다.
방법론이란?
- 시스템을 어떻게 만들것인지에 대한 절차를 설명
- 어떤문서를 작성해야하는지 정리해놓은 공동 절차
- 일반적인 절차는 아래와 같다.
    - 분석 -> 설계 -> 개발 및 테스트 ->  시스템 릴리즈

분석
- 어떻게 개발하기를 원하는지 요구사항 분석이라고 한다.

설계
- 분석단계에서 만든 대략적인 그림을 프로그램으로 만들 수 있도록 설계
- 어떤 클래스, 변수, 메소드를 만들 것인지, 데이터는 어떻게 저장할 지 등등의 세부적인 것을 정함.
- 

개발 및 테스트
- 설계에서 만들기로 한 것들을 개발하는 단계
- 실제 시스템에서 제공해야 하는 기능들을 만듦 이 만드는 작업을 개발이라고 함.
- 필요한 기능들이 제대로 동작하는지 확인하는 테스트 작업을 수행

시스템 릴리즈
- 시스템을 드디어 사용자들에게 제공하는 단계
- 시스템을 오픈한 이후에는 운영/유지보수 단계를 거치면서 문제가 있는 부분들을 고쳐나간다.

내용들을 문서에만 정리하면 나중에 메소드 관련 내용들이 변경되면 문서도 수정해야하므로, 2중 3중의 일이 된다. 
이 설계 단계에서 인터페이스라는 것을 만들어 두면 개발할 때 메소드의 이름을 어떻게 할지. 매개 변수를 어떻게 할지를 일일이 고민하지 않아도 된다. 게다가. 개발자의 역량에 따라서 메소드 이름과 매개 변수 이름이 천차만별일 수가 있는데, 그러한 격차를 줄이는 데도 큰 역할을 한다.


```
public boolean equals (Object o);
```
equals() 메소드의 경우는 '내가 equals() 메소드에 객체를 하나 던져줄 테니 넌 같으면 true를, 다르면 false를 리턴해 줘라고 이야기 하는 것과 같다. 해당 메소드를 사용하는 사용자의 입장에서는 내부 구현이 어떻게 되어 있는지는 별로 궁금하지 않고, 원하는 메소드를 호출하고, 그 답을 받으면 된다.

가장 일반적인 것이 `DAO Data Access Object`라는 패턴이다. 
이 패턴은 데이터를 저장하는 저장소에서 원하는 값을 요청하고 응답을 받는다. 이 세상에는 여러 가지 종류의 DBMS가 있다.

>• DBMS는 Database Management System의 약자로 데이터를 저장하는 저장소의 하나이며, Oracle, MysOL, MSSQL 등의 관계형 DB(Relational Database)와 요즘 유행하는 MongoDB,CouchDB 등과 같이 NoSql도 포함하는 포괄적인 단어이다.


학원의 회원 정보를 확인하는 MemberDAO라는 인터페이스 만든다고 생각해 보자. 
어떤 메소드들이 필요할까? 예를 들어, 회원의 전화번호를 매개 변수로 넘겨주면, 회원의 상세 정보를 제공한다든지, 
이름을 넘겨주면 동명이인을 포함한 정보들의 목록을 보여준다든지 여러 종류의 메소드들이 존재할 것이다.
한 번 여러분들이 직접 생각한 메소드들을 아래의 공란에 적어보자.

생년월일 -> 상세정보
상위 몇퍼센트인지
하위 몇퍼센트인지
한주간 출석률
학원비 수납 여부
결석횟수
수학 점수


인터페이스 작성시 장점
- 메소드들이 어떤 DBMS를 쓰는지 상관없도록 만들어짐.
- 선언과 구현을 구분할 수 있음


결론 : 인터페이스와 atstract 클래스를 사용하는 이유
• 설계시 선언해 두면 개발할 때 `기능을 구현하는 데에만 집중`할 수 있다.
• 개발자의 역량에 따른 메소드의 이름과 매개 변수 선언의 격차를 줄일 수 있다.
• 공통적인 인터페이스와 abstract 클래스를 선언해 놓으면, 선언과 구현을 구분할 수 있다.


인터페이스 선언
- public class로 시작하지 않고, public interface로 시작.

```
package c.service;

import c.model.MemberDTO;
public interface MemberManager {
    public boolean addMember (MemberDTO member);
    public boolean removeMember (String name, String phone); 
    public boolean updateMember (MemberDTO member);
}
```

이렇게 컴파일을 하고 나면 동일한 이름에 확장자가 .cass인 클래스 파일이 만들어진다. 
소스 파일 이름과 클래스 파일 이름만 보면 이 파일 에 있는 것이 인터페이스인지 클래스인지 알 수가 없다. 
그래서 프로젝트마다 표준을 잡는 데, 보통은 이와 같이 아무런 명시적인 단어를 지정하지 않거나. 클래스 이름 앞에 내를 붙어서 INembenYanager라고 할 수도 있다. 프로젝트 개발 표준을 잡는 사람의 마음대로다. 하지만, 마음대로 하지는 말고, 선배들이 시키는 대로 하라.

그러면 이렇게 만든 인터페이스를 어떻게 활용할까? 
```
package c.service;

public class MemberManager-Imp1 implements MemberManager {}
```
이와 같이 만들어져 있는 인터페이스를 적용한다고 할 때에는 클레스 선언문에 클래스 이름 뒤에 implements라는 예약어를 쓴 후 인터페이스들을 나열하면 된다. 자바의 예약어 중에서 가장 긴 단어이다. . 그리고 끝에 s가 붙는 것은 클래스 자체가 3인칭 단수이기 때문

그런데, 인터페이스들을 나열하면 된다고? 자비는 상속이 하나밖에 안 된다고 하지 않았다?

라고 생각하는 분이 있을 수 있다. 자바에서 인터페이스를 implemens하면 "상속한다고 하 지 않고, "구현한다"고 한다. 즉, implomens 한다고 해서 상속받는다는 것이 아니라.

해당 클래스에서 구현해야 하는 인터페이스들을 정의함으로써 클래스에 짐을 지어 주는 것이다.
>interface를 구현하는 것은 상속이 아니므로 여러 개를 implements 할 수 있다. 

그러면 방금 만든 MemberManagerImpl 클래스를 컴파일해 보자.


```
MemberManagerImp1 is not abstract and does not override abstract method updateMember (MemberDTO) in MemberManager
```

굉장히 긴, 두 줄에 걸친 에러 메시지가 출력된 것을 볼 수 있다. 번역해보면 'MemberManagerImpl'는 abstract 클래스도 아니고,
 MemberManager 에 정의되어 있는 update()라는  abstract 메소드도 구현하지 않았다."라는말이다.


도대체 abstract 뭐길래 아니라고 할까? abstract의 사전적 의미는 '추상적인'이라는 말이다. 
자바에서는 메소드가 구현되지 않은, 인터페이스에 있는 메소드 선언문들과 같이 몸통이 없이
선언한 것을 abstract라는 용어로 표현한다.


여러분들이 인터페이스를 구현할 경우 implemonts 뒤에 인터페이스 이름을 나열한 경우에는
'반드시 인터페이스에 정의된 메소드들의 몸통을 만들어 주어야만 한다. 
즉, 메소드들을 구현해야만 한다. 
MembernanagerImpl 클래스는 적어도 다음과 같이 구현해야만 한다.

```
package c.service;
import c.model. MemberDTO;
public class MemberManagerImp1 implements MemberManager {

    @Override
    public boolean addMember (MemberDTO member) {
        return false;
    }

    @Override
    public boolean removeMember (String name, String phone) {
        return false;
    }

    @Override
    public boolean updateMember (MemberDTO member) {
        return false;
    }
}
```

MemberManager에 정의된 메소드들을 모두 구현해야만 컴파일이 정상적으로 수행된다. 
이제 이 클래스를 컴파일하면 정상적으로 컴파일이 될 것이다. 
이제 개발자들이 해야 하는 일은 대충 return false만 적어 놓은 메소드들이 실제로 Member 정보를 저장하고, 삭제하고, 수정하는 일을 할 수 있도록 제대로 된 코드를 만드는 것이다.

바로 이 작업이 개발 프로세스 중에서 개발에 해당한다. 
 
참고로 여기에서 처음 등장하는 @Overide는 17장에서 배우는 어노테이션이 라는 것 중 하나이다. 

우선 명시적으로 Overide 했다는 것을 알려주는 표현이라는 정도만 알고가자.
정리하자면, 설계 단계에서 인터페이스만 만들어 놓고, 
개발 단계에서 실제 작업을 수행하는 메소드를 만들면 
설계 단계의 산출물과 개발 단계의 산출물이 보다 효율적으로 관리된다.

그런데, 이렇게 설계에서만 사용하려고 인터페이스를 만드는 것은 아니다.
인터페이스의 또다른 용도는 외부에 노출되는 것을 정의해 놓고자 할 때 사용된다. 

다시 말해서 MemberManagerImpl이라는 클래스가 있는데, 이 클래스가 "저한테 직접 이야기하지 마시구요. 공식적인 것은 저의 대변인을 통해서 말씀하세요"라고 내 놓는 대변인이 바로 인터페이스다.

혹시나 해서 이야기하지만, 인터페이스에는 지금까지 설명한 대로 구현이 되어 있는 코드가 없다. 따라서, 만들어지는 파일이 .cass의 확장자를 가진다고 해서 인터페이스를 그대로 사용한 수는 없다. 왜냐하면 생성자도 없고, 메소드의 내용 중 아무것도 채워져 있지 않기 때문이다.

```
package c;
import c.service.MemberManager;

public class InterfaceExample {
    public static void main(String args[]) {
        MemberManager member=new MemberManager ();
    }
}
```

에러가 발생하고 MemberWanager가 abstract이기 때문에 초기화가 되지 않는다는 메시지가 출력 된다. 컴파일러가 아무것도 구현해 놓지 않았는데, 왜 얘로 초기화하려는 것이냐?"라며 에러 를 내뿜는 것이다. 당연히 이렇게 사용하면 안 된다.
그러면 어떻게 사용해야 제대로 인터페이스를 사용하는 것인가? 

상속 관계에 있는 클래스의 형 변환에 대해서 생각하면 답은 바로 나온다.

```
MemberManager member=new MemberManagerImpl();
```

겉 으로 보기에 member의 타입 아래 MemberManager이다. 
그리고, MemberManagerImpl 클래스에는 인터페이스에 선언되어있는 모든 메소드들이 구현되어 있다.


---

Java8에서 인터페이스에 디폴트 메소드(Default methods)라는 것이 추가되었습니다. 인터페이스는 메소드 정의만 할 수 있고 구현은 할 수 없었습니다만, Java8부터 디폴트 메소드라는 개념이 생겨 구현 내용도 인터페이스에 포함시킬 수 있었습니다.

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