import java.io.*;
import java.util.*;

public class Main {

    static int W, B;
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 12시부터 시계방향
    static List<String> foundWord = new LinkedList<>();
    static char[][] board = new char[4][4];
    static boolean[][] visited = new boolean[4][4];
    static TrieNode root = new TrieNode();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // Trie에 단어 입력
        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            insert(br.readLine());
        }
        br.readLine();

        // 주어진 보드 개수만큼 탐색
        B = Integer.parseInt(br.readLine());
        for (int i = 0; i < B; i++) {

            // board, visited, 단어리스트 초기화
            initialize();

            // board 입력
            for (int y = 0; y < 4; y++) {
                String str = br.readLine();
                board[y] = str.toCharArray();
            }
            if (i < B - 1) br.readLine(); // 마지막 보드 이후에는 빈 줄 없음

            // dfs 돌기
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {

                    // dfs에 들어가는 첫 글자가 root의 children에 존재하는지 확인!!!
                    char startC = board[y][x];
                    if (root.hasChild(startC)) {
                        dfs(y, x, root.getChild(startC));
                    }
                }
            }

            // 출력!
            int score = getScore(foundWord);
            String maxWord = findMaxWord(foundWord);
            int wordCount = foundWord.size();
            System.out.println(score + " " + maxWord + " " + wordCount);
        }
    }

    static void dfs(int y, int x, TrieNode ptr) {

        // 1. 체크인 : 여기서 단어를 기록해가야된다~
        visited[y][x] = true;
        sb.append(board[y][x]);

        // 2. 목적지인가?
        if (!ptr.isHit && ptr.isWord) {
            ptr.isHit = true;
            foundWord.add(sb.toString());
        }

        // 3. 연결된 노드 순회
        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            // 4. 갈 수 있는가?
            if(!isInbound(ny, nx)) continue;
            if(visited[ny][nx]) continue;
            char nextC = board[ny][nx];
            if (ptr.hasChild(nextC)) {
                // 5. 간다
                dfs(ny, nx, ptr.getChild(nextC));
            }
        }

        // 6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(sb.length()-1);

    }

    static void initialize() {
        // 보드 초기화
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                board[y][x] = 0;
            }
        }
        // 방문 초기화
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                visited[y][x] = false;
            }
        }
        // 찾은 단어 리스트 초기화
        foundWord = new LinkedList<>();

        // Trie의 isHit 초기화
        root.clearHit();
    }

    static boolean isInbound(int y, int x) {
        return 0 <= y && y < 4 && 0 <= x && x < 4;
    }

    static int getScore(List<String> foundWord) {
        int sum = 0;
        for (String word : foundWord) {
            int len = word.length();
            if (len <= 2) sum += 0;
            else if (len <= 4) sum += 1;
            else if (len == 5) sum += 2;
            else if (len == 6) sum += 3;
            else if (len == 7) sum += 5;
            else if (len == 8) sum += 11;
        }
        return sum;
    }

    static String findMaxWord(List<String> foundWord) {
        Collections.sort(foundWord, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();  // 길이가 긴 순서대로 정렬
                }
                return o1.compareTo(o2);  // 길이가 같으면 알파벳 순으로 정렬
            }
        });
        return foundWord.get(0);
    }

    static void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int wordIdx = word.charAt(i) - 'A';
            if (curr.children[wordIdx] == null) {
                curr.children[wordIdx] = new TrieNode();
            }
            curr = curr.children[wordIdx];
        }
        curr.isWord = true;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
    boolean isHit;

    void clearHit() {
        isHit = false;
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                children[i].clearHit();
            }
        }
    }

    boolean hasChild(char c) {
        int idx = c - 'A';
        return this.children[idx] != null;
    }

    TrieNode getChild(char c) {
        return this.children[c - 'A'];
    }
}
