import java.io.*;
import java.util.*;

public class Main {

    static int N, K, S = 1;
    static int[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while (S < N) {
            S *= 2;
        }
        tree = new int[2 * S];
        init();

        int index = 1;
        sb.append("<");
        for (int i = 0; i < N; i++) {

            int size = N - i;
            index += K - 1;

            if (index % size == 0) {
                index = size;
            } else if (index > size) {
                index %= size;
            }

            int num = query(1, S, 1, index);
            update(1, S, 1, num); 

            if (i == N - 1) sb.append(num).append(">");
            else sb.append(num).append(", ");
        }
        System.out.println(sb.toString());
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            tree[S + i] = 1;
        }
        for (int i = S - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static int query(int left, int right, int node, int rank) {
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        if (rank <= tree[node * 2]) {
            return query(left, mid, node * 2, rank);
        } else {
            return query(mid + 1, right, node * 2 + 1, rank - tree[node * 2]);
        }
    }

    static void update(int left, int right, int node, int idx) {
        if (left == right) {
            tree[node] = 0;
            return;
        }

        tree[node]--;

        int mid = (left + right) / 2;
        if (idx <= mid) {
            update(left, mid, node * 2, idx);
        } else {
            update(mid + 1, right, node * 2 + 1, idx);
        }
    }
}
