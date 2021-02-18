import java.io.*;
import java.util.*;

public class Main_BOJ_1987_알파벳 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer = 1;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		st =new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i=0; i< R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		boolean[] visitedA = new boolean['Z'-'A'+1];
		dfs(0,0,1,visitedA);
		System.out.println(answer);
	}
	
	private static void dfs(int x, int y, int cnt, boolean[] visitedA) {
		visitedA[map[x][y]-'A'] = true;
		visited[x][y] = true;
		answer = Math.max(answer, cnt);
		for(int i=0; i< 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isRange(nx, ny) || visited[nx][ny]) continue;
			if(!visitedA[map[nx][ny]-'A']) {
				dfs(nx, ny, cnt+1, visitedA);
				visitedA[map[nx][ny]-'A'] = false;
				visited[nx][ny] = false;
			}
		}
	}

	private static boolean isRange(int nx, int ny) {
		return nx <0 || nx >= R || ny < 0 || ny >= C ? false: true;
	}
}
