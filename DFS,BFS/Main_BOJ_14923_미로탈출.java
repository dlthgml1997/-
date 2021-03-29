import java.util.*;
import java.io.*;

public class Main_BOJ_14923_미로탈출 {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, Hx, Hy, Ex, Ey;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx= {0,0,-1,1};
	static int[] dy = {-1, 1, 0, 0};
	static int D= Integer.MAX_VALUE; 
	static ArrayList<int[]> walls = new ArrayList<>();
	private static class Pos {
		int x;
		int y;
		int cnt;
		int broken;
		
		public Pos(int x, int y, int cnt, int broken) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.broken = broken;
		}
	}
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Hx = Integer.parseInt(st.nextToken());
		Hy = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken());
		Ey = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][2];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		int answer = D == Integer.MAX_VALUE ? -1: D;
		System.out.println(answer);
	}

	private static void bfs() {
		int nx, ny;
		Deque<Pos> deque = new ArrayDeque<>();
		deque.add(new Pos(Hx, Hy, 0, 0));
		visited[Hx][Hy][0] = true;
		
		while(!deque.isEmpty()) {
			Pos pos = deque.poll();
			
			for(int i=0;i<4;i++) {
				nx = pos.x + dx[i];
				ny = pos.y + dy[i];
				if(!isRange(nx, ny)) continue;
				if(nx == Ex && ny == Ey) {
					D = Math.min(D, pos.cnt+1);
					continue;
				}
				if(map[nx][ny] == 1 && pos.broken == 0) {
					deque.add(new Pos(nx, ny, pos.cnt+1, 1));
					visited[nx][ny][1] = true;
				} else if(map[nx][ny] ==0 && !visited[nx][ny][pos.broken]) {
					deque.add(new Pos(nx, ny, pos.cnt+1, pos.broken));
					visited[nx][ny][pos.broken] = true;
				}
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x <= 0 || x >= N+1 || y <= 0 || y >= M+1 ? false : true;
	}
}
