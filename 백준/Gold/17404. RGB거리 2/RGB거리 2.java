import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] arr;
    static int[][] dp;
    static final int INF = 1000000;
    static int ans = INF;
    
  public static void main(String[] args) throws IOException{
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      N = Integer.parseInt(br.readLine());
      arr = new int[N][3];
      dp = new int[N][3];
      
      for(int i=0; i<N; i++){
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=0; j<3; j++){
              arr[i][j] = Integer.parseInt(st.nextToken());
          }
      }
      
      
      for(int j=0; j<3; j++){ // 첫번째 칠할 집
          for(int i=0; i<3; i++){
              if(i==j) dp[0][i] = arr[0][i];
              else dp[0][i] = INF;
          }
          
          for(int i=1; i<N; i++){
              dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + arr[i][0];
              dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + arr[i][1];
              dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + arr[i][2];
          }
          
          for(int i=0; i<3; i++){
              if(i!=j) ans = Math.min(ans,dp[N-1][i]);
          }
      }
      
          bw.write(ans + "\n");
          bw.close();
          br.close();
      
  }  
}