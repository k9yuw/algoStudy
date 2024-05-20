import java.io.*;
import java.util.*;

class Node{
    int y, x, cnt;
    public Node(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

public class Main{

    static int n;
    static int idx = 2;
    static int ans = Integer.MAX_VALUE;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1}; // 상하좌우
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]==1){
                    indexIsland(i,j);
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]>=2){
                    visited = new boolean[n][n];
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }


    static void indexIsland(int y, int x){

        visited[y][x] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(y, x, 0));
        board[y][x] = idx;

        while(!q.isEmpty()){

            Node curr = q.poll();
            for(int i=0; i<4; i++){
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];
                if ((ny >= 0 && ny < n && nx >= 0 && nx < n) && !visited[ny][nx] && board[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    board[ny][nx] = idx;
                    q.offer(new Node(ny, nx, 0));
                }
            }
        }
        idx++;
    }


    static void bfs(int y, int x){
        Queue<Node> q = new LinkedList<Node>();
        q.offer(new Node(y, x, 0));
        int currLandNum = board[y][x];
        visited[y][x] = true;
        while(!q.isEmpty()){
            Node curr = q.poll();
            for(int i=0; i<4; i++){
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];
                if((ny>=0 && ny<n && nx>=0 && nx<n) && !visited[ny][nx] && board[ny][nx] != currLandNum){
                    visited[ny][nx] = true;
                    if(board[ny][nx]==0){
                        q.offer(new Node(ny, nx, curr.cnt + 1));
                    } else {
                        ans = Math.min(ans, curr.cnt);
                    }
                }
            }
        }
    }

}