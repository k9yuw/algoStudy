import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0}; // 좌,하,우,상
    static int[] dc = {1, 1, 2, 2};   // 토네이도의 각 방향으로 이동하는 횟수
    static int[] ratio = {2, 10, 7, 1, 5, 10, 7, 1, 2, 0};
    static int[][] tornadoX = {
        {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
        {0, 1, 0, -1, 2, 1, 0, -1, 0, 1},
        {2, 1, 1, 1, 0, -1, -1, -1, -2, 0},
        {0, -1, 0, 1, -2, -1, 0, 1, 0, -1}
    };

    static int[][] tornadoY = {
        {0, -1, 0, 1, -2, -1, 0, 1, 0, -1},
        {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
        {0, 1, 0, -1, 2, 1, 0, -1, 0, 1},
        {2, 1, 1, 1, 0, -1, -1, -1, -2, 0}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(spreadSand(n/2, n/2));
    }


    static int spreadSand(int x, int y){
        int totalOutSand = 0;

        while (true) {
            for(int dir=0; dir<4; dir++){
                for(int moveCount = 0; moveCount<dc[dir]; moveCount++){
                    //현재위치에서 이동
                    int nextX = x+dx[dir];
                    int nextY = y+dy[dir];

                    if(nextX<0 || nextY<0 || nextX>=n ||nextY>=n){
                        return totalOutSand;
                    }

                    //이동한 위치의 모래 뿌리기
                    int currSpreadingSand = board[nextX][nextY];
                    board[nextX][nextY] = 0;
                    int sum = 0;


                    for(int i=0; i<9; i++){
                        int spreadedX = nextX + tornadoX[dir][i];
                        int spreadedY = nextY + tornadoY[dir][i];
                        int currSectionSand = (currSpreadingSand * ratio[i]) / 100;

                        if(spreadedX<0 || spreadedX>=n || spreadedY<0 || spreadedY>=n){
                            totalOutSand += currSectionSand;
                        }
                        else{
                            board[spreadedX][spreadedY] += currSectionSand;
                        }
                        sum+= currSectionSand;
                    }

                    //알파
                    int alphaX = nextX + dx[dir];
                    int alphaY = nextY + dy[dir];
                    int alphaAmount = currSpreadingSand - sum;
                    if(alphaX<0 || alphaX>=n || alphaY<0|| alphaY>=n){
                        totalOutSand +=alphaAmount;
                    }
                    else{
                        board[alphaX][alphaY] +=alphaAmount;
                    }


                    //이동한 위치를 현재위치로 업데이트
                    x = nextX;
                    y = nextY;
                }
            }

            //횟수 업데이트
            for(int i=0; i<4; i++){
                dc[i] +=2;
            }
        }
    }
}

