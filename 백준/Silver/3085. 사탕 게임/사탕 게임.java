import java.util.*;

public class Main{
    
    public static int N;
    public static int max=0;
    public static char[][] arr;
    
    public static void maxCount(){
        
        // 가로 확인
        for (int i=0; i<N; i++){
            int cnt = 1;
            for (int j=0; j<N-1; j++){
                if (arr[i][j] == arr[i][j+1]){
                    cnt ++;
                }
                
                else {
                    cnt = 1;
                }
                
                max = Math.max(max, cnt);                 
            }
        }
        
        // 세로 확인
        for (int j=0; j<N; j++){
            int cnt = 1;
            for (int i=0; i<N-1; i++){
                if (arr[i][j] == arr[i+1][j]){
                    cnt ++;
                }
                
                else {
                    cnt = 1;
                }
                
                max = Math.max(max, cnt);                 
            }
        }
             
    }
    
    
    public static void main(String[] args){
        
            
    
    //1. 기본 값 스캔
    Scanner scan = new Scanner(System.in);
    N = scan.nextInt();
    arr = new char[N][N];
    
    for(int i=0; i<N; i++){
        String line = scan.next();
        for(int j=0; j<N; j++){
            arr[i][j] = line.charAt(j);
        }       
    }
        
    
    // 2. 가로방향 확인
    for(int i=0; i<N; i++){
        for(int j=0; j<N-1; j++){
            char temp = arr[i][j];
            arr[i][j] = arr[i][j+1];
            arr[i][j+1] = temp;

            maxCount();
            
            temp = arr[i][j];
            arr[i][j] = arr[i][j+1];
            arr[i][j+1] = temp;
        }
    }
    
    // 3. 세로방향 확인
    for(int i=0; i<N-1; i++){
        for(int j=0; j<N; j++){
            char temp = arr[i][j];
            arr[i][j] = arr[i+1][j];
            arr[i+1][j] = temp;

            maxCount();
            
            temp = arr[i][j];
            arr[i][j] = arr[i+1][j];
            arr[i+1][j] = temp;
        }
    }        
    
    // 4. 출력
    System.out.println(max);
    scan.close();       
        
    }
}