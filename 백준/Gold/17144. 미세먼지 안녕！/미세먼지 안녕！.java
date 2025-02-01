import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] board;
    static int airPurifierTop = -1, airPurifierBottom = -1;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for(int r=0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());

                if(board[r][c] == -1) { // 무조건 상단부부터 입력이 들어올 것
                    if(airPurifierTop == -1) {
                        airPurifierTop = r;
                    } else {
                        airPurifierBottom = r;
                    }
                }

            }
        }

        for(int t=0; t<T; t++){
            spreadDust();
            runAirPurifier();
        }

        System.out.println(sumTotalDust());
    }


    static void spreadDust() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                temp[i][j] = board[i][j];
            }
        }

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(board[r][c] > 0) {
                    int spreadAmount = board[r][c] / 5;
                    int count = 0;

                    for (int d = 0; d < 4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        // If within bounds and not an air purifier
                        if(nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] != -1) {
                            temp[nr][nc] += spreadAmount;
                            count++;
                        }
                    }

                    temp[r][c] -= spreadAmount * count;
                }
            }
        }

        board = temp;
    }


    static void runAirPurifier() {

        // 윗부분(반시계)
        for (int r = airPurifierTop - 1; r > 0; r--){
            board[r][0] = board[r - 1][0];
        }

        for (int c = 0; c < C - 1; c++){
            board[0][c] = board[0][c + 1];
        }

        for (int r = 0; r < airPurifierTop; r++){
            board[r][C - 1] = board[r + 1][C - 1];
        }

        for (int c = C - 1; c > 1; c--){
            board[airPurifierTop][c] = board[airPurifierTop][c - 1];
        }

        board[airPurifierTop][1] = 0;

        // 아랫부분(시계)
        for (int r = airPurifierBottom + 1; r < R - 1; r++){
            board[r][0] = board[r + 1][0];
        }

        for (int c = 0; c < C - 1; c++){
            board[R - 1][c] = board[R - 1][c + 1];
        }

        for (int r = R - 1; r > airPurifierBottom; r--){
            board[r][C - 1] = board[r - 1][C - 1];
        }

        for (int c = C - 1; c > 1; c--){
            board[airPurifierBottom][c] = board[airPurifierBottom][c - 1];
        }

        board[airPurifierBottom][1] = 0;

        board[airPurifierTop][0] = -1;
        board[airPurifierBottom][0] = -1;
    }

    static int sumTotalDust() {
        int sum = 0;
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(board[r][c] > 0) {
                    sum += board[r][c];
                }
            }
        }
        return sum;
    }

}