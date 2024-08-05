import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int action = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (action == 0) {
				union(a, b);
			} else if (action == 1) {
				sb.append(isSameParent(a, b) ? "YES" : "NO");
				sb.append("\n");					
			}
		}
		
		System.out.println(sb);
	}

	static void union(int x, int y) {

		x = find(x);
		y = find(y);

		if (x != y) { // 더 작은 쪽이 부모노드
			if (x <= y)
				parent[y] = x;
			else
				parent[x] = y;
		}

	}

	static int find(int x) {

		if (parent[x] == x)
			return x;

		// 경로압축을 꼭 유의해야 한다.
		// root노드가 항상 바로 다음단계의 부모노드가 되도록 한다.
		return parent[x] = find(parent[x]);
	}
	
	static boolean isSameParent(int a, int b) {
		if(find(a) == find(b)) return true;
		return false;
	}
}
