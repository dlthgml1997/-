import java.util.*;
import java.io.*;

public class Main_BOJ_9372_상근이의여행 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] airplanes;
	static boolean[] visited;
	static Stack<Integer> stack;
	static int cN, aN;
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		for(int i=1; i<=N; i++) {
			input();
			bw.append(bfs(1)+"\n");
		}
		bw.flush();
		bw.close();
	}
	private static int bfs(int start) {
		int result = 0;
		stack.add(start);
		visited[start] = true;
		while(!stack.isEmpty()) {
			int a = stack.pop();
			for(int i=1; i<= cN; i++) {
				if(airplanes[a][i] == 1 && !visited[i]) {
					stack.add(i);
					visited[i] = true;
					result++;
				}
			}
		}
		return result;
	}
	
	private static void input() throws Exception {
		st= new StringTokenizer(br.readLine());
		cN = Integer.parseInt(st.nextToken());
		aN = Integer.parseInt(st.nextToken());
		airplanes = new int[cN+1][cN+1];
		visited = new boolean[cN+1];
		stack = new Stack();
		for(int i =0; i<aN; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			airplanes[a][b] = 1;
			airplanes[b][a] = 1;
		}
	}

}
