import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int K; // 전체 투표수
	static Student[] candidate = new Student[101];
	static ArrayList<Student> frame = new ArrayList<Student>();
	static List<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {

			int currIdx = Integer.parseInt(st.nextToken());

			// 처음 투표받는 경우
			if (candidate[currIdx] == null) {
				candidate[currIdx] = new Student(currIdx, 0, 0); // 객체 초기화
			}
			// 한 번 이상 투표받았으면 count 무조건 존재
			// frame에서 삭제되는 순간 count는 0으로 초기화되기 때문에 양수라는 뜻은 frame내에 있다는 것
			if (candidate[currIdx].count != 0) {
				candidate[currIdx].count++;
			} else {// 투표받은 적 있지만 프레임에서 튕긴경우

			// frame이 다 찼을 때
			if (frame.size() == N) {
				Collections.sort(frame);
				Student s = frame.remove(N - 1);

				// frame에서 튕기는 순간 초기화
				s.count = 0;
				s.timeStamp = 0;
			
			}
			
			candidate[currIdx].count = 1;
			candidate[currIdx].timeStamp = i;
			frame.add(candidate[currIdx]);
			
			}
		}

		// frame을 출력한다.
		for (Student s : frame) {
			ans.add(s.idx);
		}

		Collections.sort(ans);

		for (int i : ans) {
			System.out.print(i + " ");
		}

	}

	public static class Student implements Comparable<Student> {

		int idx;
		int count;
		int timeStamp;

		public Student(int idx, int count, int timeStamp) {
			this.idx = idx;
			this.count = count;
			this.timeStamp = timeStamp;
		}

		@Override // 우선순위가 높은 순부터 내림차순 정렬
		public int compareTo(Student o2) {
			int compCount = o2.count - count;
			if (compCount == 0) {
				return o2.timeStamp - timeStamp;
			}
			return compCount;
		}

	}

}