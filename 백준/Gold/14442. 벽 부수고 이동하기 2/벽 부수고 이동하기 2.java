import java.io.*;
import java.util.*;

class Node {
    int y, x, moveCnt, breakCnt;
    public Node(int y, int x, int moveCnt, int breakCnt) {
        this.y = y;
        this.x = x;
        this.moveCnt = moveCnt;
        this.breakCnt = breakCnt;
    }
}

public class Main {

    static int n, m, k;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m][k + 1];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            // 도착점에 도달한 경우
            if (curr.y == n - 1 && curr.x == m - 1) {
                return curr.moveCnt;
            }

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                // 범위, 방문여부 확인
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                // 벽이 아니고, 방문하지 않은 경우
                if (board[ny][nx] == 0 && !visited[ny][nx][curr.breakCnt]) {
                    visited[ny][nx][curr.breakCnt] = true;
                    q.add(new Node(ny, nx, curr.moveCnt + 1, curr.breakCnt));
                }

                // 벽이지만 breakCnt가 아직 k에 도달하지 않은 경우
                if (board[ny][nx] == 1 && curr.breakCnt < k && !visited[ny][nx][curr.breakCnt + 1]) {
                    visited[ny][nx][curr.breakCnt + 1] = true;
                    q.add(new Node(ny, nx, curr.moveCnt + 1, curr.breakCnt + 1));
                }
            }
        }
        return -1;
    }
}
