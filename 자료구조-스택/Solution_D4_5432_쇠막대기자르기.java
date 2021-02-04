import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_5432_쇠막대기자르기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		char[] ch;
		for(int t=1; t<= T; t++) {
			int answer = 0;
			int cnt = 0;
			char pre = '(';
			Stack<Character> stack = new Stack<>();
			
			ch = br.readLine().toCharArray();
			for(char c : ch) {
				if(c=='(') {
					pre = '(';
					cnt++;
					stack.add(c);
				} else if(c==')') { 
					if(pre == '(') {
						cnt--;
						answer += cnt;
						stack.pop();
					} else {
						answer++;
						cnt--;
					}
					pre = ')';
				}
			}
		System.out.printf("#%d %d\n",t ,answer);
		}
	}
}
