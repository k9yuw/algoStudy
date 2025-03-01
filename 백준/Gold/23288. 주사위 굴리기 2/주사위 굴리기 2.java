import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] board;
    static int sum = 0;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0}; // 동남서북
    static int currDir = 0; // 초기 방향: 동
    static Position currPos = new Position(1, 1);
    static Position nextPos = new Position(1, 2);
    static Dice dice = new Dice(1, 6, 2, 5, 4, 3);

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            getScorePerStep();
        }

        System.out.println(sum);
    }

    public static void getScorePerStep() {
        // 1. 현재 위치와 방향을 통해 다음 위치 계산
        int dir = getDirection();
        nextPos.y = currPos.y + dy[dir];
        nextPos.x = currPos.x + dx[dir];

        // 2. 다음 칸에서 BFS를 통해 점수를 계산 (영역 크기 * 칸의 숫자)
        int score = bfs(nextPos.y, nextPos.x);
        sum += score;

        // 3. 현재 위치를 다음 위치로 업데이트
        currPos.y = nextPos.y;
        currPos.x = nextPos.x;

        // 4. 주사위를 해당 방향으로 굴리기
        rollDice(dir);

        // 5. 주사위 바닥 면(A)과 도착 칸의 숫자(B)를 비교하여 진행 방향 업데이트
        updateDirection();
    }

    public static int bfs(int sY, int sX) {
        boolean[][] visited = new boolean[N+1][M+1];
        Queue<Position> q = new LinkedList<>();
        int num = board[sY][sX];
        int count = 0;
        q.add(new Position(sY, sX));
        visited[sY][sX] = true;

        while (!q.isEmpty()) {
            Position curr = q.poll();
            count++;
            for (int d = 0; d < 4; d++) {
                int nY = curr.y + dy[d];
                int nX = curr.x + dx[d];
                if (nY < 1 || nY > N || nX < 1 || nX > M) continue;
                if (!visited[nY][nX] && board[nY][nX] == num) {
                    visited[nY][nX] = true;
                    q.add(new Position(nY, nX));
                }
            }
        }
        return num * count;
    }

    public static void rollDice(int dir) {
        int temp;
        switch (dir) {
            case 0: // 동
                temp = dice.top;
                dice.top = dice.left;
                dice.left = dice.bottom;
                dice.bottom = dice.right;
                dice.right = temp;
                break;
            case 1: // 남
                temp = dice.top;
                dice.top = dice.front;
                dice.front = dice.bottom;
                dice.bottom = dice.back;
                dice.back = temp;
                break;
            case 2: // 서
                temp = dice.top;
                dice.top = dice.right;
                dice.right = dice.bottom;
                dice.bottom = dice.left;
                dice.left = temp;
                break;
            case 3: // 북
                temp = dice.top;
                dice.top = dice.back;
                dice.back = dice.bottom;
                dice.bottom = dice.front;
                dice.front = temp;
                break;
        }
    }

    // currDir으로 다음 칸이 범위 내에 있는지 확인하고, 벗어났다면 방향을 반대로 전환
    public static int getDirection() {
        int ny = currPos.y + dy[currDir];
        int nx = currPos.x + dx[currDir];
        if (ny < 1 || ny > N || nx < 1 || nx > M) {
            currDir = (currDir + 2) % 4;
        }
        return currDir;
    }

    // 이동 후 주사위 바닥 면(A)와 도착 칸의 숫자(B)를 비교하여 진행 방향을 업데이트
    public static void updateDirection() {
        int A = dice.bottom;
        int B = board[currPos.y][currPos.x];
        if (A > B) {
            currDir = (currDir + 1) % 4;
        } else if (A < B) {
            currDir = (currDir + 3) % 4; // (currDir - 1 + 4) % 4
        }
    }

    public static class Position {
        int y, x;
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static class Dice {
        int top, bottom, front, back, left, right;
        public Dice(int top, int bottom, int front, int back, int left, int right) {
            this.top = top;
            this.bottom = bottom;
            this.front = front;
            this.back = back;
            this.left = left;
            this.right = right;
        }
    }
}