import java.util.*;
import java.io.*;

public class Main_BOJ_16935_배열돌리기3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] mapClone;
	static int[][] map;
	static int N, M, R;

	public static void main(String[] args) throws Exception {
		input();
		st = new StringTokenizer(br.readLine());
		for (int r = 0; r < R; r++) {
			move(stoi(st.nextToken()));
		}
		print();
	} // main

	public static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		map = new int[N][M];
		mapClone = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int input = stoi(st.nextToken());
				map[i][j] = input;
				mapClone[i][j] = input;
			}
		}
	} // input

	public static int stoi(String input) {
		return Integer.parseInt(input);
	} // stoi

	private static void move(int n) {
		switch (n) {
		case 1:
			reverseUpDown();
			break;
		case 2:
			reverseLeftRight();
			break;
		case 3:
			rotate90Right();
			break;
		case 4:
			rotate90Left();
			break;
		case 5:
			rotateGroupClock();
			break;
		case 6:
			rotateGroupAntiClock();
			break;
		}
	} // move

	private static void reverseUpDown() {
		int x = N - 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapClone[x][j];
			}
			x--;
		}
		cloneMap();
	} // reverseUpDown

	private static void reverseLeftRight() {
		int y = M - 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapClone[i][y--];
			}
			y = M - 1;
		}
		cloneMap();
	} // reverseLeftRight

	private static void rotate90Right() {
		int tempN = N;
		map = new int[M][N];
		int indexR = N - 1;
		for (int i = 0; i < N; i++) { 
			for (int j = 0; j < M; j++) { 
				map[j][i] = mapClone[indexR][j];
			}
			indexR--;
		}
		mapClone = new int[M][N];
		cloneMap();
		N = M;
		M = tempN;
	} // rotete90Right

	private static void rotate90Left() {
		int tempN = N;
		map = new int[M][N];
		int indexC = M - 1;
		for (int i = 0; i < N; i++) { 
			for (int j = 0; j < M; j++) { 
				map[j][i] = mapClone[i][indexC--];
			}
			indexC = M - 1;
		}
		mapClone = new int[M][N];
		cloneMap();
		N = M;
		M = tempN;
	} // rotate90Left

	private static void rotateGroupClock() {
		moveGroup(0, 0, 0, M / 2);
		moveGroup(0, M / 2, N / 2, M / 2);
		moveGroup(N / 2, M / 2, N / 2, 0);
		moveGroup(N / 2, 0, 0, 0);
		cloneMap();
	} // rotateGroupClock

	private static void rotateGroupAntiClock() {
		moveGroup(0, 0, N / 2, 0);
		moveGroup(N / 2, 0, N / 2, M / 2);
		moveGroup(N / 2, M / 2, 0, M / 2);
		moveGroup(0, M / 2, 0, 0);
		cloneMap();
	} // rotateGroupAntiClock

	private static void moveGroup(int x, int y, int nx, int ny) {
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				map[nx + i][ny + j] = mapClone[x + i][y + j];
			}
		}
	} // moveGroup

	private static void cloneMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				mapClone[i][j] = map[i][j];
			}
		}
	} // cloneMap

	public static void print() throws Exception {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.append(map[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	} // print
}
