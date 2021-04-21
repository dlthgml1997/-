import java.io.*;
import java.util.*;

public class Main_BOJ_1194_달이차오른다가자 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][][] visited;
	
	private static class Pos {
		int x;
		int y;
		int keyStatus;
		int move;
		
		public Pos(int x, int y, int keyStatus, int move) {
			this.x = x;
			this.y =y;
			this.keyStatus = keyStatus;
			this.move = move;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][1<<6];
		
		Pos start = null;
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == '0') {
					start = new Pos(i, j, 0, 0);
				}
			}
		}
		
		System.out.println(bfs(start.x, start.y));
	}

	private static int bfs(int x, int y) {
		Deque<Pos> deque = new ArrayDeque<>();
		deque.add(new Pos(x, y, 0, 0));
		
		while(!deque.isEmpty()) {
			Pos pos = deque.poll();
			
			for(int i=0;i<4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				int keyStatus = pos.keyStatus;
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
		
				if(map[nx][ny] == '1' ) {
					return pos.move + 1;
				}
				
				if(map[nx][ny] == '#') continue;
				
				if(visited[nx][ny][pos.keyStatus]) continue;
				
				if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') { // key 
					keyStatus |= (1 << (map[nx][ny] - 'a'));
				}
				
				if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F' ) { // door
					if((pos.keyStatus & (1 << map[nx][ny]- 'A')) == 0) {
						continue;
					}
				}
				
				deque.add(new Pos(nx, ny, keyStatus, pos.move+1));
				visited[nx][ny][keyStatus] = true;
			}
		}

		return -1;
	}
}
