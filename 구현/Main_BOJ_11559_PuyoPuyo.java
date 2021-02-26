import java.io.*;
import java.util.*;

public class Main_BOJ_11559_PuyoPuyo {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int total = 0;
	static char[][] map = new char[12][6];
	static boolean[][] visited = new boolean[12][6];
	static ArrayList<int[]> list = new ArrayList();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Deque<int[]> deque = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean popFlag = false;
		while (true) {
			popFlag = false;
			
			for (int row = 0; row < 12; row++) {
				for (int i = 0; i < 6; i++) {
					if (visited[row][i]) {
						continue;
					}
					if (map[row][i] == '.') {
						continue;
					}
					if (isPop(row, i, map[row][i])) { // 터트릴수 있으면
						doPop(row, i); 
						popFlag = true;
						continue;
					}
				}
			}
			if (!popFlag) {
				System.out.println(total);
				return;
			}
			total++;
			move(); 
			list.clear();
			visited = new boolean[12][6];
		}
	}

	private static void move() {
		
		for(int i= 10; i>= 0; i--) {
			for(int j=5; j>= 0; j--) {
				if(map[i][j] != '.' && map[i+1][j] == '.') {
					for(int k =i; k < 11; k++) {
						if(map[k+1][j] != '.') break;
						map[k+1][j] = map[k][j];
						map[k][j] = '.';
					}
				}
			}
		}
	} 

	private static void doPop(int x, int y) {
		for (int i = 0; i < list.size(); i++) {
			int[] pos = list.get(i);
			map[pos[0]][pos[1]] = '.';
		}
	}

	// bfs
	private static boolean isPop(int x, int y, char c) {
		int colorCnt = 1;
		int nx, ny;
		deque.add(new int[] { x, y });
		list.add(new int[] { x, y });
		visited[x][y] = true;
		while (!deque.isEmpty()) {
			int[] pos = deque.poll();
			for (int i = 0; i < 4; i++) {
				nx = pos[0] + dx[i];
				ny = pos[1] + dy[i];
				if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6)
					continue;
				if (map[nx][ny] == c && !visited[nx][ny]) {
					colorCnt++;
					deque.add(new int[] { nx, ny });
					list.add(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
		if (colorCnt < 4) {
			list.clear();
			return false;
		}
		return true;
	}
}
