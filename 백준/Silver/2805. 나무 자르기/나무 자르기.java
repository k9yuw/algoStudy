import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int high = 0, low = 0, mid = 0;
	static int[] trees;
	static int result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, trees[i]);
		}
		
		
		while(true) {
			
			mid = (low + high) / 2;
			
			// 나무 높이 구하기
			long sum = getSum();
			
			if(sum == M) {
				result = mid;
				break;
			} else if (sum < M) { // 나무가 모자란 경우 high를 내려서 나무를 더 잘라줌
				high = mid - 1;
			} else { // 나무가 과한 경우
				result = mid; 
				low = mid + 1; // mid를 버린다면 근소하지만 탐색 범위를 줄일 수 있음
			}
			
			if(low > high) break;
		}

		System.out.println(result);
	}
	
	public static long getSum() { // long으로 선언 해줘야됨!!!!
		
		long tempSum = 0;
		for(int t : trees) {
			if(t - mid > 0) { // 나무가 자르는 지점보다 더 큰 경우에만 잘라서 가져갈 수 있음
				tempSum += (t - mid);
			}
		}
		return tempSum;
	}
	
	
}
