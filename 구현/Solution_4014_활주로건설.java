import java.util.*;
import java.io.*;

public class Solution_4014_활주로건설 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;
	static int N, X;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		int pre, now = 0, cnt = 0, answer;

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			answer = N + N;
			map = new int[N][N];
			visited = new boolean[N][N];
			X = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 방향
			// 행 검사
			for (int i = 0; i < N; i++) {
				pre = map[i][0];
				cnt = 0;
				now = -1;
				for (int j = 0; j < N; j++) {
					now = map[i][j];
					if (!visited[i][j]) {
						if (now == pre) {
							cnt++;
						} else if (now - pre == 1) {
							if(cnt < X) {
								answer--;
								break;
							}
							cnt = 1;
						} else if (pre - now == 1) {
							if (!check(i, j, 0, 1)) {
								answer--;
								break;
							}
							cnt = 1;
						} else {
							answer--;
							break;
						}
					} else {
						cnt =0;
					}
					pre = now;
				}
			}
			
			// 열 검사 
			visited = new boolean[N][N];
			for(int j=0; j<N; j++) {
				pre = map[0][j];
				cnt = 0;
				now = -1;
				for (int i=0; i<N; i++) {
					now = map[i][j];
					if (!visited[i][j]) {
						if (now == pre) {
							cnt++;
						} else if (now - pre == 1) {
							if(cnt < X) {
								answer--;
								break;
							}
							cnt = 1;
						} else if (pre - now == 1) {
							if (!check(i, j, 1, 0)) {
								answer--;
								break;
							}
							cnt = 1;
						} else {
							answer--;
							break;
						}
					} else {
						cnt =0;
					}
					pre = now;
				}
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static boolean check(int x, int y, int dx, int dy) {
		int pre = map[x][y];
		int nx = x, ny = y;
		int cnt = 0;
		while (cnt < X) {
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				return false;
			if (pre != map[nx][ny])
				return false;
			pre = map[nx][ny];
			visited[nx][ny] = true;
			nx += dx;
			ny += dy;
			cnt++;
		}
		return true;
	}
}
