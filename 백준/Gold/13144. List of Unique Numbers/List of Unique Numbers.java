import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        long result = 0;

        for(int right=0; right<N; right++) {
            countMap.put(arr[right], countMap.getOrDefault(arr[right], 0) + 1);

            while(countMap.get(arr[right]) > 1) {
                countMap.put(arr[left], countMap.get(arr[left]) - 1);
                left++;
            }

            result += (right - left) + 1; // 맨 오른쪽꺼 포함 연속하도록 하는 부분배열 개수
        }

        System.out.println(result);
    }
}