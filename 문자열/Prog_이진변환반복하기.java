import java.util.*;

public class Prog_이진변환반복하기 {
    public int[] solution(String s) {
        int cnt = 0, zeroCnt = 0; // 이진 변환 횟수, 제거된 0의 개수

        while(!s.equals("1")) { // 1이 될 때까지
            String[] strList = s.split(""); // 문자열을 String 배열로 변환
            for(String str: strList) { 
                if(str.equals("0")) { // 제거될 0의 개수 세기
                    zeroCnt++;
                }
            }
            // step01: s에서 0 제거
            s = s.replace("0","");
            
            // step02: s의 길이를 이진법으로 표현한 문자열로 바꾸기
            int value = s.length(); // s의 길이
            Stack<Integer> st = new Stack<>();
            // value가 4인 경우 st에 001 순서로 push됨.
            while(value > 0) {
                st.push(value % 2);
                value /= 2;
            }
            // 선입후출로 "100" 만들기 (= s의 길이(4)를 이진법으로 표현(100)한 문자열("100")로 바꿈
            s = "";
            while(!st.isEmpty()) {
                s += String.valueOf(st.pop());
            }
            
            cnt++; // 이진 변환 횟수 1 UP
        }
        
        return new int[] {cnt, zeroCnt};
    }
}