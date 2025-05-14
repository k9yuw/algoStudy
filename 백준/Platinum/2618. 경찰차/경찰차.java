import java.io.*;
import java.util.*;

public class Main {

    static int N, W;
    static int[][] dp, choice;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
    static List<Coor> coorList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        dp = new int[W+1][W+1];
        choice = new int[W+1][W+1];
        for(int i=0; i<=W; i++) {
            Arrays.fill(dp[i], -1);
        }

        coorList.add(new Coor(-1, -1));
        for(int i=0; i<W; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            coorList.add(new Coor(y, x));
        }

        System.out.println(dfs(0,0));
        printPath();
    }

    // 경찰차 1번의 마지막 처리 사건이 i, 경찰차 2번의 마지막 처리 사건이 j일 때
    // 남은 사건들(W번째까지)을 모두 해결하기 위해 이동해야 하는 최소거리
    public static int dfs(int i, int j) {

        // 가장 마지막 사건에 도달한 경우
        if(i == W || j == W) return 0;

        int k = Math.max(i,j) + 1;
        Coor next = coorList.get(k);

        if(dp[i][j] != -1) return dp[i][j];

        Coor car1 = (i == 0) ? new Coor(1, 1) : coorList.get(i);
        Coor car2 = (j == 0) ? new Coor(N, N) : coorList.get(j);

        int dist1 = dist(next, car1) + dfs(k, j); // 경찰차1: i -> k
        int dist2 = dist(next, car2) + dfs(i, k); // 경찰차2: j -> k

        if(dist1 < dist2) {
            choice[i][j] = 1;
            dp[i][j] = dist1;
        } else {
            choice[i][j] = 2;
            dp[i][j] = dist2;
        }

        return dp[i][j];
    }

    public static void printPath() {
        int i = 0;
        int j = 0;
        for(int k=1; k<=W; k++) {
            if(choice[i][j] == 1) {
                System.out.println(1);
                i = k;
            } else {
                System.out.println(2);
                j = k;
            }
        }
    }

    public static int dist(Coor a, Coor b) {
        return Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
    }

    public static class Coor {
        int y, x;

        public Coor(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
