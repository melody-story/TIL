package 투포인터;

/*
    https://www.acmicpc.net/problem/2018

    문제
	어떠한 자연수 N은, 몇 개의 연속된 자연수의 합으로 나타낼 수 있다.
	당신은 어떤 자연수 N(1 ≤ N ≤ 10,000,000)에 대해서, 이 N을 몇 개의 연속된 자연수의 합으로 나타내는 가지수를 알고 싶어한다.
	이때, 사용하는 자연수는 N이하여야 한다.

	예를 들어, 15를 나타내는 방법은 15, 7+8, 4+5+6, 1+2+3+4+5의 4가지가 있다. 반면에 10을 나타내는 방법은 10, 1+2+3+4의 2가지가 있다.

	N을 입력받아 가지수를 출력하는 프로그램을 작성하시오.

	입력
	첫 줄에 정수 N이 주어진다.

	출력
	입력된 자연수 N을 몇 개의 연속된 자연수의 합으로 나타내는 가지수를 출력하시오

	예제 입력 1
	15
	예제 출력 1
	4

 */

import java.io.IOException;
import java.util.Scanner;

public class Main_2018{
	public static void main_2018(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); 								// 자연수 < 10000000(1000만) => O(n)으로 풀어야함.
		// 투포인터 O(2n) 사용하기
		int start_index = 1;
		int end_index = 1;
		int count =1; 										// 자연수가 17인 경우(자기자신일 경우를 미리 포함)
		int sum=1; 											// 1~n 사이의 연속된 자연수의 합
		while (end_index != n){
			if (sum == n) {
				count++; end_index++; sum+=end_index;
			}
			else if (sum > n) {
				sum-=start_index; 							//  기존것을 빼준다.
				start_index++;
			}
			else {
				end_index++;
				sum+=end_index;
			}
		}
		System.out.println(count);
	}
}
