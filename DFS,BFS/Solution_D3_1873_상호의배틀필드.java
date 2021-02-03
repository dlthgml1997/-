import java.io.*;
import java.util.StringTokenizer;

public class Solution_D3_1873_상호의배틀필드 {
	static int T, N, M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[][] maps;
	static char[] doing;
	static int[] dx = { 0, 0, -1, 1 }; // <>^v
	static int[] dy = { -1, 1, 0, 0 };
	static char[] dir = { '<', '>', '^', 'v' };
	static int nx, ny;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			flag = false;
			input();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (maps[i][j] == '<' || maps[i][j] == '>' || maps[i][j] == '^' || maps[i][j] == 'v') {
						startGame(i, j);
						flag = true;
						break;
					}
				}
				if (flag) break;
			}
			bw.append("#" + t + " ");
			print();
		}
		bw.flush();
		bw.close();
	}

	private static void startGame(int x, int y) throws IOException {
		int[] XY = { x, y };
		for (char d : doing) {
			switch (d) {
			case 'U': { // ^
				XY = move(2, XY[0], XY[1]);
				break;
			}
			case 'D': { // v
				XY = move(3, XY[0], XY[1]);
				break;
			}
			case 'L': { // <
				XY = move(0, XY[0], XY[1]);
				break;
			}
			case 'R': { // >
				XY = move(1, XY[0], XY[1]);
				break;
			}
			case 'S': {
				switch (maps[XY[0]][XY[1]]) {
				case '<':
					shoot(0, XY[0], XY[1]);
					break;
				case '>':
					shoot(1, XY[0], XY[1]);
					break;
				case '^':
					shoot(2, XY[0], XY[1]);
					break;
				case 'v':
					shoot(3, XY[0], XY[1]);
					break;
				}
				break;
			}
			}
		}
	}

	private static void shoot(int dir, int x, int y) {
		while (true) {
			nx = x + dx[dir];
			ny = y + dy[dir];
			if (!isRange(nx, ny) || maps[nx][ny] == '#')
				break;
			if (maps[nx][ny] == '*') {
				maps[nx][ny] = '.';
				break;
			}
			x = nx;
			y = ny;
		}
	}

	private static int[] move(int i, int x, int y) {
		nx = x + dx[i];
		ny = y + dy[i];
		maps[x][y] = dir[i];
		if (!isRange(nx, ny)) {
			return new int[] { x, y };
		}
		if (maps[nx][ny] == '.') {
			maps[nx][ny] = maps[x][y];
			maps[x][y] = '.';
			x = nx;
			y = ny;
		}
		return new int[] { x, y };
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new char[N][M];
		for (int i = 0; i < N; i++) {
			char[] newLine = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				maps[i][j] = newLine[j];
			}
		}
		br.readLine();
		doing = br.readLine().toCharArray();
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}

	private static void print() throws IOException {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.append(maps[i][j]);
			}
			bw.append("\n");
		}
	}
}
