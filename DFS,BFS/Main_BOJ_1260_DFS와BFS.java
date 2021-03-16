import java.util.*;
import java.io.*;

public class Main_BOJ_1260_DFS와BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	// init
	static int[][] map;
	static int N, M;
	static boolean[] visited;
	// bfs
	static Deque<Integer> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		map = new int[N+1][N+1];
		for(int i=0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			map[n][m] = 1;
			map[m][n] = 1;
		}
		
		dfs(start);
		bw.append("\n");
		visited = new boolean[N+1];
		bfs(start);
		bw.flush();
		bw.close();
	}

	private static void dfs(int start) throws Exception{
		visited[start] = true;
		bw.append(start+" ");
		for(int i=0; i< N+1; i++) {
			if(map[start][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}

	private static void bfs(int start) throws Exception {
		deque.add(start);
		while(!deque.isEmpty()) {
			int current = deque.poll();
			visited[current] = true;
			bw.append(current+" ");
			for(int i=0; i< N+1; i++) {
				if(map[current][i] == 1 && !visited[i]) {
					deque.add(i);
					visited[i] = true;
				}
			}
		}
	}
}
