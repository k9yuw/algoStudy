import java.io.*;
import java.util.*;

public class Main {

    static int T, N;
    static int[][] dp = new int[10001][4];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1; // 2 1 은 경우에서 뺌. 항상 오름차순 정렬
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i=4; i<=10000; i++){
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }

        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            int sum = dp[N][1] + dp[N][2] + dp[N][3];
            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }
}