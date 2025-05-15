import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] board;
    static int[][][] dp;
    static Pipe pipe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        dp = new int[3][N+1][N+1];
        for(int w=0; w<3; w++) {
            for(int i=0; i<=N; i++) {
                Arrays.fill(dp[w][i], -1);
            }
        }


        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pipe = new Pipe(0, new Coor(1, 2));
        System.out.println(dfs(pipe));
    }

    public static int dfs(Pipe p) {
        if(p.end.y == N && p.end.x == N) return 1;

        int eY = p.end.y;
        int eX = p.end.x;
        if(dp[p.dir][eY][eX] != -1) return dp[p.dir][eY][eX];

        Coor right = new Coor(eY, eX+1);
        Coor down = new Coor(eY+1, eX);
        Coor diagonal = new Coor(eY+1, eX+1);

        if(isHorizontal(p)) {
            int sum = 0;
            if(canGo(right)){
                sum += dfs(new Pipe(0, right));
            }
            if(canGo(diagonal) && canGo(right) && canGo(down)) {
                sum += dfs(new Pipe(2, diagonal));
            }

            return dp[0][eY][eX] = sum;
        }
        else if(isVertical(p)) {
            int sum = 0;
            if(canGo(down)) {
                sum += dfs(new Pipe(1, down));
//                System.out.println("dfs 세로 호출 " + down.toString());
            }
            if(canGo(diagonal) && canGo(right) && canGo(down)) {
                sum += dfs(new Pipe(2, diagonal));
//                System.out.println("dfs 대각선 호출 " + diagonal.toString());
            }

            return dp[1][eY][eX] = sum;
        }
        else if(isDiagonal(p)) {
            int sum = 0;
            if(canGo(right))  {
                sum += dfs(new Pipe(0, right));
//                System.out.println("dfs 가로 호출 " + right.toString());
            }
            if(canGo(down)) {
                sum += dfs(new Pipe(1, down));
//                System.out.println("dfs 세로 호출 " + down.toString());
            }
            if(canGo(diagonal) && canGo(right) && canGo(down)) {
                sum += dfs(new Pipe(2, diagonal));
//                System.out.println("dfs 대각선 호출 " + diagonal.toString());
            }

            return dp[2][eY][eX] = sum;
        }

        return 0;
    }


    public static boolean canGo(Coor c) {
        if(c.y<1 || c.x<1 || c.y>N || c.x>N) return false;
        if(board[c.y][c.x] == 0) return true;
        return false;
    }
    public static boolean isHorizontal(Pipe p) {
        if(p.dir == 0) return true;
        return false;
    }

    public static boolean isVertical(Pipe p) {
        if(p.dir == 1) return true;
        return false;
    }

    public static boolean isDiagonal(Pipe p) {
        if(p.dir == 2) return true;
        return false;
    }

    public static class Pipe {
        int dir; // 0 가로 1 세로 2 대각선
        Coor end;

        public Pipe(int dir, Coor end) {
            this.dir = dir;
            this.end = end;
        }
    }

    public static class Coor {
        int y, x;
        public Coor(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Coor{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
}