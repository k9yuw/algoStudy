import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
    }

    static void bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparing(Point::getCnt));
        boolean[][] visited = new boolean[N][M];
        pq.add(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Point curr = pq.poll();

            if (curr.y == N - 1 && curr.x == M - 1) {
                System.out.println(curr.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                if (ny >= 0 && nx >= 0 && ny < N && nx < M && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    if (board[ny][nx] == 1) {
                        pq.add(new Point(ny, nx, curr.cnt + 1));
                    } else {
                        pq.add(new Point(ny, nx, curr.cnt));
                    }
                }
            }
        }
    }
}

class Point {
    int y, x;
    int cnt;

    public Point(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    public int getCnt() {
        return cnt;
    }
}
