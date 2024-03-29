import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n];
        int[] p = new int[n];
        int[] dp = new int[n+1];
        
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<n; i++){
            if(i+t[i]<=n){
                dp[i+t[i]]=Math.max(dp[i+t[i]],dp[i]+p[i]);	
            }
            dp[i+1]=Math.max(dp[i+1],dp[i]);
		}
		System.out.println(dp[n]);	
	}
}      