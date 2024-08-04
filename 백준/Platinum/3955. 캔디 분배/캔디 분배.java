import java.io.*;
import java.util.*;

public class Main {

    static long A, B; // A: 사람수, B: 한 봉지에 든 사탕 개수
    static int t;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        // x: 인당 사탕수
        // y: 사야하는 사탕 봉지수
        // A(-x) + By = 1 (K에 음수가 들어가게 되면 나머지 음수 처리가 어려워지므로
        // x에 -를 넘겨 -x(음수), y(양수)를 찾고자 한다.

        // D = gcd(A, B)
        // Ax + By = C일 때 C % D == 0 이면 해가 존재함

        // As + Bt = C 를 만족하는 초기해
        // x0 = s* C/D
        // y0 = t* C/D

        // x = x0 + B/D * k
        // y = y0 - A/D * k : 일반해 규칙. 실제로 작은 숫자 넣어가보면서 하면 이 식 찾을 수 있음.

        // x = x0 + B/D * k <0
        // k < -x0/B
        // 0 < y = y0 - A/D * k <= 1e9
        // - y0 < -A*k <= 1e9 -y0
        // 부가설명: 일반해가 존재하기 위해서는, 초기해가 무조건 존재해야 한다.
        // 초기해가 존재하기 위해서는 C%D == 0이어야 하기 때문에
        // C가 1로 설정된 이 문제에서는 D가 무조건 1일 수 밖에 없다.
        // 초기해를 통해 일반해를 구해야 하는 이유는, 초기해가 항상 주어진 문제의 답 조건을 만족한다는 보장이 없기 때문이다.

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            EuclidResult init = extendedEuclidean(A, B);
            if (init.r != 1) {
                System.out.println("IMPOSSIBLE");
            } else {
                long x0 = init.s;
                long y0 = init.t;

                // (y0 - 1e9) / A <= k < y0/A
                // (y0 - 1e9) / A <= k < -x0/B
                y0 %= A;
                if(y0 < 0) y0 += A;
                y0 = Math.max(y0, (A+B)/B);

                if(y0 <= 1e9) {
                    System.out.println(y0);
                } else {
                    System.out.println("IMPOSSIBLE");
                }
            }
        }
    }

    public static EuclidResult extendedEuclidean(long A, long B) {
        long s0 = 1, t0 = 0, r0 = A;
        long s1 = 0, t1 = 1, r1 = B;
        long temp;

        while (r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1;
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }

        return new EuclidResult(s0, t0, r0);
    }
}

class EuclidResult {
    long s, t, r;

    public EuclidResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
