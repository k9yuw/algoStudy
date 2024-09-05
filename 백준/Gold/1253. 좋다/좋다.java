import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int ans = 0;
        for(int i=0 ; i<N ; i++){
            int left = 0;
            int right = N-1;

            while(true){
                if(left == i) left++;
                else if(right == i) right--;

                if(left >= right) break;

                if(arr[left] + arr[right] > arr[i]) right--;
                else if(arr[left] + arr[right] < arr[i]) left++;
                else{
                    ans++;
                    break;
                }
            }
        }
        System.out.println(ans);
    }

}