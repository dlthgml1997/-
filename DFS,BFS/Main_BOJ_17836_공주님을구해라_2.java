import java.io.*;
import java.util.*;

public class Main_BOJ_17836_공주님을구해라_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N, M, T;
	static int answer = Integer.MAX_VALUE;

	private static class Warrior {
		int x;
		int y;
		int time;

		public Warrior(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = Integer.MAX_VALUE;
		bfs();

		if (answer > T) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
	}

	private static void bfs() {
		Deque<Warrior> deque = new ArrayDeque<>();
		deque.add(new Warrior(0, 0, 0));
		int nx, ny;
		while (!deque.isEmpty()) {
			Warrior w = deque.poll();
			for (int i = 0; i < 4; i++) {
				nx = w.x + dx[i];
				ny = w.y + dy[i];
				if (!isRange(nx, ny) || map[nx][ny] == 1 || visited[nx][ny])
					continue;
				
				if (nx == N - 1 && ny == M - 1) {
					visited[nx][ny] = true;
					answer = Math.min(answer, w.time + 1);
					continue;
				}

				if (map[nx][ny] == 2) {
					answer = Math.min(answer, w.time + 1 + Math.abs(nx - (N - 1)) + Math.abs(ny - (M - 1)));
				}

				visited[nx][ny] = true;
				deque.add(new Warrior(nx, ny, w.time + 1));
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
