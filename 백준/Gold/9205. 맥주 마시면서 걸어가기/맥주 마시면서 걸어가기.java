import java.io.*;
import java.util.*;

class Node {
    int y, x;

    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int n,t;
    static List<List<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        while(t-- > 0)
        {
            // 편의점 개수
            n = Integer.parseInt(br.readLine());

            Node[] nodes = new Node[n+2];
            //정점들의 좌표 입력
            for(int i = 0; i < n+2; ++i)
            {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                nodes[i] = new Node(y,x);
            }

            map = new ArrayList<>();
            for(int i = 0; i < n+2; i++) map.add(new ArrayList<>());

            // 거리가 1000 이하인 정점끼리 연결
            for(int i = 0; i < n+1; ++i)
            {
                for(int j = i+1; j < n+2; ++j)
                {
                    int dist = Math.abs(nodes[i].x - nodes[j].x) + Math.abs(nodes[i].y - nodes[j].y);
                    if(dist <= 1000)
                    {
                        map.get(i).add(j);
                        map.get(j).add(i);
                    }
                }
            }
            sb.append(bfs()).append("\n");
        }
        System.out.print(sb);
    }
    static String bfs()
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        boolean [] visited = new boolean[n+2];
        visited[0] = true;

        while(!q.isEmpty())
        {
            int current = q.poll();

            if(current == n+1) return "happy";
            for(int i : map.get(current))
            {
                if(visited[i]) continue;
                visited[i] = true;
                q.add(i);
            }
        }
        return "sad";
    }
}