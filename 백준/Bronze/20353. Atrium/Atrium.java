import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long area = Long.parseLong(br.readLine().trim());
        double sideLength = Math.sqrt(area);
        double perimeter = 4 * sideLength;

        System.out.printf("%.6f\n", perimeter);
    }
}
