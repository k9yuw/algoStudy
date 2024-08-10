import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] board, dist;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getCost));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int problemCnt = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N==0) break;
            board = new int[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(problemCnt).append(": ");
            sb.append(dijkstra()).append("\n");
            problemCnt++;
        }
        System.out.println(sb);
    }

    static int dijkstra(){

        dist = new int[N][N];
        for(int i=0; i<N; i++){
            Arrays.fill(dist[i], INF);
        }
        pq.offer(new Node(0,0,board[0][0]));
        dist[0][0] = board[0][0];

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            for(int i=0; i<4; i++){
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
                if(dist[ny][nx] > dist[curr.y][curr.x] + board[ny][nx]){
                    dist[ny][nx] = dist[curr.y][curr.x] + board[ny][nx];
                    pq.offer(new Node(ny, nx, dist[ny][nx]));
                }
            }
        }
        return dist[N-1][N-1];
    }
}

class Node{
    int y, x;
    int cost;

    public Node(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}