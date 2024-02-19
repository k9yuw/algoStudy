import java.util.*;

public class Main {
    public static void main(String[] args){
        
        //1. 주어진 값들 입력받기
        Scanner scan = new Scanner(System.in);
        
        int[] height = new int[9];
        int sum = 0;
        int fake1 = 0, fake2 = 0;
        
        
        //2. sum에 height array 합 계산
        for(int i=0; i < height.length ; i++){
            height[i] = scan.nextInt();
            sum += height[i];
        }        
        
        //3. 오름차순 정렬
        Arrays.sort(height);
        
        //4. fake1, fake2 할당 후 sum에서 빼서 100 되는지 확인
        //※ a,b 안 겹치게 인덱스 설정!
        for(int a=0; a < height.length - 1 ; a++){
            for(int b=a+1; b < height.length ; b++){ 
                if(sum - height[a] - height[b] == 100){
                    fake1 = height[a];
                    fake2 = height[b];
                    break;
                }
            }
        }
        
        //5. 출력
        for(int j=0; j < height.length; j++){
            if(height[j] != fake1 && height[j] != fake2){
                System.out.println(height[j]);
            }
        }        
             
    }
}