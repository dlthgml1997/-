import java.util.*;
import java.io.*;

public class Solution_1868_파핑파핑지뢰찾기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, C;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			int answer = 0;
			for(int i=0; i<N; i++) {
				for(int j=0;j<N; j++) {
					if(map[i][j] == '.' && findBomb(i, j) == '0') {
						dfs(i, j);
						map[i][j] = '0';
						answer++;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '.') {
						answer++;
					}
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static void dfs(int x, int y) {
		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(new int[] {x,y});
		
		while(!deque.isEmpty()) {
			int[] pos = deque.pollLast();
			int xx = pos[0]; int yy = pos[1];
			char bomb;
			for(int i=0; i<8; i++) {
				int nx = xx + dx[i];
				int ny = yy + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if(map[nx][ny] != '.') continue;
				bomb = findBomb(nx, ny);
				
				if(bomb == '0') { // 주변에 지뢰 없음 
					deque.add(new int[] {nx, ny});
					map[nx][ny] = bomb;
				} else {
					map[nx][ny] = bomb;
				}
			}
		}
	}

	private static char findBomb(int x, int y) {
		int bomb = 0;
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(map[nx][ny] == '*')
				bomb++;
		}
		return String.valueOf(bomb).charAt(0);
	}
}
