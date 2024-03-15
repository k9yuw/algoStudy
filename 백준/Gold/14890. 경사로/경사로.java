import java.io.*;
import java.util.*;

public class Main {

    static int N, L;
    static int[][] board;
    static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        L = Integer.parseInt(st1.nextToken());
        board = new int[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        // 가능한 경로 확인 후 답안 출력
        for (int i = 0; i < N; i++) {
            if (pathCheck(i, 0, true)) cnt++; // 행 체크
            if (pathCheck(0, i, false)) cnt++; // 열 체크
        }
        System.out.println(cnt);
    }


    static boolean pathCheck(int x, int y, boolean isRowCheck) {

        int[] height = new int[N];
        boolean[] isGradient = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (isRowCheck) height[i] = board[x][i];
            else height[i] = board[i][y];
        }

        for (int i = 0; i < N - 1; i++) {

            // 높이가 같을 때
            if (height[i] == height[i + 1]) {
                continue;
            }

            // 내려갈 때
            else if (height[i] - height[i + 1] == 1) {
                for (int j = i + 1; j < i + 1 + L; j++) {
                    // j가 인덱스 범위를 초과하거나, i+1과 j의 높이가 다르거나(그럼 설치 못하니까), 경사로가 이미 설치 되었으면
                    if (j > N-1 || height[i + 1] != height[j] || isGradient[j]) return false;
                    isGradient[j] = true;
                }

            }

            // 올라갈 때
            else if (height[i] - height[i + 1] == -1) {
                for (int j=i; j>i-L ; j--) {
                    // j가 인덱스 범위를 초과하거나, i와 j의 높이가 다르거나(그럼 설치 못하니까), 경사로가 이미 설치 되었으면
                    if (j<0 || height[i] != height[j] || isGradient[j]) return false;
                    isGradient[j] = true;
                }
            }

            // 높이차이가 1칸 초과
            else {
                return false;
            }

        }

        return true;

    }
}
