import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[][] phone = {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        int leftx = 3, lefty=0, rightx=3, righty= 2;
        for(int n: numbers) {
            switch(n) {
                case 1:
                case 4:
                case 7: {
                    leftx = phone[n][0];
                    lefty = phone[n][1];
                    answer+="L"; break;
                }
                case 3:
                case 6:
                case 9: {
                    rightx = phone[n][0];
                    righty = phone[n][1];
                    answer+="R"; break;
                }
                case 2:
                case 5:
                case 8:
                case 0: {
                    int leftDir = getDistance(leftx, lefty,phone[n][0], phone[n][1]);
                    int rightDir = getDistance(rightx, righty, phone[n][0], phone[n][1]);
                    if(leftDir < rightDir) {
                        leftx = phone[n][0];
                        lefty = phone[n][1];
                        answer+="L"; break;
                    } else if (leftDir == rightDir) {
                        if(hand.equals("left")){
                            leftx = phone[n][0];
                            lefty = phone[n][1];
                            answer+="L"; break;
                        } else {
                            rightx = phone[n][0];
                            righty = phone[n][1];
                            answer+="R"; break;
                        }
                    } else {
                        rightx = phone[n][0];
                        righty = phone[n][1];
                        answer+="R"; break;
                    }
                }
            }
        }
        return answer;
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1- x2) + Math.abs(y1- y2);
    }
}