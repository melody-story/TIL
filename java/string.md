# String

- 클래스중에서 유일하기 더하기 연산을 제공하는 클래스
- 객체를 더하기하면 toString() 메소드가 호출되고 그 결과를 더한다.

#### String 클래스의 선언

```java
public final class String extends Object
    implements Serializable, Comparable<String>, CharSequence {
}
```

1. `public` 로 선언된 클래스

- 누구나 접근가능

2. `final` 로 선언된 클래스

- 더이상 클래스를 확장할 수 없다. 자식 클래스 양산 불가능

3. `implements`

- `Serializable`, `Comparable<String>`, `CharSequence` 인터페이스를 구현한 클래스이다.
- `Serializable`
    - 구현해야하는 메소드가 하나도 없는 특이한 인터페이스
    - 구현을 선언해놓으면, 해당 객체를 파일로 저장하거나, 다른 서버에 전송가능한 상태가 됨.
- `Comparable`
    - `compareTo()` 라는 메소드 하나만 선언됨.
        - 매개변수로 넘어가는 객체와 현재 객체가 같은지를 비교하는데 사용됨.
        - 객체의 순서를 처리할때 유용 : 리턴타입이 int로, 같으면 0, 다르면 순서상 앞이면 -1, 뒤에있으면 1을 리턴함.(equals()와 다름)
        - 서로다른 두 문자열이 index 0부터 포함 관계에 있을 경우 문자열 길이의 차이를 반환.
          - `System.out.println("abc".compareTo("a"));` : `2`
          - `System.out.println("a".compareTo("abc"));` : `-2`
          - `System.out.println("abc".compareTo("ce"));` : `-2` -> 같은 인덱스에 해당하는 값이 다른 최초 문자의 순서 차를 반환
    - `<String>` : 제네릭을의미
- `CharSequence`
    - 해당 클래스가 문자열을 다루기 위한 클래스라는 것을 명시적으로 나타내는데 사용됨.
        - `StringBuilder`와 `StringBuffer`도 이 인터페이스를 통해 구현됨.

## String의 생성자

- 대부분 문자열을 만들때 아래와 같이 간단하게 만듦

```java
String name="Melody";
```

#### 용어

- character set : 한글, 일본어와 깉이 특정 나라의 글자
- decoding : 일반적으로 암호화되어 있거나 컴퓨터가 이해할 수 있는 값들을 알아보기 쉽게 변환하는 것

## 종류

| 생성자                                                              | 설명                                                                                  |
|------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| `String()`                                                       | 비어있는 String 객체를 생성. 그러나 이렇게 생성하는 것은 전혀 의미가 없으며, `Sting name=null;`로 선언하는 것이 더 효율적 임 |
| `String(byte[] bytes)`                                           | 현재 사용중인 플랫폼의 캐릭터 셋을 사용하여 제공된 byte 배열을 디코딩한 String 객체를 생성한다.                         |
| `String(byte[] bytes, Charset charset)`                          | 지정된 캐릭터 셋을 사용하여 제공된 byte 배열을 디코딩한 String 객체를 생성한다.                                  |
| `String(byte[] bytes, String charsetName)`                       | 지정한 이름을 갖는 캐릭터 셋을 사용하여 지정한 byie 배 열을 디코딩한 String 객체를 생성한다.                          |
| `String(byte[] bytes, int offset, int length)`                     | 현재 사용중인 플랫폼의 기본 캐릭터 셋을 사용하여 지정한 byte 배열의 일부를 디코딩한 String 객체를 생성한다.                  |
| `String(byte[] bytes, int offset, Int length, Charset charset)`    | 지정된 캐릭터 셋을 사용하여 byte 배열의 일부를 디코딩한 String 객체를 생성한다.                                  |
| `String(byte[] bytes, int offset, int length, String charsetName)` | 지정한 이름을 갖는 캐릭터 셋을 사용하여 byte 배열의 일 부를 디코딩한 String 객체를 생성한다.                          |
| `String(char[] value)`                                           | Char 배열의 내용들을 붙여 String 객체를 생성한다.                                                   |
| `String(char[] value, int offset, int count)`                    | char 배열의 일부 내용들을 붙여 String 객체를 생성한다.                                                |
| `String(int[] codePoints, int offset, int count)`                  | 유니코드 코드 위치(Unicode code point)로 구성되어 있는 배열의 일부를 새로운 string 객체를 생성한다.                |
| `String(String original)`                                          | 매개 변수로 넘어온 String과 동일한 값을 갖는 String 객체를 생성한다. 다시 말해서, 복제본을 생성한다.                    |
| `String(StringBuffer buffer)`                                      | 매개 변수로 넘어온 StringBuffer 클래스에 정의되어 있는 문자열의 값과 동일한 String 객체를 생성한다.                   |
| `String(StringBuilder builder)`                                    | 매개 변수로 넘어온 StringBuilder 클래스에 정의되어 있 는 문자열의 값과 동일한 String 객체를 생성한다.                 |

## String 문자열 byte로 변환하기

- `getBytes()` : 기본 캐릭터 셋의 바이트 배열을 생성 / 같은 프로그램내에서 문자열로 바이트 배열을 만들때 사용
- `getBytes(Charset charset)` : 지정한 캐릭터 셋 객체 타입으로 바이트 배열을 생성 / 다른 시스템에서 전달받은 문자열을 변환할때 사용
- `getBytes(String charsetName)` : 지정한 이름의 캐릭터 셋을 갖는 바이트 배열을 생성 / 다른 시스템에서 전달받은 문자열을 변환할때 사용

=> 리턴 타입은 `byte[]` 로 동일

### 참고

- `java.nio.Charset` : 표준 캐릭터 셋이 정해져 있다.
- 브라우저에서 한글이 꺠지는 이유  : 브라우저에 설정된 캐릭터 셋과 웹페이지에 설정된 캐릭터 셋이 다르기 때문.
- UCS : 유니코드 캐릭터 셋(Unicode Character Set)
    - 종류 :  US-ASCII, ISO-8859-1, UTF-8, UTF-16BE, UTF-16LE, UTF-16, EUC-KR, MS949
- 한글을 처리하기 위해 자바에서 가장 많이 사용하는 캐릭터 셋 : UTF-16(예전에는 UTF-8이나 EUC-KR을 많이 사용함.)

```java
public class StringSample {
    public static void main(String[] args) {
        StringSample sample = new StringSample();
        sample.convert();
    }

    public void convert() {
        try {
            String korean = "한글";
            byte[] array1 = korean.getBytes();
            for (byte data : array1) {
                System.out.print(data + " ");   // EUC-KR : -57 -47 -79 -37, UTF-8 : -19 -107 -100 -22 -72 -128
            }
            System.out.printin();
            String korean2 = new String(array1);
            System.out.printin(korean2);        // "한글"
        } catch (Exception e) {
            e.printSackTrace();
        }
    }
}
```

### 한글 깨짐 현상 발생시

```java
public void convertUTF16(){
    try{
        String korean="한글";
        byte[]array1=korean.getBytes("UTF-16");
        printByteArray(array1);      // UTF-16 : -2 -1 -43 92 -82 0
        String korean2=new String(array1);
        System.out.printIn(korean2); // ◆◆◆\◆
    }catch(Exception e){
        e.printStackTrace();
    }
}
```

**`byte` 배열을 생성할 때 사용한 캐릭터 셋**을 **문자열로 다시 전환할 때에도** 동일하게 사용해야만 한다.
따라서 `convertUTF16()` 메소드를 아래와 같이 변경해준다.

```java
public void convertUTF16(){
    try {
        String korean="한글";
        byte[]array1=korean.getBytes("UTF-16");
        printByteArray(array1);      // UTF-16 : -2 -1 -43 92 -82 0
        String korean2=new String(array1,"UTF-16");
        System.out.printIn(korean2); // 한글
    } catch(Exception e){
        e.printStackTrace();
    }
}
```

- `EUC-KR` : 한글 두글자를 표현하기 위해 4바이트를 사용
- `UTF-16` : 한글 두글자를 표현하기 위해 6바이트를 사용
- 글자수 상관없이 무조건 2바이트씩 차이가 난다.

- `UnsupportedEncodingException` : 메소드와 생성자에서 발생가능. 존재하지 않는 캐릭터 셋의 이름을 지정할 경우, 예외가 발생하게 되므로, 반드시 `try-catch`로 감싸주거나 메소드
  선언시 throws 구분을 추가해주어야만 함.

### 객체의 null 체크

- 어떤 참조자료형도 null이 될 수 있다. 이는 객체가 아무런 초기화가 되어있지 않으며, 클래스에 선언되어있는 어떤 메소드도 사용할 수 없다는 것을 의미한다.
- 따라서 널체크 하지 않으면 객체에서 사용할 수 있는 메소드들은 모두 예외를 발생시킴.

```java
public boolean nullCheck2(String text){
    if(text==null){ // null 체크 중요
        return true;
    }else{
        int textLength=text.length();
        System.out.println(textLength);
        return false;
    }
}
```

## String의 내용을 비교하고 검색하는 메소드

- 문자열의 길이를 확인하는 메소드 : length() ,Int, 문자열의 길이를 리턴한다
- 문자열이 비어 있는지 확인하는 메소드 : isEmpty(), boolean, 문자열이 비어 있는지를 확인한다. 비어 있으면 true를 리턴한다.
  즉, 문자열의 길이가 0인것을 의미하며, 문자열의 길이가 0인지 아닌지를 확인하는 것보다, 이 메소드를 사용하는 것이 훨씬 간단한다.

```java
System.out.println("text.isEmpty()="+text.isEmpty());
```

- 문자열이 같은지 비교하는 메소드

| 리턴 타입     | 메소드 이름 및 매개 변수                      | 설명                                                                    |
|-----------|-------------------------------------|-----------------------------------------------------------------------|
| `boolean` | `equals(Object anObject)`             | 문자열 값을 비교할때 사용                                                        |
| `boolean` | `equalsIgnoreCase(String anotherStr)` | 문자열 값을 비교할때 사용(대소문자 구분안함)                                             |
| `int`     | `compareTo(String anotherStr)`        | 정렬할때 사용, 비교하려는 매개변수로 넘겨준 String 각체가 알파벳 순으로 앞에있으면, 양수를, 뒤에있으면, 음수를 리턴 |
| `int`     | `compareToIgnoreCase(String str)`     | 위와 동일 (대소문자 구분안함)                                                     |
| `boolean` | `contentEquals(CharSequence cs)`      | 매개 변수로 넘어오는 `CharSequence` 객체가 String 객체와 같은지를 비교하는 데 사용됨.              |
| `boolean` | `contentEquals(StringBuffer sb)`      | 매개 변수로 넘어오는 `StringBuffer`객체가 String 객체와 같은지를 비교하는 데 사용됨.                                                                      |

1) equals

```java
public void checkCompare(){
    String text="Check value";
    String text2="Check value";
    if（text==text2）{
        System.out.printIn("text==text2 result is same.");
    }else{
        System.out.printIn("text==text2 result is different.");
    }
    if(text.equals("Check value")){
        System.out.printIn("text.equals (text2) result is same.");
    }
}
```

- 결과

```shell
text==text2 result is same. 
text.equals(text2) result is same.
```

- 이유 : 자바에 `Constant Pool`이라는 것이 존재하기 때문이다. 이 풀에 대해서 간단하게 이야기하면, 자바에서는 객채들을 재사용하기 위해서 `Constant Pool`이라는 것이 만들어져
  있고, `String`의 경우 동일한 값을 갖는 객체가 있으면, 이미 만든 객체를 재사용한다. 따라서, text와 text2 객체는 실제로는 같은 객체다.
- `==`:  연산은 기본자료형에서는 값을 비교하지만, 참조자료형에서는 값인 주소값을 비교 하게 된다.

```java
// String text2 = "Check value";
String text2 = new String("Check value");
```

- 위와 같이 String 객체를 생성하면 값이 같은 String객체를 생성한다고 할지라도 Constant Pool의 값을 재활용 하지 않고, 별도의 객체를 생성한다. 따라서 아래와 같이 출력됨.

```shell
text==text2 result is different.
text.equals(text2) result is same.
```

2) compareTo

```java
public void checkCompareTo() {
    String text1="a";
    string text2="b"；
    string text3="c"；
    System.out.println(text2.compareTo(text1)); // 1  a(1) b(0)
    System.out.println(text2.compareTo(text3)); // -1      b(0) c(-1)
    System.out.println(text1.compareTo(text3)); // -2 a(0) b(-1) c(-2)
}
```

- `b`가 `a`보다 뒤에 있으므로, 첫 번째 결과는 양수를 리턴한다. 
- 나머지 `b`와 `c`, `a`와 `c`들 비교한 결과는 각각 음수를 리턴한 것을 볼 수 있다.

```shell
1
-1
-2
```

3) contentEquals

- 매개 변수로 넘어오는 CharSequence와  StringBuffer 객체가 String 객체와 같은지를 비교하는 데 사용됨.

- 특정 조는건에 맞는 문자열이 있는지를 확인하 메소드 :

| 리턴 타입     | 메소드 이름 및 매개 변수                                                                     | 설명                                                                                                                                      |
|-----------|------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `boolean` | `startsWith(String prefix)`                                                          | 해당 문자열이 매개변수로 넘겨준 값으로 시작하는지 확인                                                                                                          |
| `boolean` | `startsWith(String prefix, int toffset)`                                             |                                                                                                                                         |
| `boolean` | `endsWith(String sffix)`                                                             | 해당 문자열이 매개변수로 넘겨준 값으로 끝나는지 확인                                                                                                           |
| `boolean` | `contains(CharSequence s)`                                                           | 해당 문자열이 매개변수로 넘겨준 값을 포함하고 있는지  확인                                                                                                       |
| `boolean` | `matched(String regex)`                                                              | 정규표현식을 매개변수로 받는다. 이메일 점검이나, 웹페이지 URL을 점검하는 등의 작업을 위해 공식에 따라 만든 식. 자바 JDK1.4부터 정규 표현식을 제공. java.util.regex 패키지의 Pattern클래스 API에 있는 내용 확인 |
| `boolean` | `regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)` | 문자열 중에서 특정 영역이 매개 변수로 넘어온 문자열과 동일한지를 확인하는데 사용된다.                                                                                        |
| `boolean` | `regionMatches(int toffset, String other, int ooffset, int len)`                     |                                                                                                                                         |

- startsWith : indexOf()메소드를 통해서도 해당 문자로 문자열이 시작하는지 알수 있으나, indexOf()는 모든 문자열을 다 확인해 봐야한다는 단점이 있다.

```java
package c.string;

public class StringCheck{
    public static void main(String args[]){
        StringCheck sample=new StringCheck();

        String addresses[]=new String[]{
                                "서울시 구로구 신도림동",
                                "경기도 성남시 분당구 정자동 개발 공장",
                                "서울시 구로구 개봉동"
                            };
        
        sample.checkAddress(addresses);
    }

    public void checkAddress(String[] addresses){
        int startCount=0, endCount=0;
        String startText="서울시";
        String endText="동";
        for(String address:addresses){
            if(address.startsWith(startText)){
                startCount++;
            }
            if(address.endsWith(endText)){
                endCount++;
            }
        }
        System.out.println("Starts with " + startText+ " count is  "+ startCount);
        System.out.println("Ends with " + endText+ " count is  "+ endCount);
    }

}
```

- regionMatches

| 매개변수       | 의미                           | 
|------------|------------------------------|
| `ignoreCase` | true일 경우 대소문자 구분을 하지 않고, 값을 비교한다. |
| `toffset`    | 비교 대상 문자열의 확인 시작 위치를 지정한다.   |
| `other`      | 존재하는지를 확인할 문자열을 의미한다.        |
| `ooffset`    | other 객체의 확인 시작 위치를 지정한다.    |
| `len`        | 비교할 char의 개수를 지정한다.          |

```java
public void checkMatch(){
    String text = "This is a text";
    String compare1 = "is";
    String compare2 = "this"；
    System.out.printin(text.regionMatches(2, compare1, 0, 1));       // 매개변수가 4개인 메소드 => true 
    System.out.printIn(text.regionMatches(5, compare1, 9, 2));       // 매개변수가 4개인 메소드 => true
    System.out.printIn(text.regionMatches(true, e, Compare2, 0, 4)); // 매개변수가 5개인 메소드 => true
}
```

    - 매개변수에 따라 결과가 무조건 false가 나오는 경우
        - toffset이 음수일 때 
        - ooffset이 음수일 때 
        - toffset + len이 비교 대상의 길이보다 클 때 
        - ooffset + len이 other 객체의 길이보다 클 때 
        - ignoreCase가 false인 경우에는 비교 범위의 문자들 중 같은 위치(index)에 있는 char가 다를 때
        - ignoreCase가 true인 경우에는 비교 범위의 문자들을 모두 소문자로 변경한 후 같은 위치(index)에 있는 char가 달라야한다.


### String에서 특정 문자의 위치 찾기

| 리턴타입 | 메소드 이름 및 매개 변수                         | 설명              |
|------|----------------------------------------|-----------------|
| `int`    | `indexOf(int ch)`                        |                 |
| `int`    | `indexOf(int ch, int fromIndex)`         |                 |
| `int`    | `indexOf(String str)`                    |                 |
| `int`    | `indexOf(String str, int fromIndex)`     |                 |
| `int`    | `lastIndexOf(int ch)`                    | 문자열의 가장 뒤쪽부터 검색 |
| `int`    | `lastIndexOf(int ch, int fromIndex)`     |                 |
| `int`    | `lastIndexOf(String str)`                |                 |
| `int`    | `lastIndexOf(String str, int fromIndex)` |                 |

- indexOf()

```java
public void checkIndexOf(){
    String text="Java technology is both a programming language and a platform.";
    System.out.println(text.index0f('a')); // char를 넘겨주면 숫자로 자동으로 형변환이 일어난다.
    System.out.println(text.index0f("a "));
    System.out.println(text.index0f('a',20));  // text 문자열의 20번째 자리부터 값을 확인
    System.out.println(text.index0f("a ",20)); // text 문자열의 20번째 자리부터 값을 확인
    System.out.println(text.index0f('z')); // 문장에 없는 값을 찾을 경우 -1을 리턴
}
```

- 출력 결과

```shell
1
3
24
24
-1
```

- lastIndexOf()

```java
public void checkLastIndexOf (){
    String text="Java technology is both a programming language and a platform.";
    System.out.println(text.lastIndex0f('a'));
    System.out.println(text.lastIndex0f("a "));
    System out.println(text.lastIndexOf('a',20));
    System.out.println(text.lastIndexOf("a ",20));
    System.out.println(text.lastIndex0f('z'));
}
```

- 출력 결과

```shell
55
51
3
3
-1
```


### String의 값의 일부를 추출하기 위한 메소드들

| 리턴 타입 | 메소드 이름 및 매개 변수                                               | 설명                                                                                       |
|-------|--------------------------------------------------------------|------------------------------------------------------------------------------------------|
| `char`  | `charAt(int index)`                                            | 특정 위치의 char값을 리턴한다.                                                                      |
| `void`  | `getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)` | 매개 변수로 넘어온 dst라는 char배열 내에 srcBegin에서 srcEnd에 있는 char를 저장한다. 이때 dst배열의 시작위치는 dstBegin이다. |
| `int`   | `codePointAt(int index)`                                       | 특정위치의 유니코드 값을 리턴한다. 리턴 타입은 int지만, 이 값을 char로 형 변환하면 char 값을 출력할 수 있다.                    |
| `int`   | `codePointBefore(int index)`                                   | 특정 위치 앞에 있는 char의 유니코드 값을 리턴한다. 리턴 타입은 int지만, 이 값을 char로 형 변환하면  char 값을 출력할 수 있다.       |
| `int`   | `codePointCount(int beginIndex, int endIndex)`                 | 지정한 범위에 있는 유니코드 개수를 리턴한다.                                                                |
| `int`   | `offsetByCodePoints(int index, int codePointOffset)`           | 지정된 index부터 오프셋(offset)이 설정된 인덱스를 리턴한다.                                                  |


### char 배열의 값을 String으로 변환하는 메소드

| 리턴 타입         | 메소드 이름 및 매개 변수                                  | 설명                                                                 |
|---------------|-------------------------------------------------|--------------------------------------------------------------------|
| `static String` | `copyValueOf(char[] data)`                        | char배열에 있는 값을 문자열로 변환한다.                                           |
| `static String` | `copyValueOf(char[] data, int offset, int count)` | char배열에 있는 값을 문자열로 변환한다. 단 offset위치 부터 count 까지의 개수만큼만 문자열로 변환한다.) |

```java
char values[] = new char[]{'J', 'a', 'v', 'a'};
String javaText = String.copyValuOf(values);
```

### String의 값을 char 배열로 변환하는 메소드

| 리턴 타입  | 메소드 이름 및 매개 변수 | 설명                     |
|--------|----------------|------------------------|
| char[] | toCharArray()  | 문자열을 char 배열로 변환하는 메소드 |

- Java 8까지의 String 클래스에서는 String 문자열의 값을 char 배열로 저장해 왔다. 하지만, 성능과 메모리 활용성을 고려하여 Java 9부터는 byte 배열로 내부적으로 저장한다.


### 문자열의 일부 값을 자라내는 메소드

| 리턴 타입        | 메소드 이름 및 매개 변수                            | 설명                                                          |
|--------------|-------------------------------------------|-------------------------------------------------------------|
| `String`       | `substring(int beginIndex)`                 | beginIndex부터 끝까지 대상 문자열을 잘라 String으로 리턴한다.                  |
| `String`       | `substring(int beginIndex, int endIndex)`   | beginIndex부터 endIndex까지 대상 문자열을 잘라 String으로 리턴한다.           |
| `CharSequence` | `subSequence(int beginIndex, int endIndex)` | beginIndex부터 endIndex 까지 대상 문자열을 잘라 CharSequence 타입으로 리턴한다. |

```java
public void checkSubstring() {
    String text="java technology";
    String technology=text.substring(5);
    System.out.println(technology);
    String tech=text.substring(5, 9);
    System.out.println(tech);
}
```

- 출력 결과

```shell
technology
tech
```

### 문자열을 여러개의 String 배열로 나누는 split 메소드

| 리턴 타입    | 메소드 이름 및 매개 변수                 | 설명                                                                                 |
|----------|--------------------------------|------------------------------------------------------------------------------------|
| `String[]` | `spilt(String regex)`            | regex에 있는 정규 표현식에 맞추어 문자열을 잘라 String 의 배열로 리턴한다.                                   |
| `String[]` | `spilt(String regex, int limit)` | regex에 있는 정규 표현식에 맞추어 문자열을 잘라 String의 배열로 리턴한다.이때 String 배열의 크기는 limit 보다 커서는 안된다. |

```java
public void checksplit() {
    String text = "Java technology is both a programming language and a platform.";
    String[] splitArray=text.split(" ");
    for (String temp: splitArray){
        System.out.println(temp);
    }
}
```

- 출력 결과

```shell
Java
technology
is
both
a
programming
language
and
a
platform.
```

### String 값을 바꾸는 메소드들도 있어요

- 문자열을 합치는 메소드와 공백을 없애는 메소드

| 리턴 타입    | 메소드 이름 및 매개 변수     | 설명                                                   |
|----------|--------------------|------------------------------------------------------|
| `String` | `concat(String str)` | 매개 변수로 받은 str을 기존 문자열의 우측에 붙인 새로운 문자열 객체를 생성하여 리턴한다. |
| `String` | `trim()`             | 문자열의 `맨 앞과 맨 뒤에 있는 공백`들을 제거한 문자열 객체를 리턴한다. 작업하려는 문자열이 공백만으로 이루어진 값인지, 아니면 공백을 제외한 값이 있는지 확인하기에 매우 편리하다.          |

```java
public void checkTrim() {
    String strings[]=new String[]{
        " a", " b ", "   c", "d    ", "e   f"， "   "
    };
    for(String string:strings) {
        System.out.println("["+string+"] ");
        System.out.println("["+string.trim()+"] ");
    }
}

public void checkTrim2() {
    String text=" a ";
    if(text!=null && text.trim().length() > 0) {
        System. out. printIn("OK");
    }
}
```


- 출력결과

```shell
[ a]
[a]
[ b ]
[b]
[    c]
[c]
[d    ]
[d]
[e   f]
[e   f]
[   ]
[]
```

- 내용을 교체(replace)하는 메소드
  - 대소를 구분한다.

| 리턴 타입    | 메소드 이름 및 매개 변수                                         | 설명                                                              |
|----------|--------------------------------------------------------|-----------------------------------------------------------------|
| `String` | `replace(char oldChar, char newChar)`                    | 해당 문자열에 있는 oldchar의 값을 newchar로 대치한다.                  |
| `String` | `replace(CharSequence target, CharSequence replacement)` | 해당 문자열에 있는 target과 같은 값을 replacement로 대치한다.                     |
| `String` | `replaceAll(String regex, String replacement)`           | 해당 문자열의 내용 중 regex에 표현된 정규표현식에 포함되는 모든 내용을 replacement로 대치한다.   |
| `String` | `replaceFirst(String regex, String replacement)`         | 해당 문자열의 내용 중 regex에 표현된 정규표현식에 포함되는 첫번째 내용을 replacement로 대치 한다. |

```java
public void checkReplace () {
    String text="The String class represents character strings."; 
    System.out.println(text.replace('s', 'z'));
    System.out.println(text);
    System.out.println(text.replace("tring", "trike"));
    System.out.println(text.replaceAll(" ", "|"));
    System.out.println(text.replaceFirst(" ", "|"));
}
```

- 출력 결과

```shell
The String clazz reprezentz character tringz.
The String class represents character strings.
The Strike class represents character strikes.
The|String|class|represents|character|strings.
The|String class represents character strings.
```

- 특정 형식에 맞춰 값을 치환하는 메소드
 - format() 메소드는 정해진 기준에 맞춘 문자열이 있으면, 그 기준에 있는 내용을 변환한다. 자바에서 `%s`는 String을, `%d`는 정수형을, `%f`는 소수점이 있는 숫자, `%%`는 %를 의미한다.


| 리턴 타입      | 메소드 이름 및 매개 변수                                         | 설명                                                              |
|---------------|--------------------------------------------------------|-----------------------------------------------------------------|
| `static String` | `format(String format, Object... args)`                 |  format에 있는 문자열의 내용 중 변환해야 하는 부 분을 args의 내용으로 변경한다.       |
| `static String` | `format(Locale 1, String format, Object... args)`       |  format에 있는 문자열의 내용 중 변환해야 하는 부 분을 args의 내용으로 변경한다. 단 첫 매개 변수인 Locale 타입의 1에 선언된 지역에 맞추어 출력한다.   |

> Locale은 지역적으로 다른 표현 형식을 제공하기 위한 것이다. 
> 보통 Locale을 지정하지 않으면 기본적으로 자바 프로그램이 수행되는 OS의 지역 정보를 기본으로 따른다.

```java
public void checkFormat() {
    String text="제 이름은 %s입니다. 지금까지 %d권의 책을 썼고, " 
        + "하루에 %f %%의 시간을 책을 쓰는데 할애하고 있습니다.";
    String realText=String.format(text, "Melody", 7, 10.5); // 치환될 문자의 갯수를 맞출 것
    // String realText=String.format(text, "Melody", 7);
    System.out.println(realText); // System.out.fomat() 이라는 메소드도 있음.
}
```

- 출력 결과

```shell
제 이름은 Melody입니다. 지금까지 1편의 책을 썻고, 하루에 10.5000000 %의 시간을 책을 쓰는데 할여하고 있습니다.
```

- 대소문자를 바꾸는 메소드

| 리턴 타입      | 메소드 이름 및 매개 변수             | 설명                                    |
|---------------|----------------------------|---------------------------------------|
| `String` | `toLowerCase()`              | 모든 문자열의 내용을 소문자로 변경한다.                |
| `String` | `toLowerCase(Locale locale)` | 지정한 지역 정보에 맞추어 모든 문자열의 내용을 소문자로 변경한다. |
| `String` | `toUpperCase()`              | 모든 문자열의 내용을 대문자로 변경한다.                |
| `String` | `toUpperCase(Local local)`   | 지정한 지역 정보에 맞추어 모든 문자열의 내용을 대문자로 변경한다. |

- 기본 자료형을 문자열로 변환하는 메소드 먼저 가장 앞에 있는 분류의 메소드를 살펴보자.
  - byte1처럼 변환하나, byte2처럼 변환하나 출력해 보면 동일한 값이 출력된다. 
  - 다시 말해 대부분 기본 자료형을 String 타입으로 변환할 필요가 있을 때에는 String과 합치는 과정을 거친다. 그럴 경우에는 별도로 valueOf() 메소드를 사용할 필요까지는 없다. 
  - 하지만, String으로 변환만 해놓고 별도의 문자열과 합치는 과정이 없을 경우에는 valueOf() 메소드를 사용하는 것을 권장한다.

```java
byte b = 1;
String byte1 = String.valueOf(b);
String byte2 = b + ""; // 다른 문자열과 합치는 과정에서 형변환이 일어남.
```

| 리턴 타입         | 메소드 이름 및 매개 변수                              | 설명                                          |
|---------------|---------------------------------------------|---------------------------------------------|
| `static String` | `valueOf(boolean b)`                          |                                             |
| `static String` | `valueOf(char c)`                             |                                             |
| `static String` | `valueOf(char[] data)`                        |                                             |
| `static String` | `valueOf(char[] data, int offset, int count)` |                                             |
| `static String` | `valueOf(double d)`                           |                                             |
| `static String` | `valueOf(float f)`                            |                                             |
| `static String` | `valueOf(int i)`                              |                                             |
| `static String` | `valueOf(long l)`                             |                                             |
| `static String` | `valueOf(Object obj)`                         | null객체가 매개변수로 들어오는 경우 NullPointerException이 발생하지 않고, null을 리턴헤준다. | 



### intern() : 절대로 사용하면 안되는 메소드

```java
public void interncheck() {
    String text1 = "Java Basic"；
    String text2 = "Java Basic"；
    String text3 = new String("Java Basic");
    System.out.println(text1==text2);        // true
    System.out.println(text1==text3);        // false
    System.out.println(text1.equals(text3)); // true
}
```

```java
public void interncheck() {
    String text1 = "Java Basic"；
    String text2 = "Java Basic"；
    String text3 = new String("Java Basic");
    text3=text3.intern();
    System.out.println(text1==text2);        // true
    System.out.println(text1==text3);        // true
    System.out.println(text1.equals(text3)); // true
}
```

- 역할 : `new String(String)`으로 생성한 문자열 객체라고 할지라도, 풀(pool)에 해당 값이 있으면, 풀에 있는 값을 참조하는 객체를 리턴한다.
- 만약 동일한 문자열이 존재하지 않으면 풀에 해당 값을 추가한다.
    - 만약 새로운 문자 열을 쉴새 없이 만드는 프로그램에서 `intern()` 메소드를 사용하여 억지로 문자열 풀에 값을 할 당하도록 만들면, 저장되는 영역은 한계가 있기 때문에 그 영역에 대해서 별도로 메모리를 청소하는 단계를 거치게 된다. 따라서, 작은 연산 하나를 빠르게 하기 위해서 전체 자바 시스템의 성능에 악영향을 주게 된다.
- `intern()` 메소드를 수행한 뒤에 문자열은 equals() 메소드가 아닌, ==으로 동일한지 비교할 수가 있다.(실 주소값을 반영)


### immnutable한 String의 단점을 보완하는 클래스에는 `StringBuffer`와 `StringBuilder`가 있다

- String 객체는 변하지 않는다. 만약 String 문자열을 더하면 새로운 String 객체가 생성되고, 기존 액체는 버려진다.
- 그러므로, 계속 하나의 String을 만들어 계속 더하는 작업을 한다면, 계속 쓰레기를 만들게 된다.

```java
string text = "Hello";
text = text + " world";
```

- Hello라는 단어를 갖고 있는 객체는 더 이상 사용할 수 없다. 즉, 쓰레기가 되며, 나중에 `GC`(Garbage Collection(가비지 컬렉션))의 대상이 된다.
- String 클래스의 단점을 보완하기 위해서 나온 클래스가 `StringBuffer`와 `StringBuilder`이다.
- 두 클래스에서 제공하는 메소드는 동일하다. 하지만, `StringBuffer`는 `Thread safe`하다고 하며, `StringBuilder`는 `Thread safe`하지 않다고 한다.
- 기능은 같지만 `StringBuffer`가 `StringBuilder` 보다 더 안전하다고만 기억해 두기 바란다.
- 속도는 `Thread safe`하지 않는 `StringBuilder`클래스가 더 빠르다.
- `StringBuffer`와 `StringBuilder` 클래스는 문자열을 더하더라도 새로운 객체를 생성하지 않는다. 
- `더하기(+)` 기호가 아닌, `append() 메소드`를 사용하여 매개 변수로 모든 기본 자료형과 참조 자료형을 모두 포함한다. 따라서, 어떤 값이라도 이 메소드의 매개 변수로 들 어갈 수 있다.

```java
StringBuilder sb=new StringBuilder();
sb.append("Hello");
sb.append(" world");
```
- 메소드를 붙여 사용해도 무방하다. `append()`를 수행한 후 `StringBuilder`객체가 리턴되기 때문.

```java
StringBuilder sb=new StringBuilder();
sb.append ("Hello").append(" world");
```

- JDK 5 이상에서는 String의 더하기 연산을 할 경우, 컴파일할 때 자동으로 해당 연산을 `StringBuilder`로 변환해준다.
따라서, 일일히 더하는 작업을 변환해 줄 필요는 없으나, for루프와 같이 반복연산을 할떄에는 자동으로 변환을 해주지 않으므로, 꼭 필요하다.


### `String`과 `StringBuilder`, `StringBuffer` 클래스의 공통점
1. 문자열을 다룬다.
2. Charsequence 인터페이스를 구현했다.
3. 세 가지 중 하나의 클래스를 사용하여 매개 변수로 받는 작업을 할 때 `String`이나 `StringBuilder` 타입으로 받는 것보다는 `Charsequence` 타입으로 받는 것이 좋다.
- 언제 `StringBuilder`를 사용하고, 언제 `StringBuffer` 클래스를 사용해야 할까?
    - 일반적으로 하나의 메소드 내에서 문자열을 생성하여 더할 경우에는 `StringBuilder`를 사용해도 전혀 상관없다.
    - 그런데, 어떤 클래스에 문자열을 생성하여 더하기 위한 문자열을 처리하기 위한 인스턴스 변수가 선언되었고, 여러 쓰레드에서 이 변수를 동시에 접근하는 일이 있을 경우에는 반드시 `StringBuffer`를 사용해야만 한다



## 정리
- `String` 클래스를 잘 사용해야만 메모리를 효율적으로 사용할 수 있다.
- 여러 `String`을 더하는 연산이 존재할 경우에는 `StringBuilder`나 `StringBuffer` 클래스를 적절하게 선택하여 활용해야만 한다.