import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new boolean[20000001];

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int curr = Integer.parseInt(st.nextToken()) + 10000000;
            arr[curr] = true;
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int curr = Integer.parseInt(st.nextToken()) + 10000000;
            if(arr[curr]) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }

        System.out.println(sb);

    }
}