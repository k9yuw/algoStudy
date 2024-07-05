import java.io.*;
import java.util.*;

class Node {
    int num;
    double x, y;

    Node(int num, double x, double y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int start, end;
    double cost;

    Edge(int start, int end, double cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return Double.compare(this.cost, e.cost);
    }
}

public class Main {
    static int n;
    static int[] parent;
    static Node[] nodes;
    static ArrayList<Edge> edges = new ArrayList<>();
    static double ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        parent = new int[n];
        nodes = new Node[n];

        // 각 노드의 부모 노드 정보 입력
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 주어진 노드 정보 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            nodes[i] = new Node(i, x, y);
        }

        // 간선 정보 입력
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double cost = distance(nodes[i], nodes[j]);
                edges.add(new Edge(nodes[i].num, nodes[j].num, cost));
            }
        }

        // cost 비용을 기준으로 오름차순 정렬
        Collections.sort(edges);

        kruskal();
        System.out.printf("%.2f", ans);
    }

    public static void kruskal() {
        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                ans += edge.cost;
            }
        }
    }

    public static double distance(Node n1, Node n2) {
        return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
