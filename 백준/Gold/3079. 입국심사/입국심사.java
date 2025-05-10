import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());  // M을 long으로

        long[] times = new long[N];
        long maxTime = 0;
        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

        long left = 1, right = maxTime * M;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long peopleChecked = 0;
            for (int i = 0; i < N; i++) {
                peopleChecked += mid / times[i];
                if (peopleChecked >= M) {
                    // 이미 M명 이상 처리 가능하므로 더 셀 필요 없음
                    break;
                }
            }

            if (peopleChecked >= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
