import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
 
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] answers = new int[5];
        for (int i = 0; i < 5; i++) {
            answers[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (answers[i] == T) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
