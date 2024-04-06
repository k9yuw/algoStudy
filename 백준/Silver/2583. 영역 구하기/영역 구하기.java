import java.io.*;
import java.util.*;

public class Main{

    static int n,m,k;
    static int count;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int y=y1; y<y2; y++) {
                for(int x=x1; x<x2; x++){
                    board[y][x] = true;
                }
            }
        }

        ArrayList<Integer> areaCount = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!board[i][j]) {
                    count = 0;
                    dfs(i,j);
                    areaCount.add(count);
                }
            }
        }

        Collections.sort(areaCount);

        System.out.println(areaCount.size());
        for(int i : areaCount)  System.out.print(i + " ");

    }

    public static void dfs(int x, int y){

        board[x][y] = true;
        count++;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=n || ny>=m || board[nx][ny]) continue;
            dfs(nx, ny);
        }
    }

}