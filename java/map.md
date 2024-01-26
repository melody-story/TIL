# Map

- 자바에서 Map은 키(Key)와 값(Value0로 이루어져있다.
- Map은 키와 값이 1:1로 저장된다.
- 키는 중복되지 않는다.
- 키가 다르고 값이 동일한 경우, Map에서는 다른 것으로 간주한다.
- java.util.Map

### Map의 특징

1) 모든 데이터는 키와 값이 존재
2) 키가 없이 값만 저장은 불가능하다.
3) 값 없이 키만 있을 수는 없다.
4) 키는 고유하다.
5) 값은 Map에서 중복이 가능하다.

## Map의 메소드

| 리턴 타입               | 메소드 이름 및 매개 변수                          | 설명                                                 |
|---------------------|-----------------------------------------|----------------------------------------------------|
| V                   | `put(K key, V value)`                   | 첫 번째 매개 변수인 키를 갖는, 두 번째 매개 변수인 값을 갖는 데이터를 저장한다.    |
| void                | putAll(Map<? extends K, ? extends V> m) | 매개 변수로 넘어온 `Map`의 모든 데이터를 저장한다.                    |
| V                   | `get(Object key)`                       | 매개 변수로 넘어온 키에 해당하는 값을 넘겨준다.                        |
| V                   | `remove(Object key)`                    | 매개 변수로 넘어온 키에 해당하는 값을 넘겨주며, 해당 키와 값은 `Map`에서 삭제한다. |
| Set<K>              | keySet()                                | 키의 목록을 `Set` 타입으로 리턴한다.                            |
| Collection<V>       | values()                                | 값의 목록을 `Collection` 타입으로 리턴한다.                     |
| Set<Map.Entry<K,V>> | entryset()                              | Map 안에 Entry라는 타입의 Set을 리턴한다.                      |
| int                 | size()                                  | Map의 크기를 리턴한다.                                     |
| void                | clear()                                 | Map의 내용을 지운다.                                      |

## Map을 구현한 클래스

1. Hash Map
2. TreeMap
3. LinkedHashMap
4. HashTable

### `Map` 인터페이스와 `HashTable` 클래스의 차이

- `HashTable`은 `JDK 1.0`부터 있었으나, `JDK 1.2`부터 `Collection`이 생기면서 `Map`을 구현하게 되었다.
- Map은 `Collection View`를 사용하지만, HashTable은 `Enumeration` 객체를 통해서 데이터를 처리한다.
- Map은 `키`, `값`, `키-값` 쌍으로 `데이터를 순환`하여 처리할 수 있지만, HashTable은 이 중에서 `키-값`쌍으로 데이터를 순환하여 처리할 수 있다.
- Map은 `이터레이션`을 처리하는 도중에 `데이터를 삭제`하는 안전한 방법을 제공하지만, HashTable은 그렇나 기능을 제공하지 않는다.

| 기능                | HashMap | HashTable | 
|-------------------|---------|-----------|
| 키나 값에 nu 저장 가능 여부 | 가능      | 불가능       |
| 여러 쓰레드 안전 여부      | 불가능     | 가능        |

### HashTable 이외 클래스의 동시성 문제 해결방법

- JDK 1.0 부터 제공되는 `HashTable` 나 `Vector` 등은 쓰레드에 안정하다. 하지만 `Collection`의 클래스들은 따로 처리를 해야한다.

1. `synchronizedMap()` 메소드 사용

```java
Map m = Collections.synchronizedMap(new HashMap(...));
```

2. java.util.concurrent 패키지 내의 `ConcurrentHashMap`, `CopyOnWriteArrayList` 등 클래스 사용

--- 

## HashMap 클래스

- 상속관계

```java
java.lang.Object
    Ljava.util.AbstractMap<K,V>
        Ljava.util.HashMap<K,V>
```

- 구현한 인터페이스

| 인터페이스        | 용도                                                              |
|--------------|-----------------------------------------------------------------|
| Serializable | 원격으로 객체를 전송하거나, 파일에 저장할 수 있음을 지정                                |                                  
| Cloneable    | Object 클래스의 clone() 메소드가 제대로 수행될 수 있음을 지정 즉, 복제가 가능한 객체임을 의미한다. |   
| Мар<E>       | 맵의 기본 메소드 지정                                                    |


 - 4개의 생성자
    - 데이터의 크기가 클 경우는 초기값을 지정할 필요가 있다.
   
| 생성자                                            | 용도                                                          |
|------------------------------------------------|-------------------------------------------------------------|
| HashMap()                                      | `16개`의 저장 공간을 갖는 `HashMap` 객체를 생성한다.                           |
| HashMap(int initialCapacity)                   | 매개 변수만큼의 저장 공간을 갖는 `HashMap` 객체를 생성한다.                       | 
| HashMap(int initialCapacity, float loadFactor) | 첫 매개 변수의 저장 공간을 갖고, 두 번째 매개 변수의 로드 팩터를 갖는 HashMap 객체를 생성한다. | 
| HashMap(Map<? extends K, ? extends V> m)       | 매개 변수로 넘어온 Map을 구현한 객체에 있는 데이터를 갖는 `HashMap` 객체를 생성한다.       |


### Hashap의 키

- 기본 자료형과 참조 자료형 모두 될 수 있다.
- 보통은 int나 long과 같은 숫자나 String 클래스를 키로 많이 사용한다.
- 클래스를 사용하는 경우 Object 클래스의 `hashCode()` 메소드와 `equals()` 메소드를 잘 구현해 놓아야만 한다.
  - 객체를 Map에 넣으면, 객체의 `hashCode() % capacity` 결과값을 index로 하여 버켓이라는 List 형태의 목록이 만들어진다.
  - 이 값이 index가 되면 값이 동일하다면 해당 기존 객체의 앞의 노드에 값이 저장된다.
  - get()메소드를 호 출할때에도 `hashCode() % capacity` 결과값이 같다면, `equals()` 메소드를 호출하여 리스트의 처음부터 모든 노드들의 값을 해당 키의 값과 일치 하는지를 비교한다.
  - 기본으로 capacity는 16, loadfactor는 0.75로 정해져 있으며, loadfactor를 늘리면, capacity는 줄어들어 차지하는 메모리가 적은대신 속도가 느려지게된다.
  - 반대로 loadfactor를 줄이면, 차지하는 메모리가 많아지는 대신 속도가 빨라지게 된다.
  - 따라서 100, 1000의 적은 양의 갯수는 무관하나, 많은 양의 데이터를 저장할 때에는 Map 객체 생성시 대략적인 메모리의 양과 loadfactor를 정하여 map을 생성하는게 좋다.
  - 메모리 확장하는데에 소요되는 비용을 줄일 수 있다.


### HashMap의 메소드

- HashMap에 있는 주요 메소드는 대부분 Map 인터페이스에 정의되어 있다.
- map에 들어있지 않은 키를 get() 메소드로 조회하면 null을 리턴한다.
- 따라서 해당 키가 map에 있는지를 여부를 알고자 한다면, containsKey() 메소드를 사용하자.
- 이미 존재하고 있는 키를 사용하여 put(키, 값)으로 값을 넣는다면, 기존 값이 대체된다.

```java
package d.collection;

import java.util.HashMap;

public class MapSample {
    public static void main(String[] args) {
        MapSample sample=new MapSample();
        sample. checkHashMap ();
    }

    public void checkHashMap() {
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("A","a");
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
    }
}
```

- 결과

```shell
a
nul
```


### 객체를 확인하는 다른 방법

1. keySet()

```java
import java.util.Set;

public void checkKeySet () {
    HashMap<String,String> map=new HashMap<String,String>();
    map.put("A", "a");
    map.put("C", "c");
    map.put("D", "d");
    Set<String> keySet=map.keySet();
    for(String tempKey:keyset) {
        System.out.println(tempKey + "=" + map.get(tempKey));
    }
}
```

2. values()

```java
import java.util.Collection;


public void checkValues() {
    HashMap<String,String> map = new HashMap<String,String>();
    map.put("A","a");
    map.put("C", "c");
    map.put("D", "d");
    Collection<String> values = map.values();
    for (String tempValue:values) {
        System.out.println(tempValue);
    }
}
```

3. entrySet()

- `Map.Entry` 타입을 리턴
- Entry에는 단 하나의 키와 값만이 저장된다. 
- 따라서, getKey()와 getValue()라는 메소드를 사용하면 키와 값을 간단하게 가져올 수 있다.

```java
import java.util.Map.Entry;
import java.util.Set;

public void checkHashMapEntry() {
    HashMap<String,String> map=new HashMap<String,String>();
    map.put("A", "a");
    map.put("B", "b");
    map.put("C", "c");
    map.put("D", "d");
    Set<Map.Entry<String, String>> entries = map.entrySet();
    for (Map.Entry<String, String> tempEntry:entries) {
        System.out.println(tempEntry.getKey() + "=" + tempEntry.getValue());
    }
}
```


4. containsKey(), containsValue()

- Boolean타입 리턴

```java
public void checkContains() {
    HashMap<String,String> map = new HashMap<String,String>();
    map.put("A","a");
    map.put("8", "6");
    map.put("C", "C");
    map.put("D", "d");
    System.out.println(map.containsKey("A"));
    System.out.println(map.containsKey("7"));
    System.out.println(map.containsValue("a"));
    System.out.println(map.containsValue("z"));
}
```


5. remove("키")

- 키에 해당하는 값을 리턴해준다.

6. size()

- map에 들어있는 데이터 갯수 확인

---

## TreeMap

- 정렬된 키 목록을 구할때, 
    - HashMap의 keySet() 메소드를 가져와 ArrayList에 담아 정렬을 해도되나, 이경우 불필요한 객체를 생성하게 된다.
- 데이터를 `저장`하면서, `정렬`을 `동시`에 해준다.
- 정렬기준은 객체의 키의 타입에 따라 다르다.
- 일반적으로 `String` 타입의 키는 `숫자 > 대문자 > 소문자 > 한글` 순으로 정렬이 된다.
- 정렬을 하기때문에 속도가 느리다는 단점이 있다. 따라서 100~1000 건의 적은 데이터양에서만 유리하다.

- 정렬이 가능한 이유는 SortedMap 인터페이스를 구현했기 때문이다.

### TreeMap의 메소드

1. firstKey() : 가장 앞의 키
2. lastKey() : 가장 뒤의 키
3. higherKey() : 특정 키보다 뒤의 키
4. lowerKey() : 특정 키보다 앞의 키

--- 

## Map을 구현한 Properties 클래스

- Map을 구현한 Hashtable을 확장(extends)하였다.
- System 클래스의 getProperties()라는 static 메소드를 통해 우리는 시스템 정보를 얻어올 수 있다.
- 이때, 리턴되는 값의 타입이 Properties이다.

`Properties prop=System getProperties();`
- Map을 구현했기 때문에, Map의 메소드를 사용할 수 있다.


```java
public void checkProperties() {
    Properties prop = System getProperties();
    Set<Object> keySet = prop.keySet();
    for (Object tempobject: keyset) {
        System.out.println(tempObject + "=" + prop.get(tempobject));
    }
}
```

  - 결과

      ```java
      java.runtime.name=Java (M) SE Runtime Environment sun.boot.library.path=C:\jdk1.X.0_XX jre \bin
      java.vm.version=XX.XX-b15
      java.vm.vendor=Oracle Corporation java.vendor.url=http://java.oracle.com/
      path.separator=;
      // 이하 생략
      ``` 

### 참고 System에서 제공하는 속성들

| 속성                      | 설명                  |
|-------------------------|---------------------|
| user.language           | 사용자의 사용 언어          |
| user.dir                | 현재 사용중인 기본 디렉터리     |   
| user.home               | 사용자 계정의 홈 디렉터리      |    
| java.io.tmpdir          | 자바에서 사용하는 임시 디렉터리   | 
| file.encoding           | 파일의 기본 인코딩          |        
| sun.io.unicode.encoding | 유니코드 인코딩            |          
| path.separator          | 경로 구분자              |
| fle.separator           | 파일 구분자 |
| line.separator          | 줄(line) 구분자 |

### Hashtable이나 HashMap에 있는 속성을 사용하지 않고 Properties을 사용하는 이유

- Properties 클래스에서 추가로 제공하는 메소드들 이밖에도 다양
  - `comments`라고 되어 있는 매개 변수들은 저장되는 속성 파일에 주석으로 저장된다.
  
| 리턴 타입  | 메소드 이름 및 매개 변수                                               | 설명                      |
|--------|--------------------------------------------------------------|-------------------------|
| void   | load(Inputstream instream)                                   | 파일에서 속성을 읽는다.           | |
| void   | load(Reader reader)                                          | 파일에서 속성을 읽는다.           |
| void   | loadFromXML(InputStream in)                                  | XML로 되어 있는 속성을 읽는다.     |
| void   | store(OutputStream out, String comments)                     | 파일에 속성을 저장한다.           |
| void   | store(Writer writer, String comments)                        | 파일에 속성을 저장한다.           |
| void   | storeToXL(OutputStream os, String comment)                   | XML로 구성되는 속성 파일을 생성 한다. |
| void   | storeToXML(OutputStream os, String comment, String encoding) | XML로 구성되는 속성 파일을 생성 한다. |

- 파일을 읽고 쓰는데에 사용
    - 파일은 기본적으로 시스템 속성중 `user.dir` 에 지정되어있는 경로에 저장된다.

#### properties 파일의 예

```java
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream;

public void saveAndLoadProperties() {
    try {
        String fileName = "test.properties";
        File propertiesFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(propertiesFile);
        Properties prop = new Properties();
        prop.setProperty("Writer","Sangmin, Lee");
        prop.setProperty("WriterHome", "http://www.GodOfJava.com");
        prop.store(fos, "Basic Properties file."); 
        fos.close();
        
        FileInputStream fis = new FileInputStream(propertiesFile);
        Properties propLoaded = new Properties();
        propLoaded.load(fis);
        fis.close();
        System.out.println(propLoaded);
    } catch (Exception e){
        e.printStackTrace();
    }
}
```
- 출력 결과
  - Hashtable에 tostring() 메소드가 구현되어 있기 때문에 가능 
```shell
{WriterHome=http://www.God0fJava.com, Writer=Sangmin, Lee}
```

- 파일 내부
```shell
#Basic Properties file.
#Wed XXX XX 09:40:31 KST 201X
WriterHome=http\://www.GodOfJava.com
Writer=Sangmin, Lee
```


#### XML파일의 예

> - XML 이란?
>   - eXtensible Markup Language에서 XML을 따온 약어다. 자주 접하는 HTML도 이 XML의 일종
>   - 즉, 태그로 구성되어 있는 텍스트 문서를 의미한다.


```java
public void saveAndLoadpropertiesXML() {
    try {
        String fileName = "text.xml";
        File propertiesFile = new File(fileName);
        FileOutputStream fos-new FileOutputStream(propertiesFile);
        Properties prop = new Properties();
        prop.setProperty("Writer", "Sangmin, Lee");
        prop.setProperty("WriterHome", "http://www.GodOfJava.com");
        prop.storeToXML(fos, "Basic XML Property file."); 
        fos.close();
        FileInputStream fis = new FileInputStream(propertiesFile);
        Properties propLoaded = new Properties(); 
        propLoaded.loadFromXML(fis);
        System.out.println(propLoaded);
        fis.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
}
```
- 파일 내부
```shell
‹?xml version="1.0"' encoding="UTF-8" standalone="no"?›
‹!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
‹properties>
<comment>Basic XML Property file.</comment>
‹entry key= "WriterHome">http: //www.GodofJava.com/entry>
<entry key="Writer">Sangmin, Lee</entry> </properties>
```