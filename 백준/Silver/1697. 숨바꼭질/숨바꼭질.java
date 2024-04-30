import java.io.*;
import java.util.*;

public class Main{

    static int n, k;
    static int[] arr = new int[100001];

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(n == k){
            System.out.println(0);
        } else {
            bfs(n);
        }
    }

    static void bfs(int start){

        Queue<Integer> q = new LinkedList<>();
        arr[start] = 1;
        q.add(start);

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int i = 0; i < 3; i++){

                int next = 0;
                if(i == 0) next = curr - 1;
                if(i == 1) next = curr + 1;
                if(i == 2) next = curr * 2;

                if(next == k){
                    System.out.println(arr[curr]); 
                    return;
                }

                if(next >= 0 && next < arr.length && arr[next] == 0){
                    q.add(next);
                    arr[next] = arr[curr] + 1;
                }
            }

        }
    }
}