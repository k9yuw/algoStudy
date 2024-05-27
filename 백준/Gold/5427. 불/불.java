import java.io.*;
import java.util.*;

class Node {
    int y, x;
    Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static int t;
    static int w, h;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static char[][] board;
    static boolean[][] fireVisited;
    static boolean[][] sgVisited;
    static Queue<Node> fireQ;
    static Queue<Node> sgQ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            board = new char[h][w];  // h와 w를 반대로 해서 배열 생성
            fireVisited = new boolean[h][w];
            sgVisited = new boolean[h][w];
            fireQ = new LinkedList<>();
            sgQ = new LinkedList<>();

            for (int j = 0; j < h; j++) {  // 높이인 h를 기준으로 반복
                String str = br.readLine();
                for (int k = 0; k < w; k++) {  // 너비인 w를 기준으로 반복
                    board[j][k] = str.charAt(k);
                    if (board[j][k] == '*') {
                        fireQ.add(new Node(j, k));
                        fireVisited[j][k] = true;
                    } else if (board[j][k] == '@') {
                        sgQ.add(new Node(j, k));
                        sgVisited[j][k] = true;
                    }
                }
            }
            bfs();
        }
    }

    static void bfs() {
        int time = 0;

        while (!sgQ.isEmpty()) {

            // 불을 먼저 확산시킴
            int fireSize = fireQ.size();
            for (int i = 0; i < fireSize; i++) {
                Node fire = fireQ.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = fire.y + dy[d];
                    int nx = fire.x + dx[d];

                    if (ny < 0 || nx < 0 || ny >= h || nx >= w) continue;  // h와 w를 올바르게 참조
                    if (fireVisited[ny][nx] || board[ny][nx] == '#') continue;

                    fireQ.add(new Node(ny, nx));
                    fireVisited[ny][nx] = true;
                }
            }

            // 상근이 이동
            int sgSize = sgQ.size();
            for (int i = 0; i < sgSize; i++) {
                Node sg = sgQ.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = sg.y + dy[d];
                    int nx = sg.x + dx[d];

                    if (ny < 0 || nx < 0 || ny >= h || nx >= w) {
                        System.out.println(time + 1);
                        return;
                    }

                    if (fireVisited[ny][nx] || sgVisited[ny][nx] || board[ny][nx] == '#') continue;

                    sgQ.add(new Node(ny, nx));
                    sgVisited[ny][nx] = true;
                }
            }
            time++;
        }
        System.out.println("IMPOSSIBLE");
    }
}
