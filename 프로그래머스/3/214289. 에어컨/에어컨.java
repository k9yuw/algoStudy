import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int OFFSET = 10;
        int MAX_TEMP = 50;
        int INF = 1000000;

        temperature += OFFSET;
        t1 += OFFSET;
        t2 += OFFSET;

        int n = onboard.length;
        int[][] dp = new int[n][MAX_TEMP + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][temperature] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int temp = 0; temp <= MAX_TEMP; temp++) {
                if (dp[i][temp] == INF) continue;
                if (onboard[i] == 1 && (temp < t1 || temp > t2)) continue;

                // 에어컨 켜고 온도 유지
                dp[i + 1][temp] = Math.min(dp[i + 1][temp], dp[i][temp] + b);

                // 에어컨 켜고 온도 증가
                if (temp < MAX_TEMP)
                    dp[i + 1][temp + 1] = Math.min(dp[i + 1][temp + 1], dp[i][temp] + a);

                // 에어컨 켜고 온도 감소
                if (temp > 0)
                    dp[i + 1][temp - 1] = Math.min(dp[i + 1][temp - 1], dp[i][temp] + a);

                // 에어컨 끄고 온도 변화
                int nextTemp = temp;
                if (temp < temperature) nextTemp++;
                else if (temp > temperature) nextTemp--;
                dp[i + 1][nextTemp] = Math.min(dp[i + 1][nextTemp], dp[i][temp]);
            }
        }

        int answer = INF;
        for (int temp = 0; temp <= MAX_TEMP; temp++) {
            if (onboard[n - 1] == 1 && (temp < t1 || temp > t2)) continue;
            answer = Math.min(answer, dp[n - 1][temp]);
        }
        return answer;
    }
}
