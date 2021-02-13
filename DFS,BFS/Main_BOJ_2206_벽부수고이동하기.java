import java.io.*;
import java.util.*;

public class Main_BOJ_2206_벽부수고이동하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static char[][] map;
	static int[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	static class Wall {
		int x;
		int y;
		int distance;
		boolean isDrill;
		
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
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j =0; j<M; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		bfs(0,0);
		
		if (visited[N-1][M-1] == Integer.MAX_VALUE) {
			System.out.println("-1");
			return;
		}
		System.out.println(visited[N-1][M-1]);
	}

	private static void bfs(int x, int y) {
		Queue<Wall> q = new LinkedList<Wall>();
		q.add(new Wall(x,y,1,false));
		visited[x][y] = 1;
		
		while (!q.isEmpty()) {
			Wall wall = q.poll();
			if(wall.x == N-1 && wall.y == M-1) {
				visited[N-1][M-1] = wall.distance; return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = wall.x + dx[i];
				int ny = wall.y + dy[i];
				if (!isRange(nx, ny))
					continue;
				if(visited[nx][ny] <= wall.distance+1) continue;
				if( map[nx][ny] == '1') {
					if(wall.isDrill) continue;
					else {
						visited[nx][ny] = wall.distance + 1;
						q.add(new Wall(nx, ny, visited[nx][ny], true));
						continue;
					}
				}
				visited[nx][ny] = wall.distance + 1;
				if(wall.isDrill) q.add(new Wall(nx, ny, visited[nx][ny], true));
				else q.add(new Wall(nx, ny, visited[nx][ny], false));
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
