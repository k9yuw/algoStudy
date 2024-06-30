import java.io.*;
import java.util.*;

// 0001 -> 1번 도시 방문
// 1010 -> 2번, 4번 방문
// 1101 -> 1, 3, 4번 도시 방문
// dp[i][j] -> 현재 i번 도시에 있고, 거쳐온 도시가 j일 때, 남은 도시를 방문하는 데 필요한 최소 비용
// dp[3][1100101] -> 1, 3, 6, 7 도시를 거쳐서 현재 3번 도시일 때 남은 2 4 5를 방문하는 데 드는 최소 비용
public class Main{

    static int n;
    static int[][] board;
    static int[][] dp;
    static int INF = 999999999;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        dp = new int[n][(1<<n) - 1]; // (1<<n) -1 이란 모든 도시를 방문한 상태를 말한다. 예를 들어 n이 5이면 1<<5 => 100000 ==> 1을 빼면 11111

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 어느 도시에서 시작하든지 "순회" 이기 때문에 최소비용은 같다.
        // 예를 들어 1->3->2->1이 최소비용 경로라면 사실 3->2->1->3 도 최소경로이고 2->1->3->2 도 최소 경로라는 것이다.
        // 따라서 그냥 0번째 도시에서 시작하는 것으로 설정하고, 0번째 도시의 비트마스크는 1이다.
        System.out.println(tsp(0,1));

    }

    // 현재 도시와 방문한 도시들의 상태를 입력으로 받아 남은 도시들을 방문하는 데 필요한 최소 비용을 계산
    static int tsp(int currLoc, int visitedBit){

        if(visitedBit == (1<<n) - 1) { // 모든 도시를 다 방문한 상태라면
            if(board[currLoc][0] == 0) return INF; // 현재 위치에서 0번째 도시로 돌아가는 방법이 없는 경우
            else return board[currLoc][0]; // 현재 위치에서 0번째 도시로 돌아가는데 드는 비용
        }

        // 이미 계산된 값이 있는 경우, 그걸 그대로 반환
        if (dp[currLoc][visitedBit] != 0) {
            return dp[currLoc][visitedBit];
        }

        // 초기화: 값을 무한대로 설정해서 최소 값을 찾고자 함
        dp[currLoc][visitedBit] = INF;

        // 방문하지 않은 도시 방문하기
        for (int i = 0; i < n; i++) {

            // currLoc에서 i번째 도시로 가는 경로가 없는 경우 (이동이 불가능한 경우)
            if (board[currLoc][i] == 0) continue;

            // 이미 방문한 경우
            // i번째 도시를 방문했는지 안했는지 확인하기 위해서 1<<i 와 and 연산을 한다.
            // 예를 들어 visited가 0101 이고 1번째 도시를 방문했는지 확인하려면 1번째 도시 자리(1<<i) 가 1인지 확인해야 한다.
            // 따라서 0101 과 0010 (1<<i) 를 and 연산해서 0인지 아닌지 확인한다.
            // 0이면 방문을 하지 않았다는 것이고, 1이면 이미 방문한 경우이다.
            if ((visitedBit & (1 << i)) != 0) continue;

            // 방문하지 않은 도시 방문하기
            // visitedBit과 1<<i (i번째 도시)를 or 연산 하는 것은 방문 상태를 업데이트 해주는 것이다.
            // 즉 tsp(i, visitedBit | (1 << i) 는 i번째 도시로 이동한 상태로 업데이트 해주는 것.
            dp[currLoc][visitedBit] = Math.min(dp[currLoc][visitedBit], tsp(i, visitedBit | (1 << i)) + board[currLoc][i]);
        }

        return dp[currLoc][visitedBit];
    }
}