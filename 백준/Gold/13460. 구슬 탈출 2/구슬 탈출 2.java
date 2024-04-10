import java.io.*;
import java.util.*;

public class Main{

    static class Status{

        int ry,rx,by,bx,count;

        public Status(int ry, int rx, int by, int bx, int count){
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.count = count;
        }
    }

    static int n,m;
    // 상, 하, 좌, 우
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static char[][] board;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        for(int i=0; i<n; i++){
            board[i] = br.readLine().toCharArray();
        }

        Status init = new Status(0,0,0,0,0);

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j]=='R'){
                    init.ry = i;
                    init.rx = j;
                    board[i][j] = '.';
                }
                if(board[i][j]=='B'){
                    init.by = i;
                    init.bx = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(init));

    }

    static int bfs(Status status){

        Queue<Status> queue = new LinkedList<>();
        queue.offer(status);

        while(!queue.isEmpty()){

            Status currStat = queue.poll();

            // 1. 종료조건 설정
            if(currStat.count == 10) break;

            // 2. 4방향 회전 for문
            for(int d=0; d<4; d++){

                int cry = currStat.ry, crx = currStat.rx;
                int cby = currStat.by, cbx = currStat.bx;
                int currCnt = currStat.count;
                boolean isRedHole = false, isBlueHole = false;

                // 2-1. red 이동
                while(true){

                    int nry = cry + dy[d];
                    int nrx = crx + dx[d];

                    if(board[nry][nrx] == '#') break;
                    if(board[nry][nrx] == 'O'){
                        isRedHole = true;
                        break;
                    }
                    cry = nry;
                    crx = nrx;
                }

                // 2-2. blue 이동
                while(true){

                    int nby = cby + dy[d];
                    int nbx = cbx + dx[d];

                    if(board[nby][nbx] == '#') break;
                    if(board[nby][nbx] == 'O'){
                        isBlueHole = true;
                        break;
                    }
                    cby = nby;
                    cbx = nbx;
                }

                // 2-3. 파란 구슬이 구멍에 빠진 경우는 무시
                if(isBlueHole) continue;

                // 2-4. 빨간 구슬이 구멍에 빠진 경우 현재 카운트 반환
                if(isRedHole) return currCnt + 1;

                // 2-5. 빨간 구슬과 파란 구슬이 같은 위치에 있는 경우 처리
                if(cry == cby && crx == cbx){
                    int redDist = Math.abs(cry - currStat.ry) + Math.abs(crx - currStat.rx);
                    int blueDist = Math.abs(cby - currStat.by) + Math.abs(cbx - currStat.bx);
                    if(redDist > blueDist){ // 더 많이 움직인 구슬을 한 칸 뒤로 이동
                        cry -= dy[d];
                        crx -= dx[d];
                    } else {
                        cby -= dy[d];
                        cbx -= dx[d];
                    }
                }

                // 2-6. 다음 상태를 큐에 추가
                queue.offer(new Status(cry, crx, cby, cbx, currCnt + 1));
            }

        }
        return -1; // 실패
    }

}
