import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static long[] nums;
	static long[] tree;
	static int S; // leaf의 개수

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new long[N];

		S = 1;
		while (S < N) {
			S *= 2; // 이거 끝나면 N보다 크거나 같은 S가 만들어짐.
		}

		tree = new long[S * 2 + 1];

		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		init();

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int action = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (action == 1) { // update
				long c = Long.parseLong(st.nextToken());
				long diff = c - tree[S+b-1];
				update(1, S, 1, b, diff);
			} else if (action == 2) { // 부분합
				int c = Integer.parseInt(st.nextToken());
				long result = query(1, S, 1, b, c);
				System.out.println(result);
			}
		}

	}

	static void init() {
		for (int i = 0; i < N; i++) {
			tree[S + i] = nums[i]; // leaf 채우기
		}
		for (int i = S - 1; 0 < i; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
			 // 내부노드: leftChild + rightChild
		}
	}

	static long query(int left, int right, int node, int qL, int qR) {
		if (qR < left || right < qL) { // 범위를 벗어나는 경우
			return 0; // 영향이 가지 않는 값
		} else if (qL <= left && right <= qR) { // 범위 안에 포함되는 경우
			return tree[node]; // 현재 나의 정보
		} else { // 걸쳐있는 경우, 자식들에게 토스
			int mid = (left + right) / 2;
			return (query(left, mid, node * 2, qL, qR) + query(mid + 1, right, node * 2 + 1, qL, qR)); // leftChild,
																										// rightChild에서
																										// 반환한 값을 더해서 리턴

		}
	}

	static void update(int left, int right, int node, int target, long diff) {
		if (target < left || right < target) { // target 바깥에 있는 경우
			return; // 상관 없음
		} else { // target과 연관 있는 경우
			tree[node] += diff;
			if (left != right) { // leaf가 아닌 경우(내부 노드인 경우)
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

	static long queryBU(int qL, int qR) {
		int left = S + qL - 1;
		int right = S + qR - 1;
		long sum = 0;

		while (left <= right) { // 경계가 뒤집어지지 않을 때까지
			if (left % 2 == 1) {
				sum += tree[left]; // 내 부모꺼가 아닌 내꺼 쓸 때 더하기. 왼쪽 경계가 오른쪽 노드에 걸린 경우 부모꺼를 쓸 수 없음
			}
			if (right % 2 == 0) {
				sum += tree[right]; // 내 부모꺼가 아닌 내꺼 쓸 때 더하기. 오른쪽 경계가 왼쪽 노드에 걸린 경우 부모꺼를 쓸 수 없음
			}
			left /= 2;
			right /= 2;
			// left, right이 같아지는 시점에서는 if문 둘 중 하나 타서 더해주게 됨.
		}

		return sum;

	}

	static void updateBU(int target, long value) {
		int node = S + target - 1;
		tree[node] = value; // 업데이트
		node /= 2;
		while (node > 0) { // root까지
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
}
