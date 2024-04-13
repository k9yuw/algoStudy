import java.io.*;
import java.util.*;

public class Main{

    static int n;
    // 좌, 하, 우, 상
    static int[] dy = {0,1,0,-1};
    static int[] dx = {-1,0,1,0};
    static int[] dc = {1,1,2,2};
    static int[] ratio = {2, 10, 7, 1, 5, 10, 7, 1, 2, 0};
    static int[][] board;
    static int[][] ty = {
            {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
            {0, 1, 0, -1, 2, 1, 0, -1, 0, 1},
            {2, 1, 1, 1, 0, -1, -1, -1, -2, 0},
            {0, -1, 0, 1, -2, -1, 0, 1, 0, -1}
    };
    static int[][] tx = {
            {0, -1, 0, 1, -2, -1, 0, 1, 0, -1},
            {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
            {0, 1, 0, -1, 2, 1, 0, -1, 0, 1},
            {2, 1, 1, 1, 0, -1, -1, -1, -2, 0}
    };

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(spread(n/2, n/2));
    }

    
    static int spread(int y, int x) {

        int totalOutSand = 0;
        while (true) {

            for (int dir = 0; dir < 4; dir++) {
                for (int dist = 0; dist < dc[dir]; dist++) {

                    // 토네이도로 인한 좌표 이동
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];

                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) return totalOutSand;


                    // 이동한 위치에서 모래 날리기
                    int currSpreadingSand = board[ny][nx];
                    board[ny][nx] = 0;

                    // 각 구역의 모래 계산
                    int sum = 0;
                    for (int i=0; i<9; i++) {

                        int currY = ny + ty[dir][i];
                        int currX = nx + tx[dir][i];
                        int currSectionSand = currSpreadingSand * ratio[i] / 100;

                        if (currY < 0 || currX < 0 || currY >= n || currX >= n) {
                            totalOutSand += currSectionSand;
                        } else {
                            board[currY][currX] += currSectionSand;
                        }

                        sum += currSectionSand;
                    }   

                    // 알파 위치 모래 계산
                    int alphaY = ny + dy[dir];
                    int alphaX = nx + dx[dir];
                    int alphaSand = currSpreadingSand - sum;

                    if (alphaY < 0 || alphaX < 0 || alphaY >= n || alphaX >= n) {
                        totalOutSand += alphaSand;
                    } else {
                        board[alphaY][alphaX] += alphaSand;
                    }

                    // 위치 업데이트
                    y = ny;
                    x = nx;

                }
            }

            // 달팽이 모양 길이 업데이트
            for(int i = 0; i < 4; i++) {
                dc[i] += 2;
            }

        }
    }

}