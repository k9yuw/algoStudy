import java.io.*;
import java.util.*;

public class Main{
    
    final static int mod = 1000;
    public static int N;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        int[][] initial = new int[N][N];
        int[][] result = new int[N][N];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                initial[i][j] = Integer.parseInt(st.nextToken()) % mod;
                if(i==j) result[i][j] = 1;
                else result[i][j] = 0; // result를 단위 행렬로 초기화(행렬 항등원)
            }
        }
        
        result = power(initial, B);
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
       
    }

    public static int[][] power(int[][] A, long B){
        if (B==1){
            return A;
        }
        
        int[][] reduced = power(A, B/2);
        int[][] result = multiply(reduced,reduced);
        
        if (B%2 == 1){
            result = multiply(result,A);
        }
        
        return result;
    }
    
    
    
    public static int[][] multiply(int[][] x, int[][] y){ // 두 행렬 곱하는 메소드
        
        int[][] result = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    result[i][j] += (x[i][k] * y[k][j]) % mod;
                    result[i][j] %= mod;
                }
            }
        }
        
        return result;
    }
        

    }
