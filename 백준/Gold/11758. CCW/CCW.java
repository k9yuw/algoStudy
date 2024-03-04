import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Point[] p = new Point[3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            p[i] = new Point(x, y);
        }

        int result = (p[0].x * p[1].y) + (p[1].x * p[2].y) + (p[2].x * p[0].y) - (p[1].x * p[0].y) - (p[2].x * p[1].y)
                - (p[0].x * p[2].y);

        if (result > 0)
            System.out.println("1");
        else if (result == 0)
            System.out.println("0");
        else
            System.out.println("-1");

    }
}