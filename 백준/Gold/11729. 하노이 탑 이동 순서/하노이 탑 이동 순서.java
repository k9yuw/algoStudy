import java.io.*;
import java.util.*;

public class Main {
    
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 원판 개수

        sb.append((int) (Math.pow(2, n) - 1)).append('\n');

        hanoi(n, 1, 2, 3);
        System.out.println(sb);

    }

    // start: 출발지점
    // mid: 중간에 거쳐가는 지점
    // to: 목적지
    public static void hanoi(int n, int start, int mid, int to) {
        
        // 이동할 원반의 수가 1개인 경우
        if (n == 1) {
            sb.append(start + " " + to + "\n");
            return;
        }

        // A -> C로 옮기는 경우를 가정
        // n-1개를 A에서 B로 이동 
        hanoi(n - 1, start, to, mid);

        // 1개를 A에서 C로 이동 
        sb.append(start + " " + to + "\n");

        // n-1개를 B에서 C로 이동 
        hanoi(n - 1, mid, start, to);
    }
}