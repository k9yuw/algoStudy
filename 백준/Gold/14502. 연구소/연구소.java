import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int answer;
    public static int[][] board;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        createWall(0);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    public static void createWall(int wallCount) {

        if (wallCount == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    createWall(wallCount + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();

        int[][] newBoard = new int[N][M];
        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = board[i][j];
                if (newBoard[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }

                if (newBoard[i][j] == 0) { // 전체 안전지역 개수 세기
                    safeArea++;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                // 빈칸이면 바이러스 증식
                if (newBoard[nx][ny] == 0) {
                    newBoard[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                    safeArea--;
                }
            }
        }

        answer = Math.max(answer, safeArea);
    }
}