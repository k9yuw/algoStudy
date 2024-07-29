import java.io.*;
import java.util.*;

public class Main {

	static int y, x;
	static int sy, sx;
	static int ans = 0;
	static final int[] dy = { -1, 1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static char[][] board;
	static int[][] visited; // 방문체크와 거리계산을 동시에 할 수 있다. 양수라는 것은 이미 방문 한 것 (단 시작지점과 방문하지 않은 지점의 구분이 불가)
	static Queue<Node> q = new LinkedList<Node>();
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int y, x;
		char val;
		
		public Node(int y, int x, char val) {
			this.y = y;
			this.x = x;
			this.val = val;
		}

		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + ", val=" + val + "]";
		}
		
		
	}

	public static void main(String[] args) throws IOException {

//		System.setIn(new FileInputStream("src/DAY01/P1062/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 초기값, 배열크기 설정
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		board = new char[y][x];
		visited = new int[y][x];

		for (int i = 0; i < y; i++) {
			String str = br.readLine();
			for (int j = 0; j < x; j++) {
				board[i][j] = str.charAt(j);
				// 물 입력하기
				// 물보다 고슴도치를 먼저 넣으면 나중에 고슴도치가 물이 번질 칸으로 이동했는지 안했는지 확인할 필요가 없어진다.
				// 따라서 물을 먼저 큐에 삽입해 물이 먼저 번지게 한 후 고슴도치를 큐에 넣어 이동하게 한다.
				if (board[i][j] == '*') {
					q.offer(new Node(i, j, '*'));
				}
				// 고슴도치 위치 따로 빼놓기
				if (board[i][j] == 'S') {
					sy = i;
					sx = j;
				}
			}
		}
		
		q.offer(new Node(sy, sx, 'S')); // 고슴도치 위치 삽입

		bfs();
		
		if(sb.length() == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(sb);
		}

	}

	public static void bfs() {

		while (!q.isEmpty()) {
			
			// 1. 큐에서 꺼내옴
			Node curr = q.poll();

			// 2. 목적지인가?
			if (curr.val == 'S' && board[curr.y][curr.x] == 'D') {
				sb.append(String.valueOf(visited[curr.y][curr.x]));
			} 
			
			// 3. 탐색
			for (int i=0; i<4; i++) {
				int ny = curr.y + dy[i];
				int nx = curr.x + dx[i];

				// 4. 갈 수 있는가?
				// 4-1. board 범위를 벗어나는 경우 더 이상 탐색 불가
				if (ny < 0 || nx < 0 || ny >= y || nx >= x) continue;
				// 4-2. 돌인 경우 더 이상 탐색 불가
				if (board[ny][nx] == 'X') continue;

				// 5. 체크인
//				visited[ny][nx] = visited[curr.y][curr.x] + 1;

				// 6. 큐에 넣기
				// 6-1. curr 값이 물이면, 다음 탐색하는 노드도 물이 되어야 한다.
				if (curr.val == '*') {
					if(board[ny][nx] != '*' && board[ny][nx] != 'D') { // 이미 물이 차있던 자리가 아니어야 함
						q.offer(new Node(ny, nx, '*'));
						board[ny][nx] = '*';
					}
				}

				// 6-2. curr값이 고슴도치이면, 큐에 고슴도치가 이동한 값을 집어넣는다.
				if (curr.val == 'S') {
					if(board[ny][nx] == '*') continue;
					// 4-2. 고슴도치가 이미 방문한 경우 더 이상 탐색 불가
					if(visited[ny][nx] > 0) continue;
					q.offer(new Node(ny, nx, 'S'));
					// 체크인을 여기서 ! 고슴도치가 이동하는 것만 이동으로 치기 때문.
					visited[ny][nx] = visited[curr.y][curr.x] + 1;
				}
			}
		}

	}
}
