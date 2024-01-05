# exception 커스컴 하기

- Throwable을 직접 상속받는 클래스 Exception과 Error 중 Error는 손댈필요도, 손대어서도 안된다.
- 반면, Exception을 처리하는 예외 클래스는 개발자가 임의로 추가하여 생성가능하다.
- 조건 : Throwable 또는 직계 자손 클래스(java.lang.Exception 등)를 상속받아야함.
  - 예외클래스가 되려면 예외 관련 클래스를 확장하면된다. 
  - Exception을 확장(extends)하지 않은 채로 해당 커스팀 예외가 사용되면, 컴파일이 되지 않는다.
    > error: incompatible types: MyException cannot be converted to Irowable
- 

```java
package c.exception;

public class MyException extends Exception { // 예외 클래스가 되려면 Exception클래스를 확장해야한다.
    public MyException() {
        super();
    }
    public MException (String message) {
        super(message);
    }
}
```
```java
package c.exception;

public class CustomException {
    public static void main(String args []) {
        CustomException sample = new CustomException ();
        try {
            sample.throwMException(13);
        } catch(MyException mye){ // 반드시 MyException로 catch할 필요는 없음. Exception으로 해도 무방
              mye.printStackTrace();
        }
    }
    public void throwMyException (int number) throws MyException{
      try {
          if (number>12){
              throw new MyException("Number is over than 12");
          }
      } catch (MyException e) {
          e.printStackTrace();
      }
    }
}
```
