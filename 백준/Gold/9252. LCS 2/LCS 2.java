import java.io.*;
import java.util.*;

public class Main{
    
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 =  br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        char[] ans;
        
        int length1 = arr1.length;
        int length2 = arr2.length;
        
        dp = new int[length1+1][length2+1];
        
        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        
        // 출력
        toString(arr1, length1, length2);
        System.out.println(dp[length1][length2]);
        System.out.println(sb);
        
    }
    
    static void toString(char[] arr, int i, int j){
        
        Stack<Character> stack = new Stack<>();
        while(i>0 && j>0){
            if(dp[i][j] == dp[i-1][j]){
                i--;
            }   
            else if(dp[i][j] == dp[i][j-1]){
                j--;
            }
            else{
                stack.push(arr[i-1]);
                i--;
                j--;
            }
        }
        
        while(!stack.isEmpty()){
            sb.append(stack.pop());     
        }
    }
    
}