import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static Queue<Integer> q = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		
		init();
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			addEdge(from, to);
			indegree[to]++;
		}
		
		topoSort();
		
		System.out.println(sb);
		
	}
	
	static void init() {
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Integer>());
		}
	}
	
	static void addEdge(int from, int to) {
		graph.get(from).add(to); 
	}
	
	static void topoSort() {
		
		for(int i=1; i<=N; i++) {
			// 제일 초기 단계 : indegree가 0이면 해당 노드를 큐에 넣는다.
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			// 큐에서 하나를 꺼내서 답에 추가하고
			int curr = q.poll();
			sb.append(curr).append(" ");
				
			// 꺼낸 노드(curr)에서부터 출발하는 모든 간선들을 제거해준다.
			for(Integer to : graph.get(curr)) {
				indegree[to]--;
				if(indegree[to] == 0) {
					q.offer(to);
				}
			}	
	
		}
	}
}
