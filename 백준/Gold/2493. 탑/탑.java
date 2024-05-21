import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<int[]> stack = new Stack<>();

        for(int n = 1; n <= N; n++) {
            int curr = Integer.parseInt(st.nextToken());
            while(!stack.empty()) {
                if(stack.peek()[1] < curr) stack.pop();
                else {
                    System.out.print(stack.peek()[0] + " ");
                    break;
                }
            }
            if(stack.isEmpty()) System.out.print("0 ");
            stack.push(new int[] {n, curr});
        }
    }
}