import java.io.*;
import java.util.*;

public class Main_BOJ_2615_오목 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map = new int[19][19];
	static int[] dx = { 0, 1, -1, 1, 0, -1, 1, -1 };
	static int[] dy = { 1, 1, 1, 0, -1, -1, -1, 0 };
	static boolean[][][] visited = new boolean[19][19][4];
	static Deque<int[]> deque = new ArrayDeque();

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1 || map[i][j] == 2)
					deque.add(new int[] { i, j });
			}
		}

		while (!deque.isEmpty()) {
			int[] pos = deque.poll();
			int x = pos[0];
			int y = pos[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (!isRange(nx, ny)) continue;
				if (!visited[nx][ny][i] && countFive(nx, ny, i, map[x][y])) {
					if(!isRange(x+dx[i+4], y+dy[i+4])) {
						System.out.println(map[x][y]);
						System.out.print((x+1) + " " + (y+1));
						return;
					}
					if(isRange(x+dx[i+4], y+ dy[i+4]) && map[x + dx[i+4]][y + dy[i+4]] != map[nx][ny]) {
						System.out.println(map[x][y]);
						System.out.print((x+1) + " " + (y+1));
						return;
					}
				}
			}
		}
		System.out.println(0);
	}

	private static boolean countFive(int x, int y, int dir, int color) {
		if(color == 0) return false;
		int cnt = 1;
		while (map[x][y] == color) {
			cnt++;
			visited[x][y][dir] = true;
			x += dx[dir];
			y += dy[dir];
		}
		return cnt == 5 ? true : false;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= 19 || ny < 0 || ny >= 19 ? false : true;
	}
}
