import java.io.*;
import java.util.*;

public class Main{
    
    static int N, M;
    static int[] arr;
    static boolean[][] dp;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new boolean[N][N];
        
        st = new StringTokenizer(br.readLine(), " ");
        // 입력값 받기
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(br.readLine());
        
        
        // 길이 1,2인 것들 dp 값 설정
        for(int i=0; i<N; i++){
            dp[i][i] = true;
        }
        
        for(int i=0; i<N-1; i++){
            if (arr[i] == arr[i+1]){
                dp[i][i+1] = true;
                dp[i+1][i] = true;
            }
        }
        
        // 나머지 dp 값 설정
        checkPalindrome();
        
        
        // 출력
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int S = Integer.parseInt(st.nextToken()) -1;
            int E = Integer.parseInt(st.nextToken()) -1;
            if (dp[S][E]) bw.write(1 + "\n");
            else bw.write(0 + "\n");
        }
        
        bw.close();           
    }
    
    static void checkPalindrome() {
		for(int i=2; i<N; i++) {
            for(int j=0; j<N-i; j++) {
                if(arr[j] == arr[j+i] && dp[j+1][j+i-1]) {
                    dp[j][j+i] = true;
                }
            }
        }
    }
    
}
