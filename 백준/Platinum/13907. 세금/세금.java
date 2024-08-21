import java.io.*;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N, M, K;
    static int S, D;
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
    static int[] tax;
    static long[][] dist;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(Edge::getCost));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new long[N + 1][N + 1];
        tax = new int[K];

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 양방향 도로
            adjList.get(from).add(new Edge(to, cost, 0));
            adjList.get(to).add(new Edge(from, cost, 0));
        }

        for (int i = 0; i < K; i++) {
            tax[i] = Integer.parseInt(br.readLine());
        }

        dijkstra();

        long result = Long.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            result = Math.min(result, dist[i][D]);
        }
        sb.append(result).append("\n");

        for (int i = 0; i < K; i++) {
            result = addTax(tax[i]);
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void init() {
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<Edge>());
        }

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }
    }

    static void dijkstra() {
        // dist 배열 초기화
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[1][S] = 0;
        pq.offer(new Edge(S, 0, 1));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.cityCnt == N) continue;
            if (dist[curr.cityCnt][curr.to] < curr.cost) continue;

            for (Edge next : adjList.get(curr.to)) {
                int nextCnt = curr.cityCnt + 1;
                long nextCost = curr.cost + next.cost;

                if (nextCnt <= N && nextCost < dist[nextCnt][next.to]) {
                    dist[nextCnt][next.to] = nextCost;
                    pq.offer(new Edge(next.to, nextCost, nextCnt));
                }
            }
        }
    }

    static long addTax(int addedTax) {
        long minCost = Long.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            if (dist[i][D] != INF) {
                dist[i][D] += addedTax * (i-1);
                minCost = Math.min(minCost, dist[i][D]);
            }
        }

        return minCost;
    }
}

class Edge {
    int to;
    long cost;
    int cityCnt;

    public Edge(int to, long cost, int cityCnt) {
        this.to = to;
        this.cost = cost;
        this.cityCnt = cityCnt;
    }

    public long getCost() {
        return cost;
    }
}
