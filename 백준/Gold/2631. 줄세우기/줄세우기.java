import java.io.*;
import java.util.*;

public class Main {

    static int N, max;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        max = 0;
        for(int i=0; i<N; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i] && dp[j]+1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.println(N-max);
    }
}