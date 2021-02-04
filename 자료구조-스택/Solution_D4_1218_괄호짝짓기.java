

import java.io.*;
import java.util.*;

public class Solution_D4_1218_괄호짝짓기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Stack<Character> st = new Stack();
	public static void main(String[] args) throws Exception {
		for(int t =1; t<=10; t++) {
			st.clear();
			System.out.printf("#%d %d\n",t,check() ? 1 : 0);
		}
	}
	private static boolean check() throws Exception {
		int N = Integer.parseInt(br.readLine());
		char[] ch = br.readLine().toCharArray();
		for(char c : ch) {
			if(c == '(' || c == '[' || c == '{' || c == '<') {
				st.add(c);
			} else if (c ==')') {
				if(st.peek() == '(') st.pop();
				else return false;
			} else if (c ==']') {
				if(st.peek() == '[') st.pop();
				else return false;
			} else if (c =='}') {
				if(st.peek() == '{') st.pop();
				else return false;
			} else if (c =='>') {
				if(st.peek() == '<') st.pop();
				else return false;
			}
		}
		return (st.isEmpty()) ? true: false;
	}
}
