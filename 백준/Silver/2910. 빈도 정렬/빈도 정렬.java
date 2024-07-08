import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 메시지의 길이 N
        int c = Integer.parseInt(st.nextToken()); // 수의 범위 C
        List<Integer> list = new ArrayList<>(); // 입력 수열을 저장할 리스트
        st = new StringTokenizer(br.readLine());

        // 각 숫자의 빈도수를 저장하는 해시맵
        Map<Integer, Integer> map = new HashMap<>();

        // 입력 수열을 리스트에 저장하고 빈도수를 해시맵에 기록
        List<Integer> original = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
            original.add(list.get(i));
            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        // 빈도수를 기준으로 리스트를 정렬
        Collections.sort(list, (o1, o2) -> {
            if (map.get(o1) == map.get(o2)) { // 빈도수가 같을 경우, 원래 입력 순서를 유지하도록 한다.
                return original.indexOf(o1) - original.indexOf(o2);
            } else { // 빈도수가 다른 경우, 빈도수를 기준으로 내림차순 정렬
                return Integer.compare(map.get(o2), map.get(o1));
            }
        });

        // 정렬된 리스트를 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(list.get(i) + " ");
        }
        System.out.println(sb);
    }
}