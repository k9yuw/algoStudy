import java.io.*;
import java.util.*;

public class Main {
	
	static int n, k;
	static int ans = 0;
	static String[] words;
	static boolean[] alphabet = new boolean[26];
	
	public static void main(String[] args) throws IOException{
		
//		System.setIn(new FileInputStream("src/DAY01/P1062/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기값, 배열크기 설정
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		words = new String[n];
		
		// 단어들 배열로 입력
		for(int i=0; i<n; i++) {
			words[i] = br.readLine();
		}
		
		// 꼭 배워야 하는 알파벳들은 true로 설정
		alphabet['a' - 'a'] = true;
		alphabet['c' - 'a'] = true;
		alphabet['i' - 'a'] = true;
		alphabet['n' - 'a'] = true;
		alphabet['t' - 'a'] = true;
		
		if(k<5) {
			System.out.println("0");
		} else {
			dfs(0,5);
			System.out.println(ans);
		}
		
	}
	
	public static void dfs(int idx, int cnt) {
			
		// 1. 체크인
		// 체크인이 끝난 시점에서 맞는 값이 들어갔는지 꼭 확인!!!!
		alphabet[idx] = true;
		
		// 2. 목적지인가? - alphabet에서 true인 값들이 k개일때, 읽을 수 있는 단어 수를 센다.
		if(cnt == k) {
			int curr = countReadableWord();
			ans = Math.max(curr, ans);
		}
		// 3. 연결된 곳을 순회 - 현재보다 다음거 ~ z까지
		for(int i = idx+1; i < 26; i++) {
			// 4. 갈 수 있는가? - 방문한 적이 없는 경우
			if(alphabet[i] == false) {
				// 5. 간다 - dfs 호출
				dfs(i, cnt + 1);
			}
		}
		// 6. 체크아웃 
		alphabet[idx] = false;
	}

	// 읽을 수 있는 단어의 개수를 알려주는 함수
	public static int countReadableWord() {	
		
		int cnt = 0;
		
		for(int i=0; i<n; i++) {
			boolean isReadable = true;	
			for(char c : words[i].toCharArray()) {
				if(alphabet[c - 'a'] == false) {
					isReadable = false;
					break;
				} else continue;
			}			
			if(isReadable) cnt++;			
		}
		return cnt;
	}
}