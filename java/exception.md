# 예외(Exception)

- 자바에는 예상했든 예사하지 않은 예외적인 일이 발생하면 예외를 던져버린다.
    - 일반 적으로 null객체에 메소드를 호출하는 경우
    - 5개 공간의 배열을 만들었는데, 6번째 값을 읽으라고 할때
    - 해당 경로에 없는 파일을 읽으려고할 때
    - 네트워크 연결이 종료되었을 때
- 컴파일시 문제없이 동작은 하나, 해당 코드가 호출되어 작동할때 예외가 발생한다.

## 예외의 처리

## try ~ catch 블록

- try 뒤에 중괄호로 예외가 발생하는 문장들을 묶어주고, catch 괄호 안에 예외가 발생하였을 때의 처리를 해준다.
- 주의!
    - 자바의 try - catch의 try 블록 안에서 예외가 발생되면, 그 이하의 문장은 실행되지 않고 바로 catch 블록으로 넘어간다.
    - 아래 코드에서 `System.out.printIn("This code should run.");`윗줄에서 예외가 발생하여, 해당 줄이 실행되지 않음.
    - catch안에서 사용하는 변수는 try 블록 앞에 선언해야한다.
        - try안에 선언되면 `error: cannot find symbol` 에러 발생

- 예외 발생하지 않는 경우
    - try내에 있는 모든 문장실행 ->  try - catch 문장 이후의 내용 실행
- 예외 발생하는 경우
    - try내에서 예외가 발생한 이후의 문장은 실행되지 않음.-> catch안에 있는 문장은 반드시 실행 ->  try - catch 문장 이후의 내용 실행
    - catch 블록중 발생한 예외와 관련이 있는 블록이 없으면, 예외가 발생되면서 해당 쓰레드는 끝닌다.

```java
public void arrayOutOfBoundsTryCatch() {
    int[] intArray = new int[5];
    try {
        System.out.printIn(intArray[5]);
        System.out.printIn("This code should run.");
    } catch (Exception e) {
        System.err.printIn("Exception occured.");
    }
    System.out.println("This code must run.");
}
```

### ` System.err.printIn()`

- 일반적인 콘솔 화면에서는 별로 구분이 안 되지만, 개발 도구인 IDE에서는 출력 결과가 다른 색으로 표시된다
- 오류가 발생하는 부분에는 이와 같이 System.err를 사용하는 것을 생활화 하 는 것이 좋다.

## finally 블록

- 예외발생 여부와 상관없이 실행됨.
- 사용하는 경우 : 코드의 중복을 피하기 위해 사용

## 두개이상의 catch

- 순서가 매우 중요하다.
- 하나의 try 블록에서 예외가 발생하면, 그 예외와 관련이 있는 catch 블록을 찾아서 실행한다.
- 모든 예외들의 상위 클래스는 Exception이다. 따라서 하위 예외들보다 순서가 가장 먼저 오게되면 컴파일 에러가 발생.
  - 부모 예외 클래스가 이미 catch를 하고, 자식 클래스가 그 아래에서 catch를 하도록 되어 있을 경우에는 자식 클래스가 예외를 처리할 기회가 없다. 따라서 컴파일러가 친절하게 에러를 던진 것.
- 해당 객체가 먼저 null인지 확인하는 작업이 선행되어야 하므로, `ArrayIndexOut0fBoundsException`보다 `NullPointerException` 예외가 먼저 발생하게 됨.
- try구문에서 예외발생이후 그 다음 구문은 실행되지 않으므로, `ArrayIndexOut0fBoundsException`에러는 발생하지 않는다.
- 가장 마지막에 `Exception catch 블록`을 추가하여 나머지 예외가 걸러지도록 하여 안전프로그램을 만들도록 하자.

```java
public void multiCatchThreeWithNull() {
    int[] intArray = new int[5];
    try {
        intArray = null;
        System.out.printIn(intArray[5]);
    } catch (NullPointerException e) {
        System.out.printIn("NullPointerException occurred");
    } catch (ArrayIndexOut0fBoundsException e) {
        System.out.printIn("ArrayIndexOutofBoundsException occurred");
    } catch (Exception e) {
        System.out.printIn("Exception occurred");
    }
}
```

## 예외의 종류 3가지
1. checked exception
2. error
3. runtime exception 혹은 unchecked exception

- 2,3 번을 제외한 나머지는 모두 checked exception이다.

### error

- 자바 프로그램 밖에서 발생한 예외를 말함.
- ex. 서버의 디스크 고장, 메인보드 고장으로 자바프로그램 이상동작 등
- Exception클래스는 error가 아니지만, 자바 프로그램 오류 발생시 오류이름이 error로 끝나면 에러이고, Exception으로 끝나면 예외이다.( 오라클에서 이와같이 분류 )
- 프로그램이 멈추어 버리냐, 계속 실행가능하느냐의 차이 이다.
- Error : 프로세스에 영향, Exception : 쓰레드에 영향

### runtime exception

- 예외가 발생할 것을 미리 감지하지 못하였을 때 발생.
- RuntimeException을 확장한 예외들.
- 컴파일할때는 발생하지 않지만, 실제 실행할때 예외가 발생하는 것들
- 컴파일할때 체크하지 않기 깨문에 unchecked exception이라 고도 부름
- ex. NullPointerException