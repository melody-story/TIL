# Queue

- LinkedList 클래스가 구현한 인터페이스
- 큐는 FIFO(First In First Out)의 용도로 사용
- Queue는 여러 쓰레드에서 들어오는 작업을 순차적으로 처리할 때 많이 사용됨

# Deque

- Double Ended Queue의 약자
- Queue 인터페이스를 확장하였기 때문에, Queue의 기능을 전부 포함한다.
- LinkedList 클래스가 구현한 인터페이스 중에서 `Java 6`에서 추가됨.
- 발음이 “deck"와 동일
- 맨 앞에 값을 넣거나 빼는 작업, 맨 뒤에 값을 넣거나 빼는 작업을 수행하는 데 용이

--- 

# LinkedList

> LinkedList를 가장 쉽게 이해하려면 열차를 생각하면 된다.
> LinkedList에 A라는 하나의 값이 추가되면 열차의 맨 앞간에 데이터를 집어 넣는다.
> 이 때, LinkedList의 가장 앞의 값도 A이며, 가장 끝에 있는 값도 A다.


LinkedList라는 것을 쓰는 이유

- 배열과 같이 데이터를 담아서 순차적으로 뺄 경우에는 별 필요가 없을 수도있다.
- 배열의 중간에 있는 데이터가 지속적으로 삭제되고, 추가될 경 우에는 LinkedList가 배열보다 메모리 공간 측면에서 훨씬 유리하다.
  왜냐하면, 배열과 같은 ArrayList와 vector는 각위치가 정해져 있고,그 위치로 데이터를 찾는다.
- 그런데, 맨 앞의 값을 삭제하면, 그 뒤에 있는 값들은 하나씩 앞으로 위치를 이동해야 제대로 된 위치의 값을 가지게 된다.
- 그에 반해 LinkedList는 중간에 있는 데이터를 삭제하면, 지운 데이터의 앞에 있는 데이터와 뒤에 있는 데이터를 연결하면 그만이다.

- List 인터페이스뿐만 아니라 Queue와 Deque 인터페이스도 구현하고 있다.
- 즉, LinkedList 자체가 List이면서도 Queue와 Deque도 된다.

### `LinkedList`의 상속 관계

```java
java.lang.object
        Ljava.util.AbstractCollection<E>
        Ljava.util.AbstractList<E>
            Ljava.util.AbstractSequentialList<E>
                Ljava.util.Linkedlist<E>
```

1. `AbstractSequentialList`가 부모 클래스
    - `AbstractList`와 `AbstractSequentialList`의 차이점 : add(), set(), remove() 등의 메소드에 대한 구현 내용이 상이.

| 인터페이스         | 용도                                                                |
|---------------|-------------------------------------------------------------------|
| Serializable  | 원격으로 객체를 전송하거나, 파일에 저장할 수 있음을 지정                                  |
| Cloneable     | Object 클래스의 `clone()` 메소드가 제대로 수행될 수 있음을 지정 즉, 복제가 가능한 객체임을 의미한다. |
| Iterable<E>   | 객체가 `foreach` 문장을 사용할 수 있음을 지정                                    |
| Collection<E> | 여러 개의 객체를 하나의 객체에 담아 처리할 때의 메소드 지정                                |
| Deque<E>      | 맨 앞과 맨 뒤의 값을 용이하게 처리하는 큐와 관련된 메소드 지정                              |
| List<E>       | 목록형 데이터를 처리하는 것과 관련된 메소드 지정                                       |
| Queue<E>      | 큐를 처리하는 것과 관련된 메소드 지정                                             |

- LinkedList 클래스는 `List`도 되고 `Queue`도 된다.
- 두 인터페이 스의 기능을 모두 구현한 특이한 클래스라고 볼 수 있다.
- 게다가 `Deque` 인터페이스도 구현하므로, 맨 앞과 끝의 데이터를 쉽게 처리할 수 있다.

### LinkedList의 2가지 생성자

| 생성자                                   | 설명                                      | 
|---------------------------------------|-----------------------------------------|
| LinkedList()                          | 비어 있는 LinkedList 객체를 생성한다.              |
| LinkedList(Collection<? extends E> c) | 매개 변수로 받은 컬렉션 객체의 데이터를 LinkedList에 담는다. |

> 일반적인 배열 타입의 클래스와 다르게 생성자로 객체를 생성할 때 `처음부터 크기를 지정하지 않는다.`

### LinkedList의 메소드

- LinkedList가 여러 종류의 인터페이스를 구현했기 때문에 중복된 기능을 수행하는 메소드가 많다.
- offer() 관련 메소드를 호출하면 add()나 addLast() 메소드를 호출하도록 되어 있다.
- 따라서`add`가 붙은 메소드를 사용하는 것이 오해의 소지가 가장 적다.

1. 객체에 데이터 추가

- LinkedList 객체의 가장 앞에 데이터를 추가

| 리턴 타입   | 메소드 이름 및 매개 변수     |
|---------|--------------------|
| void    | addFirst(Object)   |
| boolean | offerFirst(Object) |
| void    | push(Object)       |

- LinkedList 객체의 가장 뒤에 데이터를 추가

| 리턴 타입   | 메소드 이름 및 매개 변수    |
|---------|-------------------|
| boolean | add(Object)       |
| void    | addLast(Object)   | 
| boolean | offer(Object)     |
| boolean | offerLast(Object) |                          

- 특정 위치의 데이터 추가 및 수정

| 리턴 타입   | 메소드 이름 및 매개 변수          | 설명                                                         |
|---------|-------------------------|------------------------------------------------------------|
| void    | add(int, Object)        | LinkedList 객체의 특정 위치에 데이터를 추가한다.                           |
| Object  | set(int, Object)        | LinkedList 객체의 특정 위치에 있는 데이터를 수정한다. 그리고, 기존에 있던 데이터를 리턴한다. |
| boolean | addAll(Collection)      | 매개 변수로 넘긴 컬렉션의 데이터를 추가한다.                                  |
| boolean | addAll(int, Collection) | 매개 변수로 넘긴 컬렉션의 데이터를 지정된 위치에 추가한다.                          |

2. 객체의 데이터 조회하기

- LinkedList 객체의 맨 앞에 있는 데이터를 리턴한다.

| 리턴 타입  | 메소드 이름 및 매개 변수 |
|--------|----------------|
| Object | getFirst()     |
| Object | peekFirst()    |
| Object | peek()         |
| Object | element()      |

- LinkedList 객체의 맨 뒤에 있는 데이터를 리턴한다.

| 리턴 타입  | 메소드 이름 및 매개 변수 |
|--------|----------------|
| Object | getLast()      |
| Object | peekLast()     |

- LinkedList 객체의 지정한 위치에 있는 데이터를 리턴한다.

| 리턴 타입  | 메소드 이름 및 매개 변수 |
|--------|----------------|
| Object | get(int)       |

- LinkedList에 어떤 객체가 포함되어 있는지를 확인

| 리턴 타입   | 메소드 이름 및 매개 변수      | 설명                                                    | 
|---------|---------------------|-------------------------------------------------------|
| boolean | contains(Object)    | 매개 변수로 넘긴 데이터가 있을 경우 `true`를 리턴 한다.                   |
| int     | indexOf(Object)     | 매개 변수로 넘긴 데이터의 위치를 `앞`에서부터 검색하여 리턴한다. 없을 경우 -1을 리턴한다. |
| int     | lastIndexOf(Object) | 매개 변수로 넘긴 데이터의 위치를 끝에서부터 검색하여 리턴한다. 없을 경우 -1을 리턴한다.   |

2. 객체의 데이터 삭제하기

- 데이터를 삭제하는 많은 메소드들은 모두 removeFirst() 메소드를 내부 적으로 호출한다.
- 반대로 맨 뒤에 있는 데이터를 삭제하는 메소드들은 모두 removeLast() 메소 드를 내부적으로 호출한다.
- 따라서, 혼동을 피하려면 `remove`가 붙은 메소드를 사용할 것을 권장한다.


- LinkedList 객체의 가장 앞에 있는 데이터를 삭제하고 리턴한다.

| 리턴 타입  | 메소드 이름 및 매개 변수 | 
|--------|----------------|
| Object | remove()       |
| Object | removeFirst()  |
| Object | poll()         |
| Object | pollFirst()    |
| Object | pop()          |

- LinkedList 객체의 가장 끝에 있는 데이터를 삭제하고 리턴한다.

| 리턴 타입  | 메소드 이름 및 매개 변수 |
|--------|----------------|
| Object | pollLast()     |
| Object | removeLast()   |

- 매개 변수로 넘겨진 객체와 동일한 데이터 중 `앞`에서부터 가장 처음에 발견된 데이터를 삭제한다.

| 리턴 타입   | 메소드 이름 및 매개 변수                 |
|---------|--------------------------------|
| boolean | remove (Object)                |
| boolean | removeFirstOccurrence (Object) |

- 매개 변수로 넘겨진 객체와 동일한 데이터 중 `끝`에서부터 가장 처음에 발견된 데이터를 삭제한다.

| 리턴 타입   | 메소드 이름 및 매개 변수                |
|---------|-------------------------------|
| boolean | removeLastOccurrence (Object) |

### ListIterator

- ListIterator는 `Iterator` 인터페이스가 다음 데이터만을 검색할 수 있다는 단점을 보완하여, 이전 데이터도 검색할 수 있는 이터레이터다.
- 따라서 `next()` 외에도 `previous()` 메소드를 사용하면 이전 데이터를 확인할 수 있다.

| 리턴 타입          | 메소드 이름 및 매개 변수         | 설명                                                     | 
|----------------|------------------------|--------------------------------------------------------|
| `ListIterator` | `listIterator(int)`    | 매개 변수에 지정된 위치부터의 데이터를 검색하기 위한 `ListIterator` 객체를 리턴한다. | 
| `Iterator`     | `descendingIterator()` | `LinkedList`의 데이터를 끝에서부터 검색하기 위한 Iterator 객체를 리턴한다.    | 