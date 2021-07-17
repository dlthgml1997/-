import java.util.*;

public class Prog_입국심사 {
    static int[] timesCopy;
    public long solution(int n, int[] times) {
        long answer = 0;
        long maxNum = 0, minNum = 1;
        timesCopy = times;
        for(int time: times) {
            maxNum = Math.max(maxNum, time);
        } 
        
        maxNum *= n;
        answer = 0;
        while(minNum <= maxNum) {
            long mid = (minNum + maxNum) / 2;
            long cnt = 0;
            for(int time : times) {
                cnt += mid / time;
                if(cnt >= n) break;
            }
            
            if(cnt >= n) {
                answer = mid;
                maxNum = mid - 1;
            } else {
                minNum = mid + 1;
            }
        }
        return answer;
    }
}