import java.io.*;
import java.util.*;

public class Main {

	static int K;
	static boolean[] checked;
	static List<Integer> primes = new ArrayList<>();
	static char[] P;
	static boolean isBad = false;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		P = st.nextToken().toCharArray();
		K = Integer.parseInt(st.nextToken());
		checked = new boolean[K + 1];

		// 에라토스테네스의 체 이용해서 소수 판별
		for (int i = 2; i < K; i++) {
			if (checked[i] == false) {
				primes.add(i);
				for (int j = i * 2; j <= K; j += i) { // 배수 체크
					checked[j] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("BAD ");
		// 구한 소수로 P를 나눠서 나눠지는지 안나눠지는지 확인
		// 이런 문제에서는 BigInteger 자료형 못쓰게 나옴. 그래서 String / char 배열 이용해서 연산을 구현할 줄 알아야 한다~
		for (int prime : primes) {
			if(checkIsBad(prime)) {
				isBad = true;
				sb.append(prime);
				break;
			}
		}
		
		// 답 출력
		if(isBad) {
			System.out.println(sb);
		} else {
			System.out.println("GOOD");
		}

	}

	// 손으로 하는 나눗셈 방법을 이용.
	static boolean checkIsBad(int x) {
		int r = 0;
		
		for(int i=0; i<P.length; i++) {
			r = (r*10 + (P[i] - '0')) % x;
		}

		if (r == 0) {
			return true;
		} else {
			return false;
		}
	}

}
