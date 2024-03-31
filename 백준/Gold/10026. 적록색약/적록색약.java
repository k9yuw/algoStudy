import java.io.*;

public class Main{
    
    static int n;
    static int cnt1, cnt2;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] board1, board2;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board1 = new char[n][n];
        board2 = new char[n][n];
        
        for(int i=0; i<n; i++){
            String st = br.readLine();
            for(int j=0; j<n; j++){
                board1[i][j] = st.charAt(j);
                board2[i][j] = st.charAt(j);
                if(board2[i][j] == 'G') board2[i][j] = 'R';
            }
        }
        
        cnt1 = countArea(board1);
        cnt2 = countArea(board2);
        
        System.out.println(cnt1 + " " + cnt2);
           
    }
    
    public static int countArea(char[][] board) {
        int count = 0;
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, board[i][j], board);
                    count++;
                }
            }
        }
        return count;
    }
    
    public static void dfs(int x, int y, char color, char[][] board){
        
        visited[x][y] = true;
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
            if(!visited[nx][ny] && board[nx][ny] == color){
                dfs(nx, ny, color, board);
            }
        }
    }     
}
