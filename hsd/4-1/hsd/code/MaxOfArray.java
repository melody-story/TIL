import java.util.Scanner;

public class MaxOfArray {
    
    static int maxOf(int[] a){
        int max = a[0];
        for (int i = 1; i < a.length; i++ ){
            // max = a[i] <= a[i+1] ? a[i+1] : a[i];
            // if(max < a[i]){
            //     max = a[i];
            // }
            // max = max <= a[i] ? a[i] : max;
            // max = Math.max(max, a[i]);
            max = a[i] > max ? a[i] : max;
        }
        return max;
    }

    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);
        System.out.print("배열의 크기: ");
        int n = sc.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("height[" + i + "]: ");
            height[i] = sc.nextInt();
        }
        System.out.println("배열의 최댓값은 " + maxOf(height) + "입니다.");
    }
}
