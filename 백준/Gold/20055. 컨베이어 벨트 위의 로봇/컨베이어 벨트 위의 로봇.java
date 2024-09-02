import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static Box[] arr;
    static int zeroCount = 0; // 내구도 0인 칸의 개수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new Box[2 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            int c = Integer.parseInt(st.nextToken());
            arr[i] = new Box(c);
        }

        int step = 0;
        while (zeroCount < K) {
            step++;

            // 1. 벨트가 한 칸 회전한다.
            moveBelt();

            // 2. 가장 먼저 벨트에 올라간 로봇부터 한 칸 이동한다.
            moveRobot();

            // 3. 올리는 위치의 내구도가 0이 아니면 로봇을 올린다.
            putRobot();
        }

        // 4. 내구도 0인 칸의 개수가 K개 이상이면 과정을 종료한다.
        System.out.println(step);
    }

    static void moveBelt() {
        Box temp = arr[2 * N];
        for (int i = 2 * N; i > 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[1] = temp;

        // 내리는 위치에 도달한 로봇을 제거
        if (arr[N].robot) {
            arr[N].robot = false;
        }
    }

    static void moveRobot() {
        for (int i = N - 1; i >= 1; i--) { // 내리는 위치에 가까운 로봇부터(= 먼저 올라간 로봇부터) 이동
            if (arr[i].robot && !arr[i + 1].robot && arr[i + 1].cnt > 0) {
                arr[i].robot = false;
                arr[i + 1].robot = true;
                arr[i + 1].cnt--;
                if (arr[i + 1].cnt == 0) {
                    zeroCount++;
                }
            }
        }

        // 내리는 위치에 도달한 로봇을 제거
        if (arr[N].robot) {
            arr[N].robot = false;
        }
    }

    static void putRobot() {
        if (arr[1].cnt > 0 && !arr[1].robot) {
            arr[1].robot = true;
            arr[1].cnt--;
            if (arr[1].cnt == 0) {
                zeroCount++;
            }
        }
    }
}

class Box {
    boolean robot;
    int cnt;

    public Box(int cnt) {
        this.cnt = cnt;
        this.robot = false;
    }
}
