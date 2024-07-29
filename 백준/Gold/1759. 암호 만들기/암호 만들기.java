import java.io.*;
import java.util.*;

public class Main {

    static int L, C;
    static char[] chars;
    static boolean[] visited;
    static List<String> ans = new ArrayList<>();
    static final String vowels = "aeiou";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 기본 값, 배열 설정
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        chars = new char[C];
        visited = new boolean[C];

        // 사용가능한 알파벳들 chars에 추가 후 알파벳 순으로 정렬
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<C; i++){
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars);

        for(int i=0; i<C; i++){
            dfs(i,1);
        }

        for(String str : ans){
            System.out.println(str);
        }

    }

    public static void dfs(int idx, int cnt) {

        // 1. 체크인
        visited[idx] = true;
        // 2. 목적지인가?
        if(cnt == L){
            checkValidity();
        }
        // 3. 연결된 노드 순회
        for(int i=idx+1; i<C; i++){
            // 4. 갈 수 있나?
            if(!visited[i]){
                // 5. 간다
                dfs(i, cnt+1);
            }
        }
        // 6. 체크아웃
        visited[idx] = false;
    }

    public static void checkValidity(){
        StringBuilder sb = new StringBuilder();
        int vowelCnt = 0;
        int csntCnt = 0;

        for(int i=0; i<C; i++){
            if(visited[i]){
                sb.append(chars[i]);
                if(vowels.indexOf(chars[i]) >= 0) vowelCnt++;
                else csntCnt++;
            }
        }

        if(vowelCnt >= 1 && csntCnt >= 2){
            ans.add(sb.toString());
        }
    }
    
}
