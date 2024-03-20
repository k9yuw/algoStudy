import java.io.*;
import java.util.*;

public class Main{
    
    static int N;
    static int[] arr;
    static int[] operator = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }
       
        calculate(arr[0], 1);
        System.out.println(max);
        System.out.println(min);      
    
    }
    
    public static void calculate(int temp, int index){
        if(index == N){ // index가 N 되는 순간 비교 후 종료
            max = Math.max(max, temp);
            min = Math.min(min, temp);
            return;
        }
        
        for(int i=0; i<4; i++){
            if(operator[i]>0){
                operator[i]--;
                
                switch(i){
                    case 0: calculate(temp+arr[index], index+1); break;
                    case 1: calculate(temp-arr[index], index+1); break;
                    case 2: calculate(temp*arr[index], index+1); break;
                    case 3: calculate(temp/arr[index], index+1); break;
                }
                
                operator[i]++;
            }
        }
    }
}