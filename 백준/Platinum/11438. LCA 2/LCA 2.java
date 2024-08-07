import java.io.*;
import java.util.*;

public class Main {

	// 최대 노드(N) 수 보다 큰 가장 작은 2^n 값을 선택하면 된다.
	// 2^15 = 32768 < N=50000 < 2^16 = 65536
	// 2^16 = 65536 < N=100000 < 2^17 = 131072
	static final int LOG = 17;
	static int N, M;
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
	static boolean[] visited;
	static int[] depth;
	static int[][] parent;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 0. 초기값 설정
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[N + 1][LOG + 1];
		init();

		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList.get(a).add(b);
			adjList.get(b).add(a); // 양방향 간선 추가
		}

		// 1. 모든 노드의 depth와, 바로 위의 부모 노드를 parent[0] 배열에 저장
		// root은 1로 설정
		bfs(1);

		// 2. 나머지 parent 배열도 채워주기
		findAncestors();

		// 3. LCA 찾기
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(lca(a, b)).append("\n");
		}

		// 4. 출력
		System.out.println(sb);

	}

	static void init() {
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Integer>());
		}
	}

	static void bfs(int root) {
		LinkedList<Integer> q = new LinkedList<>();
		q.add(root);
		visited[root] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int next : adjList.get(curr)) {
				if (!visited[next]) {
					visited[next] = true;

					// next에서 2^0 올라간 만큼이 얼마인지 정의
					// 즉 바로 위의 노드를 입력해줌
					parent[next][0] = curr;
					depth[next] = depth[curr] + 1;
					q.add(next);
				}
			}
		}

	}

	static void findAncestors() {
		for (int k = 1; k <= LOG; k++) {
			for (int x = 1; x <= N; x++) {
				// parent[x][k]는 x 노드의 2^k만큼 위에 위치한 부모노드이다.
				parent[x][k] = parent[parent[x][k - 1]][k - 1];
			}
		}
	}
	
	static int lca(int a, int b) {
		// 0. 항상 b가 더 깊은 노드가 될 수 있게 a와 b 바꿔주기
		if(depth[a] > depth[b]) {
			int temp = b;
			b = a;
			a = temp;
		}
		
		// 1. a, b의 depth 맞추기 위해서 b를 a와 같은 depth로 끌어올려준다.
		for(int k=LOG; k>=0; k--) {
			if(depth[b] - depth[a] >= (1 << k)) { // 비트 시프트 연산자: 1을 k만큼 왼쪽으로 이동 (이진수)
				b = parent[b][k]; // 예를 들어 depth 차이가 2^3 ~ 2^4 사이라면 b를 먼저 2^3 만큼 끌어올려줘서 높이가 같아질때까지 반복문을 돈다
			}
		}
		
		// 2. depth만 맞췄는데 a == b이면 그게 lca
		if(a == b) {
			return a;
		}
		
		// 3. depth가 맞춰진 a와 b를 조상이 같아질 때까지 끌어올린다.
		// 이 때 높은곳부터 아래로부터 내려가며 처음으로 조상이 같지 않은 시점까지 이동한다.
		 for(int k=LOG; k>=0; k--) {
			 if(parent[a][k] != parent[b][k]) {
				 a = parent[a][k];
				 b = parent[b][k];
			 }
		 }
		 
		 return parent[a][0]; // 불일치하는 시점의 바로 위가 LCA가 된다. 
	}
	

}
