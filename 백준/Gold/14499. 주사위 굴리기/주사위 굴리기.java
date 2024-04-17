import java.io.*;
import java.util.*;

public class Main{

    static int n, m, k;
    static int diceY, diceX;
    static int[] dice = new int[7];
    static int[] dy = {0,0,-1,1};
    static int[] dx = {1,-1,0,0}; // 동서북남
    static int[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        diceY = Integer.parseInt(st.nextToken());
        diceX = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            int dir = Integer.parseInt(st.nextToken());
            move(dir);
        }

    }

    static void move(int dir){
        int ny = diceY + dy[dir-1];
        int nx = diceX + dx[dir-1];
        if(ny<0 || nx<0 || ny>=n || nx>=m) return;
        roll(dir, ny, nx);
        diceY = ny;
        diceX = nx;
    }

    // 1 2 3 4 (동 서 남 북)
    static void roll(int dir, int y, int x) {
        int tmp = dice[3];
        switch(dir) {
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
                break;
            case 4:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
        }
        if(board[y][x] == 0) {
            board[y][x] = dice[6];
        } else {
            dice[6] = board[y][x];
            board[y][x] =0;
        }
        System.out.println(dice[3]);

    }

}