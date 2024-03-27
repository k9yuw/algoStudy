import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int birds = Integer.parseInt(br.readLine());
        int num = 1;
        int cnt = 0;

        while (birds != 0) {
            if (num > birds) {
                num = 1;
            }

            birds -= num;
            cnt++;
            num++;
        }

        System.out.println(cnt);
    }
}