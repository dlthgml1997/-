import java.io.*;
import java.util.*;

public class Main_BOJ_5427_불 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static int N, M, T;
	static Deque<Pos> fires;
	static Deque<Pos> sang;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int minNum;
	static int time;

	private static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			minNum = Integer.MAX_VALUE;
			time = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			fires = new ArrayDeque<Pos>();
			sang = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == '*') {
						fires.add(new Pos(i, j));
					} else if (map[i][j] == '@') {
						sang.add(new Pos(i, j));
					}
				}
			}
			boolean isPossible = false;
			while (sang.size() > 0) {
				time++;
				moveFire();
				if (!moveSang()) {
					System.out.println(time);
					isPossible = true;
					break;
				}
			}
			if (!isPossible) {
				System.out.println("IMPOSSIBLE");
			}
		}
	}

	private static boolean moveSang() {
		int nx, ny;
		int turn = sang.size();
		for (int s = 0; s < turn; s++) {
			Pos pos = sang.poll();
			for (int i = 0; i < 4; i++) {
				nx = pos.x + dx[i];
				ny = pos.y + dy[i];
				if (!isRange(nx, ny)) {
					return false;
				}
				if (map[nx][ny] == '#' || map[nx][ny] == '*') {
					continue;
				}
				if(map[nx][ny] != '@') {
					map[nx][ny] = '@';
					sang.add(new Pos(nx, ny));	
				}
			}
		}
		return true;
	}

	private static void moveFire() {
		int turn = fires.size();
		int nx, ny;

		for (int i = 0; i < turn; i++) {
			Pos pos = fires.poll();
			for (int d = 0; d < 4; d++) {
				nx = pos.x + dx[d];
				ny = pos.y + dy[d];
				if (!isRange(nx, ny) || map[nx][ny] == '#' || map[nx][ny] == '*')
					continue;
				map[nx][ny] = '*';
				fires.add(new Pos(nx, ny));
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
