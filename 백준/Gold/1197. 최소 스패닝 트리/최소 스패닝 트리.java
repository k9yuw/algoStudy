import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
public class Main {
    
    static int V,E;
    static Edge[] pq;
    static int[] root;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        root = new int[V + 1];
        pq = new Edge[E];
        
        for(int i = 0; i <= V; i++){
            root[i] = i;
        }
        
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq[i] = new Edge(from, to, cost);
        }
        
        System.out.println(kruskal());
        
    }
    
    public static int kruskal(){
        int sum = 0;
        int cnt = 0;
        Arrays.sort(pq);
        for (Edge edge : pq) {
            if(union(edge.from, edge.to)){
                sum += edge.cost;
                if(++cnt == V - 1) return sum;
            }
        }
        return sum;
    }
    
    public static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b) return false; // 사이클 형성하는 경우
        root[a] = b;
        return true; // 사이클 형성하지 않는 경우
    }
    
    public static int find(int a){
        if(root[a] == a) return a;
        else {
            return root[a] = find(root[a]);
        }

    }
}