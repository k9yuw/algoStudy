import java.io.*;
import java.util.*;

class Fireball{

    int y,x;
    int m,s,d;

    Fireball(int y, int x, int m, int s, int d) {
        this.y = y;
        this.x = x;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main{

    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static int[] dx = {0,1,1,1,0,-1,-1,-1};
    static int n,m,k;
    static ArrayList<Fireball> balls = new ArrayList<>();
    static ArrayList<Integer>[][] board;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new ArrayList[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new Fireball(y, x, m, s, d));
            board[y][x].add(i);
        }

        solve();
        int ans = 0;
        for(Fireball ball: balls){
            ans += ball.m;
        }
        System.out.println(ans);
    }

    static void move(){
        ArrayList<Integer>[][] newBoard = new ArrayList[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                newBoard[i][j] = new ArrayList<>();
            }
        }

        for(Fireball ball: balls){
            int dir = ball.d;
            // %N을 통해 파이어볼이 화면을 벗어날 경우 대비
            int speed = (ball.s % n);
            // +N을 통해 좌표가 음수가 되는 경우 대비
            int ny = (ball.y + (dy[dir] * speed) + n) % n;
            int nx = (ball.x + (dx[dir] * speed) + n) % n;

            // ball을 이동시키고, tempBoard에 이동한 위치정보 업데이트
            ball.y = ny;
            ball.x = nx;
            newBoard[ny][nx].add(balls.indexOf(ball));
        }
        board = newBoard;
    }

    static void sum(){
        ArrayList<Fireball> newBalls = new ArrayList<>();
        for(int y=0; y<n; y++){
            for(int x=0; x<n; x++){
                // 해당 위치에 아무런 파이어볼도 없는 경우
                if(board[y][x].size() == 0) continue;
                // 해당 위치에 파이어볼이 하나만 존재하는 경우
                if(board[y][x].size() == 1){
                    int index = board[y][x].get(0); // 들어있는 한 개의 파이어볼 인덱스를 가져와서
                    newBalls.add(balls.get(index)); // balls에서 index번째 ball을 가져와 newBalls에 입력해줌
                    continue;
                }

                // 각 위치에 있는 파이어볼을 합치고
                // 합쳐지는 파이어볼들의 방향값을 확인
                int sumM = 0, sumS = 0;
                boolean odd = true, even = true;
                for(int index : board[y][x]) {
                    sumM += balls.get(index).m;
                    sumS += balls.get(index).s;

                    if(balls.get(index).d % 2 == 0) odd = false; // 짝수값이 하나라도 있는 경우 odd는 false
                    else even = false; // 홀수값이 하나라도 있는 경우 even은 false
                }

                // 합쳐지는 파이어볼들의 질량이 5를 넘지 않을 경우
                if(sumM / 5 == 0) continue;

                // 여러 개의 파이어볼들 합쳐서 다시 4개로 나누기
                int curM = sumM / 5;
                int curS = sumS / board[y][x].size();
                for(int i=0; i<4; i++){
                    if(odd || even){ // 파이어볼이 모두 홀수이거나 짝수인 경우
                        newBalls.add(new Fireball(y, x, curM, curS, i*2));
                    } else {
                        newBalls.add(new Fireball(y, x, curM, curS, i*2+1));
                    }
                }
            }
        }

        balls = newBalls;
    }

    static void solve(){
        while(k-- > 0){
            move();
            sum(); 
        }
    }
}

