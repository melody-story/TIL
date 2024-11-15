package 투포인터;

/*
    https://www.acmicpc.net/problem/1940

    시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
	2 초	128 MB	35716	17173	12351	46.796%

	문제
	주몽은 철기군을 양성하기 위한 프로젝트에 나섰다. 그래서 야철대장을 통해 철기군이 입을 갑옷을 만들게 하였다.
	야철대장은 주몽의 명에 따르기 위하여 연구에 착수하던 중 아래와 같은 사실을 발견하게 되었다.

	갑옷을 만드는 재료들은 각각 고유한 번호를 가지고 있다. 갑옷은 두 개의 재료로 만드는데 두 재료의 고유한 번호를 합쳐서
	M(1 ≤ M ≤ 10,000,000)이 되면 갑옷이 만들어 지게 된다.
	야철대장은 자신이 만들고 있는 재료를 가지고 갑옷을 몇 개나 만들 수 있는지 궁금해졌다.
	이러한 궁금증을 풀어 주기 위하여 N(1 ≤ N ≤ 15,000) 개의 재료와 M이 주어졌을 때 몇 개의 갑옷을 만들 수 있는지를 구하는 프로그램을 작성하시오.

	입력
	첫째 줄에는 재료의 개수 N(1 ≤ N ≤ 15,000)이 주어진다. 그리고 두 번째 줄에는 갑옷을 만드는데 필요한 수 M(1 ≤ M ≤ 10,000,000) 주어진다.
	그리고 마지막으로 셋째 줄에는 N개의 재료들이 가진 고유한 번호들이 공백을 사이에 두고 주어진다. 고유한 번호는 100,000보다 작거나 같은 자연수이다.

	출력
	첫째 줄에 갑옷을 만들 수 있는 개수를 출력한다.

	예제 입력 1
6
9
2 7 4 1 5 3

	예제 출력 1
	2


	** 풀이 전략
	크기 비교이므로, 정렬을 해주면 더 쉽게 풀린다.
	start index는 1, end index는 마지막인덱스로 한다.
	sum > M 일 경우 endindex를 -- 해준다.
	sum < M 일 경우 start index를 ++ 해준다.
	sum == M 일 경우 start index를 ++, endindex를 --, cont ++ 해준다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();                                // 자연수 < 10000000(1000만) => O(n)으로 풀어야함.
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		// int n = Integer.parseInt(bufferedReader.readLine()); //  첫째줄 N(1 ≤ N ≤ 15,000)
		int n = Integer.parseInt(stringTokenizer.nextToken()); //  첫째줄 N(1 ≤ N ≤ 15,000)
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		// int m = Integer.parseInt(bufferedReader.readLine()); //  둘째줄 M(1 ≤ M ≤ 10,000,000)
		int m = Integer.parseInt(stringTokenizer.nextToken()); //  둘째줄 M(1 ≤ M ≤ 10,000,000)
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int[] items = new int[n];
		// ArrayList<Integer> items = new ArrayList<>();
		for (int i = 0; i < n; i++) { //O(n)(1 ≤ N ≤ 15,000)
			// items.add(Integer.parseInt(stringTokenizer.nextToken()));// 셋째줄 N개의 숫자들((1 ≤ N ≤ 15,000))
			items[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		// 정렬해주기
		// items.sort(Comparator.naturalOrder());// 오름차순(O(nlogN))
		Arrays.sort(items);// 오름차순(O(nlogN))   Arrays.sort() vs Collections.sort()
		// 투포인터 O(2n) 사용하기
		int start_index = 0;
		int end_index = n - 1; //  2개를 양 끝에서 확인하고 인덱스를 점점 접혀 오기
		int count = 0;                                        // 자연수가 17인 경우
		int sum = 0;                                            // sum ==M
		while (start_index < end_index) {// O(n)
			// sum = items.get(start_index) + items.get(end_index);
			sum = items[start_index] + items[end_index];
			if (sum == m) {
				count++;
				start_index++;
				end_index--;
			} else if (sum < m) {
				start_index++;
			} else {
				end_index--;
			}
		}
		System.out.println(count);
	}
}
