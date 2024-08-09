import java.io.*;
import java.util.*;

public class Main {

	// 음수사이클의 최대값은 6000 * -10000 = -6천만이다.
	// 그리고 전체 과정을 정점의 개수(500)만큼 돌면서 값을 키우기 때문에 500 * -6천만 만큼 절대값 크기가 늘게 된다.
	// 따라서 dist 자료형을 long으로 선언해야 한다!
	static final long INF = Integer.MAX_VALUE;
	static int N, M;
	static long[] minDist;
	static boolean doesNegativeCycleExists = false;
	static ArrayList<Edge> graph = new ArrayList<Edge>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minDist = new long[N+1];
		Arrays.fill(minDist, INF);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.add(new Edge(a, b, c));
		}
		
		BellmanFord(N, M, 1);
		getAns();
		System.out.println(sb);
	}
	
	static void BellmanFord(int n, int m, int start) {
		
		minDist[start] = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Edge e = graph.get(j);
				
				if(minDist[e.from] != INF && minDist[e.to] > minDist[e.from] + e.cost) {
					minDist[e.to] = minDist[e.from] + e.cost;
				}
			}
		}
		
		for(int j=0; j<M; j++) {
			Edge e = graph.get(j);
			if(minDist[e.from] != INF && minDist[e.to] > minDist[e.from] + e.cost) {
				doesNegativeCycleExists = true;
			}
		}
		
	}
	
	static void getAns() {
			
		if(doesNegativeCycleExists) {
			sb.append("-1").append("\n");
		} else {
			for(int i=2; i<=N; i++) {
				long result = minDist[i];
				if(result == INF) {
					sb.append("-1").append("\n");
				} else {
					sb.append(result).append("\n");
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
