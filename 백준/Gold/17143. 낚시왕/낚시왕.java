import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static int[][] board; // 각 칸에 존재하는 상어의 id (없는 경우 -1)
    static HashMap<Integer, Shark> sharks;
    static int ans = 0;

    // 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
    static int[] dY = {0, -1, 1, 0, 0};
    static int[] dX = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[R + 1][C + 1];
        // 보드 초기화
        for (int i = 1; i <= R; i++) {
            Arrays.fill(board[i], -1);
        }

        sharks = new HashMap<>();
        for (int id = 1; id <= M; id++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r, c, speed, dir, size);
            sharks.put(id, shark);
            board[r][c] = id;
        }

        // 낚시꾼은 1번 열부터 C번 열까지 이동
        for (int col = 1; col <= C; col++) {
            fishing(col);
            moveSharks();
        }

        System.out.println(ans);
    }

    // 현재 열에서 가장 위에 있는 상어를 잡는다.
    public static void fishing(int col) {
        for (int r = 1; r <= R; r++) {
            int sharkId = board[r][col];
            if (sharkId != -1) {
                // 해당 상어를 잡고 제거
                Shark caught = sharks.get(sharkId);
                if (caught != null) {
                    ans += caught.size;
                    sharks.remove(sharkId);
                    board[r][col] = -1;
                }
                break;
            }
        }
    }

    // 모든 상어를 이동시키고, 겹치는 경우 크기가 큰 상어만 남김
    public static void moveSharks() {
        int[][] newBoard = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            Arrays.fill(newBoard[i], -1);
        }
        HashMap<Integer, Shark> newSharks = new HashMap<>();

        // 기존 상어들에 대해 새 위치 계산
        for (Map.Entry<Integer, Shark> entry : sharks.entrySet()) {
            int id = entry.getKey();
            Shark shark = entry.getValue();

            // 상어 이동
            int[] moveResult = moveShark(shark);
            shark.r = moveResult[0];
            shark.c = moveResult[1];
            shark.dir = moveResult[2];

            // 해당 칸에 상어가 없는 경우 그대로 배치
            if (newBoard[shark.r][shark.c] == -1) {
                newBoard[shark.r][shark.c] = id;
                newSharks.put(id, shark);
            } else {
                // 이미 상어가 있는 경우, 크기가 큰 상어가 승리
                int otherId = newBoard[shark.r][shark.c];
                Shark other = newSharks.get(otherId);
                if (other.size < shark.size) {
                    newSharks.remove(otherId);
                    newBoard[shark.r][shark.c] = id;
                    newSharks.put(id, shark);
                }
            }
        }

        board = newBoard;
        sharks = newSharks;
    }

    // 현재 상어의 위치, 속도, 방향을 이용하여 새 위치, 새 방향 계산
    public static int[] moveShark(Shark shark) {
        int r = shark.r;
        int c = shark.c;
        int s = shark.speed;
        int d = shark.dir;

        // 상하 이동
        if (d == 1 || d == 2) {
            int mod = s % (2 * (R - 1));
            for (int i = 0; i < mod; i++) {
                if (d == 1 && r == 1) d = 2;
                else if (d == 2 && r == R) d = 1;
                r += (d == 1 ? -1 : 1);
            }
        }
        
        // 좌우 이동인 경우
        else {
            int mod = s % (2 * (C - 1));
            for (int i = 0; i < mod; i++) {
                if (d == 4 && c == 1) d = 3;
                else if (d == 3 && c == C) d = 4;
                c += (d == 4 ? -1 : 1);
            }
        }

        return new int[]{r, c, d};
    }
}

class Shark {
    int r, c;
    int speed, dir, size;

    public Shark(int r, int c, int speed, int dir, int size) {
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }
}