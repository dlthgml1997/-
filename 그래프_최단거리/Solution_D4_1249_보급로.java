import java.io.*;
import java.util.*;

public class Solution_D4_1249_보급로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] map, time;
	static boolean[][] check;
	static int[] dx = {0,-1,1,0};
	static int[] dy = {-1,0,0,1};

	private static class Vertex implements Comparable<Vertex> {
		int x;
		int y;
		int depth;

		public Vertex(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return this.depth - o.depth;
		}
	} 

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			check = new boolean[N][N];
			time = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] temp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = temp[j]-'0';
					time[i][j] = Integer.MAX_VALUE;
				}
			}
			
			System.out.println("#"+tc+" "+dijkstra(0,0));
		}
	}

	private static int dijkstra(int x, int y) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(x, y, map[x][y]));
		time[x][y] = map[x][y];
		while(!pq.isEmpty()) {
			Vertex vertex = pq.poll();
			if(check[vertex.x][vertex.y]) continue;
			check[vertex.x][vertex.y]= true; 
			for(int i=0; i<4; i++) {
				int nx = vertex.x + dx[i];
				int ny = vertex.y + dy[i];
				if(nx <0 || nx >= N || ny < 0 || ny >= N) continue;
				if(!check[nx][ny] && time[nx][ny] > time[vertex.x][vertex.y]+ map[nx][ny]) {
					time[nx][ny] = time[vertex.x][vertex.y] + map[nx][ny];
					pq.add(new Vertex(nx, ny, time[nx][ny]));
				}
			}
		}
		return time[N-1][N-1];
	}
}
