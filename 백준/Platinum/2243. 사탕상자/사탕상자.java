import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static int maxFlavor = 1000000;
    static int[] nums;
    static int[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 초기값, 배열 설정
        N = Integer.parseInt(br.readLine());
        S = 1;
        while(S < maxFlavor) {
            S *= 2;
        }

        nums = new int[S];
        tree = new int[2*S];
        visited = new boolean[2*S];

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a==1) {
                int index = query(1, S, 1, b);
                update(1, S, 1, index, -1);
                System.out.println(index);
            }
            else if (a==2) {
                int c = Integer.parseInt(st.nextToken());
                update(1, S, 1, b, c);
            }
        }
    }

    public static void init() {

        for(int i=0; i<S; i++) { // leaf node
            tree[S+i] = nums[i];
        }

        for(int i=S-1; 0<i; i--) { // 내부 노드
            tree[i] = tree[2*i] + tree[2*i + 1];
        }
    }

    public static int query(int left, int right, int node, int rank) {

        if(left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        if(tree[node*2] >= rank) {
            return query(left, mid, node*2, rank);
        } else {
            rank -= tree[node * 2];
            return query(mid+1, right, node*2 + 1, rank);
        }

    }

    public static void update(int left, int right, int node, int target, int diff) {
        if(right < target || target < left) {
            return;
        } else {
            tree[node] += diff;
            if(left != right) { // 내부 노드 업데이트
                int mid = (left + right) / 2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2 + 1, target, diff);
            }
        }
    }
}
