import java.io.*;
import java.util.*;

public class Main{

    static int n,m;
    static int[] A;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<m; i++){
            int curr = Integer.parseInt(st.nextToken());
            if(binarySearch(curr)>=0){
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);

    }

    public static int binarySearch(int data){

        int left = 0;
        int right = A.length - 1;

        while(left<=right){

            int mid = (left + right) / 2;

            if(data < A[mid]){
                right = mid - 1;
            } else if(data > A[mid]){
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;

    }

}      