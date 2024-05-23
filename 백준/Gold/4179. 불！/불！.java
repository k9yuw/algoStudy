import java.io.*;
import java.util.*;

class Node {
    int y, x;
    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    
    static int r, c;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[][] board;
    static Queue<Node> jhQ = new LinkedList<>();
    static Queue<Node> fireQ = new LinkedList<>();
    static boolean[][] jhVisited;
    static boolean[][] fireVisited;
    
    public static void main(String[] args) throws IOException {
            
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        board = new char[r][c];
        jhVisited = new boolean[r][c];
        fireVisited = new boolean[r][c];
                
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {    
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'J') {
                    jhQ.add(new Node(i, j));
                    jhVisited[i][j] = true;
                } else if (board[i][j] == 'F') {
                    fireQ.add(new Node(i, j));
                    fireVisited[i][j] = true;
                }
            }
        }
        
        bfs();
    }
    
    static void bfs() {
        int time = 0;

        while (!jhQ.isEmpty()) {
            // 불이 먼저 확산
            int fireSize = fireQ.size();
            for (int i=0; i<fireSize; i++) {
                Node fire = fireQ.poll();
                for (int d=0; d<4; d++) {
                    int ny = fire.y + dy[d];
                    int nx = fire.x + dx[d];

                    if (ny<0 || nx<0 || ny>=r || nx>=c) continue;
                    if (fireVisited[ny][nx] || board[ny][nx] == '#') continue;

                    fireQ.add(new Node(ny, nx));
                    fireVisited[ny][nx] = true;
                }
            }

            // 지훈이가 이동한다.
            int jhSize = jhQ.size();
            for (int i=0; i<jhSize; i++) {
                Node jh = jhQ.poll();
                for (int d=0; d<4; d++) {
                    int ny = jh.y + dy[d];
                    int nx = jh.x + dx[d];

                    // 범위를 벗어났다는 것은 탈출했다는 뜻
                    if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
                        System.out.println(time + 1);
                        return;
                    }
                    if (jhVisited[ny][nx] || board[ny][nx] == '#' || fireVisited[ny][nx]) continue;

                    jhQ.add(new Node(ny, nx));
                    jhVisited[ny][nx] = true;
                }
            }
            time++;
        }
        System.out.println("IMPOSSIBLE");
    }
}
