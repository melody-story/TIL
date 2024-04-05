package main.java.sample;

import java.util.ArrayList;
import java.util.Collections;

public class ReifiableSample {
	/*
		List<String>과 같은 reifiable 타입은 런타임에도 타입 정보를 유지
		하지만, List<T>와 같은 비 제네릭은 배열을 생성하거나, 제네릭 타입의 instanceof 검사를 할 수 없음.
		Java에서는 제네릭 타입의 인스턴스화된 구체적인 타입 정보가 컴파일 시간에는 지워지는데, 이는 타입 소거라고 함.
		그러나 일부 타입은 타입 소거가 발생하지 않고 런타임에도 타입 정보를 유지하는데, 이러한 타입을 reifiable 타입이라고 함.

		@SafeVarargs
		public static <T> boolean addAll(Collection<? super T> c, T... elements) {
			boolean result = false;
			for (T element : elements)
				result |= c.add(element);
			return result;
		}

	 */
	public void addData() {
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		ArrayList<String> newDataList = new ArrayList<>();
		newDataList.add("a");
		Collections.addAll(list, newDataList);
		System.out.println(list);
	}

}
