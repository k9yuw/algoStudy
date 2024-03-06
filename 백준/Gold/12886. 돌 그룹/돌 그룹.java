import java.io.*;
import java.util.*;

public class Main{
    
    static int a,b,c;
    static boolean[][] visited = new boolean[1501][1501];
    static int ans = 0;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        if ((a+b+c)%3 != 0){
            bw.write("0");
        } else {
            dfs(a,b,c);
            bw.write(String.valueOf(ans));
        }    
        bw.close();
        br.close();
    }
    
    static void dfs(int a, int b, int c){
        if(a==b && b==c){
            ans = 1;
            return;
        }
        calc(a,b,c);
        calc(b,c,a);
        calc(c,a,b);
    }
    
    static void calc(int a, int b, int c){
        int small = Math.min(a,b);
        int big = Math.max(a,b);
        
        if (!visited[small*2][big-small]){
            visited[small*2][big-small] = visited[big-small][small*2] = true;
            dfs(small*2, big-small, c);
        }
    }
}