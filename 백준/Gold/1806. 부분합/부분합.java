import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int[] arr;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 기본값 입력
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int pL = 0, pR = 0;
        int sum = 0;
        while (true) {
            if (sum >= S) {
                sum -= arr[pL++];
                min = Math.min(min, pR - pL + 1);
            } else if (pR == N) {
                break;
            } else {
                sum += arr[pR++];
            }
        }

        if (min == Integer.MAX_VALUE) min = 0;
        System.out.println(min);
    }
}
