import java.io.*;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
    static int[] oil;
    static long[][] dp; // dp[i][j]: 현재 위치한 도시 i, 현재까지의 경로에서 방문한 도시 중 가장 저렴한 기름값 j
    static PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingLong(Route::getCost));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oil = new int[N+1];
        dp = new long[N+1][2501];

        for (int i=0; i<=N; i++) {
            adjList.add(new ArrayList<Edge>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            oil[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, cost));
            adjList.get(to).add(new Edge(from, cost));
        }

        dijkstra(1);
        printAns();
    }

    static void dijkstra(int start) {
        for(int i=0; i<=N; i++){
            Arrays.fill(dp[i], INF);
        }

        pq.add(new Route(0, start, oil[start]));
        dp[start][oil[start]] = 0;

        while(!pq.isEmpty()){
            Route curr = pq.poll();
            int currCity = curr.idx;
            long currCost = curr.cost;
            int minOil = curr.minOil;

            if(dp[currCity][minOil] < currCost) continue;

            for(Edge e : adjList.get(currCity)){
                int nextCity = e.to;
                int nextCost = e.cost;
                int nextOil = Math.min(minOil, oil[nextCity]);

                if(dp[nextCity][nextOil] > currCost + (long)nextCost * minOil) {
                    pq.add(new Route(currCost + (long)nextCost * minOil, nextCity, nextOil));
                    dp[nextCity][nextOil] = currCost + (long)nextCost * minOil;
                }
            }
        }
    }

    static void printAns(){
        long ans = INF;
        for (int i = 1; i <= 2500; i++) {
            ans = Math.min(ans, dp[N][i]);
        }
        System.out.println(ans);
    }
}

class Route {
    long cost; // 현재까지 이동하는데에 든 돈
    int idx; // 현재 위치한 도시의 번호
    int minOil; // 현재까지의 경로에서 방문한 도시 중 최소 기름값

    public Route(long cost, int idx, int minOil) {
        this.cost = cost;
        this.idx = idx;
        this.minOil = minOil;
    }

    public long getCost() {
        return cost;
    }
}

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
