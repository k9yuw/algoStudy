import java.io.*;
import java.util.*;

public class Main{

    static int N, D, S;
    static int[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        arr = new int[N+1];

        S = 1;
        while(S < N) {
            S *= 2;
        }
        tree = new long[2*S];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        update(1, S, 1, 1, arr[1]);
        // dp를 이용해서 현재 징검다리까지의 최대 점수를 구해준다.
        // 세그먼트 트리에 앞서 구한 dp값을 저장해준다.
        for(int i=2; i<=N; i++){
            // 0과 i-D중 큰 값을 구하는 이유는 i-D가 음수일 때를 대비
            long maxScore = Math.max(arr[i], arr[i] + query(1, S, 1, Math.max(1, i-D) , i-1));
            update(1, S, 1, i, maxScore);
        }

        // dp중 최댓값을 출력한다. (아무때나 내가 나가고 싶은 지점에서 나갈 수 있기 때문에)
        System.out.println(tree[1]);

    }

    static long query(int left, int right, int node, int qL, int qR) {

        if (right < qL || qR < left) return 0;
        else if (qL <= left && right <= qR) return tree[node];
        else {
            int mid = (left + right) / 2;
            return Math.max(query(left, mid, node*2, qL, qR), query(mid+1, right, node*2 + 1, qL, qR));
        }
    }

    static void update(int left, int right, int node, int target, long score) {
        if (target < left || target > right) {
            return;
        } else {
            if(target == 1) tree[node] = score;
            else tree[node] = Math.max(score, tree[node]);
            if(left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, score);
                update(mid + 1, right, node * 2 + 1, target, score);
            }
        }
    }

}

