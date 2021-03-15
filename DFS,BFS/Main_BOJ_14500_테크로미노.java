import java.util.*;
import java.io.*;

public class Main_BOJ_14500_테크로미노 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, maxNum = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] caseX = { { 0, 0, 0, -1 }, { 0, 0, 0, 1 }, { 0, 0, 1, -1 }, { 0, 1, 2, 1 } };
	static int[][] caseY = { { 0, 1, 2, 1 }, { 0, 1, 2, 1 }, { 0, 1, 1, 1 }, { 0, 0, 0, 1 } };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;

				// 한 붓 그리기가 안되는 경우
				for (int d = 0; d < 4; d++) {
					int total = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i+ caseX[d][k];
						int ny = j + caseY[d][k];
						if (!isRange(nx, ny)) {
							total = 0;
							break;
						}
						total += map[nx][ny];
					}
					maxNum = Math.max(total, maxNum);
				}

			}
		}
		System.out.println(maxNum);
	}

	private static void dfs(int x, int y, int cnt, int total) {
		if (cnt == 3) {
			maxNum = Math.max(total, maxNum);
			return;
		}

		int nx, ny;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (!isRange(nx, ny) || visited[nx][ny]) {
				continue;
			}
			visited[nx][ny] = true;
			dfs(nx, ny, cnt + 1, total + map[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;
	}
}