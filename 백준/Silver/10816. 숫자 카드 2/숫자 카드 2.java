import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int key = Integer.parseInt(st.nextToken());
            int count = upperBound(key) - lowerBound(key);
            sb.append(count).append(" ");
        }
        
        System.out.println(sb);
    }

    static int upperBound(int key) {
        int low = 0;
        int high = arr.length;

        while(low < high) {
            int mid = (low + high) / 2;

            if(key < arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    static int lowerBound(int key) {
        int low = 0;
        int high = arr.length;

        while(low < high) {
            int mid = (low + high) / 2;
            if(key <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}