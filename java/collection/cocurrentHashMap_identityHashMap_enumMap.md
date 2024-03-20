## ConcurrentHashMap

### ConcurrentHashMap와 HashTable 차이
`ConcurrentHashMap`과 `Hashtable`은 모두 **멀티스레드 환경에서 안전하게 동작**하는 해시 테이블 기반의 자료구조입니다. 그러나 두 클래스 간에는 몇 가지 중요한 차이점이 있습니다.

1. **동기화 방식:**
    - `ConcurrentHashMap`: 동기화를 세분화하여 세그먼트(구역) 단위로 동시성을 관리합니다. 이를 통해 동시에 여러 스레드가 맵을 읽고 쓸 수 있습니다.
    - `Hashtable`: 모든 메서드가 동기화되어 있어 하나의 스레드만 맵에 접근할 수 있습니다. 이로 인해 멀티스레드 환경에서는 성능 저하가 발생할 수 있습니다.
2. **null 허용 여부:**
    - `ConcurrentHashMap`: 키와 값 모두 null을 허용합니다. 즉, null 키나 값도 저장할 수 있습니다.
    - `Hashtable`: 키나 값으로 null을 저장할 수 없습니다. null을 저장하려고 하면 NullPointerException이 발생합니다.
3. **성능:**
    - `ConcurrentHashMap`은 세그먼트 단위로 동기화를 하기 때문에 멀티스레드 환경에서도 성능이 우수합니다. 따라서 많은 수의 동시 요청을 처리할 때 더 뛰어난 성능을 보입니다.
    - `Hashtable`은 전체 메서드가 동기화되어 있기 때문에 단일 스레드 환경에서도 성능이 저하될 수 있습니다.
4. **호환성:**
    - `ConcurrentHashMap`은 Java 5부터 도입된 클래스로, `Map` 인터페이스를 구현하고 있습니다. 따라서 Java Collections Framework의 일부입니다.
    - `Hashtable`은 JDK 1.0부터 존재하는 클래스이며, `Map` 인터페이스를 구현하고 있지만 컬렉션
    - 프레임워크와의 호환성은 덜합니다.

따라서 일반적으로 멀티스레드 환경에서 `ConcurrentHashMap`을 사용하는 것이 권장됩니다. `Hashtable`은 JDK 초기부터 존재하던 클래스이지만, 세분화된 동기화와 null 허용 여부 등에서 `ConcurrentHashMap`이 더 유용하고 성능적으로 우수합니다.

세그먼트 단위로 동기화하는 것은 `ConcurrentHashMap`에서 사용되는 특정한 동기화 전략을 의미합니다. 이러한 전략은 동시성을 관리하고 맵을 안전하게 동작시키는 방법 중 하나입니다.

`ConcurrentHashMap`은 내부적으로 여러 개의 독립적인 해시 테이블 세그먼트를 가지고 있습니다. 각 세그먼트는 하나의 해시 테이블을 나타냅니다. 이러한 세그먼트는 독립적으로 동기화되어 있으므로 서로에게 영향을 주지 않고 동시에 여러 스레드가 맵을 안전하게 읽고 쓸 수 있습니다.

세그먼트 단위로 동기화하는 이유는 전체 맵을 잠그는 것보다 더 나은 동시성을 제공하기 때문입니다. 여러 스레드가 맵을 동시에 사용하더라도 각 세그먼트는 독립적으로 동작하므로 동시성 병목 현상이 줄어듭니다. 따라서 `ConcurrentHashMap`은 많은 수의 동시 요청을 처리할 때 뛰어난 성능을 보입니다.

세그먼트 단위로 동기화하는 것은 맵의 크기가 커질수록 성능을 향상시키는데 도움이 됩니다. 작은 맵의 경우에는 오버헤드가 발생할 수 있으므로 주의해야 합니다.


## IdentityHashMap
- **객체의 동일성을 기반으로 키를 관리**
- `IdentityHashMap`은 객체의 동일성(identity)을 기준으로 키의 등가성(equality)을 판별합니다. 즉, `==` 연산자를 사용하여 키의 동일성을 검사합니다.
- 따라서 `IdentityHashMap`은 객체의 해시 코드(hash code)를 기반으로 해시 테이블을 구성하지 않습니다. 대신 객체의 메모리 주소나 참조 값을 사용하여 해시 테이블을 구성합니다.
- 이러한 특성 때문에 `IdentityHashMap`은 일반적인 해시 충돌 문제를 해결하는 것이 아니라, 객체의 동일성을 기반으로 키를 관리합니다.
```java
public class IdentityHashMapExample { 
	public static void main(String[] args) { 
		// IdentityHashMap 객체 생성 
		IdentityHashMap<String, Integer> identityHashMap = new IdentityHashMap<>(); 
		// 문자열 객체 생성 
		String key1 = new String("key"); String key2 = new String("key"); 
		// 해시맵에 키-값 쌍 추가 
		identityHashMap.put(key1, 1); identityHashMap.put(key2, 2); 
		// 키 객체가 같은지 확인 
		System.out.println("key1 == key2: " + (key1 == key2)); 
		// 해시맵 출력 
		System.out.println("IdentityHashMap: " + identityHashMap); 
	} 
}
```


### EnumMap
- `EnumMap`은 열거형(enum)을 키로 사용하는 특수한 종류의 해시 맵입니다.
- `EnumMap`은 내부적으로 배열을 사용하여 각 열거형 **상수에 대한 인덱스를 기반**으로 요소를 저장합니다. 열거형 상수의 순서는 열거형 정의에 의해 고정되어 있으므로 해시 충돌 문제가 발생하지 않습니다.
- 따라서 `EnumMap`은 해시 충돌을 해결하는 별도의 로직이 필요하지 않습니다.
```java
import java.util.EnumMap; 

enum Day { 
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY 
} 

public class EnumMapExample { 
	public static void main(String[] args) { 
		// EnumMap 객체 생성 
		EnumMap<Day, String> enumMap = new EnumMap<>(Day.class); 
		// 열거형 상수를 키로 사용하여 값을 저장 
		enumMap.put(Day.MONDAY, "First day of the week"); 
		enumMap.put(Day.TUESDAY, "Second day of the week"); 
		// EnumMap 출력 
		System.out.println("EnumMap: " + enumMap); 
	} 
}
```