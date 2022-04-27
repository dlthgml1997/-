import java.io.*;
import java.util.*;

public class Main_BOJ_23289_온풍기안녕 {
	static int[][] map; 
	static boolean[][] visited;
	static List<Pos> checkAreas;
	static List<Pos> machines;
	static boolean[][][][] wall;
	static int N, M, K, W;
	static int[] dx = {0, 0, 0, -1, 1}; // 1우, 2좌, 3상, 4하 
	static int[] dy = {0, 1, -1, 0, 0};
	static int[][] mx = {
			{0,-1,0,1}, // 0우 
			{0,-1,0,1}, // 1좌 
			{-1,-1,-1,-1}, // 2상 
			{1,1,1,1} // 3하 
	};
	static int[][] my = {
			{1,1,1,1},
			{-1,-1,-1,-1},
			{0,-1,0,1},
			{0,-1,0,1}
	};
	
	
	
	private static class Pos {
		int x;
		int y;
		int dir;
		int depth;
		
		Pos(int x, int y, int dir, int depth) {
			this.x = x;
			this.y = y;
			this.dir = dir; // 온풍기인 경우 방향
			this.depth = depth; // 깊이 
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 
		M = Integer.parseInt(st.nextToken()); // 열 
		K = Integer.parseInt(st.nextToken()); // 온도 
		
		map = new int[N][M];
		visited = new boolean[N][M];
		wall = new boolean[N][M][N][M];
		
		checkAreas = new ArrayList<Pos>();
		machines = new ArrayList<Pos>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 5) {
					checkAreas.add(new Pos(i, j, 0, 0));
					map[i][j] = 0;
				} else if (map[i][j] > 0) {
					machines.add(new Pos(i, j, map[i][j], 0));
					map[i][j] = 0;
				}
			}
		}
		
		// 벽 셋팅 
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 벽 갯수
		for(int i=0; i<W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			if(d == 0) {
				wall[x][y][x-1][y] = true;
				wall[x-1][y][x][y] = true;
			} else {
				wall[x][y][x][y+1] = true;
				wall[x][y+1][x][y] = true;
			}
		}
		
		int answer = 0;
		while(true) {
			// 1. 온풍기 바람 나오기. 온풍기 방향은 우좌상하 순.
			for(int i=0; i< machines.size(); i++) {
				Pos pos = machines.get(i);
				spreadAir(pos.x, pos.y, pos.dir);
				clearVisited();
			}
			
			// 2. 온도 조절
			// 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
			changeTemper();
			answer++;
			if(answer > 100) {
				answer = 101; break;
			}
			if(checkTemper()) break;
		}
		System.out.println(answer);
		
	}
	
	private static boolean checkTemper() {
		for(int i=0; i<checkAreas.size(); i++) {
			Pos pos = checkAreas.get(i);
			if(map[pos.x][pos.y] < K) return false;
		}
		return true;
	}

	private static void changeTemper() {
		int[][] tempMap = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int d=1; d<5; d++) {
					if(d != 1 && d != 4) continue;
					int nx = i+dx[d];
					int ny = j+dy[d];
					if(!isRange(nx,ny) || isWall(i,j,nx,ny,d)) continue;
					int diff = map[i][j] - map[nx][ny];
					if(diff > 0) { // i,j 온도가 더 높음 
						tempMap[nx][ny] += diff/4;
						tempMap[i][j] -= diff/4;
					} else { // nx, ny 온도가 더 높음
						diff = Math.abs(diff);
						tempMap[i][j] += diff/4;
						tempMap[nx][ny] -= diff/4;
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] += tempMap[i][j];
				// 온도 감소
				if(i == 0 || i == N-1 || j == 0 || j == M-1) {
					if(map[i][j] > 0) map[i][j]--;
				}
			}
		}
	}

	private static void clearVisited() {
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				visited[i][j] = false;
	}

	private static void spreadAir(int x, int y, int dir) {
		Deque<Pos> dq = new ArrayDeque<Pos>();
		int nx = x+mx[dir-1][0];
		int ny = y+my[dir-1][0];
		dq.add(new Pos(nx, ny, dir, 1));
		map[nx][ny] += 5; 
		visited[nx][ny] = true;
			
		while(!dq.isEmpty()) {
			Pos pos = dq.poll();
			
			if(pos.depth >= 5) continue;
			
			for(int d = 0; d<4; d++) {
				nx = pos.x + mx[dir-1][d];
				ny = pos.y + my[dir-1][d];
				if(!isRange(nx,ny) || visited[nx][ny] || isWall(pos.x, pos.y, nx, ny, dir)) continue;
				dq.add(new Pos(nx, ny, pos.dir, pos.depth+1));
				map[nx][ny] += 5 - pos.depth;
				visited[nx][ny] = true;
			}
		}
	}

	private static boolean isWall(int x, int y, int nx, int ny, int dir) {
		if(x == nx || y == ny) { // 상하좌우 이동 
			if(wall[x][y][nx][ny]) return true;
		} else { 
			if (dir == 3 || dir == 4) { // 상 또는 하 대각선 
				if(wall[x][y][x][ny] || wall[x][ny][nx][ny]) return true;	
			} else if (dir == 1 || dir == 2) { // 좌 또는 우 대각선 
				if(wall[x][y][nx][y] || wall[nx][y][nx][ny]) return true;
			}
		}
		return false;
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false: true;
	}
}
