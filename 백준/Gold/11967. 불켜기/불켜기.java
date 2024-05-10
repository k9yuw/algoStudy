import java.io.*;
import java.util.*;

class Node {
    int y, x;
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int n, m;
    static int count = 1;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited, isLight;
    static ArrayList<Node>[][] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        switches = new ArrayList[n][n];
        isLight = new boolean[n][n];
        visited = new boolean[n][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            switches[y][x].add(new Node(a, b));
        }

        bfs();
        System.out.println(count);
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        isLight[0][0] = true;
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Node curr = q.poll();

            // 꺼낸 노드에 switch가 있는 경우
            if (!switches[curr.y][curr.x].isEmpty()) {
                visited = new boolean[n][n];
                visited[curr.y][curr.x] = true;
                for (Node node : switches[curr.y][curr.x]) {
                    // 불이 꺼져있다면 켜줌
                    if (!isLight[node.y][node.x]) {
                        isLight[node.y][node.x] = true;
                        count++;
                    }
                }
                // 켤 수 있는 곳을 다 켰기 때문에 없애줌
                switches[curr.y][curr.x].clear();
            }

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                if (ny<0 || nx<0 || ny>=n || nx>=n) continue;

                if (isLight[ny][nx] && !visited[ny][nx]) {
                    // 불이 켜져있고 방문을 하지 않았으면 방문
                    q.offer(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }
}

