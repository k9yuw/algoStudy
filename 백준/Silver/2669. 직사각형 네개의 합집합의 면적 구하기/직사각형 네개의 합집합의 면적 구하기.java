import java.io.*;
import java.util.*;

public class Main{
    
    static boolean[][] board = new boolean[101][101];
    static int count;
    
    public static void main(String[] args) throws IOException{
        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					board[y][x] = true;
				}
			}
		}

        count = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (board[i][j] == true) {
					count++;
				}
			}
		}
		System.out.println(count);

	}
}
