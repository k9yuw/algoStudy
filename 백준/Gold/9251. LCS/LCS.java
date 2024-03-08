import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        
        int length1 = arr1.length;
        int length2 = arr2.length;
        
        int[][] dp = new int[length1+1][length2+1];
        
        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(arr1[i-1] == arr2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        
        System.out.println(dp[length1][length2]);
        
    }
}