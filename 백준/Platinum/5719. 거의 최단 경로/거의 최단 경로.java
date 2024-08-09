import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, S, D;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<Edge>> adjList, reverseAdjList;
    static PriorityQueue<Edge> pq;
    static List<Edge> minPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            adjList = new ArrayList<>();
            reverseAdjList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                adjList.add(new ArrayList<>());
                reverseAdjList.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                adjList.get(u).add(new Edge(u, v, p));
                reverseAdjList.get(v).add(new Edge(v, u, p));
            }

            dijkstra(S);

            minPath = new ArrayList<>();
            findMinPath(D);
            removeMinPath();

            dijkstra(S);

            if (dist[D] == INF) {
                System.out.println(-1);
            } else {
                System.out.println(dist[D]);
            }
        }
    }

    public static void dijkstra(int start) {
        dist = new int[N];
        visited = new boolean[N];
        pq = new PriorityQueue<>(Comparator.comparing(Edge::getCost));
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.offer(new Edge(start, start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (visited[curr.to]) continue;
            visited[curr.to] = true;

            for (Edge next : adjList.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.cost) {
                    dist[next.to] = dist[curr.to] + next.cost;
                    pq.offer(new Edge(next.from, next.to, dist[next.to]));
                }
            }
        }
    }

    public static void findMinPath(int node) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        queue.offer(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Edge e : reverseAdjList.get(curr)) {
                if (dist[curr] == dist[e.to] + e.cost) {
                    minPath.add(new Edge(e.to, curr, e.cost));
                    if (!visited[e.to]) {
                        queue.offer(e.to);
                        visited[e.to] = true;
                    }
                }
            }
        }
    }

    public static void removeMinPath() {
        for (Edge edge : minPath) {
            adjList.get(edge.from).removeIf(e -> e.to == edge.to && e.cost == edge.cost);
        }
    }

    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }
}
