import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long n = Long.parseLong(br.readLine());
        int cycle = 1500000;
        long index = n % cycle;
        long[] arr = new long[(int)index + 1];
        
        arr[0] = 0;
        arr[1] = 1;
        
        for(int i=2; i<=index; i++){
            arr[i] = (arr[i-2]+arr[i-1])%1000000;
        }
        
        System.out.println(arr[(int)index]);
        
    }
}