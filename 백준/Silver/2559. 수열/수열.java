import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] arr;
    static int before, max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        before = 0;
        for(int i=0; i<K; i++) {
            before += arr[i];
        }
        max = before;

        for(int i=0; i<N-K; i++) {
            int curr = before + arr[K+i] - arr[i];
            max = Math.max(curr, max);
            before = curr;
        }

        System.out.println(max);
    }
}