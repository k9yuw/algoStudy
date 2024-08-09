import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean[][] connected;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		connected = new boolean[N+1][N+1];
		
		// dist 초기 배열 설정
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				connected[i][j] = false;
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connected[a][b] = true;
		}
		
		
		floydWarshall();	
		printAns();
	}
	
	static void floydWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(connected[i][k] && connected[k][j]) {
						connected[i][j] = true;
						

					}
				}
			}
		}
	}
	
	static void printAns() {
		int ans = 0;
		for(int i=1; i<=N; i++) {
			int cnt = 0;
			for(int j=1; j<=N; j++) {
				if(connected[i][j]|| connected[j][i]) cnt++;
			}
			if(cnt == N-1) ans++;
		}
		System.out.println(ans);
	}
	
}
