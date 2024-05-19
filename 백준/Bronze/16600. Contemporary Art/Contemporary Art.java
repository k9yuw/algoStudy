import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double a = Double.parseDouble(br.readLine());

        double ans = 4 * Math.sqrt(a);

        System.out.printf("%.7f\n", ans);
    }
}
