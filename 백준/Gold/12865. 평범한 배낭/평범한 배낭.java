import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			for(int j=1; j<=K; j++) {
				dp[i][j] = dp[i-1][j];
				if(j-w>=0) dp[i][j] = Math.max(dp[i-1][j], v + dp[i-1][j-w]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
