import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int N = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int[] topHeight = new int[N]; // 탑의 높이가 담겨있다. topHeight[i] == 높이, i+1 == 탑의 번호
		int[] answer = new int[N]; // 탑의 번호가 담겨진다.
		for (int i = 0; i < N; i++) {
			topHeight[i] = stoi(st.nextToken());
		}
		for (int i = N - 1; i >= 0; i--) {
			if (stack.isEmpty()) {
				stack.add(i);
				continue;
			}
			while (topHeight[stack.peek()] <= topHeight[i]) {
				answer[stack.pop()] = i + 1;
				if (stack.size() == 0)
					break;
			}
			stack.add(i);
		}

		for (int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
