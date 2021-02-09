import java.util.*;

import java.io.*;

public class Main_BOJ_1158_요세푸스문제 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Queue<Integer> queue;
	static int N, K;
	public static void main(String[] args) throws Exception {
		queue = new LinkedList<>();
		input();
		init();
		bw.append("<");
		while(queue.size() >1) {
			bw.append(yosepus()+", ");	
		}
		bw.append(queue.poll()+"");
		bw.append(">");
		bw.flush();
		bw.close();
	}
	
	private static int yosepus() {
		for(int i=0; i<K-1; i++) {
			queue.add(queue.poll());
		}
		return queue.poll();
	}
	
	private static void init() {
		for(int i=1; i<=N; i++) 
			queue.add(i);
	}
	
	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
}
