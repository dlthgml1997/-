import java.io.*;
import java.util.*;

public class Main_BOJ_2164_카드2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, N;
	static Queue<Integer> queue = new LinkedList<>();
	static boolean isFloorTurn = true; 

	public static void main(String[] args) throws Exception {
		input();
		while(queue.size()>1) {
			if(isFloorTurn) {
				queue.poll();
			} else {
				queue.add(queue.poll());
			}
			isFloorTurn = isFloorTurn ? false: true;
		}
		
		printAnswer();
	}

	private static void input() throws Exception {
		T = stoi(br.readLine());
		for(int t = 1 ; t<=T; t++) {
			queue.add(t);
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

	private static void printAnswer() {
		System.out.print(queue.peek());
	}
}
