import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        String[] str = new String[N];

        // 주어진 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // arr[i] 왼쪽을 확인하며(arr[j]) 더 작은 수가 있는지 확인
        // arr[i]가 arr[j] 다음 붙을 수 있는지?

        for(int i=0; i<N; i++){
            dp[i]=1;
            str[i] = arr[i] + "";
            for(int j=0; j<i; j++){

                if(arr[j]<arr[i] && dp[j]+1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    str[i] = str[j] + " " + arr[i];
                }
            }
        }

        int max=0;
        int index=0;

        // 만들어진 수열 중 가장 긴 길이 찾기
        for(int i=0; i<N; i++){
            if(max<dp[i]){
                max = dp[i];
                index = i;
            }
        }

        bw.write(max + "\n");
        bw.write(str[index] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}