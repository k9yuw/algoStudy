// 중국인 나머지 정리를 쓰는 문제라고 생각했는데
// 그냥 while문 돌리다가 조건 도달 시 break 하고 출력하면 됨.. ;;
import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int year = 0;
        
        while(true){
            year++;
            if((year-e)%15 == 0 && (year-s)%28 == 0 && (year-m)%19 == 0)
                break;
        }
     
     System.out.println(year);
        
    }
}