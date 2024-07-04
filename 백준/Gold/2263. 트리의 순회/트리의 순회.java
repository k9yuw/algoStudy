import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int idx = 0; // preorder 배열에 값을 채울 때 사용할 인덱스
    static int[] preorder, inorder, postorder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        inorder = new int[n];
        postorder = new int[n];
        preorder = new int[n];

        // inorder 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        // postorder 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        getPreOrder(0, n - 1, 0, n - 1);

        // preorder 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(preorder[i]).append(' ');
        }
        System.out.println(sb.toString().trim());
    }

    static void getPreOrder(int is, int ie, int ps, int pe) {

        // is, ie : inorder의 시작, 끝
        // ps, pe : postorder의 시작, 끝

        if (is <= ie && ps <= pe) {

            // postorder[pe] 는 root이고, preorder은 맨 앞에 root값이 나온다. 
            preorder[idx++] = postorder[pe]; 

            // postorder[pe] 가 root라는 사실을 이용해 rootPos 찾기
            int rootPos = is;
            for (int i = is; i <= ie; i++) {
                if (inorder[i] == postorder[pe]) {
                    rootPos = i;
                    break;
                }
            }

            // 왼쪽 자식 트리를 가지고 재귀
            getPreOrder(is, rootPos - 1, ps, ps + rootPos - is - 1);
            // 오른쪽 자식 트리를 가지고 재귀
            getPreOrder(rootPos + 1, ie, ps + rootPos - is, pe - 1);
        }
    }
}
