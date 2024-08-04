import java.io.*;
import java.util.*;

public class Main {

    static int n, s;
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        s = 1;
        while (s < n) {
            s *= 2;
        }
        tree = new int[2 * s];
        arr = new int[s + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(1, s, 1);
        long ans = getMaxArea(1, n);
        System.out.println(ans);

    }

    static void init(int left, int right, int node) {
        if (left == right) {
            if (left <= n) {
                tree[node] = left;
            }
            return;
        }

        int mid = (left + right) / 2;
        init(left, mid, node * 2);
        init(mid + 1, right, node * 2 + 1);

        int leftNode = tree[node * 2];
        int rightNode = tree[node * 2 + 1];

        if (leftNode == 0) {
            tree[node] = rightNode;
        } else if (rightNode == 0) {
            tree[node] = leftNode;
        } else if (arr[leftNode] < arr[rightNode]) {
            tree[node] = leftNode;
        } else {
            tree[node] = rightNode;
        }
    }

    static int query(int left, int right, int node, int qL, int qR) {
        if (right < qL || qR < left) {
            return 0;
        } else if (qL <= left && right <= qR) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            int leftMinIdx = query(left, mid, node * 2, qL, qR);
            int rightMinIdx = query(mid + 1, right, node * 2 + 1, qL, qR);

            if (leftMinIdx == 0) return rightMinIdx;
            if (rightMinIdx == 0) return leftMinIdx;

            if (arr[leftMinIdx] < arr[rightMinIdx]) {
                return leftMinIdx;
            } else {
                return rightMinIdx;
            }
        }
    }

    static long getMaxArea(int qL, int qR) {
        if (qL > qR) {
            return 0;
        }

        int minIndex = query(1, s, 1, qL, qR);
        long maxArea = (long) (qR - qL + 1) * arr[minIndex];

        if (qL < minIndex) {
            long leftMaxArea = getMaxArea(qL, minIndex - 1);
            maxArea = Math.max(leftMaxArea, maxArea);
        }

        if (minIndex < qR) {
            long rightMaxArea = getMaxArea(minIndex + 1, qR);
            maxArea = Math.max(rightMaxArea, maxArea);
        }

        return maxArea;
    }
}
