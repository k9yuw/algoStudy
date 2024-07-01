import java.io.*;
import java.util.*;

public class Main{

    static int n;
    static int[][] board;
    static int[][] dp; // dp[i][j]는 행렬 i부터 j까지 행렬곱 최소값

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][2];
        dp = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=1; k<n; k++) {
            for(int i=0; i+k<n; i++) {
                dp[i][i+k] = Integer.MAX_VALUE;
                for(int j=i; j<i+k; j++)
                    // i에서 i+k까지, i에서 i+k까지 중간에 한번 끊은거
                    dp[i][i+k] = Math.min(dp[i][i+k], dp[i][j] + dp[j+1][i+k] + board[i][0]*board[j][1]*board[i+k][1]);
            }
        }

        System.out.println(dp[0][n-1]);

    }
}