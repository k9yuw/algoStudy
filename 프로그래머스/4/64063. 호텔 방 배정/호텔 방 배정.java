import java.io.*;
import java.util.*;

class Solution {
    
    Map<Long, Long> map = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        
        int N = room_number.length;        
        long[] answer = {};
        answer = new long[N];
        
        for(int i=0; i<N; i++) {
            answer[i] = find(room_number[i]);
        }
        
        return answer;
    }
    
    
    public long find(long num) {
        if(!map.containsKey(num)) {
            map.put(num, num+1);
            return num;
        }
        
        long next = find(map.get(num));
        map.put(num, next);
        return next;
    }
    
}