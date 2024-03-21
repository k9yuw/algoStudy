import java.io.*;
import java.util.*;

public class Main{
    
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N];
        
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0,0);
        System.out.println(min);

    }
    
    public static void dfs(int depth, int idx){
        
        if(depth==N/2){
            checkTeam();
            return;
        }
        
        for(int i=idx; i<N; i++){
            visited[i] = true;
            dfs(depth+1, i+1);
            visited[i] = false;
        } 
    }
    
    public static void checkTeam(){
        int start = 0;
        int link = 0;
        
        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                if(visited[i] == true && visited[j] == true){
                    start += board[i][j] + board[j][i];
                } else if(visited[i] == false && visited[j] == false){
                    link += board[i][j] + board[j][i];
                }
            }
        }
        
        int diff = Math.abs(start - link);
        min = Math.min(min, diff);
        
    }
}
