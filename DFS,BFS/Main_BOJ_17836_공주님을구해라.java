import java.io.*;
import java.util.*;

public class Main_BOJ_17836_공주님을구해라 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[][][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean hasGram;
	static int N, M, T;

	private static class Warrior {
		int x;
		int y;
		int time;
		boolean gram;

		public Warrior(int x, int y, int time, boolean gram) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.gram = gram;
		}

	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
		int answer;
		if (visited[N - 1][M - 1][0] == 0) {
			answer = visited[N - 1][M - 1][1];
		} else if (visited[N - 1][M - 1][1] == 0) {
			answer = visited[N - 1][M - 1][0];
		} else {
			answer = Math.min(visited[N - 1][M - 1][0], visited[N - 1][M - 1][1]);
		}
		if (answer == 0) {
			System.out.println("Fail");
		} else {
			System.out.println(answer);
		}
	}

	private static void bfs() {
		Deque<Warrior> deque = new ArrayDeque<>();
		deque.add(new Warrior(0, 0, 0, false));
		int nx, ny;
		boolean flag = true;
		visited[0][0][0] = 1;
		visited[0][0][1] = 1;
		while (!deque.isEmpty()) {
			Warrior w = deque.poll();
			if (w.time >= T)
				continue;
			if (hasGram && w.gram) {
				for (int i = 0; i < 4; i++) {
					nx = w.x + dx[i];
					ny = w.y + dy[i];
					if (!isRange(nx, ny) || visited[nx][ny][1] > 0)
						continue;
					visited[nx][ny][1] = w.time + 1;
					deque.add(new Warrior(nx, ny, w.time + 1, true));
				}
			}
			if (!w.gram) {
				for (int i = 0; i < 4; i++) {
					nx = w.x + dx[i];
					ny = w.y + dy[i];

					if (!isRange(nx, ny) || map[nx][ny] == 1 || visited[nx][ny][0] > 0)
						continue;
					if (map[nx][ny] == 2) {
						hasGram = true;
						visited[nx][ny][1] = w.time + 1;
						deque.add(new Warrior(nx, ny, w.time + 1, true));
					}
					visited[nx][ny][0] = w.time + 1;
					deque.add(new Warrior(nx, ny, w.time + 1, false));
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
