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

    static int n;
    static int[][] board;
    static int[][] dist;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1}; // 상하좌우

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        dist = new int[n][n];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                board[i][j] = str.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(dist[n-1][n-1]);
    }

    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0));
        dist[0][0] = 0;

        while(!q.isEmpty()){
            Node curr = q.poll();

            for(int i=0; i<4; i++){
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                if(ny<0 || nx<0 || ny>=n || nx>=n) continue;
                if(dist[ny][nx] <= dist[curr.y][curr.x]) continue;

                if(board[ny][nx] == 1) dist[ny][nx] = dist[curr.y][curr.x];
                else dist[ny][nx] = dist[curr.y][curr.x] + 1;
                q.offer(new Node(ny, nx));
            }
        }
    }

}
