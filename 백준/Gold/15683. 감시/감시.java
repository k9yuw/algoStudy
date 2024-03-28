import java.io.*;
import java.util.*;

public class Main {

    static class cctv {
        int type;
        int x, y;
        public cctv(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] dirArr = {
        {},
        {1},
        {1, 3},
        {0, 1},
        {0, 1, 3},
        {0, 1, 2, 3}
    };
    static int ans = Integer.MAX_VALUE;
    static ArrayList<cctv> cctvList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] != 0 && board[i][j] != 6) {
                    cctvList.add(new cctv(board[i][j], i, j));
                }
            }
        }

        dfs(0, board);
        System.out.println(ans);
    }

    static void dfs(int cctvCnt, int[][] board) {
        if(cctvCnt == cctvList.size()) {
            ans = Math.min(ans, countBlindSpot(board));
            return;
        }

        cctv currentCctv = cctvList.get(cctvCnt);
        int x = currentCctv.x;
        int y = currentCctv.y;
        int type = currentCctv.type;

        for(int fourWay=0; fourWay<4; fourWay++) {
            int[][] tempBoard = copyBoard(board);
            for(int d : dirArr[type]) {
                int nx = x, ny = y;
                while(true) {
                    nx += dx[(fourWay + d) % 4];
                    ny += dy[(fourWay + d) % 4];
                    if (!isAbleToVisit(nx, ny) || tempBoard[nx][ny] == 6) break;
                    if (tempBoard[nx][ny] == 0) tempBoard[nx][ny] = -1; // 탐색완료 한건 -1로 바꿈
                }
            }
            dfs(cctvCnt + 1, tempBoard);
        }
    }

    static int[][] copyBoard(int[][] board) {
        int[][] tempBoard = new int[n][m];
        for(int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, m);
        }
        return tempBoard;
    }

    static boolean isAbleToVisit(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static int countBlindSpot(int[][] board) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

}