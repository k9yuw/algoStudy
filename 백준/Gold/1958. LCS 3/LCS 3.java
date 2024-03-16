import java.io.*;
import java.util.*;
 
public class Main {
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        char[] str3 = br.readLine().toCharArray();

        int length1 = str1.length;
        int length2 = str2.length;
        int length3 = str3.length;

        dp = new int[length1 + 1][length2 + 1][length3 + 1];

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                for (int k = 1; k <= length3; k++) {
                    if (str1[i-1] == str2[j-1] && str2[j-1] == str3[k-1]) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i-1][j][k],Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                    }
                }
            }
        }
        System.out.println(dp[length1][length2][length3]);
    }
}
