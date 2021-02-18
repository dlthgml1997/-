import java.util.*;
import java.io.*;

public class Main_BOJ_7576_토마토 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int[][] box;
	static Deque<Pos> deque;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int emptyCnt = 0, zeroCnt = 0;
	static int day = -1;

	private static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		box = new int[N][M];
		deque = new ArrayDeque<Pos>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = stoi(st.nextToken());
				if (box[i][j] == 1)
					deque.add(new Pos(i, j));
				if (box[i][j] == 0)
					zeroCnt++;
			}
		}

		if (zeroCnt == 0) {
			System.out.println("0");
			return;
		}
		
		bfs();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					System.out.println("-1");
					return;
				}
			}
		}

		System.out.println(day);
	}

	private static void bfs() {
		while (!deque.isEmpty()) {
			day++;
			int limit = deque.size();
			for (int l = 0; l < limit; l++) {
				emptyCnt = 0;
				Pos tomato = deque.pollFirst();

				for (int i = 0; i < 4; i++) {
					int nx = tomato.x + dx[i];
					int ny = tomato.y + dy[i];
					if (!isRange(nx, ny)) {
						continue;
					}
					if (box[nx][ny] == 0) {
						box[nx][ny] = 1;
						deque.add(new Pos(nx, ny));
					}
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
