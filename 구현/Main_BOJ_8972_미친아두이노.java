import java.util.*;
import java.io.*;

public class Main_BOJ_8972_미친아두이노 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C;
	static char[][] map;
	static int[][] visited;
	static Pos jongsu;
	static Deque<Pos> crazyAdu = new ArrayDeque<Pos>();
	static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
	static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	static Deque<Integer> jongsuDirs = new ArrayDeque<Integer>();
	static int cnt = 0;

	private static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'R')
					crazyAdu.add(new Pos(i, j));
				else if (map[i][j] == 'I')
					jongsu = new Pos(i, j);
			}
		}

		char[] chars = br.readLine().toCharArray();
		for (char c : chars) {
			jongsuDirs.add(c - '0');
		}

		while (!jongsuDirs.isEmpty()) {
			if (moveJongsu(jongsu.x, jongsu.y)) {
				if (!moveCrazyAduino()) {
					System.out.println("kraj " + cnt);
					return;
				}
				popAdu();
			} else {
				// 중간에 진경우 return !!
				System.out.println("kraj " + cnt);
				return;
			}
		}
		for(int i=0; i< R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void popAdu() { // 터치기, visited초기화, 미친 아두 위치 담기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visited[i][j] > 1) {
					map[i][j] = '.';
				} else if(visited[i][j] ==1) {
					crazyAdu.add(new Pos(i, j));
				}
				visited[i][j] = 0;
			}
		}
	}

	private static boolean moveCrazyAduino() { // false: 종수랑 만남
		int nx, ny;
		int minNum = Integer.MAX_VALUE;
		int minX, minY;
		Deque<Pos> pos = new ArrayDeque<Pos>();
		while(!crazyAdu.isEmpty()) {
			minNum = Integer.MAX_VALUE;
			Pos aduDir = crazyAdu.pollFirst();
			minX = aduDir.x;
			minY = aduDir.y;
			for (int d = 1; d <= 9; d++) {
				nx = aduDir.x + dx[d];
				ny = aduDir.y + dy[d];
				if (isRange(nx, ny)) {
					int dir = calcDir(jongsu.x, jongsu.y, nx, ny);
					if (dir < minNum) {
						minNum = dir;
						minX = nx;
						minY = ny;
					}
				}
			}
			map[aduDir.x][aduDir.y] = '.';
			if (map[minX][minY] == 'I') {
				return false;
			}
			pos.add(new Pos(minX, minY));
			visited[minX][minY]++;
		}
		while(!pos.isEmpty()) {
			Pos p = pos.poll();
			map[p.x][p.y] = 'R';
		}
		return true;
	}

	private static boolean moveJongsu(int x, int y) {
		cnt++;
		int dir = jongsuDirs.pollFirst();
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if (map[nx][ny] == 'R')
			return false;
		map[x][y] = '.';
		map[nx][ny] = 'I';
		jongsu.x = nx;
		jongsu.y = ny;
		return true;
	}

	static boolean isRange(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C ? false : true;
	}

	static int calcDir(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
