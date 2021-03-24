import java.util.*;
import java.io.*;

public class Main_BOJ_2636_치즈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;
	static int time, cnt, preCnt, N, M;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	
	static private class Pos{
		int x;
		int y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		time =0; cnt =0; preCnt =0;
		while(true) {
			visited = new boolean[N][M];
			cnt = bfs();
			if(cnt == 0) break;
			preCnt = cnt;
			time++;
		}
		System.out.println(time);
		System.out.println(preCnt);
	}
	private static int bfs() {
		int cnt = 0, nx, ny, x, y;
		Deque<Pos> deque = new ArrayDeque<Pos>();
		deque.add(new Pos(0,0));
		while(!deque.isEmpty()) {
			Pos pos = deque.poll();
			x = pos.x;
			y = pos.y;
			for(int i=0;i<4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if(!isRange(nx,ny) || visited[nx][ny]) continue;
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					deque.add(new Pos(nx, ny));
				} else if(map[nx][ny] == 1) {
					cnt++;
					map[nx][ny] = 0;
					visited[nx][ny] = true;
				}
			}
		}
		return cnt;
	}
	
	private static boolean isRange(int x,int y) {
		return x < 0 || x>= N|| y <0 || y >=M ? false : true;
	}
}
