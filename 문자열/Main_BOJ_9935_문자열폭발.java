import java.io.*;
import java.util.*;

public class Main_BOJ_9935_문자열폭발 { 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		System.out.println(solution(s, t));
	}
	
	private static String solution(String s, String t) {
		Stack<Integer> st = new Stack<>(); // t와 일치하는 문자열 넣기
		String[] tArr = t.split("");
		int tLen = tArr.length - 1;
		String[] sCh = s.split("");
		String answer = "";
		
		for (int i = 0; i < tArr.length; i++) {
			s = s.replace(tArr[i], String.valueOf(i));
		}
		
		for (int i = 0; i < sCh.length; i++) {
			if (sCh[i].charAt(0) >= 'a' && sCh[i].charAt(0) <= 'z') {
				answer += sCh[i];
				continue;
			}
			
			if (sCh[i].charAt(0) >= 'A' && sCh[i].charAt(0) <= 'Z') {
				answer += sCh[i];
				continue;
			}
			
			int value = Integer.parseInt(sCh[i]);
			
			if (value == 0) {
				if(t.length() != 1) {
					answer += tArr[value];
					st.push(value);
				}
				continue;
			}
			
			if (!st.isEmpty() && value - 1 == st.peek()) {
				if (value == tLen) {
					for (int j = 0; j < tLen; j++) {
						st.pop();
					}
					answer.substring(0, i- tLen + 1);
					continue;
				}
				answer += tArr[value];
				st.push(value);
			}
		}
		
		return answer.equals("") ? "FRULA" : answer;
	}
}
