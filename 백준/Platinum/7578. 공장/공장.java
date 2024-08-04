import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static long result;
    static int[] arrA, tree;
    static HashMap<Integer,Integer> arrB = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = 1;
        while(S < N){
            S *= 2;
        }
        arrA = new int[N+1];
        tree = new int[2*S];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int num = Integer.parseInt(st.nextToken());
            arrB.put(num, i);
        }

        for(int i=1; i<=N; i++){
            int b = arrB.get(arrA[i]);
            update(1, S, 1, b, 1);
            result += query(1, S, 1, b+1, S);
        }
        System.out.println(result);
    }

    static long query(int left, int right, int node, int qL, int qR){

        if (right < qL || qR < left) {
            return 0;
        } else if (qL <= left && right <= qR){
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return (query(left, mid, node*2, qL, qR) + query(mid+1, right, node*2 + 1, qL, qR));
        }
    }

    static void update(int left, int right, int node, int target, int diff){
        if(right < target || target < left){
            return;
        } else {
            tree[node] += diff;
            if(left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2 + 1, target, diff);
            }
        }
    }

}
