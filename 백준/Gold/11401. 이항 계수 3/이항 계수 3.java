import java.io.*;
import java.util.*;

public class Main{
    
    final static long P = 1000000007;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
    
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
    
        long numer = factorial(N) % P;
        long denom = factorial(N-K) * factorial(K) % P;
    
        //a^p mod p 는 a mod p와 합동 (fermat's theorem)
        //양변을 a^2로 나눴을 때, a^p-2 mod p는 a^-1 mod p와 합동
        //따라서 denom^-1은 denom^p-2와 합동
        System.out.println(numer * power(denom, P-2) % P);    
    }
                       
    public static long factorial(long N){
       long result = 1L;
       while (N>1){
            result = (result * N) % P;
            N--;                
        }
        return result;        
    }
    
    // divide&conquer 방식 사용                   
    public static long power(long base, long exp){
        if(exp == 1){
            return base % P;
        }
        
        long reduced = power(base, exp/2);
        if (exp%2 == 1){
            return (reduced * reduced % P) * base % P;
        }
        return reduced * reduced % P;
   }
                                     
}