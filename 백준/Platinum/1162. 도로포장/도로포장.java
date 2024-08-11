import java.io.*;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N, M, K;
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
    static long[][] dist;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new long[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<Edge>());
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Edge(to, cost, 0));
            adjList.get(to).add(new Edge(from, cost, 0));
        }

        dijkstra();

        long answer = INF;
        for (int i = 0; i <= K; i++) {
            answer = Math.min(answer, dist[N][i]);
        }

        System.out.println(answer);
    }

    static void dijkstra() {
        pq.offer(new Edge(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (dist[curr.to][curr.paveCnt] < curr.cost) continue;

            for (Edge next : adjList.get(curr.to)) {
                // 도로를 포장하지 않는 경우
                if (dist[next.to][curr.paveCnt] > curr.cost + next.cost) {
                    dist[next.to][curr.paveCnt] = curr.cost + next.cost;
                    pq.offer(new Edge(next.to, dist[next.to][curr.paveCnt], curr.paveCnt));
                }
                // 도로를 포장하는 경우
                if (curr.paveCnt < K && dist[next.to][curr.paveCnt + 1] > curr.cost) {
                    dist[next.to][curr.paveCnt + 1] = curr.cost;
                    pq.offer(new Edge(next.to, dist[next.to][curr.paveCnt + 1], curr.paveCnt + 1));
                }
            }
        }
    }
}

class Edge {
    int to;
    long cost;
    int paveCnt;

    public Edge(int to, long cost, int paveCnt) {
        this.to = to;
        this.cost = cost;
        this.paveCnt = paveCnt;
    }
}
