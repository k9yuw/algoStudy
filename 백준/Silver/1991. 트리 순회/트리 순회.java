import java.io.*;
import java.util.*;

class Node {
    char value;
    Node left;
    Node right;

    public Node(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    static Node[] tree;

    // 전위 순회
    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회
    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    // 후위 순회
    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new Node[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            int parentIdx = parent - 'A';
            char left = st.nextToken().charAt(0);
            int leftIdx = left - 'A';
            char right = st.nextToken().charAt(0);
            int rightIdx = right - 'A';

            // Java에서 char 데이터 타입은 내부적으로 ASCII 코드를 사용
            if (tree[parentIdx] == null) { // 부모 노드가 아직 생성되지 않은 경우. 'A'는 문자 'A'의 ASCII 값
                tree[parentIdx] = new Node(parent); // 부모 노드를 생성
            }
            if (left != '.') { // 왼쪽 자식이 존재할 경우
                tree[leftIdx] = new Node(left); // 왼쪽 자식 노드를 생성
                tree[parentIdx].left = tree[leftIdx]; // 부모 노드와 연결
            }
            if (right != '.') { // 오른쪽 자식이 존재할 경우
                tree[rightIdx] = new Node(right); // 오른쪽 자식 노드를 생성
                tree[parentIdx].right = tree[rightIdx]; // 부모 노드와 연결
            }
        }

        // 전위 순회
        preorder(tree[0]);
        System.out.println();

        // 중위 순회
        inorder(tree[0]);
        System.out.println();

        // 후위 순회
        postorder(tree[0]);
        System.out.println();
    }
}