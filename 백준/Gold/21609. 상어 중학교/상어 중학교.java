import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static List<Coor> biggestBlock;
    static int[] dy = {-1,1,0,0}, dx = {0,0,-1,1}; // 상하좌우
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            biggestBlock = new ArrayList<>();
            boolean[][] globalVisited = new boolean[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][j] <= 0 || globalVisited[i][j]) continue;

                    visited = new boolean[N][N];
                    List<Coor> currBlock = bfs(i, j);

                    if(isAllRainbow(currBlock)) continue;
                    if(currBlock.size() < 2) continue;

                    for (Coor c : currBlock) {
                        if (board[c.y][c.x] != 0)
                            globalVisited[c.y][c.x] = true;
                    }

                    // 1. biggestBlock과 currBlock을 비교해서 갱신한다.
                    if(compareBlock(biggestBlock, currBlock)) {
                        biggestBlock = currBlock;
                    }
                }
            }

            // 2. biggestBlock이 없는 경우 break
            if(biggestBlock.isEmpty()) break;

            // 3. biggestBlock이 있는 경우
            ans += biggestBlock.size() * biggestBlock.size();
            removeBlock();
            gravity();
            rotate();
            gravity();
        }

        System.out.println(ans);
    }

    public static boolean compareBlock(List<Coor> a, List<Coor> b) {
        // 만약 아직 최고 그룹이 없다면 b를 선택
        if (a.isEmpty()) return true;

        // 1. 그룹 크기 비교 (큰 그룹이 우선)
        if (b.size() != a.size()) return b.size() > a.size();

        // 2. 무지개 블록 개수 비교 (많은 것이 우선)
        int aRainbow = countRainbow(a);
        int bRainbow = countRainbow(b);
        if (aRainbow != bRainbow) return bRainbow > aRainbow;

        // 3. 기준 블록 비교
        // 기준 블록은 (일반 블록 중) 행, 열이 가장 작은 블록으로 구한 후,
        // 그룹 비교 시에는 기준 블록의 행 번호가 큰(즉, 아래쪽에 있는) 그룹을 우선
        Coor aStd = getStandardBlock(a);
        Coor bStd = getStandardBlock(b);
        if (aStd.y != bStd.y) return bStd.y > aStd.y;
        return bStd.x > aStd.x;
    }


    public static int countRainbow(List<Coor> group) {
        int cnt = 0;
        for (Coor c : group) {
            if (board[c.y][c.x] == 0) cnt++;
        }
        return cnt;
    }

    public static Coor getStandardBlock(List<Coor> group) {
        Coor std = null;
        for (Coor c : group) {
            if (board[c.y][c.x] == 0) continue; // 무지개 블록 제외
            if (std == null || c.y < std.y || (c.y == std.y && c.x < std.x)) {
                std = c;
            }
        }
        return std;
    }

    public static boolean isAllRainbow(List<Coor> block) {
        int size = block.size();
        for(int i=0; i<size; i++) {
            Coor curr = block.get(i);
            if(board[curr.y][curr.x] != 0) return false;
        }
        return true;
    }

    public static List<Coor> bfs(int sY, int sX) {
        Queue<Coor> q = new LinkedList<Coor>();
        List<Coor> currGroup = new ArrayList<>();
        q.add(new Coor(sY, sX));
        visited[sY][sX] = true;
        currGroup.add(new Coor(sY, sX));

        int groupColor = board[sY][sX];

        while(!q.isEmpty()) {
            Coor curr = q.poll();
            for(int i=0; i<4; i++) {
                int nY = curr.y + dy[i];
                int nX = curr.x + dx[i];

                if(nY<0 || nY>=N || nX<0 || nX>=N) continue; // 보드 벗어남
                if(board[nY][nX] == -1) continue; // 검은색 블록
                if(visited[nY][nX]) continue;

                if(groupColor == board[nY][nX] || board[nY][nX] == 0) {
                    q.add(new Coor(nY, nX));
                    currGroup.add(new Coor(nY, nX));
                    visited[nY][nX] = true;
                }
            }
        }
        return currGroup;
    }

    public static void removeBlock() {
        for(Coor c : biggestBlock) {
            board[c.y][c.x] = -100;
        }
    }

    public static void gravity() {
        for(int x=0; x<N; x++) { // 열별로 검사
            for(int y=N-2; y>=0; y--) {
                if(board[y][x] < 0) continue; // 검은 블록, 빈칸 무시

                int ny = y;
                while(true) {
                    if(ny+1 >= N) break; // 맨 아래 도달
                    if(board[ny+1][x] != -100) break; // 빈칸이 아니면 멈춤
                    board[ny + 1][x] = board[ny][x];
                    board[ny][x] = -100;
                    ny++;
                }
            }
        }
    }

    public static void rotate() {
        int[][] rotated = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                rotated[N-1-j][i] = board[i][j];
            }
        }
        board = rotated;
    }
}

class Coor {
    int y, x;
    public Coor(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

