import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] board, dp;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 }; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 칸에서 dfs 호출하여 최댓값 갱신
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.println(ans);
    }

    // (y, x)에서 시작했을 때 판다가 최대 몇 칸 이동할 수 있는지
    static int dfs(int y, int x) {
        // 이미 계산된 적 있으면 바로 리턴
        if (dp[y][x] != 0) return dp[y][x];

        dp[y][x] = 1;  // 최소한 자기 자신(이 칸)에서 1일

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
            // 더 큰 대나무가 있는 칸으로만 이동.
            // dp[y][x]가 여기서 갱신 안되고 1로 남아있다는 건 --> 더 갈 수 없는 칸이 없다는 뜻이고
            // 4방향 중 dp값이 가장 큰 칸 + 1
            if (board[ny][nx] > board[y][x]) {
                dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
            }
        }

        return dp[y][x];
    }
}
