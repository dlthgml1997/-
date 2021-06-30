import java.io.*;
import java.util.*;

public class Main_gr_최소이동비용 {
	static int[][] map;
	static int[][] dp;
	static int n, m;
	static int[] dx = {1, 0}; // 우, 하 이동
	static int[] dy = {0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m];
		
		for(int i=0; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		bfs(0,0);
		System.out.println(dp[n-1][m-1]);
	}
	
	private static void bfs(int x, int y) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {x, y});
		dp[x][y] = map[x][y];

		while(!dq.isEmpty()) {
			int nx, ny;
			int[] pos = dq.poll();
			for(int i=0; i<2; i++) {
				nx = pos[0] + dx[i];
				ny = pos[1] + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if(dp[nx][ny] > map[nx][ny] + dp[pos[0]][pos[1]]) {
					dp[nx][ny] = dp[pos[0]][pos[1]] + map[nx][ny];
					dq.offer(new int[] {nx, ny});
				}
			}
		}
	}
}