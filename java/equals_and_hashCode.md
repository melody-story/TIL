# equals와 hashCode란?

- equals와 hashCode는 모든 Java 객체의 부모 객체인 Object 클래스에 정의되어 있다. 
- 그렇기 때문에 Java의 모든 객체는 Object 클래스에 정의된 equals와 hashCode 함수를 상속받고 있다.
 
 
# 1. equals()란?
boolean equals(Object obj)로 정의된 equals 메소드는 기본적으로 2개의 객체가 동일한지 검사하기 위해 사용된다. equals가 구현된 방법은 2개의 객체가 참조하는 것이 동일한지를 확인하는 것이며, 이는 동일성(Identity)을 비교하는 것이다.
즉, 2개의 객체가 가리키는 곳이 동일한 메모리 주소일 경우에만 동일한 객체가 된다.
```java
public boolean equals(Object obj) {
return (this == obj);
}
``` 
 
하지만 프로그래밍을 하다보면 동일한 객체가 메모리 상에 여러 개 띄워져있는 경우가 있다. 해당 객체는 서로 다른 메모리에 띄워져있으므로 동일한(Identity) 객체가 아니다. 하지만 프로그래밍 상으로는 같은 값을 지니므로 같은 객체로 인식되어야 하는데, 이러한 동등성(Equality)를 위해 우리는 값으로 객체를 비교하도록 equals 메소드를 오버라이딩해주는 것이다.
예를 들어 아래와 같이 동일한 값을 갖는 문자열을 2개 생성하면 각각은 서로 다른 메모리에 할당되므로 동일하지 않다. 대신 같은 값을 지니므로 동등하다. 하지만 동일성을 비교하는 equals 메소드를 호출해보면 true가 나오는데, 그 이유는 String 클래스에서 equals 메소드를 오버라이드하여 객체가 같은 값을 갖는지 동등성(Equality)을 비교하도록 처리했기 때문이다.
```java

String s1 = new String("Test");
String s2 = new String("Test");

System.out.println(s1 == s2);			// false
System.out.println(s1.equals(s2));		// true;

// equals, overridden in String Class
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                return false;
                i++;
            }
            return true;
        }
    }
    
        return false;
}
```
<br> 

## hashCode()란? 
int hashCode()로 정의된 hashCode 메소드는 실행 중에(Runtime) 객체의 유일한 integer값을 반환한다. Object 클래스에서는 heap에 저장된 객체의 메모리 주소를 반환하도록 되어있다. (항상 그런 것은 아니다.)
public native int hashCode();
 
 
여기서 native 키워드는 메소드가 JNI(Java Native Interface)라는 native code를 이용해 구현되었음을 의미한다. native는 메소드에만 적용가능한 제어자로, C or C++ 등 Java가 아닌 언어로 구현된 부분을 JNI를 통해 Java에서 이용하고자 할 때 사용된다. 우리같은 일반 개발자는 어디에서도 사용할 수 없다.
hashCode는 HashTable과 같은 자료구조를 사용할 때 데이터가 저장되는 위치를 결정하기 위해 사용된다.
 
<br> 

 
## equals와 hashCode의 관계 
동일한 객체는 동일한 메모리 주소를 갖는다는 것을 의미하므로, 동일한 객체는 동일한 해시코드를 가져야 한다. 그렇기 때문에 우리가 equals() 메소드를 오버라이드 한다면, hashCode() 메소드도 함께 오버라이드 되어야 한다.
이러한 equals와 hashCode의 관계를 정의하면 다음과 같다.

Java 프로그램을 실행하는 동안 equals에 사용된 정보가 수정되지 않았다면, hashCode는 항상 동일한 정수값을 반환해야 한다. (Java의 프로그램을 실행할 때 마다 달라지는 것은 상관이 없다.)
두 객체가 equals()에 의해 동일하다면, 두 객체의 hashCode() 값도 일치해야 한다.
두 객체가 equals()에 의해 동일하지 않다면, 두 객체의 hashCode() 값은 일치하지 않아도 된다.

즉, obj1.equals(obj2) == True 이면 hashCode(obj1) == hashCode(obj2) 이여야하지만  hashCode(obj1) == hashCode(obj2) 라고 obj1.equals(obj2) == True일 필요는 없다. 하지만 우리는 다른 객체에 대해 동일한 hashCode를 생성한다면 hashTable을 생성하는데 불이익을 받을 수 있음을 인지하고 있어야 한다.
 

---


# 2. equals와 hashCode의 Override

## equals() Override의 필요성 
만약 아래와 같은 Employee 클래스가 있다고 하자. Employee는 id를 고유값으로 갖는다.
```java
public class Employee {
private Integer id;
private String firstname;
private String lastName;
private String department;

    //Setters and Getters
}
```
 
 
만약 아래와 같이 같은 id 값을 갖는 2개의 Employee를 서로 다른 처리 과정에 의해 얻었다고 하자. 2개의 Employee는 같은 id를 갖기 때문에 equals 연산을 한다면 true를 반환해야 한다. 하지만 아래의 예제는 깊게 볼 필요도 없이 false를 반환할 것이다.
```java
public class EqualsTest {
    public static void main(String[] args) {
    Employee e1 = new Employee();
    Employee e2 = new Employee();
    
            e1.setId(100);
            e2.setId(100);
     
            System.out.println(e1.equals(e2));  //false
        }
}
```
 
이러한 문제를 해결하기 위해 우리는 Employ에 다음과 같은 equals 메소드를 오버라이드 해야 한다.
public boolean equals(Object o) {
    if(o == null) {
    return false;
    }
    if (o == this) {
    return true;
    }
    if (getClass() != o.getClass()) {
    return false;
    }
    
    Employee e = (Employee) o;
    return (this.getId() == e.getId());

}
 
 
이제 equals에 의한 문제는 해결된 것 처럼 보인다.  하지만 우리가 Employee를 HashSet과 같은 자료구조에 저장하려고 하면 또 다른 문제가 생기게 된다.
 
 
 
## hashCode() Override의 필요성 
앞서 설명한대로 HashTable이나 HashSet, HashMap과 같은 자료구조는 자료를 저장하기 위한 위치를 선택하기 위해 hashCode를 이용한다. 그렇다면 우리가 수정한 Employee를 HashSet에 저장하면 어떤 결과가 나올까?
```java

import java.util.HashSet;
import java.util.Set;

public class EqualsTest {
public static void main(String[] args) {
Employee e1 = new Employee();
Employee e2 = new Employee();

        e1.setId(100);
        e2.setId(100);
 
        //Prints 'true' now!
        System.out.println(e1.equals(e2));
 
        Set<Employee> employees = new HashSet<Employee>();
        employees.add(e1);
        employees.add(e2);
         
        System.out.println(employees);  //Prints two objects
    }
}
```
 
 
Object 클래스의 hashCode() 메소드는 해당 메모리 주소값을 반환한다고 설명하였다. 그렇기 때문에 위의 e1과 e2는 다른 해시값을 반환할 것이고, HashSet에는 2개의 객체가 서로 다른 위치에 저장될 것이다.
우리는 이러한 문제를 해결하기 위해 hashCode 메소드도 Employee 클래스에 오버라이드하여 수정해주어야 한다.
```java
@Override
public int hashCode() {
    final int PRIME = 31;
    int result = 1;
    result = PRIME * result + getId();
    return result;
}
```
이러한 작업을 모두 거치면 hashSet에도 1개의 데이터만 저장되게 된다.
 
 
 
 
## 라이브러리를 사용한 Override 
Apache Commons의 라이브러리를 사용하면 HashCodeBuilder와 EqualsBuilder를 사용할 수 있다. 외부 라이브러리를 이용하면 더욱 간편히 해당 메소드들을 오버라이드할 수 있다.
```java
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Employee {
    private Integer id;
    private String firstname;
    private String lastName;
    private String department;

    //Setters and Getters
 
    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(getId()%2==0?getId()+1:getId(), PRIME).toHashCode();
    }
 
    @Override
    public boolean equals(Object o) {
    if (o == null)
       return false;
        
    if (o == this)
       return true;
        
    if (o.getClass() != getClass())
       return false;
     
    Employee e = (Employee) o;
     
    return new EqualsBuilder().
              append(getId(), e.getId()).
              isEquals();
    }
}
```
 
 
## Guaidlines to override hashCode() & equals() 

- hashCode와 equals를 생성하기 위해서는 같은 attribute를 이용하라.(e.g. Employee id)
- equals는 일관되어야 한다. 즉, 객체가 수정되지 않았다면 항상 결과가 동일해야 한다.
- `a.equals(b) == true`이면, `a.hashCode() == b.hashCode()` 역시 `true`여야 한다.
두 메소드는 항상 함께 오버라이드 되어야 한다.

## 라이브러리를 사용한 Override 
- 만약 ORM을 사용하고 있는 경우라면, hashCode와 equals를 오버라이드 하는 메소드 내부에서 Getter를 사용하기를 권장한다. 그 이유는 ORM에 의해 fields가 Lazy Loaded되어, getter를 부르기 전에는 사용이 불가능할 수 있기 때문이다.
- 예를 들어 만약 Employee 클래스의 정보가 Lazy loaded 되었다면, id에 0이 할당되어 `*e1.id == e2.id*가 0==0으`로 처리될 수 있기 때문이다. 하지만 이것을 e1.getId() == e2.getId()로 수정한다면 ORM에 의해 id에 값이 할당된 후에 getId()가 호출가능하므로, 오작동을 멈출 수 있다.

#### **참고**
- https://mangkyu.tistory.com/101