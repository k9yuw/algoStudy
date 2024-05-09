import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();	// 출력할 결과물 저장

        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());

        int start = 0;

        // N 번 반복
        while(n -- > 0) {

            int value = Integer.parseInt(br.readLine());

            if(value > start) {
                for(int i = start + 1; i <= value; i++) {
                    stack.push(i);
                    sb.append('+').append('\n');	
                }
                start = value; 	
            }
            
            else if(stack.peek() != value) {
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append('-').append('\n');

        }

        System.out.println(sb);
    }
}
