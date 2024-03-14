import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class BeadsStatus { 
        int redX, redY;
        int blueX, blueY;
        int cnt;

        public BeadsStatus(int redX, int redY, int blueX, int blueY, int cnt) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        BeadsStatus init = new BeadsStatus(0,0,0,0,0); 
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    init.redX = i;
                    init.redY = j; 
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    init.blueX = i; 
                    init.blueY = j; 
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(init));
    }

    static int bfs(BeadsStatus beads) {
        Queue<BeadsStatus> queue = new LinkedList<>();
        queue.offer(beads);

        while (!queue.isEmpty()) {
            BeadsStatus dequeuedBeads = queue.poll();

            if (dequeuedBeads.cnt == 10) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int redX = dequeuedBeads.redX;
                int redY = dequeuedBeads.redY;
                int blueX = dequeuedBeads.blueX;
                int blueY = dequeuedBeads.blueY;
                boolean isRedHole = false;
                boolean isBlueHole = false;

                // red의 위치 이동
                while (true) {
                    int newRedX = redX + dx[d];
                    int newRedY = redY + dy[d];

                    if (board[newRedX][newRedY] == '#') {
                        break;
                    }

                    if (board[newRedX][newRedY] == 'O') {
                        isRedHole = true;
                        break;
                    }

                    redX = newRedX;
                    redY = newRedY;
                }

                // blue의 위치 이동
                while (true) {
                    int newBlueX = blueX + dx[d];
                    int newBlueY = blueY + dy[d]; 

                    if (board[newBlueX][newBlueY] == '#') {
                        break;
                    }

                    if (board[newBlueX][newBlueY] == 'O') {
                        isBlueHole = true;
                        break;
                    }

                    blueX = newBlueX;
                    blueY = newBlueY; 
                }

                if (isBlueHole) {
                    continue;
                } else if (isRedHole) {
                    return dequeuedBeads.cnt+1; 
                }

                if (dequeuedBeads.redX == redX && dequeuedBeads.redY == redY && dequeuedBeads.blueX == blueX && dequeuedBeads.blueY == blueY) {
                    continue;
                }

                if (redX == blueX && redY == blueY) {
                    if (d == 0) {
                        if (dequeuedBeads.redX < dequeuedBeads.blueX) redX--;
                        else blueX--;
                    } else if (d == 1) {
                        if (dequeuedBeads.redX < dequeuedBeads.blueX) blueX++;
                        else redX++;
                    } else if (d == 2) {
                        if (dequeuedBeads.redY < dequeuedBeads.blueY) redY--;
                        else blueY--;
                    } else if (d == 3) {
                        if (dequeuedBeads.redY < dequeuedBeads.blueY) blueY++;
                        else redY++;
                    }
                }

                queue.offer(new BeadsStatus(redX, redY, blueX, blueY, dequeuedBeads.cnt + 1));
            }
        }

        return -1;
    }
}
