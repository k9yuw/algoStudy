import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board, dp;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1}; // 상하좌우

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[M][N];
        dp = new int[M][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dp[M-1][N-1] = 1;

        dfs(0,0);
        System.out.println(dp[0][0]);
    }

    public static int dfs(int y, int x) {

        // 제일 끝점일 때
        if(y==M-1 && x==N-1) return 1;

         // 방문한 적 있는 경우
        if(dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;

        // 첫 방문
        for(int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(0 <= ny && ny < M && 0 <= nx && nx < N && board[y][x] > board[ny][nx]) {
                dp[y][x] += dfs(ny, nx);
            }
        }
        return dp[y][x];
    }

}