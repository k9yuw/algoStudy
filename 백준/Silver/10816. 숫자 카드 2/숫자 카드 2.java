import java.io.*;
import java.util.*;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());
            // upperBound와 lowerBound의 차이 값이 카드의 개수
            sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(' ');
        }
        System.out.println(sb);
    }

    private static int lowerBound(int[] arr, int key) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (key <= arr[mid]) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private static int upperBound(int[] arr, int key) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (key < arr[mid]) high = mid;
            else low = mid + 1;
        }
        return low;
    }

}