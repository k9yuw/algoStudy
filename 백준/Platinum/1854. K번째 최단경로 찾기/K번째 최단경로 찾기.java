import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, K;
	static ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
	static ArrayList<PriorityQueue<Integer>> dist = new ArrayList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<Node>(Comparator.comparing(Node::getDist));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList.get(a).add(new Edge(b, c));
		}
		
		dijkstra(1);
		getAns();
		System.out.println(sb);
	}
	
	
	static void init() {
		for(int i=0; i<=N; i++) {
			adjList.add(new ArrayList<Edge>());
		}		
		for(int i=0; i<=N; i++) {
			dist.add(new PriorityQueue<Integer>(Collections.reverseOrder()));
		}
	}
	
	static void dijkstra(int start) {
		
		dist.get(start).add(0);
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node currNode = pq.poll();	
			for(Edge next : adjList.get(currNode.idx)) {
				if(dist.get(next.to).size() < K || currNode.dist + next.length < dist.get(next.to).peek()) {
					dist.get(next.to).add(currNode.dist + next.length);
					if(dist.get(next.to).size() > K) {
						dist.get(next.to).remove();
					}
					pq.add(new Node(next.to, currNode.dist + next.length));
				} 	
			}
		}
		
	}
	
	static void getAns() {
		for(int i=1; i<=N; i++) {
			if(dist.get(i).size() < K) {
				sb.append("-1").append("\n");
			} else {
				sb.append(dist.get(i).peek()).append("\n");
			}
		}
	}
}

class Edge{
	int to;
	int length;
	public Edge(int to, int length) {
		this.to = to;
		this.length = length;
	}
	public int getLength() {
		return length;
	}
}

class Node{
	int idx;
	int dist;
	public Node(int idx, int dist) {
		this.idx = idx;
		this.dist = dist;
	}
	public int getDist() {
		return dist;
	}
	
}