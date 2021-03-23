import java.util.*;
import java.io.*;
// 최단거리는 dfs 보단 bfs로! 
public class Main_BOJ_4485_녹색옷입은애가젤다지 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] map, rupeeMap;
	static int N;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static private class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		int t = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			rupeeMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					rupeeMap[i][j] = Integer.MAX_VALUE;
				}
			}
			rupeeMap[0][0] = map[0][0];
			bfs();
			bw.append("Problem " + t + ": " + rupeeMap[N - 1][N - 1] + "\n");
			t++; // 다음 테스트 케이스로~
		}
		bw.flush();
		bw.close();
	}

	private static void bfs() {
		Deque<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(0, 0));
		while (!queue.isEmpty()) {
			int nx, ny;
			Pos pos = queue.poll();
			int x = pos.x;
			int y = pos.y;
			for (int i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (!isRange(nx, ny))
					continue;
				if (rupeeMap[nx][ny] <= rupeeMap[x][y] + map[nx][ny])
					continue;
				rupeeMap[nx][ny] = rupeeMap[x][y] + map[nx][ny];
				queue.add(new Pos(nx, ny));
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || y < 0 || x >= N || y >= N ? false : true;
	}

}
