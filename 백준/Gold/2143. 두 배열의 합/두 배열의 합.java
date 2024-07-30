import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static long count;
    static int a, b;
    static int[] A, B;
    static ArrayList<Integer> subA = new ArrayList<>();
    static ArrayList<Integer> subB = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 기본 입력 받기
        T = Integer.parseInt(br.readLine());

        a = Integer.parseInt(br.readLine());
        A = new int[a];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        b = Integer.parseInt(br.readLine());
        B = new int[b];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 부배열 만들기
        for (int i = 0; i < a; i++) {
            int subSum = 0;
            for (int j = i; j < a; j++) {
                subSum += A[j];
                subA.add(subSum);
            }
        }

        for (int i = 0; i < b; i++) {
            int subSum = 0;
            for (int j = i; j < b; j++) {
                subSum += B[j];
                subB.add(subSum);
            }
        }

        // 부배열 정렬
        Collections.sort(subA); // 오름차순 정렬
        Collections.sort(subB, (a, b) -> b - a); // 내림차순 정렬

        // 포인터 사이즈
        int sizeA = subA.size();
        int sizeB = subB.size();

        // 이중포인터로 움직이면서 sum 확인
        int pA = 0, pB = 0;
        while (pA < sizeA && pB < sizeB) {

            int sum = subA.get(pA) + subB.get(pB);

            if (sum == T) {

                // 이 때 같은값이 지속되는 만큼 곱해서 더하기
                int sameA = subA.get(pA); // 같은 값으로 지속되는 subA의 값
                int sameB = subB.get(pB); // 같은 값으로 지속되는 subB의 값
                int aCnt = 0;
                int bCnt = 0;

                while(pA < sizeA && subA.get(pA) == sameA){
                    pA++;
                    aCnt++;
                }
                while(pB < sizeB && subB.get(pB) == sameB){
                    pB++;
                    bCnt++;
                }

                // 숫자 중 같은거 개수 세가지고 곱해서 count에 더하기
                count += (long) aCnt * bCnt;

            } else if (sum < T) {
                pA++;
            } else pB++;

            if(pA == sizeA || pB == sizeB) break;

        }

        System.out.println(count);
    }
}
