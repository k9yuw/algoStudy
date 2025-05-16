import java.io.*;
import java.util.*;

public class Solution {

    int maxSheep = 0;
    boolean[] isWolf;
    List<List<Integer>> adjList = new ArrayList<>();

    public int solution(int[] info, int[][] edges) {
        int N = info.length;
        isWolf = new boolean[N];

        for (int i = 0; i < N; i++) {
            isWolf[i] = info[i] == 1;
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);

        dfs(0, 0, 0, nextNodes);

        return maxSheep;
    }

    public void dfs(int node, int sheep, int wolf, List<Integer> nextNodes) {
        if (isWolf[node]) wolf++;
        else sheep++;

        if (wolf >= sheep) return; // 이 상태는 불가능

        maxSheep = Math.max(maxSheep, sheep);

        // 현재 노드에서 갈 수 있는 후보 노드를 확장
        List<Integer> candidates = new ArrayList<>(nextNodes);
        candidates.remove(Integer.valueOf(node));
        candidates.addAll(adjList.get(node));

        for (int next : candidates) {
            dfs(next, sheep, wolf, candidates);
        }
    }
}
