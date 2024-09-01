import java.io.*;
import java.util.*;

public class Main {

    static String S, T;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        dfs(T);
        System.out.println(ans);

    }

    static void dfs(String currT) { // T를 한 글자씩 줄여서 S가 될 수 있는지 확인한다.

        // 1. 종료조건인가?
        if(S.length() == currT.length()){
            if(S.equals(currT)){
                ans = 1;
            }
            return;
        }

        // 2. 순회
        if(currT.endsWith("A")) {
            // 3. 간다: 끝이 A로 끝나는 경우 맨 뒤의 A를 뺀다.
            dfs(currT.substring(0, currT.length() - 1));
        }
        if(currT.startsWith("B")) {
            // 3. 간다: B로 시작하는 경우 맨 앞에 B를 빼고 뒤집는다.
            dfs(new StringBuilder(currT.substring(1, currT.length())).reverse().toString());
        }

    }
}