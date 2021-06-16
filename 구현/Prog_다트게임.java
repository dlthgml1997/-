import java.util.*;

public class Prog_다트게임 {
    public int solution(String dartResult) {
        int answer = 0;
        String[] list = dartResult.split("");
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] multiList = new int[3];
        int pre = 0, i = 0;
        
        for(int j=0; j< list.length; j++) {
            String s = list[j];
            switch(s) {
                case "S": {
                    queue.offer(pre);
                    i++;
                    break;
                }
                case "D": {
                    queue.offer(pre*pre);
                    i++;
                    break;
                }
                case "T": {
                    queue.offer(pre*pre*pre);
                    i++;
                    break;
                }
                case "*": {
                    if(i-1 > 0) {
                        if(multiList[i-2] != 0) {
                            multiList[i-2] *= 2;
                        } else {
                            multiList[i-2] = 2;
                        }
                    }
                    multiList[i-1] = 2;
                    break;
                }
                case "#": {
                    if(multiList[i-1] == 0) {
                        multiList[i-1] = -1;
                    } else {
                        multiList[i-1] *= -1;
                    }
                    break;
                }
                default: {
                    if(list[j].equals("0")) {
                        if(j > 0 && list[j-1].equals("1")) {
                            pre = 10;                            
                        } else {
                            pre = 0;
                        }
                    } else {
                        pre = Integer.parseInt(s);    
                    }
                    break;
                }
            }
            
        }
        
        i=0; 
        while(!queue.isEmpty()) {
            int value = queue.poll();
            
            answer += value * (multiList[i] == 0 ? 1 : multiList[i]);
            i++;
        }
        return answer;
    }
}