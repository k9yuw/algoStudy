    import java.io.*;
    import java.util.*;

    public class Main{

        static int f,s,g,u,d;
        static int[] arr;

        public static void main(String[] args) throws IOException{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            f = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            u = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            arr = new int[f+1];

            System.out.println(bfs(f,s,g,u,d,arr));
        }

        static String bfs(int f, int s, int g, int up, int down, int[] arr){

            Queue<Integer> q = new LinkedList<>();
            arr[s] = 1;
            q.add(s);

            while(!q.isEmpty()){

                int curr = q.poll();

                if(curr == g) return String.valueOf(arr[curr]-1); // 1 빼는 이유는 처음에 1에서 시작하기 때문에

                if(curr + up <= f){
                    if(arr[curr+up] == 0){
                        arr[curr+up] = arr[curr] + 1;
                        q.add(curr+up);
                    }
                }

                if(curr - down > 0){
                    if(arr[curr-down] == 0){
                        arr[curr-down] = arr[curr] + 1;
                        q.add(curr-down);
                    }
                }
            }

            return "use the stairs";

        }
    }