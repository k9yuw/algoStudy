import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] root, dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			init();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String action = st.nextToken().toString();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (action.equals("!")) {
					int w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				} else if (action.equals("?")) {
					if (find(a) != find(b))
						System.out.println("UNKNOWN");
					else
						System.out.println(dist[b] - dist[a]);
				}
			}
		}
	}

	static void init() {
		root = new int[100001];
		dist = new int[100001];
		for (int i = 1; i <= N; i++) {
			root[i] = i;
		}
	}

	static int find(int x) {
		if (root[x] == x)
			return x;
		else {
			int parent = root[x];
			int grandparent = find(parent);
			dist[x] += dist[parent];
			return root[x] = grandparent;
		}
	}

	static void union(int x, int y, int w) { // y가 항상 x보다 w그램 더 무거움

		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot == yRoot)
			return;

		else {
			root[yRoot] = xRoot;
			dist[yRoot] = w + dist[x] - dist[y];
		}
	}
}
