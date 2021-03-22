import java.util.*;
import java.io.*;

public class Main_JO_1681_해밀턴순환회로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[] visited;
	static int[] list;
	static int N, total = 0;
	static int minNum = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		list = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[0] = true;
		permutation(0,0,0);
		System.out.println(minNum);
	}
	
	private static void permutation(int cnt, int now, int cost) {
		if(cnt == N-1) {
			if(map[now][0] == 0) return;
			cost += map[now][0];
			minNum = Math.min(minNum, cost);
			return;
		}
		if(cost > minNum) return;
		 
		for(int i= 1; i<N; i++) {
			if(visited[i]) continue;
			if(map[now][i] == 0) continue;
			visited[i] = true;
			permutation(cnt+1, i, cost+ map[now][i]);
			visited[i] = false;
		}
		
	}
}
