package 슬라이딩윈도우;

/*

9 8
CCTGGATTG
2 0 1 1

1 ≤ |P| ≤ |S| ≤ 1,000,000)

풀이 전략 : 중간 것은 그대로 두고 새로 들어오는 아이랑 나가는 아이만 신경쓰자.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		 String items = br.readLine();

		st = new StringTokenizer(br.readLine());
		 int[] countArray = new int[4];
		 for (int i = 0; i < 4; i++) {
		 	countArray[i]=Integer.parseInt(st.nextToken()); //  ‘A’-0, ‘C’-1, ‘G’-2, ‘T-3’
		 }

		 int[] myPwCountArray = new int[4];

		int startIndex = 0;
		int count =0;

		while (startIndex+p-1 < s) { // O(n)
			String substring = items.substring(startIndex, startIndex + p);
			ArrayList<Object> list = new ArrayList<>();
			char[] charArray = substring.toCharArray();
			for (char c : charArray) { //O(n)
				list.add(String.valueOf(c));
			}
			if(Collections.frequency(list,"A") >= countArray[0] &&
			Collections.frequency(list,"C") >= countArray[1] &&
			Collections.frequency(list,"G") >= countArray[2] &&
			Collections.frequency(list,"T") >= countArray[3] ){
				count++;
			}
			startIndex++;
		}
		System.out.println(count);
	}


	public void answer_time_out(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		String items = br.readLine();

		st = new StringTokenizer(br.readLine());
		int[] countArray = new int[4];
		for (int i = 0; i < 4; i++) {
			countArray[i]=Integer.parseInt(st.nextToken()); //  ‘A’-0, ‘C’-1, ‘G’-2, ‘T-3’
		}
		int startIndex = 0;
		int count =0;

		while (startIndex+p-1 < s) { // O(n)
			String substring = items.substring(startIndex, startIndex + p);
			ArrayList<Object> list = new ArrayList<>();
			char[] charArray = substring.toCharArray();
			for (char c : charArray) { //O(n)
				list.add(String.valueOf(c));
			}
			if(Collections.frequency(list,"A") >= countArray[0] &&
				Collections.frequency(list,"C") >= countArray[1] &&
				Collections.frequency(list,"G") >= countArray[2] &&
				Collections.frequency(list,"T") >= countArray[3] ){
				count++;
			}
			startIndex++;
		}
		System.out.println(count);
	}
}
