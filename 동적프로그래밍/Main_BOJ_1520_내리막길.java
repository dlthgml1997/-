import java.util.*;
import java.io.*;

public class Main_BOJ_1520_내리막길 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, total = 0;
	static int[][] map, visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	private static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int height;
		
		public Pos(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}

		@Override
		public int compareTo(Pos o) {
			return this.height - o.height;
		}
	}
	
	public static void main(String[] args) throws Exception {
		input();
		bfs();
		System.out.println(visited[N-1][M-1]);
	}

	private static void bfs() {
		PriorityQueue<Pos> pq = new PriorityQueue();
		pq.add(new Pos(0,0,map[0][0]));
		visited[0][0] = 1;
		int nx, ny;
		while(!pq.isEmpty()) {
			Pos now = pq.poll();
			for(int i=0; i<4; i++) {
				nx = now.x + dx[i];
				ny = now.y + dy[i];
				if(nx <0 || nx >= N || ny < 0 || ny >= M) continue;
				if(map[nx][ny] < map[now.x][now.y]) {
					if(visited[nx][ny] == 0)
						pq.add(new Pos(nx, ny, map[nx][ny]));
					visited[nx][ny] += visited[now.x][now.y];
				}
			}
		}
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
