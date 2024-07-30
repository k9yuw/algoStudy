import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N+1];

        arr[0] = 0;
        arr[1] = 1;

        for(int i=0; i<N-1 ; i++){
            arr[i+2] = arr[i+1] + arr[i];
        }

        System.out.println(arr[N]);
    }
}
