import java.io.*;
import java.util.*;

public class Main {
    
    static int V;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray();
        int aCnt = 0;
        int bCnt = 0;
        for (int i = 0; i < V; i++) {
            if (arr[i] == 'A') {
                aCnt++;
            } else {
                bCnt++;
            }
        }

        if (aCnt > bCnt) {
            System.out.println("A");
        } else if (aCnt == bCnt) {
            System.out.println("Tie");
        } else {
            System.out.println("B");
        }
    }
}