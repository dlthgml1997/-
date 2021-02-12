import java.io.*;
import java.util.*;

public class Main_BOJ_5397_키로거 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		int T = stoi(br.readLine());
		for (int i = 0; i < T; i++) {
			Deque<Character> pw = new ArrayDeque<Character>();
			Deque<Character> wait = new ArrayDeque<Character>();

			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < ch.length; j++) {
				char c = ch[j];
				if (c == '<' || c == '>' || c == '-') {
					switch (c) {
					case '<': {
						if (pw.isEmpty())
							break;
						wait.addFirst(pw.removeLast());
						break;
					}
					case '>': {
						if (wait.isEmpty())
							break;
						pw.addLast(wait.removeFirst());
						break;
					}
					case '-':
						if(pw.isEmpty()) break;
						pw.removeLast();
						break;
					}
				} else {
					pw.addLast(c);
				}
			}
			
			while (!wait.isEmpty())
				pw.addLast(wait.removeFirst());
			while (!pw.isEmpty())
				bw.append(pw.removeFirst()+"");
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
