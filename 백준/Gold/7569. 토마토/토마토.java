import java.io.*;
import java.util.*;

public class Main {

    static class Tomato {
        int x, y, z;

        public Tomato(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int m, n, h;
    static int[][][] box;
    static Queue<Tomato> queue = new LinkedList<>();

    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        box = new int[m][n][h];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < m; k++) {
                    box[k][j][i] = Integer.parseInt(st.nextToken());
                    if (box[k][j][i] == 1) {
                        queue.add(new Tomato(k, j, i)); // 익은 토마토 추가
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int maxDay = 0;

        while (!queue.isEmpty()) {
            Tomato currentTomato = queue.poll();
            int x = currentTomato.x;
            int y = currentTomato.y;
            int z = currentTomato.z;

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (isValidTomato(nx, ny, nz)) {
                    queue.offer(new Tomato(nx, ny, nz));
                    box[nx][ny][nz] = box[x][y][z] + 1;
                    maxDay = Math.max(maxDay, box[nx][ny][nz]);
                }
            }
        }

        // 모든 토마토가 익은 상태인지 확인
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    // 안 익은 토마토가 있는 경우
                    if (box[k][j][i] == 0)
                        return -1;
                }
            }
        }

        if (maxDay == 0) return 0;
        else return maxDay - 1; // 처음에 1일이 경과
    }


    private static boolean isValidTomato(int x, int y, int z) {
        // 주어진 범위를 벗어나거나 이미 익은 토마토인 경우 false 반환
        if (x < 0 || x >= m || y < 0 || y >= n || z < 0 || z >= h || box[x][y][z] != 0) return false;
        return true;
    }
}
