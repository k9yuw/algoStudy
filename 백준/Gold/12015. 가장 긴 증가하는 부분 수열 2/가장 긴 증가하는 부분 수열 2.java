import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[N];
        int[] sortedArr = new int[N];
        
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }    

        sortedArr[0] = arr[0];
        int index = 1;

        for (int i = 1; i < N; i++) {
            int key = arr[i];

            if (sortedArr[index - 1] < key) { // 추가하려는 key 값이 sortedArr 현재 길이의 맨 오른쪽값보다 크다면
                index++;
                sortedArr[index - 1] = key; // 그 다음 칸에 key를 추가
            } else { // 추가하려는 key값이 sortedArr 현재 길이의 맨 오른쪽값보다 작다면
                int insertionPoint = binarySearch(key, sortedArr, index); 
                sortedArr[insertionPoint] = key; // key 값이 들어갈 인덱스를 binarySearch를 통해 찾아서 삽입
            }
        }

        System.out.println(index);    
    }

    private static int binarySearch(int num, int[] arr, int size) {
        int first = 0;
        int last = size - 1;

        while (first <= last) {
            int mid = (first + last) / 2;
            int current = arr[mid];

            if (num <= current) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }

        return first;
    }
}
