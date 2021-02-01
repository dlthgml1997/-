// https://www.acmicpc.net/problem/10845
import java.io.*;
import java.util.*;

public class Main_BOJ_10845_큐 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		T = stoi(br.readLine());
		int last = 0;
		for(int i =0 ; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			String doing = st.nextToken();
			
			switch(doing) {
				case "push" :
					last = stoi(st.nextToken());
					queue.add(last); 
					break;
				case "pop" :
					if(queue.isEmpty()) {
						bw.append("-1\n"); break;
					}
					bw.append(queue.poll()+"\n"); break;
				case "size" :
					bw.append(queue.size()+"\n"); break;
				case "empty":
					if(queue.isEmpty()) {
						bw.append(1+"\n"); break;
					} else {
						bw.append(0+"\n"); break;
					}
				case "front" :
					if(queue.isEmpty()) {
						bw.append("-1\n"); break;
					}
					bw.append(queue.peek()+"\n");break;
				case "back" :
					if(queue.isEmpty()) {
						bw.append("-1\n"); break;
					}
					bw.append(last+"\n"); break;
			}
		}
		bw.flush();
		bw.close();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
