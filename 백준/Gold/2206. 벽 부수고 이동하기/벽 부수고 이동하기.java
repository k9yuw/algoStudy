import java.io.*;
import java.util.*;

class Node {
    int y, x, dist;
    boolean isCrashed;

    public Node(int y, int x, int dist, boolean isCrashed) {
        this.y = y;
        this.x = x;
        this.dist = dist;
        this.isCrashed = isCrashed;
    }
}

public class Main {

    static int n, m;
    static int ans = -1;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static Queue<Node> q;
    static int[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(ans);

    }

    static void bfs() {

        q = new LinkedList<>();
        Node start = new Node(0, 0, 1, false);
        q.add(start);
        visited[0][0][0] = true; // 시작점 방문 처리

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.y == n - 1 && curr.x == m - 1) {
                ans = curr.dist;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];
                int ndist = curr.dist + 1;

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                if (board[ny][nx] == 0) { // 벽이 아닐 때 (이 때는 isCrashed 여부가 중요하지 않음)
                    if (!visited[ny][nx][curr.isCrashed ? 1 : 0]) { // 아직 방문하지 않은 곳이라면
                        q.add(new Node(ny, nx, ndist, curr.isCrashed));
                        visited[ny][nx][curr.isCrashed ? 1 : 0] = true; // 방문 처리
                    }
                } else { // 벽일 때
                    if (!curr.isCrashed && !visited[ny][nx][1]) { // 아직 벽을 부순 적이 없고, 벽을 부수고 방문하지 않은 곳이라면
                        q.add(new Node(ny, nx, ndist, true)); // 벽을 부수고 방문
                        visited[ny][nx][1] = true; // 방문 처리
                    }
                }
            }
        }
    }
}
