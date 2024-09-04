import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M, X;
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
    static ArrayList<ArrayList<Edge>> reverseList = new ArrayList<>();
    static PriorityQueue<Edge> pq;
    static int[] toX, backHome, roundWay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        toX = new int[N+1];
        backHome = new int[N+1];
        roundWay = new int[N+1];  
        Arrays.fill(toX, INF);
        Arrays.fill(backHome, INF);

        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<Edge>());
            reverseList.add(new ArrayList<Edge>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Edge(to, time));
            reverseList.get(to).add(new Edge(from, time));
        }

        dijkstra(X, adjList, toX);
        dijkstra(X, reverseList, backHome);
        getAns();
    }

    static void dijkstra(int start, ArrayList<ArrayList<Edge>> graph, int[] dist){
        pq = new PriorityQueue<>(Comparator.comparing(Edge::getCost));
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            for(Edge next : graph.get(curr.to)){
                if(dist[next.to] > dist[curr.to] + next.cost){
                    dist[next.to] = dist[curr.to] + next.cost;
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }

    static void getAns(){
        int ans = Integer.MIN_VALUE;
        for(int i=1; i<=N; i++){
            roundWay[i] = toX[i] + backHome[i];
            ans = Math.max(roundWay[i], ans);
        }

        System.out.println(ans);
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
