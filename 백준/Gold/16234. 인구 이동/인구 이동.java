import java.io.*;
import java.util.*;

public class Main{

    static class Node {
        int y, x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int n, l, r;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static ArrayList<Node> countryList;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());

    }

    static int move() {

        int day = 0;

        while(true){
            visited = new boolean[n][n];
            boolean isMoving = false;

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j]){
                        int sum = bfs(i,j);
                        if(countryList.size()>1){
                            distribute(sum);
                            isMoving = true;
                        }
                    }
                }
            }

            if(!isMoving) return day;
            day++;

        }
    }

    static void distribute(int sum){

        int countryNum = countryList.size();
        int avg = sum / countryNum;
        for(int i=0; i<countryNum; i++){
            Node node = countryList.get(i);
            int y = node.y;
            int x = node.x;

            board[y][x] = avg;
        }

    }

    static int bfs(int y, int x) {

        visited[y][x] = true;
        Queue<Node> queue = new LinkedList<>();
        countryList = new ArrayList<Node>();

        queue.offer(new Node(y, x));
        countryList.add(new Node(y, x));
        int populationSum = board[y][x];

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            int cy = curr.y;
            int cx = curr.x;
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if(ny<0 || nx<0 || ny>=n || nx>=n || visited[ny][nx]) continue;

                int diff = Math.abs(board[cy][cx] - board[ny][nx]);
                if(diff>=l && diff<=r){
                    visited[ny][nx] = true;
                    queue.offer(new Node(ny, nx));
                    countryList.add(new Node(ny, nx));
                    populationSum += board[ny][nx];
                }
            }
        }

        return populationSum;
    }
}
  