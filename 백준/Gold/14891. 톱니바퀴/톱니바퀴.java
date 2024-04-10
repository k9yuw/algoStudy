    import java.io.*;
    import java.util.*;

    public class Main{

        static int k;
        static int ans;
        static int[] wheelDir;
        static int[][] wheels = new int[4][8];

        public static void main(String[] args) throws IOException{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for(int i=0; i<4; i++){
                String input = br.readLine();
                for(int j=0; j<8; j++){
                    wheels[i][j] = input.charAt(j) - '0';
                }
            }

            k = Integer.parseInt(br.readLine());

            for(int i=0; i<k; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                wheelDir = new int[4];
                wheelDir[num] = dir;
                checkSide(num);
                rotate();
            }

            ans = 0;
            for(int i=0; i<4; i++){
                if(wheels[i][0]==1) ans += Math.pow(2,i);
            }
            System.out.println(ans);
        }

        static void checkSide(int n){

            // 왼쪽 바퀴 확인
            for(int i=n; i>0; i--){
                if(wheels[i][6] != wheels[i-1][2]) wheelDir[i-1] = -wheelDir[i];
                else break;
            }

            // 오른쪽 바퀴 확인
            for(int i=n; i<3; i++){
                if(wheels[i][2] != wheels[i+1][6]) wheelDir[i+1] = -wheelDir[i];
                else break;
            }

        }

        static void rotate() {
            for (int i = 0; i < 4; i++) {
                // 시계방향으로 회전
                if (wheelDir[i] == 1) {
                    int temp = wheels[i][7];
                    for (int j = 7; j > 0; j--) {
                        wheels[i][j] = wheels[i][j - 1];
                    }
                    wheels[i][0] = temp;
                }
                // 반시계방향으로 회전
                else if (wheelDir[i] == -1) {
                    int temp = wheels[i][0];
                    for (int j = 0; j < 7; j++) {
                        wheels[i][j] = wheels[i][j + 1];
                    }
                    wheels[i][7] = temp;
                }
            }
        }

    }