import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        
        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
        
        
            if (maxPQ.size()==minPQ.size()){
                maxPQ.add(x);
            
                if(!minPQ.isEmpty() && maxPQ.peek()>minPQ.peek()){
                    maxPQ.add(minPQ.poll());
                    minPQ.add(maxPQ.poll());
                }
            } else {
                minPQ.add(x);
                
                if(maxPQ.peek()>minPQ.peek()){
                    maxPQ.add(minPQ.poll());
                    minPQ.add(maxPQ.poll());
                }
            }             
        
        bw.write(maxPQ.peek() + "\n");
        }
        bw.flush();
        bw.close();
    }
}