import java.io.*;
import java.util.*;

public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 
		int[] arr = new int[3];
 
		for (int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
 
		for (int i = 0; i < 2; i++) {
			for (int j = i + 1; j < 3; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(arr[1]);
	}
}