import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] buildTime, initTime, indegree;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        indegree = new int[N + 1];
        initTime = new int[N + 1];
        buildTime = new int[N + 1];

        init();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            initTime[i] = Integer.parseInt(st.nextToken());
            buildTime[i] = initTime[i]; // 초기 건물의 buildTime 설정
            while (true) {
                int from = Integer.parseInt(st.nextToken());
                if (from == -1) break;
                adjList.get(from).add(i); // 선행 노드 추가
                indegree[i]++;
            }
        }

        topoSort();
        printAns();
    }

    static void init() {
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    static void topoSort() {
        // 초기 그래프에서 indegree가 0인 노드를 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int from = q.poll();

            // 현재 뽑은 from 노드에 연결된 모든 간선 제거
            for (Integer to : adjList.get(from)) {
                // buildTime 계산
                buildTime[to] = Math.max(buildTime[to], buildTime[from] + initTime[to]);

                // 진입차수 줄여주고
                indegree[to]--;

                // 간선 제거 시 to 노드의 진입 차수가 0이 된다면, 큐에 넣기
                if (indegree[to] == 0) {
                    q.offer(to);
                }
            }
        }
    }

    static void printAns() {
        for (int i = 1; i <= N; i++) {
            sb.append(buildTime[i]).append("\n");
        }
        System.out.println(sb);
    }
}
