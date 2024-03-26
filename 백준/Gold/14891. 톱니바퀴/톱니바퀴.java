import java.io.*;
import java.util.*;

public class Main {

    static int[][] wheel = new int[4][8];
    static int[] wheelDir;
    static int k;
    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 4; i++){
            String input = br.readLine();
            for(int j = 0; j < 8; j++){
                wheel[i][j] = input.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int isClockwise = Integer.parseInt(st.nextToken());

            wheelDir = new int[4];
            wheelDir[n] = isClockwise;
            checkSide(n);
            rotate();
        }

        ans = 0;

        for(int i = 0; i < 4; i++){
            if(wheel[i][0] == 1) ans += Math.pow(2, i);
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        bw.close();
    }


    static void checkSide(int n){
        // 왼쪽 톱니바퀴 확인
        for(int i = n; i > 0; i--){
            if(wheel[i][6] != wheel[i - 1][2]){
                wheelDir[i - 1] = -wheelDir[i];
            } else {
                break;
            }
        }

        // 오른쪽 톱니바퀴 확인
        for(int i = n; i < 3; i++){
            if(wheel[i][2] != wheel[i + 1][6]){
                wheelDir[i + 1] = -wheelDir[i];
            } else {
                break;
            }
        }
    }


    static void rotate(){

        int temp = 0;

        for(int i = 0; i < 4; i++){
            // 시계방향 회전인 경우 전 칸꺼 끌어옴
            if(wheelDir[i] == 1){
                temp = wheel[i][7];
                for(int j = 7; j > 0; j--){
                    wheel[i][j] = wheel[i][j - 1];
                }
                wheel[i][0] = temp;
            }

            // 반시계방향 회전인 경우 앞 칸꺼 끌어옴
            if(wheelDir[i] == -1){
                temp = wheel[i][0];
                for(int j = 0; j < 7; j++){
                    wheel[i][j] = wheel[i][j + 1];
                }
                wheel[i][7] = temp;
            }
        }
    }

}