import java.io.*;
import java.util.*;

public class Main {
    static int[] arr, num;
    static int n, result = 0;
    static boolean[] visited;

    public static void dfs(int cnt) {
        if (cnt == n) {
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs(arr[i] - arr[i + 1]);
            }
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                visited[i] = true;
                arr[cnt] = num[i];
                dfs(cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        num = new int[n];
        arr = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(result);
    }
}