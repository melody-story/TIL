## Arrays.sort() vs Collections.sort()

````java

@Test
public void arrayTest() {
	int[] array = new int[] {1, 3, 5, 4, 2};
	Arrays.sort(array);

	System.out.println("array = " + Arrays.toString(array));
}

@Test
public void collectionTest() {
	List<Integer> collection = new ArrayList<>(List.of(1, 3, 5, 4, 2));
	Collections.sort(collection);

	System.out.println("collection = " + collection);
}

````

## Arrays.sort()

먼저 배열을 정렬하는 Arrays.sort에 대해서 알아보도록 하겠습니다. Arrays.sort의 코드를 확인했을 때 아래와 같이 주석과 코드가 나왔습니다. 이를 통해서 보면 듀얼피봇 퀵정렬(Dual-Pivot
QuickSort)를 사용한다고 명시되어있는 것을 확인할 수 있었습니다.

```java
/**
 * Sorts the specified array into ascending numerical order.
 *
 * <p>Implementation note: The sorting algorithm is a Dual-Pivot Quicksort
 * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
 * offers O(n log(n)) performance on many data sets that cause other
 * quicksorts to degrade to quadratic performance, and is typically
 * faster than traditional (one-pivot) Quicksort implementations.
 *
 * @param a the array to be sorted
 */
public static void sort(int[] a) {
	DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
}
```

듀얼피봇 퀵정렬은 일반적인 퀵정렬과는 다르게 피봇을 2개를 두고 3개의 구간을 만들어 퀵정렬을 진행한다고 합니다. 이 정렬방식은 랜덤 데이터에 대해서 평균적으로 퀵소트 보다 좋은 성능을 낸다고 알려져있습니다.

## Arrays.sort() VS Collections.sort()

그렇다면 Arrays를 정렬했을때와 Collections를 정렬했을때 왜 사용하는 알고리즘이 다른걸까요 ? 그 이유는 바로 참조 지역성 원리(Local of Reference)에 있습니다. 참조 지역성의 원리란
동일한 값 또는 해당 값의 근처에 있는 스토리지 위치가 자주 액세스되는 특성을 말합니다. 이 참조 지역성의 원리는 CPU의 캐싱 전략에 영항을 미칩니다.

즉, CPU가 데이터를 엑세스하며 **해당 데이터만이 아니라 인접한 메모리에 있는 데이터 또한 캐시 메모리에 함께 올려둡니다.**

정렬의 실제 동작 시간은 C * 시간복잡도 + a라고 합니다. 시간복잡도는 다 아시는 내용일 것이고 a 상대적으로 무시할 수 있습니다.
하지만 곱해지는 C의 영향도는 고려를 해야합니다. 생각하지 않을 수 없습니다. 이 **C라는 값이 바로 참조 지역성 원리가 영향을 미칩니다.**

Array는 메모리적으로 각 값들이 연속적인 주소를 가지고 있기 때문에 C의 값이 낮습니다. 그래서 참조 지역성이 좋은 퀵 정렬을 이용하면 충분한 성능을 제공할 수 있다고 합니다.

하지만 Collection은 List를 기준으로 봤을때 메모리간 인접한 ArrayList 뿐만 아니라 메모리적으로 산발적인 LinkedList도 함께 존재합니다.
따라서 참조 인접성이 좋지 않고 C의 값이 상대적으로 높다고 합니다. 이럴 때는 **퀵 정렬 보다는 합병정렬(nlogN)과 삽입정렬(N^2)** 을 병합한 Tim 정렬을 이용하는게 평균적으로 더 좋은 성능을 기대할
수 있다고합니다.

### Tim 정렬

팀 정렬은 Tim Peter 에 의해 고안된 Merge Sort(합병 정렬), Insertion Sort(삽입 정렬)이 혼용 된 하이브리드 정렬 알고리즘이다. 
좀 더 자세하게 말하자면, **합병정렬을 기반으로 구현하되, 일정 크기 이하의 부분 리스트에 대해서는 이진 삽입 정렬을 수행하는 것이다.**

> ### 참조 지역성의 원리란 쉽게말해 CPU에서 참조하는 주소를 읽어들이는데 얼마나 소요되는가이다.
> 배열은 메모리가 순차적이므로 저장되므로 참조지역성 원리가 낮다. 따라서 퀵정렬(nlogN)을 사용해도 무방,
> 리스트는 참조지역성 원리가 높으므로, 퀵 정렬 보다는 합병정렬(nlogN)과 삽입정렬(N^2)을 병합한 Tim 정렬을 사용 하는 것이 더 좋은 성능을 기대한다.

### 참고

- https://sabarada.tistory.com/138
- https://st-lab.tistory.com/276