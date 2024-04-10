import java.io.*;
import java.util.*;

class Fish {
    int y, x;
    int dir;

    Fish(int y, int x, int dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
}

public class Main {
    static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static final int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };

    static int ans;

    public static void main(String[] args) throws IOException{
        Fish[] fish = new Fish[16];
        int[][] board = new int[4][4];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int y = 0; y < 4; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 4; ++x) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                a--;
                b--;
                fish[a] = new Fish(y, x, b);
                board[y][x] = a;
            }
        }
        ans = 0;
        solve(board, fish, 0, 0, 0);
        System.out.println(ans);
    }

    static void solve(int[][] board, Fish[] fish, int sharkY, int sharkX, int sum) {
        int[][] tempBoard = new int[4][4];
        Fish[] tempFish = new Fish[16];
        for (int y = 0; y < 4; ++y) {
            // arraycopy: (원본 배열, 원본 배열에서 복사 시작 위치, 대상 배열, 대상 배열에서 복사 시작 위치, 복사할 요소 개수)
            System.arraycopy(board[y], 0, tempBoard[y], 0, 4);
        }
        System.arraycopy(fish, 0, tempFish, 0, 16);

        //// 현재 상어 위치의 물고기 먹기
        int fishNumber = tempBoard[sharkY][sharkX];
        int sharkDir = tempFish[fishNumber].dir;
        tempFish[fishNumber] = new Fish(-1, -1, -1);
        tempBoard[sharkY][sharkX] = -1;

        // sum에 먹은 물고기값 더하고 ans 갱신
        sum += (fishNumber + 1);
        ans = Math.max(ans, sum);

        //// 물고기 이동
        for (int f = 0; f < 16; ++f) {

            // 해당 자리에 물고기가 없는 경우
            if (tempFish[f].y == -1) {
                continue;
            }

            // 현재 물고기
            int cy = tempFish[f].y;
            int cx = tempFish[f].x;
            int cd = tempFish[f].dir;

            // 현재 물고기에서 탐색하여 찾은 바꿀 물고기
            int ny = cy + dy[cd];
            int nx = cx + dx[cd];
            int nd = cd;

            // 탐색한 물고기가 범위를 벗어나거나 상어 위치에 있는 경우,
            // 방향을 틀어 재탐색
            while (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || (ny == sharkY && nx == sharkX)) {
                nd = (nd + 1) % 8;
                ny = cy + dy[nd];
                nx = cx + dx[nd];
            }

            // 타겟 물고기 <-> 현재 물고기
            // 이는 tempFish, tempBoard에서 다 이루어져야 함
            if (tempBoard[ny][nx] != -1) {
                int target = tempBoard[ny][nx];
                tempFish[target] = new Fish(cy, cx, tempFish[target].dir);
                tempFish[f] = new Fish(ny, nx, nd);

                tempBoard[ny][nx] = f;
                tempBoard[cy][cx] = target;
            } else {
                // 탐색한 자리에 물고기가 없을 경우
                // 현재 물고기 자리에는 (-1, -1, -1)을 넣어주고
                // 탐색한 자리에는 현재 물고기를 넣어줌
                tempFish[f] = new Fish(ny, nx, nd);

                tempBoard[ny][nx] = f;
                tempBoard[cy][cx] = -1;
            }
        }

        //// 상어 움직임
        for (int dist = 1; dist < 4; dist++) {
            int ny = sharkY + dy[sharkDir] * dist;
            int nx = sharkX + dx[sharkDir] * dist;
            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) {
                break;
            }
            if (tempBoard[ny][nx] != -1) {
                // 원본 배열을 유지하고, 복사한 배열의 상태를 바꾼 후(물고기 먹기, 물고기 이동, 상어 이동)
                // 다음 재귀 호출에 input으로 넣어줌
                solve(tempBoard, tempFish, ny, nx, sum);
            }
        }
    }

}
