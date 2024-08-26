import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= N; i++) {
            // 현재값 = 전 값 + 1
            dp[i] = dp[i - 1];
            for(int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - (j * j)]);
            }
            //최솟값이 저장되었을테니 +1
            dp[i]++;
        }
        System.out.println(dp[N]);
    }

}