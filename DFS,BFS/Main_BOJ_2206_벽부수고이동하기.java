import java.io.*;
import java.util.*;

public class Main_BOJ_2206_벽부수고이동하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static char[][] map;
	static int[][][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Wall {
		int x; // 위치를 나타낸다. 행
		int y; // 열
		int distance; // 현재 좌표까지의 최단 거리
		boolean isDrill; // true: 벽을 한번 뚫었음 , false: 벽을 한번도 뚫지 않음

		Wall(int x, int y, int distance, boolean isDrill) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.isDrill = isDrill;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열

		map = new char[N][M];
		visited = new int[2][N][M]; // [0][][] -> 벽 안뚫고, [1][][] -> 벽 뚫고

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray(); // 벽 정보 받아오기
			for (int j = 0; j < M; j++) {
				visited[0][i][j] = Integer.MAX_VALUE; // 최댓값으로 초기화
				visited[1][i][j] = Integer.MAX_VALUE;
			}
		}

		bfs(0, 0);

		int answer = Math.min(visited[0][N - 1][M - 1], visited[1][N - 1][M - 1]); // 둘 중 최솟값이 정답
		if (answer == Integer.MAX_VALUE) { // 둘 다 초기값이면 도달하지 못한 것을 의미한다.
			System.out.println("-1");
			return;
		}
		System.out.println(answer);
	}

	private static void bfs(int x, int y) {
		Queue<Wall> q = new LinkedList<Wall>();
		q.add(new Wall(x, y, 1, false));
		visited[0][x][y] = 1;
		visited[1][x][y] = 1;

		while (!q.isEmpty()) {
			Wall wall = q.poll();
			if (wall.x == N - 1 && wall.y == M - 1) {
				if (wall.isDrill) {
					visited[1][N - 1][M - 1] = wall.distance;
				} else {
					visited[0][N - 1][M - 1] = wall.distance;
				}
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = wall.x + dx[i];
				int ny = wall.y + dy[i];
				if (!isRange(nx, ny))
					continue;
				if (!wall.isDrill) {// 벽을 한번도 안 뚫고 왔다면
					if (visited[0][nx][ny] == Integer.MAX_VALUE && map[nx][ny] == '0') {// 벽을 안뚫는 경우와 (다음 칸이 벽이 아닌 경우)
						visited[0][nx][ny] = wall.distance + 1; // 여태 벽을 뚫고 오지 않았으므로 (벽을 안뚫은 경우의 거리 + 1)
						q.add(new Wall(nx, ny, visited[0][nx][ny], false));
					} else {
						if (visited[1][nx][ny] == Integer.MAX_VALUE && map[nx][ny] == '1') {// 벽을 뚫는 경우 모두 따진다. (다음 칸이 벽인 경우)
							visited[1][nx][ny] = wall.distance + 1; // 여태 벽을 뚫고 오지 않았으므로 (벽을 안뚫은 경우의 거리 + 1)
							q.add(new Wall(nx, ny, visited[1][nx][ny], true));
						}
					}
				} else {// 벽을 이미 한번 뚫고 왔다면
					if (visited[1][nx][ny] == Integer.MAX_VALUE && map[nx][ny] == '0') { // 벽을 안뚫는 경우만 따진다. (다음 칸이 벽이 아닌 경우)
						visited[1][nx][ny] = wall.distance + 1; // 벽을 뚫고 온 경우이므로 (벽을 뚫은 경우의 거리 + 1)
						q.add(new Wall(nx, ny, visited[1][nx][ny], true));
					}
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
