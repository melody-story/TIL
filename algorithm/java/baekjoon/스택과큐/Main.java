package 스택과큐;
/*
	https://www.acmicpc.net/problem/1874

	시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
	2 초	128 MB	178663	71401	49419	38.641%

	문제
	스택 (stack)은 기본적인 자료구조 중 하나로, 컴퓨터 프로그램을 작성할 때 자주 이용되는 개념이다.
	스택은 자료를 넣는 (push) 입구와 자료를 뽑는 (pop) 입구가 같아 제일 나중에 들어간 자료가 제일 먼저 나오는 (LIFO, Last in First out) 특성을 가지고 있다.
	1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다.
	이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자.
	임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지, 있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다.
	이를 계산하는 프로그램을 작성하라.

	입력
	첫 줄에 n (1 ≤ n ≤ 100,000)이 주어진다. 둘째 줄부터 n개의 줄에는 수열을 이루는 1이상 n이하의 정수가 하나씩 순서대로 주어진다. 물론 같은 정수가 두 번 나오는 일은 없다.

	출력
	입력된 수열을 만들기 위해 필요한 연산을 한 줄에 한 개씩 출력한다. push연산은 +로, pop 연산은 -로 표현하도록 한다. 불가능한 경우 NO를 출력한다.

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] su = new int[n];
		for (int i = 0; i < n; i++) {
			su[i] = sc.nextInt();  //  수열 배열 생성.
		}

		Stack<Integer> stack = new Stack<>(); // 스택에 자연수를 오름차순으로 집어넣음
		// 수열을 만들 수 없는 경우는
		// 스택의 top이 수열의 현재 수보다 큰 경우이다. top = 8, 수열의 현재 수 = 5

		int stack_number = 1;
		// List<String> result = new ArrayList<>();
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i <n; i++) {
			if (stack_number <= su[i]) { // 자연수가 순열보다 작거나 같은 경우
				while (stack_number <= su[i]) {// 자연수가 순열보다 작거나 같을 떄 까지 스택에 넣는다.
					stack.push(stack_number);
					stack_number++;
					bf.append("+\n");
				}
				stack.pop();// 같아질때까지 넣었으면 빼는 동작 수행.
				bf.append("-\n");
			}
			else { // 자연수가 순열보다 큰 경우 (자연수는 1부터 시작)
				if (stack.peek() > su[i]){ // 스택의 top에 있는 수가 순열보다 큰 경우
					bf= new StringBuffer("NO");
					// System.out.println("NO");
					break;
				}
				else { // 스택의 top에 있는 수가 순열보다 작은 경우
					stack.pop();
					bf.append("-\n");
				}
			}
		}
		System.out.println(bf.toString()); //  위에서 break를 하게되면 이부분까지 출력된다. 따라서 플래그 조건을 달아준다.
	}
}
