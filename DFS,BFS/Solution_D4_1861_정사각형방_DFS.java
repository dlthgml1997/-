import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D4_1861_정사각형방_DFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N;
	static int[][] rooms;
	static Stack<Pos> stack;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		T = stoi(br.readLine());
		for (int t = 1; t <= T; t++) {
			int maxNum = 0;
			int roomNum = 1000000;
			input();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dp[i][j] == -1) {
						dp[i][j] = 0;
						int num = move(i, j);
						if(num> maxNum) {
							maxNum = num;
							roomNum = rooms[i][j];
						} else if(num == maxNum) {
							roomNum = Math.min(roomNum, rooms[i][j]);
						}
					}
				}
			}
			bw.append("#" + t + " " + roomNum + " " + maxNum + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int move(int i, int j) {
		stack.clear();
		stack.add(new Pos(i, j));
		dp[i][j] = 1;
		while (!stack.isEmpty()) {
			Pos loc = stack.pop();
			int x = loc.x;
			int y = loc.y;
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (!isRange(nx, ny) || rooms[nx][ny] - rooms[x][y] != 1)
					continue;
				stack.add(new Pos(nx, ny));
				dp[nx][ny] = 0;
				dp[i][j]++;
			}
		}
		return dp[i][j];
	}

	private static void input() throws Exception {
		N = stoi(br.readLine());
		rooms = new int[N][N];
		stack = new Stack<>();
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				rooms[i][j] = stoi(st.nextToken());
				dp[i][j] = -1;
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N ? false : true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}

class Location {
	int x;
	int y;

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

	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
