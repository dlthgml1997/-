import java.util.*;
import java.io.*;

public class Main_BOJ_1600_말이되고픈원숭이 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dxH = { -2, -1, -2, -1, 1, 2, 2, 1 };
	static int[] dyH = { -1, -2, 1, 2, -2, -1, 1, 2 };
	static int N, M, K;

	static private class Pos {
		int x;
		int y;
		int k; // 말처럼 이동할 수 있는 횟수
		int move; // 이동 횟수

		public Pos(int x, int y, int k, int move) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.move = move;
		}
	}

	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if (map[0][0] == 1 || map[N - 1][M - 1] == 1) {
			System.out.println(-1);
			return;
		}
		int answer = bfs();
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}

	private static int bfs() {
		Deque<Pos> deque = new ArrayDeque<Pos>();
		deque.add(new Pos(0, 0, 0, 0));
		int nx, ny;
		int minNum = Integer.MAX_VALUE;
		while (!deque.isEmpty()) {
			Pos pos = deque.poll();
			int x = pos.x;
			int y = pos.y;
			int k = pos.k;
			int move = pos.move;
			if (x == N - 1 && y == M - 1) {
				minNum = Math.min(minNum, move);
				continue;
			}
			move++;
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (!isRange(nx, ny) || map[nx][ny] == 1 || visited[nx][ny][k])
					continue;
				deque.add(new Pos(nx, ny, k, move));
				visited[nx][ny][k] = true;
			}

			if (k < K) {
				for (int i = 0; i < 8; i++) {
					nx = x + dxH[i];
					ny = y + dyH[i];
					if (!isRange(nx, ny) || map[nx][ny] == 1 || visited[nx][ny][k + 1])
						continue;
					deque.add(new Pos(nx, ny, k + 1, move));
					visited[nx][ny][k+1] = true;
				}
			}
		}
		return minNum;
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
