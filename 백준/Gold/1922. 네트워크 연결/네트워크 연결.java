import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parent;
	static int minCost = 0;
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(Comparator.comparing(Edge::getCost));
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, cost));
		}

		kruskal();
		System.out.println(minCost);
	}
	
	public static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x!=y) {
			if(x<=y) parent[y] = x;
			else parent[x] = y; /// 더 작은 쪽을 부모노드로 설정
		}
	}
	
	public static void kruskal() {
		for(int i=0; i<M; i++) {
			Edge e = pq.poll();
			
			if(find(e.from) != find(e.to)) {
				minCost += e.cost;
				union(e.from, e.to);
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
	public int getCost() {
		return cost;
	}
}
