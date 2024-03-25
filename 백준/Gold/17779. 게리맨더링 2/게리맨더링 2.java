import java.io.*;
import java.util.*;

public class Main{
    
    static int n;
    static int[][] board;
    static int answer = Integer.MAX_VALUE;
    static int totalPopulation = 0;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st;
        
        // 입력 받기
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                totalPopulation += board[i][j];
            }
        }
        
        
        // 4중 for문으로 x,y,d1,d2 설정
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                for(int d1=1; d1<n; d1++){
                    for(int d2=1; d2<n; d2++){
                        
                        if(x+d1+d2>=n) continue;
                        if(y-d1<0 || y+d2>=n) continue;
                        
                        divCalculate(x,y,d1,d2);
                    }
                }
            }
        }
        
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
        
    }
    
        
    public static void divCalculate(int x, int y, int d1, int d2){
            
        //// 경계선 설정
        boolean[][] boundary = new boolean[n][n];
            
        // 기울기 증가하는 부분 경계
        for(int i=0; i<=d1; i++){
            boundary[x+i][y-i] = true;
            boundary[x+d2+i][y+d2-i] = true;
        }
            
        // 기울기 감소하는 부분 경계
        for(int i=0; i<=d2; i++){
            boundary[x+i][y+i] = true;
            boundary[x+d1+i][y-d1+i] = true;
        }
            
        
        //// 각 선거구 계산
        int[] divPopulation = new int[5];
        
        // 1번 선거구 
        for(int i=0; i<x+d1; i++){
            for(int j=0; j<=y; j++){
                if(boundary[i][j]) break;
                divPopulation[0] += board[i][j];
            }
        }
        
        // 2번 선거구 
        for(int i=0; i<=x+d2; i++){
            for (int j=n-1; j>y; j--) {
                if(boundary[i][j]) break;
                divPopulation[1] += board[i][j];
            }
        }
        
        // 3번 선거구 
        for(int i=x+d1; i<n; i++){
            for(int j=0; j<y-d1+d2; j++){
                if(boundary[i][j]) break;
                divPopulation[2] += board[i][j];
            }
        }
        
        // 4번 선거구 
        for(int i=x+d2+1; i<n; i++){
            for(int j=n-1; j>=y-d1+d2; j--){
                if(boundary[i][j]) break;
                divPopulation[3] += board[i][j];
            }
        }
        
        // 5번 선거구
        divPopulation[4] = totalPopulation;
        for(int i=0; i<4; i++){
            divPopulation[4] -= divPopulation[i];
        }
        
        Arrays.sort(divPopulation);
        answer = Math.min(answer, divPopulation[4] - divPopulation[0]);
            
    }
        

}
