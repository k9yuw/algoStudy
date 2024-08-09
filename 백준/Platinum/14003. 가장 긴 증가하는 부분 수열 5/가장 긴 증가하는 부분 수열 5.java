import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] input, dp, trace;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		input = new int[N];
		dp = new int[N];
		trace = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int lastIndex = 0;
		// 수열의 첫번째 값은 dp(LIS) 배열에 추가하고 시작한다.
		dp[0] = input[0];
		
		for(int i=0; i<N; i++) {
			// dp의 가장 끝에 있는 값보다 더 큰값이 들어오면 맨 뒤에 붙여준다.
			if(input[i] > dp[lastIndex]) { 
				dp[++lastIndex] = input[i];
				trace[i] = lastIndex;
			} else {
				int lb = lowerBound(lastIndex, input[i]);
				dp[lb] = input[i];
				// i번째 input이 dp의 몇번째 인덱스에 들어갔는지 기록한다. 
				trace[i] = lb;
			}
		}
		
		int LISlength = lastIndex + 1;
		sb.append(LISlength).append("\n");
		
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		for(int i=N-1; 0<=i; i--) {
			if(trace[i] == lastIndex) {
				stack.push(input[i]);
				lastIndex--;
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb);
		
	}
	
	// target이 들어갈 수 있는, target보다 같거나 작은 값을 가지고 있는 dp 위치를 찾는다. 
	static int lowerBound(int lastIndex, int target) {
		int mid;
		int left = 0;
		int right = lastIndex;
		
		while(left < right) {
			mid = (left + right) / 2;
			if(dp[mid] >= target) {
				right = mid; // 더 왼쪽 탐색
			} else {
				left = mid+1; // 더 오른쪽 탐색
			}
		}
		
		return right;
	}
}
