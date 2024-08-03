import java.io.*;
import java.util.*;

public class Main {

    static int N, S;
    static Person[] arr;
    static int[] tree;
    static int[] result;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 초기값, 배열 설정
        N = Integer.parseInt(br.readLine());
        S = 1;
        while(S < N) {
            S *= 2;
        }
        arr = new Person[N];
        tree = new int[2*S];
        result = new int[N+1];

        for(int i=1; i<=N; i++) {
            int stat = Integer.parseInt(br.readLine());
            arr[i-1] = new Person(stat, i);
        }

        // 1. 배열에 선수를 stat순으로 정렬한다.
        Arrays.sort(arr, Comparator.comparing(Person::getStat));

        // 2. update: stat순으로 정렬한 배열 순서대로 돌면서 업데이트
        for(int i=0; i<N; i++) {
            Person p = arr[i];
            result[p.rank] = p.rank - query(1, S, 1, 1, p.rank - 1); // 나보다 스탯이 좋은 사람들을 뺌
            update(1, S, 1, p.rank, 1);
        }

        // 4. 사람수 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(result[i]);
            sb.append("\n");
        }
        System.out.print(sb);

    }

    public static int query(int left, int right, int node, int qL, int qR) {
        if(right < qL || qR < left) {
            return 0;
        } else if (qL <= left && right <= qR){
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return (query(left, mid, node*2, qL, qR) + query(mid+1, right, node*2 + 1, qL, qR));
        }
    }

    public static void update(int left, int right, int node, int target, int diff) {
        if(right < target || target < left) {
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

class Person {

    int stat;
    int rank;

    public int getStat() {
        return stat;
    }

    public Person(int stat, int rank) {
        this.stat = stat;
        this.rank = rank;
    }

}
