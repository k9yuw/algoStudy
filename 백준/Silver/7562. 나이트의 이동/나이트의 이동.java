import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int t;
    static int n;
    static int x1, y1, x2, y2;
    static int[] dx = {1,2,2,1,-1,-2,-2,-1};
    static int[] dy = {2,1,-1,-2,-2,-1,1,2};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int i=0; i<t; i++) {

            n = Integer.parseInt(br.readLine());
            int[][] board = new int[n][n];
            boolean[][] visited = new boolean[n][n];

            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            bfs(n, board, visited);
            System.out.println(board[x2][y2]);
        }
    }

    public static void bfs(int n, int[][] board, boolean[][] visited) { 

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x1, y1));
        visited[x1][y1] = true;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            int x = curr.x;
            int y = curr.y;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny])
                    continue;
                board[nx][ny] = board[x][y] + 1;
                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
            }

        }
    }
}
