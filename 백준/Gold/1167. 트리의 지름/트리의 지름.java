import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Edge>[] list;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            while (true) {
                int n2 = Integer.parseInt(st.nextToken());
                if (n2 == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                list[n1].add(new Edge(n2, cost));
            }
        }

        // 임의의 노드(1)에서부터 가장 먼 노드를 찾기. 이때 찾은 노드를 farthestNode에 저장한다.
        visited = new boolean[n + 1];
        dfs(1, 0);

        // farthestNode에서부터 가장 먼 노트까지의 거리를 구한다.
        visited = new boolean[n + 1];
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    public static class Edge {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void dfs(int currentNode, int currentDist) {
        if (currentDist > maxDist) {
            maxDist = currentDist;
            farthestNode = currentNode;
        }

        visited[currentNode] = true;

        for (Edge connected : list[currentNode]) {
            if (!visited[connected.node]) {
                dfs(connected.node, connected.cost + currentDist);
                visited[connected.node] = true;
            }
        }
    }
}
