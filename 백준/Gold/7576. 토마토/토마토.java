import java.io.*;
import java.util.*;

public class Main {

    static class Tomato {
        int x, y;

        public Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] board;
    static Queue<Tomato> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    queue.add(new Tomato(i,j));
                }
            }
        }

        System.out.println(bfs());
    }


    public static int bfs() {
        int maxDay = 0;

        while (!queue.isEmpty()) {
            Tomato currTomato = queue.poll();
            int x = currTomato.x;
            int y = currTomato.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] != 0) continue;
                queue.offer(new Tomato(nx, ny));
                board[nx][ny] = board[x][y] + 1;
                maxDay = Math.max(maxDay, board[nx][ny]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) return -1; // 익지 않은 토마토가 있는 경우
            }
        }

        if (maxDay == 0) return 0; // 처음부터 다 익어있는 상태
        else return maxDay - 1;
    }
}
