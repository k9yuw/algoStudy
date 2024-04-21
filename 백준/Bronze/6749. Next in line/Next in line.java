import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int young = Integer.parseInt(br.readLine());
        int middle = Integer.parseInt(br.readLine());
        
        int diff = middle - young;
        int old = diff + middle;
        
        System.out.println(old);
    }
}