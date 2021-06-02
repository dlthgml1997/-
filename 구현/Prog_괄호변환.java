import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        answer += convert(p);
        return answer;
    }

    private String convert(String p) {
        if(p.equals("")) return "";

        int i;
        int left = 0;
        int right = 0;
        for(i=0; i< p.length(); i++) {
            if(p.charAt(i) == '(') left++;
            if(p.charAt(i) == ')') right++;
            if(left == right) break;
        }

        String u = p.substring(0, i+1);
        String v = p.substring(i+1, p.length());

        String temp = "";
        if(isAlright(u)) {
            return u + convert(v);
        } else {
            temp += "(";
            temp += convert(v);
            temp += ")";
            if(u.length() > 0) {
                u = u.substring(1, u.length() -1);
            }
            for(i=0; i< u.length(); i++) {
                temp += u.charAt(i) == '(' ? ')' : '(';
            }
        }
        return temp;
    }

    private boolean isAlright(String u) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i< u.length(); i++) {
            if(u.charAt(i) == '(') {
                stack.push(u.charAt(i));
            } else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty() ? true : false;
    }
}