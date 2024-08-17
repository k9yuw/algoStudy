import java.io.*;
import java.util.*;

public class Main{

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int start, end;
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getCost));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1];
        visited = new boolean[N+1];

        init();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int ans = dijkstra();

        System.out.println(ans);
    }

    static void init(){
        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<Edge>());
        }
    }

    static int dijkstra() {
        Arrays.fill(dist, INF);
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){

            Edge curr = pq.poll();
            if(!visited[curr.to]){
                visited[curr.to] = true;

                for(Edge e : adjList.get(curr.to)){
                    if(dist[e.to] > dist[curr.to] + e.cost){
                        dist[e.to] = dist[curr.to] + e.cost;
                        pq.offer(new Edge(e.to, dist[e.to]));
                    }
                }
            }
        }
        return dist[end];
    }

}

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}