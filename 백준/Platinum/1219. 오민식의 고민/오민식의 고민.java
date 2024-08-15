import java.io.*;
import java.util.*;

public class Main{

    static int N, M;
    static int start, end;
    static Edge[] graph;
    static long[] dist, money;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new Edge[M];
        dist = new long[N];
        money = new long[N];

        // 각 교통수단 입력
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[i] = new Edge(from, to, cost);
        }

        // 각 도시에서 버는 돈 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            money[i] = Integer.parseInt(st.nextToken());
        }

        bellmanFord();

        // 도착점에 도달하는게 불가능한 경우
        if(dist[end] == Long.MIN_VALUE){
            sb.append("gg").append("\n");
        }
        // 도착점이 양의 사이클에 포함된 경우
        else if(dist[end] == Long.MAX_VALUE){
            sb.append("Gee").append("\n");
        }
        // 정상적으로 도착점에 도착한 경우
        else {
            sb.append(dist[end]).append("\n");
        }

        System.out.println(sb);
    }

    static void bellmanFord(){

        Arrays.fill(dist, Long.MIN_VALUE);
        dist[start] = money[start];

        for(int i=0; i<=N+50; i++){
            for(int j=0; j<M; j++){
                Edge curr = graph[j];

                // 출발 도시에 방문할 수 없는 경우
                if(dist[curr.from] == Long.MIN_VALUE) continue;

                // 출발 도시가 양의 사이클에 포함된 경우
                // A -> B 경로에서 A가 양의 사이클에 연결된 노드라면 B 또한 양의 사이클에 포함될 것
                if(dist[curr.from] == Long.MAX_VALUE) {
                    dist[curr.to] = Long.MAX_VALUE;
                }
                // 최단 거리를 갱신할 수 있는 경우
                else if(dist[curr.to] < dist[curr.from] + money[curr.to] - curr.cost) {
                    dist[curr.to] = dist[curr.from] + money[curr.to] - curr.cost;

                    // N번째 이상 반복해서 갱신되는 경우 양의 사이클 존재
                    if(i >= N-1) {
                        dist[curr.to] = Long.MAX_VALUE;
                    }
                }
            }
        }
    }

}

class Edge{
    int from, to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
