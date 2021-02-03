import java.io.*;
import java.util.*;

// 40mins

public class Main_BOJ_16956_늑대와양 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C;
	static char[][] farm;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Location> queue;
	static List<Location> wolf = new ArrayList();

	public static void main(String[] args) throws Exception {
		input();
		if (check()) {
			System.out.println(1);
			print();	
		}
		else
			System.out.println(0);
	}

	private static boolean check() {
		queue = new LinkedList();
		for (Location location : wolf) {
			int x = location.x;
			int y = location.y;
			queue.add(new Location(x, y));
			visited = new boolean[R][C];
			while (!queue.isEmpty()) {
				Location now = queue.poll();
				x = now.x;
				y = now.y;
				visited[x][y] = true;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (!isRange(nx, ny) || farm[nx][ny] == 'W' || farm[nx][ny] == 'D' || visited[nx][ny])
						continue;
					if (farm[nx][ny] == 'S') {
						return false;
					}
					queue.add(new Location(nx, ny));
				}
			}
		}
		return true;
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C ? false : true;
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		farm = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			farm[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (farm[i][j] == 'W')
					wolf.add(new Location(i, j));
				if (farm[i][j] == '.')
					farm[i][j] = 'D';
			}
		}
	}

	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(farm[i][j]);
			}
			System.out.println();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}

class Location {
	int x;
	int y;

	public Location(int x, int y) {
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
