import java.io.*;
import java.util.*;

public class Main{
    
    static int n;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] board;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        
        int maxLevel = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(maxLevel < board[i][j]) maxLevel = board[i][j];
            }
        }
        
        int maxAreaCount = 0;
        for(int i=0; i<maxLevel; i++){
            int count = 0;
            visited = new boolean[n][n];
            for(int j=0; j<n; j++){
                for(int k=0; k<n; k++){
                    if(board[j][k]>i && !visited[j][k]){
                        count++;
                        dfs(j,k,i);
                    }
                }
            }
            maxAreaCount = Math.max(count, maxAreaCount);
        }
        
        System.out.println(maxAreaCount);
     
    }
    
    static void dfs(int x, int y, int level){
        
        visited[x][y] = true;
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 범위 벗어났거나, 물에 잠겼거나, 이미 방문한 경우
            if(nx<0 || ny<0 || nx>=n || ny>=n || board[nx][ny]<=level || visited[nx][ny]) continue;
            dfs(nx, ny, level);
        }
    }
}