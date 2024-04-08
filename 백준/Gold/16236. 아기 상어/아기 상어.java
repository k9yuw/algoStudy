import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int eaten, sharkX, sharkY, sharkSize = 2, time;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] board;
    static boolean[][] visited;

    static class Fish {

        int x, y, dist;
        public Fish(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    board[i][j] = 0;
                }
            }
        }

        while(true){
            Fish fish = findNearestFish();
            if(fish == null) break;
            moveShark(fish);
            time += fish.dist;
        }
        System.out.println(time);
    }


    static Fish findNearestFish() {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sharkX, sharkY, 0});
        visited[sharkX][sharkY] = true;

        Fish nearestFish = null;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], dist = current[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && board[nx][ny] <= sharkSize) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, dist + 1});

                    if (board[nx][ny] > 0 && board[nx][ny] < sharkSize) {
                        if (nearestFish == null || nearestFish.dist > dist + 1 ||
                            (nearestFish.dist == dist + 1 && (nearestFish.x > nx || (nearestFish.x == nx && nearestFish.y > ny)))) {
                            nearestFish = new Fish(nx, ny, dist + 1);
                        }
                    }
                }
            }
        }
        return nearestFish;
    }


    static void moveShark(Fish fish){

        board[sharkX][sharkY] = 0;
        sharkX = fish.x;
        sharkY = fish.y;
        eaten++;

        if(eaten == sharkSize){
            eaten = 0;
            sharkSize++;
        }
    }

}