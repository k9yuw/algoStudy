import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());		
		for(int i=0; i<N; i++) {
			
			int x = Integer.parseInt(br.readLine());
			
			if(x==0) {
				
				if(minHeap.size()==0) {
					System.out.println("0");
				} else {
					System.out.println(minHeap.poll());
				}
				
			} else {
				minHeap.add(x);
			}
		}
		
	}

}