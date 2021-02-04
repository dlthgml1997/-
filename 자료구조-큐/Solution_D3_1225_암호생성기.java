import java.io.*;
import java.util.*;

public class Solution_D3_1225_암호생성기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Queue<Integer> queue = new LinkedList();

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			input();
			bw.append("#"+t);
			recommendPassword();
			print();
		}
		bw.flush();
		bw.close();
	}

	private static void recommendPassword() {
		int value = 0;
		while(true) {
			for(int i =1; i<=5; i++) {
				value = queue.poll() - i;
				if(value > 0) queue.add(value);
				else {
					queue.add(0);
					return;
					
				}
			}
		}
	}

	private static void print() throws IOException {
		while(!queue.isEmpty()) bw.append(" "+queue.poll());
		bw.append("\n");
	}

	private static void input() throws Exception {
		br.readLine();
		st = new StringTokenizer(br.readLine());
		queue.clear();
		while(st.hasMoreTokens()) {
			queue.add(Integer.parseInt(st.nextToken()));
		}
	}
}
