import java.io.*;
import java.util.*;

public class Main{
    
    static int N,M,max = Integer.MIN_VALUE; // 정수 중 가장 작은 값을 초기값으로 설정해서 초기 비교 시 그 값이 반영되게 함
    static int[][] arr;
    static boolean[][] visited;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    
    public static void main(String[] args) throws IOException{
        
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        arr = new int[N][M];
        visited = new boolean[N][M];
        
        // 1. 어레이 입력 받기
        for(int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                arr[i][j] = scan.nextInt();
            }
        }
        
        // 2. 모든 좌표에서 dfs, figureT 한 번씩 실행
        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                visited[y][x] = true;
                dfs(y, x, 1, arr[y][x]);
                visited[y][x] = false;
                figureT(y,x);
                }
            }
        
        System.out.println(max);
        }
    
    
    private static void dfs(int y, int x, int cnt, int sum){
        if (cnt == 4){
            max = Math.max(max, sum);
            return;
        }
        
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx<0 || ny<0 || nx>=M || ny>=N) continue;
            if (visited[ny][nx]) continue;
            
            visited[ny][nx] = true;
            dfs(ny,nx,cnt+1,sum+arr[ny][nx]);
            visited[ny][nx] = false;
        }
         
    }
    
    private static void figureT(int y, int x){
        for(int exclude=0; exclude<4; exclude++){ //제외할 방향 하나 정해서 그거 빼고 세 방향 다 더함
            int sumT = arr[y][x];
            boolean isFigureValid = true;
        
        
            for(int d=0; d<4; d++){
                if(d==exclude) continue;
            
                int nx = x+dx[d];
                int ny = y+dy[d];
            
                if(nx<0 || ny<0 || nx>=M || ny>=N){
                    isFigureValid = false;
                    break;
                }
            
                sumT += arr[ny][nx];
            }
        
            if(isFigureValid){
                max = Math.max(max,sumT);
            }
        
        }
        
    }
}