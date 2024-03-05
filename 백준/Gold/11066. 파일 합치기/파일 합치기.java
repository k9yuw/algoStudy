import java.io.*;
import java.util.*;

public class Main {

    public static int[] sum = new int[501];
    public static int[][] dp = new int[501][501];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= K; j++) {
                sum[j] = sum[j - 1] + Integer.parseInt(st.nextToken());
            }
            mergeFile(K);
            bw.write(dp[1][K] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void mergeFile(int K) {
        for (int j = 2; j <= K; j++) {
            for (int i = j - 1; i >= 1; i--) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int div = i; div < j; div++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][div] + dp[div + 1][j]);
                }

                dp[i][j] += sum[j] - sum[i - 1]; // i부터 j까지 합치는 과정: (전단계 값) + (i부터 j까지 모든 cost 합)

            }
        }
    }

}