import java.io.*;
import java.util.*;

public class Main {
   
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memoryArr = new int[N];
        int[] costArr = new int[N];
        int ans = Integer.MAX_VALUE;
        
        int[][] dp = new int[N][100001]; // dp[i][j] = i번째까지 앱을 사용했을 때, j 비용으로 얻을 수 있는 메모리 최댓값
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memoryArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costArr[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < N; i++) {
            int memory = memoryArr[i];
            int cost = costArr[i];
            
            for (int j = 0; j < 100001; j++) {
                if (i == 0) {
                    if (j >= cost) dp[i][j] = memory;
                } else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i-1][j-cost] + memory, dp[i-1][j]);
                    else dp[i][j] = dp[i-1][j];
                }  
                if (dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);   
    }
}
