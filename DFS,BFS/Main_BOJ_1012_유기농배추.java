import java.io.*;
import java.util.*;

// 30mins

public class Main_BOJ_1012_유기농배추 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, L;
	static char[][] farm;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Loc> queue = new LinkedList();

	public static void main(String[] args) throws Exception {
		int T = stoi(br.readLine());
		for (int t = 0; t < T; t++) {
			queue = new LinkedList();
			int answer = 0;
			input();
			
			for(int i =0; i< R; i++) {
				for(int j=0; j< C; j++) {
					if(farm[i][j] == 1) {
						check(i, j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}

	private static void check(int x, int y) {
			queue.add(new Loc(x, y));
			while (!queue.isEmpty()) {
				Loc now = queue.poll();
				x = now.x;
				y = now.y;
				farm[x][y] = 0;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!isRange(nx, ny) || farm[nx][ny] == 0)
						continue;
					queue.add(new Loc(nx, ny));
					farm[nx][ny] = 0;
				}
			}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C ? false : true;
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		L = stoi(st.nextToken());
		
		farm = new char[R][C];
		
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			farm[x][y] = 1;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}

class Loc {
	int x;
	int y;

	public Loc(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
