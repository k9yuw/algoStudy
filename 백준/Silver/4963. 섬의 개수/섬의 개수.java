import java.io.*;
import java.util.*;

public class Main{

    static int w, h;
    static int count;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            board = new int[h][w];
            visited = new boolean[h][w];

            if(w==0 && h==0) break;

            for(int i=0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            count = 0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(board[i][j] == 1 && !visited[i][j]) {
                        dfs(i,j);
                        count++;
                    }

                }
            }

            System.out.println(count);
        }

    }

    static void dfs(int y, int x){

        visited[y][x] = true;
        for(int i=0; i<8; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0 || nx<0 || ny>=h || nx>=w || visited[ny][nx] || board[ny][nx] == 0) continue;
            dfs(ny, nx);
        }
    }
}
