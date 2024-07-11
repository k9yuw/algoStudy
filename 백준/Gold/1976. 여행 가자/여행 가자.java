import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int parent[];
    static boolean isSameParent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        // 연결 상태 입력
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 1) union(i, j);
            }
        }

        // 여행 계획 입력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        isSameParent = true;
        for (int i = 1; i < m; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (find(start) != find(next)) {
                isSameParent = false;
                break;
            }
        }

        // 출력
        if (isSameParent) System.out.println("YES");
        else System.out.println("NO");
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (x < y) parent[y] = x;
            else parent[x] = y;
        }
    }
}
