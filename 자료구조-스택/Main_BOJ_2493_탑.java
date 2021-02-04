import java.io.*;
import java.util.*;

public class Main_BOJ_2493_탑 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		int N = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int[] lazer = new int[N]; // 탑의 높이가 담겨있다. lazer[i] == 높이, i+1 == 탑의 번호 
		int[] answer = new int[N]; // 탑의 번호가 담겨진다.
		for(int i=0; i<N; i++) {
			lazer[i] = stoi(st.nextToken());
		}
		for(int i =N-1; i >= 0; i-- ) {
			if(stack.isEmpty()) {
				stack.add(i); continue; 
			}
			if(lazer[stack.peek()] <= lazer[i]) {
				while(lazer[stack.peek()] <= lazer[i]) {
					answer[stack.pop()] = i+1;
					if(stack.size() == 0) break;
				}
				stack.add(i);
			} else {
				stack.add(i);
			}
		}
		while(!stack.isEmpty()) {
			answer[stack.pop()] = 0;
		}
		
		for(int i =0 ; i< N; i++) {
			System.out.print(answer[i] + " ");
		}
	}
	
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}
