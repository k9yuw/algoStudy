import java.io.*;
import java.util.*;

public class Main {

    static int N, M, L;
    static List<Integer> arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new ArrayList<>();
        for(int i=0; i<N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        arr.add(0);
        arr.add(L);
        Collections.sort(arr);

        int min = 1;
        int max = L-1;

        while(min <= max) {
            int dist = (min+max) / 2;
            int cnt = 0;

            for(int i=0; i<=arr.size()-2; i++) {
                cnt += (arr.get(i+1) - arr.get(i) - 1) / dist;
            }
            if(cnt <= M) {
                max = dist-1;
            } else {
                min = dist+1;
            }
        }

        System.out.println(min);

    }
}