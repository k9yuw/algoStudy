import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        
        //1. 주어진 값 할당
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        boolean[] broken = new boolean[10];
        for(int i=0; i<M; i++){
            broken[scan.nextInt()] = true;
        }
 
        int result = Math.abs(N-100);
        
        //2. 모든 숫자에 대해서 시도
        //999999라고 한 이유는 0부터 8까지 모든 버튼이 고장난 경우에 대비하기 위함
        for(int i=0; i<=999999; i++){
            String num = String.valueOf(i);
            boolean isBroken = false;
            
            for(int j=0; j<num.length(); j++){
                //임의의 수 i를 구성하고 있는 숫자들이 고장났는지 확인.
                if (broken[num.charAt(j)-'0']){ //0을 빼는 이유는 charAt이 아스키값을 반환해주기 때문
                    isBroken = true;
                    break;                    
                }
            } 
            if(!isBroken){
                int startI = Math.abs(N-i) + num.length(); //num.length 더하는 이유는 처음에 버튼 누르는 과정
                result = Math.min(startI, result);
            }
        }
        System.out.println(result);
    }
}