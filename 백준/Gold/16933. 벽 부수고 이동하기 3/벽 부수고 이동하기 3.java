import java.io.*;
import java.util.*;

class Node {
    int y, x, moveCnt, breakCnt;
    boolean isDay;

    public Node(int y, int x, int moveCnt, int breakCnt, boolean isDay) {
        this.y = y;
        this.x = x;
        this.moveCnt = moveCnt;
        this.breakCnt = breakCnt;
        this.isDay = isDay;
    }
}

public class Main {
    static int n, m, k;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m][k+1];

        for (int i=0; i<n; i++) {
            String str = br.readLine();
            for (int j=0; j<m; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0, true));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.y == n-1 && curr.x == m-1) {
                return curr.moveCnt;
            }

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                // 범위 내 체크
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                // 벽이 아닌 경우: 낮이든 밤이든 움직일 수 있음.
                if (board[ny][nx] == 0 && !visited[ny][nx][curr.breakCnt]) {
                    q.add(new Node(ny, nx, curr.moveCnt + 1, curr.breakCnt, !curr.isDay));
                    visited[ny][nx][curr.breakCnt] = true;
                }

                // 벽인 경우: 낮일 때와 밤일 때를 각각 다르게 처리해야 함.
                else if (board[ny][nx] == 1 && curr.breakCnt < k && !visited[ny][nx][curr.breakCnt + 1]) {
                    if (curr.isDay) { // 벽이 있고 낮일 때 벽을 부숨
                        q.add(new Node(ny, nx, curr.moveCnt + 1, curr.breakCnt + 1, !curr.isDay));
                        visited[ny][nx][curr.breakCnt + 1] = true;
                    } else {
                        // 벽이 있고 밤일 때 제자리에 머물며 낮을 기다림
                        q.add(new Node(curr.y, curr.x, curr.moveCnt + 1, curr.breakCnt, !curr.isDay));
                    }
                }
            }
        }
        return -1;
    }
}
