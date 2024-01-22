# 제네릭 Generic

## 제네릭 탄생 배경

- 자바는 여러 타입들이 존재하기 때문에, `형변환`을 하면서 많은 예외가 발생할 수 있다.

1. DTO 클래스를 만들고
```java
package d.generic;

import java.io.Serializable;

public class CastingDTO implements Serializable {
    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    public object getobject() {
        return object;
    }
}
```

> 참고) 제대로된 DTO 클래스는 private 변수, getter, setter, Serializable 구현을 해야만 한다.

2. 해당 DTO 클래스를 사용하는 메소드를 만들자.
```java
package d.generic;

public class GenericSample {
    public static void main(String[] args) {
        GenericSample sample = new GenericSample();
        sample.checkCastingDTO();
    }

    public void checkCastingDTO() {
        CastingDTO dto1 = new CastingDTO();
        dto1.setobject(new String());
        
        CastingDTO dto2 = new CastingDTO();
        dto2.setObject(new StringBuffer());
        
        CastingDTO dto3 = new CastingDTO();
        dtos.setobject(new StringBuilder());
    }
}
```

3. 각기 다른 타입의 객체를 만들었다.
- dto1에는 `String`을, dto2에는 `StringBuifer`를, dto3에는 `StringBuilder` 객체를 각각 지정. 이 코드는 컴파일과 문제없이 실행된다.
- `object` 클래스는 모든 클래스의 부모 클래스이기 때문.

4. 문제는 저장되어 있는 값을 꺼낼 때 발생한다.
- 각 객체의 `getobject()` 메소드를 호출 했을 때 리턴값으로 넘어오는 타입은 `0bject`다. 그래서, 다음과 같이 `String`, `StringBufer`, `StringBuilder` 타입으로 각각 형 변환을 해야만 한다.

```java
String temp1 = (String) dto1.getObject();
StringBuffer temp2 = (StringBuffer) dto2.getObject();
StringBuilder temp3 = (StringBuilder) dto3.getObject();
```

만약 dto2의 인스턴스 변수의 타입이 StringBuilder 인지, StringBufer 인지가 혼동될 경우 `instanceof` 예약어를 사용하여 타입을 점검할 수 있다.

```java
Object tempobject = dto2.getobject();
if(tempObject instanceof StringBuilder()) {
        ...
}
```
하지만 매번 이렇게 타입을 점검할수는 없는 노릇이기에 `Java5`부터 제네릭이 새롭게 추가되게 되었다.

<br>

## 제네릭 사용법

- 컴파일 시점에 타입을 점검할 수 있도록 함.

- 제네릭 사용 전
```java
package d.generic; 

import java.io.Serializable;
        
public class CastingDTO implements Serializable {
    private Object object; 
     
    public void setObject(Object object) {
        this.object = object;
    }
             
    public object getobject() {
        return object;
    }
} 
```

- 제네릭 사용 후
  - T : 현존하든 현존하지 않든 어떠한 클래스가와도 문제가 없으나, 명명 규칙과 동일하게, `T(type)`를 적어주는 것이 좋다.
```java
package d.generic;

import java.io.Serializable;

public class CastingGenericDTO<T> implements Serializable{
    private T object;
    
    public void setObject(T obj) {
        this.object=obj;
    }
    public T getObject() {
        return object;
    }
}
```

## 제네릭을 사용한 클래스 사용하기

1. 객체 생성 및 변수에 값 저장
-  객체생성시 `<>`꺽쇠를 사용하여, 각 데이터 타입을 선언해주어야한다.

```java
package d.generic;
public class GenericSample {
    public static void main(String args[]) {
        GenericSample sample = new GenericSample();
        sample. checkGenericDTO();
    }
    public void checkGenericTO() {
        CastingGenericDTO<String> dto1 = new CastingGenericDTO<String>();
        dto1.setObject(new String());
        CastingGenericDTO<StringBuffer> dto2 = new CastingGenericDTO<StringBuffer>();
        dto2.setobject(new StringBuffer());
        CastingGenericDTO<StringBuilder> dto3 = new CastingGenericDTO<StringBuilder>();
        dto3.setObject(new StringBuilder());
    }
}
```
2. 객체의 변수에 저장된 데이터 가져오기
- `dt01` ~ `dt03`까지 객체의 `getobject()` 메소드를 사용하여 객체를 가져올 때는 다음과 같이 간단해진다.
- 형변환을 할필요가 없어짐.

```java
String temp1 = dto1.getObject();
StringBuffer temp2 = dto2.getObject();
StringBuilder temp3 = dto3.getObject();
```

> 명시적으로 데이터의 타입을 지정할 때 사용하는 것을 제네릭이라고 한다.

<br>

## 제네릭 타입 이름을 지정하는 방법
>- `E` : 요소 (Element, 자바 컬렉션 `Collection`에서 주로 사용됨)
>- `K` : 키
>- `N` : 숫자
>- `T` : 타입
>- `V` : 값
>- `S`, `U`, `V` : 두 번째, 세 번째, 네 번째에 선언된 타입
>* 꼭 이 규칙을 지켜야 컴파일이 되는 것은 아니다. 
>* 하지만, 다른 어떤 사람이 보더라도 쉽게 이해할 수 있도록 하려면 이 규칙을 따르는 것이 좋다.

<br>

## 메소드의 매개변수로 넘어가는 제네릭

- 간단한 제네릭 클래스
```java
package d.generic;

public class WildcardGeneric<W> {
    W wildcard;
    public void setWildcard(w wildcard) {
        this.wildcard = wildcard;
    }
    public W getWildcard() {
        return wildcard;
    }
}
```

- 위 제네릭 클래스를 사용하는 메소드

```java
package d.generic;

public class WildcardSample {
    
    public static void main(String[] args) {
        WildcardSample sample = new WildcardSample();
        sample.callWildcardMethod();
    }
    public void callWildcardMethod() {
        WildcardGeneric<String> wildcard = new WildcardGeneric<String>(); // --- 1)
        wildcard.setwildcard("A");
        wildcardStringMethod(wildcard); // --- 2)
    }
    public void wildcardStringMethod(WildcardGeneric<String> c) { // --- 3)
        String value = c.getwildcard();
        System.out.println(value);
    }
}
```

1) callWildcardMethod() 메소드에서는 방금 만든 WildcardGeneric 클래스에 String을 사용하는 제네릭한 객체를 생성한다.
2) 생성한 객체로 wildcardStringMethod()를 호출할 때 넘겨준다
3) wildcardStringMethod()에서는 해당 매개 변수를 받아서 결과를 출력한다.

> 메소드의 매개 변수는 반드시 String을 사용하는 WildcardGeneric 객체만 받을 수 있다. 
> 하지만, Wi1dcardGeneric <Integer>와 같이 선언된 객체를 받는 경우 어떻게 해야할까?
> 제네릭한 클래스의 타입만 바뀐다고 Overloading은 불가능하다.

#### String 대신에 `?`를 적어주면 어떤 타입이 제네릭 타입이 되더라도 상관 없다
- 하지만, 메소드 내부에서는 해당 타입을 정확히 모르기 때문에 앞서 사용한 것처럼 String으로 값을 받을 수는 없다.
- 따라서 `Object`로 처리해야만 한다. 여기서 `?`로 명시한 타입을 영어로는 `wildcard` 타입 이라고 부른다.

```java
public void wildcardStringMethod (WildcardGeneric<?> c) {
    Object value = c.getWildcard();
    System.out.println(value);
}
```
- 만약 넘어오는 타입이 두세 가지로 정해져 있다면, 다음과 같이 메소드 내에서 `instanceof` 예약어를 사용하여 해당 타입을 확인하면 된다.

```java
public void wildcardStringMethod (WildcardGeneric<?> c) {
    Object value = c.getWildcard();
    if(value instanceof String){
        System.out.println(value);
    }
}
```

- wildcard : wildcard는 메소드의 매개 변수로만 사용하는 것이 좋다.
    - 객체에 사용되는 타입으로 지정하는 경우
        ```java
        public void callWildcardMethod () {
        WildcardGeneric<?> wildcard=new WildcardGeneric<String>();
        wildcard.setWildcard("A");
        wildcardStringMethod(wildcard);
        ```
    - 컴파일시 오류 발생
    ```shell
    C: Igodofjava>javac d/generic/WildcardSample.java
    dIgeneric\WildcardSample. java: 12: setWildcard(capture#2 of ?) in d.generic.
    WildcardGeneric<capture#2 of ?> cannot be applied to (java. lang.String)
    wildcard.setWildcard ("A");
            ^
    1 error
    ```
  
> ### wildcard로 객체선언시
> 객체의 값을 `가져오는 것(get)`은 `가능`하지만, 특정 타입으로 값을 `지정하는것(set)`은 `불가능`하다.



## 제네릭 선언에 사용하는 타입의 범위 지정
- `Bounded Wildcards` : `? exends 타입 `
- `Bounded Wildcards`로 선언한 타입에는 값을 할당할 수는 없다. 조화용 매개변수로만 사용해야함.

- 간단한 제네릭 클래스
```java
package d.generic;

public class WildcardGeneric<W> {
    W wildcard;
    public void setWildcard(w wildcard) {
        this.wildcard = wildcard;
    }
    public W getWildcard() {
        return wildcard;
    }
}
```

1. 부모클래스
```java
package d.generic;

public class Car {
    protected String name;
    public Car(String name) {
        this.name = name;
    }
    public String toString() {
        return "Car name=" + name;
    }
}
```
 
2. 자식클래스
```java
package d.generic;

public class Bus extends Car {
    public Bus (String name) {
        super(name);
    }
    public String toString() {
        return "Bus name=" + name;
    }
}
```

3. `Bus` 클래스를 사용하는 메소드
```java
package d.generic;

public class CarWildcardSample {
    public static void main(String args[]) {
        CarWildcardSample sample = new CarWildcardSample();
        sample.callBoundedWildcardMethod();
    }
    public void callBoundedWildcardMethod () {
        WildcardGeneric<Car> wildcard1 = new WildcardGeneric<Car>();
        wildcard1.setWildcard(new Car ("Mustang"));
        boundedWildcardMethod(wildcard1);
        
        WildcardGeneric<Bus> wildcard2=new WildcardGeneric<Bus>();
        wildcard2.setWildcard(new Bus ("6900"));
        boundedWildcardMethod(wildcard2);
    }
    public void boundedWildcardMethod (WildcardGeneric<? extends Car> c) {
        Car value = c.getWildcard(); //  기존에는 Object value 로 선언했어야 했다.
        System.out.println(value);
    }
}
```
- 출력 결과
```shell
Car name=Mustang
Bus name=6900
```

`(WildcardGeneric<? extends Car> c)` - 상한선
- 제네릭 타입으로 Car를 상속받은 모든 클래스를 사용할 수 있다는 의미 <-> `(WildcardGeneric<? super Car> c)` - 하한선
- 다른 타입을 제네릭 타입으로 선언한 객체가 넘어올 수 없다.

## 매개 변수로 객체 타입을 와일드카드로 선언한 메소드에서는 객체에 값을 추가할 수가 없다!??


```java
package d.generic;

public class GenericWildcardSample {
    public static void main(String args[]) {
        GenericwildcardSample sample = new GenericwildcardSample();
    }
    
    public <T> void genericMethod (WildcardGeneric<T> c, T addValue){
        c.setWildcard(addValue);
        T value = c.getWildcard();
        System.out.println(value);
    }
}
```
- `메소드선언부`를 잘 보면 리턴 타입 앞에 `<>`로 제네릭 타입을 선언
- `매개변수`에서는 그 제네릭 타입이 포함된 객체를 받아서 처리
- `Bounded Wildcard` 사용하기
  - `public <T extends Car> void boundedGenericMethod (WildcardGeneric<T> c, T addValue)`
  

- 아래 메소드 실행
```java
public void callGeneriMethod(){
    WildcardGeneric<String> wildcard = new WildcardGeneric<String>();
    genericMethod(wildcard,"Data");
}
```
- 출력
```shell
Data
```

- 메소드에 제네릭 타입이 두 개이상 쓰이는 경우
  - `publc ‹S,T extends Car> void multiGenericMethod (WildcardGeneric<T> c, T addValue, S another)`
  - 이렇게 하면 S와 T라는 제네릭 타입을 메소드에서 사용할 수 있다.


### 제네릭 사용의 예
- 수강생 종류 별 강좌 수강신청

```java
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 강좌 개설
		Course<Person> personCourse = new Course("일반인과정");// 일반인 강좌 개설 
		Course<Worker> workerCourse = new Course("직장인과정"); // 직장인 강좌 개설
		Course<Student> studentCourse = new Course("학생과정");// 학생 강좌 개설
		Course<HighStudent> highStudentCourse = new Course("고등학생과정");// 고등학생 강좌 개설
		
		// 강의 관리 시스템 객체 생성
		CourseSystem cs = new CourseSystem();
		
		// 학생 생성
		Person person1 = new Person("김지현"); //일반인
		Person person2 = new Person("오수진"); //일반인
		Student student1 = new Student("이시영"); // 학생
		Worker worker1 = new Worker("차인하"); // 직장인
		Worker worker2 = new Worker("차승현"); // 직장인
		HighStudent highStd1 = new HighStudent("김새롬"); // 고등학생
		
		
		// 학생 강좌에 등록
		cs.register(personCourse, person1);  //일반인 강좌 등록
		cs.register(personCourse, person2); // 일반인 강좌 등록
		cs.register(studentCourse, student1);// 학생 강좌 등록
		cs.register(workerCourse, worker1); // 직장인 강좌 등록
		cs.register(workerCourse, worker2); // 직장인 강좌 등록
		cs.register(highStudentCourse, highStd1); // 고등학생 강좌 등록
		
		// 출력
		CourseSystem.printCourse(personCourse); // 일반인 강좌 수강생 출력
		CourseSystem.printCourse(studentCourse); // 학생 강좌 수강생 출력
		CourseSystem.printCourse(highStudentCourse); // 고등학생 강좌 수강생 출력 
		CourseSystem.printCourse(workerCourse); // 직장인 강좌 수강생 출력
	}
}
```