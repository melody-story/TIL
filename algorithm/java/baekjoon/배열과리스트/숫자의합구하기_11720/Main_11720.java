package 배열과리스트.숫자의합구하기_11720;

/**
 * 
 * N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 숫자의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄에 숫자 N개가 공백없이 주어진다.
 * 
 * 출력
 * 입력으로 주어진 숫자 N개의 합을 출력한다.
 * n=3
 * 1,3,5
 */

import java.util.Scanner;

public class Main_11720 {
    public static void main_11720(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String numbers = sc.next();
        char[] numbers_array = numbers.toCharArray();
        int sum = 0; // 최대 9*100 = 900
        for (int i = 0; i < N; i++) { // 시간복잡도는 100이다.
            sum += numbers_array[i] - '0'; // '0' => 48이다.
        }
        System.out.println(sum);
    }
}
