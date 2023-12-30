# final 클래스

- 상속과 관련하여 알아두어야할 예약어
- 사용할 수 있는 곳 :  클래스, 메소드, 변수에 선언가능

## 1. 클래스를 final로 선언하는 경우
- 더이상 확장해서는 안되는 클래스인 경우
- 누군가 이 클래스를 상속 받아서 내용을 변경해서 안되는 클래스 선언시

```java
public final class FinalClass {
}

```
- 클래스가 final로 선언되어 있으면, 상속을 해줄 수 없다.

```java
public class FinalChildClass extends FinalClass {
}
```
=> `cannot inherite from final FinalClass` 컴파일 에러 발생!

<br>

## 2. 메소드를 final로 선언하는 경우
- 다른 개발자가 해당 메소드를 덮어써서 메소드를 변경하는 것을 방지하고 싶을 때

```java
public abstract class FinalMethodClass{
    public final void printLog(String data){
        System.out.println("Data="+data);
    }
}
```
- 위에서 `printLog`메소드를 overriding하면 어떻게 될까?
```java
public class FinalMethodChildClass extends FinalMethodClass{
    public void printLog(String data){
        System.out.println("Data="+data);
    }
}
```
=> `cannot override printLog(String) int FinalMethodClass ... overridden method is final`컴파일 에러가 뜬다.


<br>

## 3. 변수를 final로 선언하는 경우
- 변수에 final을 사용하는 것은 더이상 변수를 바꿀수없다는 뜻.
- 월, 일처럼 변하지 않는 값에 선언.
- 따라서 클래스의 instance 변수나, static으로 선언된 변수는 `선언과 함께 값을 지정`해야만 한다.
```java
public class FinalVariable {
    final int instanceVariable=0;
} 
```
- 매개변수나 지역변수를 final로 선언하면 어떻게 될까?

```java
public class FinalVariable {
    final int instanceVariable=0;
    
    public void method(final int parameter){
        final int localVariable;
    }
} 
```
=> 매개변수는 이미 초기화 되어 넘어왔으며, 지녁변수는 메소드 선언 중괄호 내에서만 참조되므로 문제없이 컴파일된다.


```java
public class FinalVariable {
    final int instanceVariable=0;
    
    public void method(final int parameter){
        final int localVariable;
        localVariable=2;
        localVariable=1; // 여기서 컴파일에러
        parameter = 4;   // 여기서 컴파일에러
    }
} 
```

### 참조자료형에대한 final 선언
- final로 지정한 변수에 담긴 객체는 두번이상 생성(new)될 수 없다.
- 하지만 그 객체 안에 있는 객체들은 제약이 없다. 즉, name, phone, email등의 변수가 변경될수있다.
- 클래스가 final이라고 해서 그 안에있는 인스턴스 변수나 클래스 변수가 final은 아니다.

```java
public class FinalReferenceType {
    final MemberDTO dto = new MemberDTO();
    public static void maijn(String args[]){
        FinalReferenceType referenceType = new FinalReferenceType();
        referenceType.checkDTO();
    }
    
    public void checkDTO() {
        System.out.println(dto);
        dto.name = "Sangmin";
        System.out.println(dto);
    }
}

```



