import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static long[] costArr; 
    final static long INF = 987654321;
    
    static class Edge {
        int from, to, cost;
        
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        costArr = new long[N+1];  
        List<Edge> edges = new ArrayList<>();

        // 값 입력 받기
        for (int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(x, y, cost));
        }

        
        // 벨만-포드 알고리즘
        // 최단거리에서 음수 간선, 등으로 인하여 계속해서 최단거리를 줄여나가는지 "감지" 해야하는 경우 사용
        Arrays.fill(costArr, INF);
        costArr[1] = 0;
        
        for(int i=2; i<=N; i++){
            for(Edge edge: edges){
                if(costArr[edge.from] != INF && costArr[edge.to] > costArr[edge.from] + edge.cost) {
                    costArr[edge.to] = costArr[edge.from] + edge.cost;
                }
            }
        }
        
        boolean hasNegativeCycle = false;
        for(Edge edge: edges){
            if(costArr[edge.from] != INF && costArr[edge.to] > costArr[edge.from] + edge.cost) { // 위에서 이거를 같게 만들어주는 작업을 했음에도 불구하고 여전히 차이가 나는 경우!!
                    hasNegativeCycle = true;
                    break; 
                }
        }
        
        
        // 출력
        if (hasNegativeCycle) {
            bw.write("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (costArr[i] == INF) {
                    bw.write("-1" + " ");
                } else {
                    bw.write(Long.toString(costArr[i]) + " "); 
                }
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
