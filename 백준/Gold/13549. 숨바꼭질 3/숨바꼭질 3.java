import java.io.*;
import java.util.*;

class Node{
    int x;
    int time;

    public Node(int x, int time){
        this.x = x;
        this.time = time;
    }
}

public class Main{

    static int n, k;
    static int max = 100000;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visited = new boolean[max+1];

        bfs();
        System.out.println(min);
    }

    public static void bfs(){

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 0));

        while(!q.isEmpty()){

            Node curr = q.poll();
            visited[curr.x] = true;

            if(curr.x == k) min = Math.min(min, curr.time);

            if(curr.x * 2 <= max && !visited[curr.x * 2]) q.offer(new Node(curr.x * 2, curr.time));
            if(curr.x + 1 <= max && !visited[curr.x + 1]) q.offer(new Node(curr.x + 1, curr.time + 1));
            if(curr.x - 1 >= 0 && !visited[curr.x - 1]) q.offer(new Node(curr.x - 1, curr.time + 1));

        }
    }

}