import java.io.*;
import java.util.*;

public class Main{

    static int n, q, m;
    static int iceCount;
    static int[] L;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        m = (int) Math.pow(2, n); // m = 2^n

        board = new int[m][m];
        visited = new boolean[m][m];
        L = new int[q];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++){
            L[i] = Integer.parseInt(st.nextToken());
        }

        // q번 파이어스톰 실행
        for(int i=0; i<q; i++){
            FireStorm(L[i]);
        }

        // 남아있는 얼음 양
        int sum = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                sum += board[i][j];
            }
        }

        // 가장 큰 덩어리
        int ans = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && board[i][j]!=0){
                    iceCount = 1;
                    dfs(i, j);
                    ans = Math.max(ans, iceCount);
                }
            }
        }
        System.out.println(sum);
        System.out.println(ans);
    }

    static void dfs(int y, int x){
        visited[y][x] = true;

        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0 || nx<0 || ny>=m || nx>=m || visited[ny][nx]) continue;
            if(board[ny][nx] > 0){
                dfs(ny, nx);
                iceCount++;
            }
        }
    }

    static void FireStorm(int l){
        int k = (int) Math.pow(2, l); // k = 2^l

        int[][] tempBoard = copy(board);
        for(int i=0; i<m; i+=k){ // k*k 격자 회전
            for(int j=0; j<m; j+=k){
                rotate(i, j, k, tempBoard);
            }
        }
        melt(); // 회전 시키고 얼음 녹이기
    }

    static void melt(){

        List<int[]> list = new ArrayList<>();

        for(int y=0; y<m; y++){
            for(int x=0; x<m; x++){
                int cnt = 0;
                for(int i=0; i<4; i++){
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if(ny<0 || nx<0 || ny>=m || nx>=m) continue;
                    if(board[ny][nx]>0) cnt++;
                }
                if(cnt<3) list.add(new int[] {y, x});
            }
        }
        for(int i=0; i<list.size(); i++){
            int[] current = list.get(i);
            if(board[current[0]][current[1]]>0){
                board[current[0]][current[1]] -= 1;
            }
        }
    }

    static void rotate(int y, int x, int k, int[][] tempBoard){

        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++){
                board[y+i][x+j] = tempBoard[y+k-j-1][x+i];
            }
        }
    }

    static int[][] copy(int[][] board){
        int[][] tempBoard = new int[m][m];
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                tempBoard[i][j] = board[i][j];
            }
        }
        return tempBoard;
    }
}

