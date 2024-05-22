import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] nd = br.readLine().split(" ");
            int N = Integer.parseInt(nd[0]);
            int D = Integer.parseInt(nd[1]); 
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                String[] vfc = br.readLine().split(" ");
                int v = Integer.parseInt(vfc[0]);
                int f = Integer.parseInt(vfc[1]);
                int c = Integer.parseInt(vfc[2]); 

                if ((double)v * f / c >= D) {
                    cnt++;
                }
            }
            System.out.println(cnt); 
        }
    }
}
