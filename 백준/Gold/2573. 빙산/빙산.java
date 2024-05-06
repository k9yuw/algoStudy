import java.io.*;
import java.util.*;

class Node{
    int y, x;
    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}

public class Main{

    static int n,m;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while(true){
            int ice = countIce();
            if(ice>=2){
                break;
            } else if(ice==0){
                year = 0; // 빙산이 다 녹아도 두 덩어리 이상으로 분리가 안되는 경우
                break;
            }

            bfs();
            year++;
        }

        System.out.println(year);
    }

    static int countIce(){

        boolean[][] visited = new boolean[n][m];
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && board[i][j]>0){
                    dfs(i,j,visited);
                    count++;
                }
            }
        }

        return count;
    }

    static void dfs(int y, int x, boolean[][] visited) {

        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                continue;
            } else {
                if (!visited[ny][nx] && board[ny][nx] > 0) {
                    dfs(ny, nx, visited);
                }
            }
        }
    }

    static void bfs(){

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j]>0){
                    q.add(new Node(i,j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();

            int sea = 0; // 빙산에 인접한 칸에 존재하는 바닷물 영역의 개수

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m) {
                    if (!visited[ny][nx] && board[ny][nx] == 0) {
                        sea++;
                    }
                }
            }

            if (board[curr.y][curr.x] - sea <= 0) {
                board[curr.y][curr.x] = 0; // 0보다 작아지거나 같아지는 경우 0을 저장
            } else {
                board[curr.y][curr.x] -= sea;
            }
        }
    }
}
