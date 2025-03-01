import java.io.*;
import java.util.*;

public class Main {
    static int N, M, fuel;
    static int[][] grid;  // 0: 빈칸, 1: 벽
    static Position taxi;
    static List<Passenger> passengers = new ArrayList<>();
    static boolean[] served;
    // 상, 좌, 우, 하
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시 시작 위치
        st = new StringTokenizer(br.readLine());
        int taxiY = Integer.parseInt(st.nextToken()) - 1;
        int taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Position(taxiY, taxiX);

        served = new boolean[M];
        // 승객 정보 입력
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int sY = Integer.parseInt(st.nextToken()) - 1;
            int sX = Integer.parseInt(st.nextToken()) - 1;
            int dY = Integer.parseInt(st.nextToken()) - 1;
            int dX = Integer.parseInt(st.nextToken()) - 1;
            passengers.add(new Passenger(new Position(sY, sX), new Position(dY, dX)));
        }

        for (int i = 0; i < M; i++){
            if (!moveTaxi()) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(fuel);
    }

    static boolean moveTaxi() {
        // 1. 현재 택시 위치에서 모든 칸까지의 최단거리를 계산
        int[][] dist = getDistances(taxi.y, taxi.x);
        // 2. 아직 태우지 않은 승객 중, 최단 거리가 가장 짧은 승객을 선택
        int targetIndex = -1;
        int minDist = Integer.MAX_VALUE;
        int targetY = -1, targetX = -1;

        for (int i = 0; i < M; i++){
            if (served[i]) continue;
            Passenger p = passengers.get(i);
            int d = dist[p.start.y][p.start.x];
            if (d == -1) continue; // 도달 불가
            if (d < minDist) {
                minDist = d;
                targetIndex = i;
                targetY = p.start.y;
                targetX = p.start.x;
            } else if (d == minDist) {
                // 행 번호 우선, 그 다음 열 번호
                if (p.start.y < targetY || (p.start.y == targetY && p.start.x < targetX)) {
                    targetIndex = i;
                    targetY = p.start.y;
                    targetX = p.start.x;
                }
            }
        }
        // 도달 가능한 승객이 없거나 연료 부족인 경우
        if (targetIndex == -1 || fuel < minDist) return false;

        // 3. 이동
        // 3-1. 승객 픽업: 택시 이동 및 연료 차감
        fuel -= minDist;
        taxi.y = targetY;
        taxi.x = targetX;

        // 3-2. 승객의 목적지까지 이동
        Passenger target = passengers.get(targetIndex);
        int[][] dist2 = getDistances(taxi.y, taxi.x);
        int d2 = dist2[target.dest.y][target.dest.x];
        if (d2 == -1 || fuel < d2) return false;

        fuel -= d2;
        // 4. 연료 보충
        fuel += d2 * 2;
        taxi.y = target.dest.y;
        taxi.x = target.dest.x;
        served[targetIndex] = true;
        return true;
    }

    // (sy, sx)에서 시작하여 각 칸까지의 최단 거리를 배열로 반환 (도달 불가능하면 -1)
    static int[][] getDistances(int sy, int sx) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(dist[i], -1);
        }
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(sy, sx));
        dist[sy][sx] = 0;

        while (!q.isEmpty()){
            Position cur = q.poll();
            for (int d = 0; d < 4; d++){
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (dist[ny][nx] != -1) continue;
                if (grid[ny][nx] == 1) continue;  // 벽이면 이동 불가
                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                q.add(new Position(ny, nx));
            }
        }
        return dist;
    }

    static class Position {
        int y, x;
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Passenger {
        Position start, dest;
        public Passenger(Position start, Position dest) {
            this.start = start;
            this.dest = dest;
        }
    }
}
