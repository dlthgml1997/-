import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        // 1단계
        answer = new_id.toLowerCase();
        // 2단계
        // answer = answer.replaceAll("[^-_.a-z0-9]","");
        String temp = "";
        for(int i=0; i< answer.length(); i++) {
            char c = answer.charAt(i);
            if(c == '_' || c == '-' || c== '.') {
                temp+=c;
            } else if (c <= 'z' && c >= 'a') {
                temp += c;
            } else if (c <= '9' && c >= '0') {
                temp += c;
            }
        }
        answer = temp;
        // 3단계
        // 정규표현식
        // answer = answer.replaceAll("[.]{2,}",".");
        char pre = answer.charAt(0);
        temp = pre+"";
        for(int i=1; i< answer.length(); i++) {
            char c = answer.charAt(i);
            if(c != '.') {
                temp += c;
            } else if (c == '.' && pre != '.') {
                temp += c;
            }
            pre = c;
        }
        answer = temp;


        // 4단계
        // 정규표현식
        // answer = answer.replaceAll("^[.]|[.]$","");
        temp = "";
        for(int i=0; i< answer.length(); i++) {
            char c = answer.charAt(i);
            if(c == '.' && i == 0) continue;
            if(c == '.' && i == answer.length()-1) continue;
            temp += c;
        }
        answer = temp;
        // 5단계
        if(answer.equals("")) {
            answer = "aaa";
        }

        // 6단계
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$","");
        }

        // 7단계
        while(answer.length() < 3) {
            // answer += answer.substring(answer.length()-1, answer.length());
            answer += answer.charAt(answer.length()-1);
        }
        return answer;
    }
}