import java.io.*;
import java.util.*;

class Shark {
    int y, x, dir;
    int[][] priority = new int[4][4];
}

public class Main {

    // 상, 하, 좌, 우
    static int[] dy = { -1, +1, 0, 0 };
    static int[] dx = { 0, 0, -1, +1 };
    static int n, m, k, ans;

    // [n][n][0]: 해당 칸에 있는 상어의 번호
    // [n][n][1]: 해당 칸에 있는 냄새의 번호
    // [n][n][2]: 해당 칸에 있는 냄새의 유효 시간
    static int[][][] board = new int[20][20][3];

    // 상어들의 정보를 저장하는 배열. 이 때 i번 상어는 i-1번째 인덱스에 저장이 됨 주의!
    static Shark[] shark = new Shark[400];


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 기본 값 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // n x n board 입력
        for (int y=0; y<n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x=0; x<n; x++) {
                board[y][x][0] = Integer.parseInt(st.nextToken());

                // 해당 칸에 상어가 존재할 경우, board에 해당 상어 번호를 저장한 후
                // shark 배열에 해당 상어의 위치 정보 저장
                if (board[y][x][0] != 0) {
                    int sharkNumber = board[y][x][0] - 1;
                    board[y][x][1] = board[y][x][0];
                    board[y][x][2] = k;

                    shark[sharkNumber] = new Shark();
                    shark[sharkNumber].y = y;
                    shark[sharkNumber].x = x;
                }
            }
        }

        // m마리 상어에 대한 방향 입력
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            shark[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        // m마리 상어에 대한 priority 입력
        for (int i=0; i<m; i++) {
            for (int d=0; d<4; d++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<4; j++) {
                    shark[i].priority[d][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        ans = -1;
        solve();
        System.out.println(ans);

    }

    static void solve() {
        int time = 0;
        int killShark = 0;
        while (time <= 1000) {
            time++;

            int[][][] tempBoard = new int[20][20][3];

            //// 1. tempBoard 생성 후 값들을 복사
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    tempBoard[y][x][0] = board[y][x][0];
                    tempBoard[y][x][2] = board[y][x][2];

                    // 냄새가 유효한 경우, 유효시간을 1 감소 시키고
                    if (tempBoard[y][x][2] > 0) {
                        tempBoard[y][x][2]--;
                    }
                    // 그래도 계속 냄새가 유효한 경우, 냄새의 주인을 복사
                    if (tempBoard[y][x][2] > 0) {
                        tempBoard[y][x][1] = board[y][x][1];
                    }
                }
            }

            //// 2. m마리 상어 중 i번째 상어의 다음 위치 탐색하고, 이동시키기
            //// 여기서 제일 중요한건 값이 조건을 충족하는지는 board에서 확인하고, 바꾸는건 tempBoard에서 !
            for (int i = 0; i < m; i++) {
                // 현재 상어의 위치값 입력
                int cy = shark[i].y;
                int cx = shark[i].x;
                int cd = shark[i].dir;

                // i번째 상어가 죽은 경우
                if (cy == -1) continue;

                boolean isMove = false;
                // cd에 따른 priority에 의거하여 다음 상어의 경로 탐색
                for(int d=0; d < 4; d++){
                    int nd = shark[i].priority[cd][d];
                    int ny = cy + dy[nd];
                    int nx = cx + dx[nd];

                    // 범위 벗어나거나, 냄새 남아있을 경우 상어의 이동위치 재탐색
                    if (ny < 0 || ny >= n || nx < 0 || nx >= n || board[ny][nx][2] != 0) {
                        continue;
                    }

                    // 상어가 이동하고, 현재의 위치에서 상어는 떠남
                    isMove = true;
                    tempBoard[cy][cx][0] = 0;

                    // 탐색한 위치가 비었을 경우 i번째 상어의 냄새를 뿌리고, 비어있지 않은 경우 상어 죽임
                    if (tempBoard[ny][nx][0] == 0) {
                        tempBoard[ny][nx][0] = i + 1;
                        tempBoard[ny][nx][1] = i + 1;
                        tempBoard[ny][nx][2] = k;

                        shark[i].y = ny;
                        shark[i].x = nx;
                        shark[i].dir = nd;
                    }
                    // 탐색한 위치가 비어있지 않은 경우상어 죽이기
                    else {
                        killShark++;
                        shark[i].y = -1;
                    }
                    break;
                }

                // 상어가 움직이지 못하는 경우 (인접한 네 칸에 모두 냄새가 남아있는 경우)
                if (!isMove) {
                    for (int d=0; d < 4; d++) {
                        int nd = shark[i].priority[cd][d];
                        int ny = cy + dy[nd];
                        int nx = cx + dx[nd];

                        // 해당 범위 내인지 확인
                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }

                        // 냄새가 계속 남아있지만, i번째 상어의 것이 아닌 경우
                        if (board[ny][nx][2] != 0 && board[ny][nx][1] != i + 1) {
                            continue;
                        }

                        // 인접한 칸 중에서 자신의 냄새가 남아있는 칸 중 비어있는 곳으로 이동하여 냄새를 다시 뿌림
                        tempBoard[cy][cx][0] = 0;
                        if (tempBoard[ny][nx][0] == 0) {
                            tempBoard[ny][nx][0] = i + 1;
                            tempBoard[ny][nx][1] = i + 1;
                            tempBoard[ny][nx][2] = k;

                            shark[i].y = ny;
                            shark[i].x = nx;
                            shark[i].dir = nd;
                        }
                        else {
                            killShark++;
                            shark[i].y = -1;
                        }
                        break; // !isMove를 벗어남
                    }
                }
            }

            // 3. 상어가 1마리만 남게된 경우 종료
            if (killShark == m - 1) {
                break;
            }

            // 4. tempBoard의 값을 board로 복사해서 넘겨줌
            for (int y = 0; y < n; y++) {
                System.arraycopy(tempBoard[y], 0, board[y], 0, n);
            }
        }

        // 5. time이 1000 이하가 아닌 경우 초기에 설정해둔 ans 값인 -1이 그대로 출력됨.
        if (time <= 1000) {
            ans = time;
        }
    }

}
