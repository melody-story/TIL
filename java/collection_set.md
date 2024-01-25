# Set

- 순서가 없다.
- 중복된 데이터가 없다.

## 자바에서 Set 인터페이스를 구현한 주요 클래스

1. `HashSet`
- 순서가 전혀 필요 없는 데이터를 해시 테이블`hash table`에 저장한다. 
- Set 중에 `가장 성능이 좋다.`
2. `TreeSet`
- 저장된 데이터의 `값에 따라서 정렬`되는 셋이다. 
- `red-black`이라는 트리tree 타입으로 값이 저장된다. 
- HashSet 보다 약간 성능이 느리다.
3. `LinkedHashSet` 
- `연결된 목록 타입`으로 구현된 해시 테이블에 데이터를 저장한다.
- `저장된 순서에 따라서 값이 정렬`된다. 대신 성능이 이 셋 중에서 가장 나쁘다.

>이러한 성능 차이가 발생하는 이유는 데이터 정렬 때문이다.
>red-black 트리는 각 노드의 색을 붉은 색 혹은 검은색으로 구분하여 데이터를 빠르고, 쉽게 찾을 수 있는 구조의 이진 트리를 말한다.


## `HashSet`

``
java.lang.Object
    Ljava.util.AbstractCollection<E>
        Ljava.util.AbstractSet<E> 
            Ljava.util.HashSet<E>
``

### `AbstractSet`
- 데이터의 중복을 허용하지 않으므로, 데이터가 같은지를 확인하는 작업이 Set의 핵심이다.
`equals()`, `hashcode()` 메소드의 구현은 매우 중요하다.
- removeAll() : 컬렉션을 매개변수로 받아, 매개 변수 컬렉션에 포함된 모든 데이터를 지울 때 사용.



### HashSet이 구현한 모든 인터페이스

- Serializable, Cloneable, Iterable<E>, Collection<E>, Set<E>

| 인터페이스         | 용도                                                               |
|---------------|------------------------------------------------------------------|
| Serializable  | 원격으로 객체를 전송하거나, 파일에 저장할 수 있음을 지정                                 |   
| Cloneable     | Object 클래스의 clone() 메소드가 제대로 수행될 수 있음을 지정. 즉, 복제가 가능한 객체임을 의미한다. |
| Iterable<E>   | 객체가 `foreach` 문장을 사용할 수 있음을 지정                                   |
| Collection<E> | 여러 개의 객체를 하나의 객체에 담아 처리할 때의 메소드 지정                               |
| Set<E>        | 셋 데이터를 처리하는 것과 관련된 메소드 지정                                        |

>- List에는 있지만, Set에는 없는 메소드
>set은 순서가 없기 떄문에, 인덱스를 필요로하는 get(int index), indexOf(Object o)와 같은 메소드들은 Set에 존재하지 않는다.


### HashSet의 생성자는 4개

- 배열처럼 사용하지만 대괄호는 사용하지 않고, 메소드를 통해서 객체를 넣고, 빼고, 조회한다.

| 생성자                                             | 설명                                                                  |
|-------------------------------------------------|---------------------------------------------------------------------|
| HashSet ()                                      | 데이터를 저장할 수 있는 `16`개의 공간과 `0.75`의 `로드 팩터`(load factor)를 갖는 객체를 생성한다. | 
| HashSet(Collection<? extends E> c)              | `매개 변수로 넘어온 컬렉션 객체`를 HashSet에 담는다.                                  | 
| HashSet (int initialCapacity)                   | `매개 변수로 넘어온 initialCapaity 개수만큼`의 `저장공간`과 `0.75`의 `로드 팩터`(load factor)를 갖는 객체를 생성한다.         |
| HashSet (int initialCapacity, float loadFactor) | 첫 매개 변수로 받은 개수만큼의 데이터 저장 공간과 두 번째 매개 변수로 받은 만큼의 로드 팩터를 갖는 객체를 생성한다. |


>로드 팩터
>- (데이터의 개수)/(저장 공간)을 의미한다.
>- 만약 데이터의 개수가 증가하여 로드 펙터보다 커지면, 저장 공간의 크기는 증가되고 해시 재정리 작업(rehash)을 해야만 한다.
>- 데이터가 해시 재정리 작업에 들어가면, 내부에 갖고 있는 자료 구조를 다시 생성하는 단계를 거쳐야 하므로 성능에 영향이 발생한다.
>- 로드 패터라는 것이 클수록 공간은 넉넉해지지만, 데이터를 찾는 시간을 증가한다. 따라서, 초기 공간 개수와 로드 팩터는 데이터의 크기를 고려하여 산정하는 것이 좋다. 
>- 왜냐하면, 초기 크기가 (데이터의 개수)/(로드 팩터) 보다 클 경우에는 데이터를 쉽게 찾기 위한 해시 재정리 작업이 발생하지 않기 때문이다.
>- 따라서, 대량의 데이터를 여기에 담아 처리할 때에는 초기 크기와 로드 패터의 값을 조절해 가면서 가장 적당한 크기를 찾아야만 한다.

### HashSet의 주요 메소드
| 리턴 타입    | 메소드 이름 및 매개 변수     | 설명                                                 
|-------------|--------------------|-----------------------------------------------------------|
| boolean     | add(E e)           | 데이터를 추가한다.                                               | 
| void        | clear()            | 모든 데이터를 삭제한다.                                           | 
| Object      | clone()            | HashSet 객체를 복제한다. 하지만, 담겨있는 데이터들은 복제하지 않는다. | 
| boolean     | `contains(Object o)` | 지정한 객체가 존재하는지를 확인한다.                                | 
| boolean     | isEmpty()          | 데이터가 있는지 확인한다.                                         | 
| Iterator<E> | iterator()         | 데이터를 꺼내기 위한 Iterator 객체를 리턴 한다.                     | 
| boolean     | remove(Object o)   | 매개 변수로 넘어온 객체를 삭제한다.                                 | 
| int         | size()             | 데이터의 개수를 리턴한다.                                          | 

- 사용시 선언문 추가

```java
import java.util.HashSet;
import java.util.Set;
```


### 데이터 출력하기

1. for 문 사용
```java
public void printCarSet(Set<String> carSet) {
        for (String temp:carSet) {
        System.out.print(temp+ " ");
    }
}
```

2. Iterator 사용

```java
import java.util.Iterator;

.....

public void printCarSet2(Set<String> carSet) {
    Iterator<String> iterator = carSet.iterator();
    While(iterator.hasNext()) {
        System.out.print(iterator.next() + " ");
    }
    System.out.println();
}
```