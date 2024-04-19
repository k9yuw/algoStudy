import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int count = 0;
        String sentence = br.readLine();
        
        for(int i=0; i<sentence.length(); i++){
            char letter = sentence.charAt(i);
            if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
                count++;
            }
        }
        
        System.out.println(count);
    }
}