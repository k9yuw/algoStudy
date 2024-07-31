import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static PriorityQueue<Jewel> pq = new PriorityQueue<Jewel>(Comparator.comparing(Jewel::getCost).reversed()); // 가격 내림차순 정렬
    static Jewel[] jewelList;
    static int[] bagList;

    // 가장 작은 가방에 가장 비싼 보석을 넣어야됨!
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewelList = new Jewel[N];
        bagList = new int[K];

        // 보석 무게 오름차순 정렬
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            jewelList[i] = new Jewel(w, c);
        }
        Arrays.sort(jewelList, Comparator.comparing(Jewel::getWeight));

        // 가방 오름차순 정렬
        for (int i = 0; i < K; i++) {
            int bagW = Integer.parseInt(br.readLine());
            bagList[i] = bagW;
        }
        Arrays.sort(bagList);

        int jIndex = 0;
        long result = 0;

        // 가방을 순회
        for (int i = 0; i < bagList.length; i++) {
            // 가방에 넣을 수 있는 보석을 pq에 넣는다.
            while (jIndex < N && jewelList[jIndex].weight <= bagList[i]) {
                pq.add(jewelList[jIndex++]);
            }

            // 이 때 pq의 top은 가방에 넣을 수 있는 것 중 가장 비싼 보석
            if (!pq.isEmpty()) {
                result += pq.poll().cost;
            }
        }

        System.out.println(result);
    }
}

class Jewel {

    int weight, cost;
    public Jewel(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }
}
