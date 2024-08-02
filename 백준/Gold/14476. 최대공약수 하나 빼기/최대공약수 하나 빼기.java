import java.io.*;
import java.util.*;

public class Main {

	static int N, S;
	static int[] arr, LR, RL;
	static int[] tree;
	static int maxGCD = 0, excludedNum;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		S = 1;
		while (S < N) {
			S *= 2;
		}
		arr = new int[S];
		tree = new int[2 * S];
		LR = new int[N + 1];
		RL = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init();
		setLRRL();
		findMaxGCD();

		if(isValid()) {
			StringBuilder sb = new StringBuilder();
			sb.append(maxGCD + " " + excludedNum);
			System.out.println(sb);	
		} else {
			System.out.println(-1);
		}

	}

	// 유클리드 호제법 : gcd(A, B) = gcd(B, A%B) 이고 A%B==0 이 될 때 종료
	static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	static void init() {

		// 리프 노드
		for (int i = 0; i < S; i++) {
			tree[S + i] = arr[i];
		}

		// 내부 노드
		for (int i = S - 1; 0 < i; i--) {
			tree[i] = gcd(tree[2 * i], tree[2 * i + 1]);
		}
	}

	// qL ~ qR까지의 gcd를 찾아주는 쿼리
	static int query(int left, int right, int node, int qL, int qR) {
		if (right < qL || qR < left) {
			return 0;
		} else if (qL <= left && right <= qR) {
			return tree[node];
		} else {
			int mid = (left + right) / 2;
			return gcd(query(left, mid, node * 2, qL, qR), query(mid + 1, right, node * 2 + 1, qL, qR));
		}
	}

	static void setLRRL() {

		for (int i = 1; i <= N; i++) {
			LR[i] = query(1, S, 1, 1, i);
		}

		for (int i = 1; i <= N; i++) {
			RL[i] = query(1, S, 1, i, N);
		}
	}

	static void findMaxGCD() {
		
		int currGCD = 0;
		
		for(int e=1; e<=N; e++) {
			
			if(e==1) {
				currGCD = RL[e+1];
			} else if(e==N) {
				currGCD = LR[e-1];
			} else { 
				currGCD = gcd(LR[e-1], RL[e+1]);
			}
			
			maxGCD = Math.max(currGCD, maxGCD);
			if(maxGCD == currGCD) excludedNum = arr[e-1];
		}	
	}
	
	static boolean isValid() {
		if(excludedNum % maxGCD == 0) return false;
		return true;
	}

}
