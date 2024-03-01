import java.io.*;
import java.util.*;

public class Main{
    
    final static int COSTLIM = 10000001;
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n, m;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        
        // 값 초기화
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                arr[i][j] = COSTLIM;
            }
            arr[i][i] = 0;
        }
        
        // 입력 받기
        for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                arr[a][b] = Math.min(arr[a][b],c);
        }
        
        // 플로이드 와샬 알고리즘
        // 거쳐가는 정점을 기준으로 부분 최적해를 찾는 DP의 일종인데
        // 거쳐가는 정점을 하위반복문으로 놓게 되면 k 를 고려하지 않고 최단경로를 결정하는 경로가 생기므로 문제 발생
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }
        
        // 갈 수 없는 경우 0 출력
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if (arr[i][j] == COSTLIM) arr[i][j]=0;
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
}