import java.io.*;
import java.util.*;

public class Main {

    static int N, K, L;
    static int[][] board;
    static boolean[][] snakeBoard;
    static int ans = 0;
    static Coor head, tail;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int dir = 0; // 우0하1좌2상3
    static Queue<Command> commandList = new LinkedList<>();
    static Deque<Coor> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        snakeBoard = new boolean[N+1][N+1];
        snakeBoard[1][1] = true;
        head = new Coor(1,1);
        snake.add(head);

        // 사과
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = -1;
        }

        // 방향 변환
        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            commandList.add(new Command(x, c));
        }

        while(true) {
            ans++;
            if (!moveSnake()) break;

            if (!commandList.isEmpty() && ans == commandList.peek().time) {
                Command c = commandList.poll();
                if (c.dir.equals("L")) dir = (dir + 3) % 4;
                else if (c.dir.equals("D")) dir = (dir + 1) % 4;
            }
        }

        System.out.println(ans);
    }

    static boolean isOutOfRange() {
        if(head.y<1 || head.x<1 || head.y>N || head.x>N) return true;
        return false;
    }

    static boolean isSnake() {
        if(snakeBoard[head.y][head.x]) return true;
        return false;
    }

    static boolean moveSnake() {
        head = new Coor(head.y + dy[dir], head.x + dx[dir]);

        if (isOutOfRange() || isSnake()) return false;

        // 머리 위치 추가
        snake.addFirst(head);
        snakeBoard[head.y][head.x] = true;

        // 사과 여부에 따라 꼬리 처리
        if (doesAppleExist(head)) {
            board[head.y][head.x] = 0;
        } else {
            Coor tail = snake.pollLast();
            snakeBoard[tail.y][tail.x] = false;
        }

        return true;
    }

    static boolean doesAppleExist(Coor c) {
        if(board[c.y][c.x] == -1) return true;
        return false;
    }

    static class Coor {
        int y, x;
        public Coor(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Command {
        int time;
        String dir;
        public Command(int time, String dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}