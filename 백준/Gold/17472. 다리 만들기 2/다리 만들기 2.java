import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int ans = 0;
	static int edgeCount = 0;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] parent;
	static int[][] board;
	static boolean[][] visited;
	static Queue<Node> q;
	static Queue<bNode> bq;
	static PriorityQueue<Bridge> pq = new PriorityQueue<Bridge>(Comparator.comparing(Bridge::getLength));

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		// 초기 보드 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬에 번호 부여
		int islandNum = 2;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1 && !visited[i][j]) {
					indexIsland(i, j, islandNum);
					islandNum++;
				}
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.print("\n");
//		}

		// 유효한 다리 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] != 0) {
					getPossibleBridge(i, j);
				}
			}
		}
		
//		int bridgeNum = pq.size();
//		for(int i=0; i<bridgeNum; i++) {
//			Bridge b = pq.poll();
//			System.out.println(b.from + " " + b.to + " " + b.length);
//		}

		// 크루스칼 알고리즘 수행 후 답 출력
		parent = new int[islandNum];
		for(int i=0; i<islandNum; i++) {
			parent[i] = i;
		}
		
		kruskal();
		if(edgeCount != islandNum - 3) {
			System.out.println("-1");
		} else {
			System.out.println(ans);
		}
		
	}

	public static void indexIsland(int y, int x, int islandNum) {
		
		q = new LinkedList<Node>();
		q.offer(new Node(y, x));
		board[y][x] = islandNum;
		visited[y][x] = true;

		while (!q.isEmpty()) {
			// 1. 큐에서 뺀다.
			Node curr = q.poll();

			// 3. 순회
			for (int i = 0; i < 4; i++) {
				int ny = curr.y + dy[i];
				int nx = curr.x + dx[i];

				// 4. 갈 수 있는가?
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (board[ny][nx] == 0)
					continue;
				if (visited[ny][nx])
					continue;

				// 5. 체크인
				visited[ny][nx] = true;
				board[ny][nx] = islandNum; // 섬에 인덱스 부여

				// 6. 큐에 넣는다.
				q.offer(new Node(ny, nx));
			}
		}

	}

	public static void getPossibleBridge(int y, int x) {
			
		int currIslandNum = board[y][x];
		visited = new boolean[N][M];
		
		for(int i=0; i<4; i++) {
			
			bq = new LinkedList<bNode>();
			bq.offer(new bNode(y, x, 0));

			while(!bq.isEmpty()) {
				// 1. 큐에서 뺀다.
				bNode b = bq.poll();
				
				// 3. 순회
				int ny = b.y + dy[i];
				int nx = b.x + dx[i];
				
				// 4. 갈 수 있는가?
				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (visited[ny][nx])
					continue;
				if(board[ny][nx] == currIslandNum)
					continue;

				// 5. 체크인
				visited[ny][nx] = true;
				
				// 6. 큐에 넣는다. 
				if(board[ny][nx] == 0) {
					bq.offer(new bNode(ny, nx, b.count + 1));
				} else if(board[ny][nx] != 0 && board[ny][nx] != currIslandNum && b.count >= 2) {
					int newIslandNum = board[ny][nx];
					pq.offer(new Bridge(currIslandNum, newIslandNum, b.count));
				}
			
			
			}
		}

	}

	public static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x <= y)
				parent[y] = x;
			else
				parent[x] = y; // 더 작은 쪽을 부모 노드로 설정
		}
	}

	public static void kruskal() {
		while(!pq.isEmpty()) {
			Bridge b = pq.poll();

			if (find(b.from) != find(b.to)) {
				union(b.from, b.to);
				ans += b.length;
				edgeCount++;
			}
		}
	}

}

class Node {
	int y, x;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class bNode{
	int y, x;
	int count;
	public bNode(int y, int x, int count) {
		this.y = y;
		this.x = x;
		this.count = count;
	}
}

class Bridge {
	int from, to;
	int length;

	public Bridge(int from, int to, int length) {
		this.from = from;
		this.to = to;
		this.length = length;
	}

	public int getLength() {
		return length;
	}
}
