import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];

        for(int i=0; i<N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int low = 1;
        int high = houses[N-1] - houses[0] + 1;

        while(low < high) {
            int mid = (low+high) / 2;
            if(wifiInstallCount(mid) < C) {
                high = mid;
            } else {
                low = mid+1;
            }
        }

        System.out.println(low-1);
    }

    public static int wifiInstallCount(int dist) {
        int count = 1;
        int prevLocation = houses[0]; // 첫번째 집에는 무조건 공유기 설치

        for(int i=1; i<houses.length; i++) {
            int location = houses[i];

            if(location - prevLocation >= dist) {
                count++;
                prevLocation = location;
            }
        }
        return count;
    }
}