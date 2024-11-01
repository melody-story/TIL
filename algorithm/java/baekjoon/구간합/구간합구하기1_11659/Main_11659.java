package 구간합.구간합구하기1_11659;/*
    https://www.acmicpc.net/problem/11659

    문제
    수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.

    입력
    첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다. 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
    
    출력
    총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
    
    제한
    1 ≤ N ≤ 100,000
    1 ≤ M ≤ 100,000
    1 ≤ i ≤ j ≤ N

    예제 입력
    5 3
    5 4 3 2 1
    1 3
    2 4
    5 5

    예제 출력
    12
    9
    1
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main_11659 {
    public static void main_11659(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); // reader기
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine()); // 라인 읽기
        int suNo = Integer.parseInt(stringTokenizer.nextToken()); // <=10만
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());// <=10만
        stringTokenizer = new StringTokenizer(bufferedReader.readLine()); // 라인 읽기
        long a[] = new long[suNo + 1];
        a[0] = 0;
        // 구간합 구하기
        for (int i = 1; i < suNo + 1; i++) { // n=10만
            a[i] = a[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
        }
        // a[e] - a[s-1]
        for (int i = 0; i < quizNo; i++) { // n=10만`
            stringTokenizer = new StringTokenizer(bufferedReader.readLine()); // 라인 읽기
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(a[e] - a[s - 1]);
        }
    }
}
