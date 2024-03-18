import java.io.*;
import java.util.*;

public class Main{
    
    static int N,M;
    static int[][] map;
    static ArrayList<Node> chickenList = new ArrayList<>();
    static ArrayList<Node> homeList = new ArrayList<>();
    static boolean[] visited; // 치킨집 방문 여부
    static int ans = Integer.MAX_VALUE; 
    
    public static class Node{
        int x;
        int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) homeList.add(new Node(i,j));
                else if(map[i][j]==2) chickenList.add(new Node(i,j));
            }
        }
        
        visited = new boolean[chickenList.size()]; 
        backTracking(0,0); 
        System.out.println(ans);        
     
    }
    
    public static void backTracking(int cnt, int idx) { 
        if(cnt == M) {
            int cityDist = 0; // 도시의 치킨거리
            for(int i=0; i < homeList.size(); i++) {
                int minDist = Integer.MAX_VALUE;
                for(int j=0; j < chickenList.size(); j++) {
                    if(visited[j] == true) { //i번째 집, j번째 치킨집 거리 중 최소값
                        int tempDist = Math.abs(homeList.get(i).x - chickenList.get(j).x) 
                                + Math.abs(homeList.get(i).y - chickenList.get(j).y);
                        minDist = Math.min(minDist, tempDist); // 각 집에 해당하는 최소 치킨거리
                    }
                }
                cityDist += minDist; 
            }
            ans = Math.min(cityDist, ans); // 답은 cityDist 중 최솟값
            return;
        }
        
        for(int i = idx; i < chickenList.size(); i++) {
            if(visited[i] == false) {
                visited[i] = true;
                backTracking(cnt+1, i+1);
                visited[i] = false;
            }
        }
    }

}
