# String타입과 hashcode 함수

- 자바의 hashcode() 메서드는 Object 클래스의 메서드로써, 모든 클래스는 Object 클래스를 상속하기 때문에 사실상 모든 객체에서 가지고 있는 메서드라고 볼 수 있다.
  - Object클래스의 hashCode()
![](./img/object_hashCode().png)
  - java.lang.String 클래스의 hashCode() 메소드
    ```java
      public int hashCode() {
            int h = hash;
            if (h == 0 && value.length > 0) {
                char val[] = value;
    
                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
      }
    
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

- hashCode() 메서드는 해싱 기법에 사용되는 해시함수(hash function)을 구현한 것이다.
- 해시함수는 찾고자 하는 `값`을 입력하면 그 값이 `저장된 위치`를 알려주는 해시코드(hash code)를 반환한다.

- Object클래스에 정의된 hashCode 메서드는 객체의 주솟값을 이용해서 해시코드를 만들어 반환하기 때문에 서로 다른 두 객체는 결코 같은 해시코드를 가질 수 없다.
- 앞서 살펴본 것과 같이 클래스의 인스턴수변수 값으로 객체의 같고 다름을 판단해야하는 경우라면 equals 메서드 뿐만 아니라, hashCode 메서드도 적절히 오버라이딩 해야한다. 같은 객체라면 hashCode메서드를 호출했을 때 결과값인 해시코드도 같아야 하기 때문이다. 만일 hashCode메서드를 오버라이딩하지 않는다면 Object클래스에 정의된 대로 모든 객체가 서로 다른 해시코드값을 가질 것이다.
- 하지만, String클래스는 문자열의 내용이 같으면, 동일한 해시코드를 반환하도록 hashCode 메서드가 오버라이딩되어 있기 때문에, 문자열의 내용이 같은 s1과 s2에 대해 hashCode()를 호출하면 항상 동일한 해시코드값을 얻지만 중복이 생길수도 있다.
- 따라서 값이 같은지 면밀히 확인하기 위해서는 equals 메서드를 통해 두 String의 내용이 정확히 일치하는지를 확인해야 한다.
  
  ```java
  public class HashCodeEx1 {
      public static void main(String[] args) {
          String s1=new String("DolphaGo");
          String s2=new String("DolphaGo");
          String s3=new String("AlphaGo");
  
          System.out.println(s1.equals(s2)); //true
          System.out.println(s1.hashCode()); //1179255728
          System.out.println(s2.hashCode()); //1179255728
          System.out.println(s3.hashCode()); //756773574
  
          System.out.println(System.identityHashCode(s1)); //1956725890
          System.out.println(System.identityHashCode(s2)); //356573597
          System.out.println(System.identityHashCode(s3)); //1735600054
      }
  }
  ```

- 반면에 `System.identityHashCode(Object x)`는 Object클래스의 hashCode 메서드처럼 객체의 주솟값으로 해시코드를 생성하기 때문에 모든 객체에 대해 항상 다른 해시코드값을 반환할 것을 보장한다. 그래서 s1과 s2가 해시코드는 같지만 서로 다른 객체라는 것을 알 수 있다.

---
### 주의!!

- String 클래스는 문자열의 내용이 달라도 동일한 hashCode()의 결과로 같은 값이 반환될 수 있다.
- 이는 hashCode()의 리턴 타입이 `int`이기 때문에 4byte(-2,147,483,648 ~ 2,147,483,647)로 표현 가능한 범위에서 결과가 나오지만
- 우리가 만들 수 있는 String 타입의 문자열은 너무나도 많기에 결국 중복되는 값이 생길 수 있기 때문

>### 결론
>1. equals() 메서드로 같은 객체는 같은 hashCode() 결과가 나와야 한다.
>2. hashCode() 메서드로 같은 객체가 항상 equals() 메서드로 같은 객체는 아니다.