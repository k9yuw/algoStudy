import java.io.*;
import java.util.*;

public class Main{

    static int t;
    static int n, m, k;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            board = new int[n][m];
            visited = new boolean[n][m];

            // 보드에 배추 위치 입력
            for(int j=0; j<k; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[a][b] = 1;
            }


            int count = 0;
            for(int p=0; p<n; p++){
                for(int q=0; q<m; q++){
                    if(board[p][q] == 0 || visited[p][q]) continue;
                    dfs(p, q);
                    count++;
                }
            }

            System.out.println(count);
        }
    }


    public static void dfs(int x, int y) {

        visited[x][y] = true;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=n || ny>=m || visited[nx][ny] || board[nx][ny] == 0) continue;
            dfs(nx, ny);
        }
    }
}