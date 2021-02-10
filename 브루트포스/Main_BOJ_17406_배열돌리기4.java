import java.util.*;
import java.io.*;

public class Main_BOJ_17406_배열돌리기4 {
	// input
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] map;
	static int[][] mapClone; // 순열의 경우의 수가 달라질 때마다 map을 원상태로 복구하기 위한 mapClone
	static int N, M, R; // N행 M열 , 회전 연산 개수 R개
	static int r, c, s; // 회전 연산 (r,c,s)

	// rotate
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	// isRange (rotate시, 범위를 넘어가는지 확인)
	static int limitX, limitY; // 초기 값은 r+s , c+s 이다.

	// permutation
	static boolean[] isSelected;
	static List<RotateInfo> rList = new ArrayList<>(); // 초기 회전 연산을 담아두는 List
	static RotateInfo[] rotates; // 순열에서 회전연산의 경우의 수가 담기는 배열
	static int minNum = 5000; // 한 행의 합의 최댓 값(=100 * 50)으로 초기화

	private static class RotateInfo { // 회전연산을 담는 클래스
		int r;
		int c;
		int s;

		public RotateInfo(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	public static void main(String[] args) throws Exception {
		input();
		
		rotates = new RotateInfo[R];
		isSelected = new boolean[R];
		permutation(0);
		
		bw.append(minNum + "");
		bw.flush();
		bw.close();
	} // main

	private static void permutation(int cnt) throws Exception {
		if (cnt == R) {
			for (RotateInfo rInfo : rotates) {
				s = rInfo.s;
				r = rInfo.r;
				c = rInfo.c;
				limitX = r + s; // 자른 배열의 제일 오른쪽 아래 인덱스
				limitY = c + s;
				r = r-s; // 최초 진입 x(자른 배열의 제일 왼쪽 위 인덱스)
				c = c-s; // 최초 진입 y
				for (int i = 0; i < s; i++) {
					rotate(r, c);
					c++;
					r++;
					limitX--;
					limitY--;
				}
			}
			minNum = Math.min(getTotal(), minNum);
			returnOriginalMap();
			return;
		}
		
		for (int i = 0; i < R; i++) {
			if (!isSelected[i]) {
				rotates[cnt] = rList.get(i);
				isSelected[i] = true;
				permutation(cnt + 1);
				isSelected[i] = false;
			}
		}
	} // permutation

	private static void rotate(int x, int y) {
		int i = 0;
		int firstX = x;
		int firstY = y;
		int temp = map[x][y];
		while (i < 4) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!isRange(nx, ny)) {
				i++;
				continue;
			}
			int t = map[nx][ny];
			map[nx][ny] = temp;
			temp = t;
			x = nx;
			y = ny;
			if (nx == firstX && ny == firstY)
				break;
		}
	} // rotate

	private static boolean isRange(int x, int y) {
		boolean answer = x < r || x > limitX || y < c || y > limitY ? false : true;
		return answer;
	} // isRange
	
	public static int getTotal() throws Exception {
		int total = 0;
		int preNum = 5000;
		for (int i = 1; i <= N; i++) {
			total = 0;
			for (int j = 1; j <= M; j++) {
				total += map[i][j];
			}
			total = Math.min(total, preNum); // 행의 합의 최솟값 
			preNum = total;
		}
		return total;
	} // getTotal
	
	private static void returnOriginalMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = mapClone[i][j];
			}
		}
	} // returnOriginMap

	public static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		
		map = new int[N + 1][M + 1];
		mapClone = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int input = stoi(st.nextToken());
				map[i][j] = input;
				mapClone[i][j] = input;
			}
		}
		
		for (int k = 0; k < R; k++) {
			st = new StringTokenizer(br.readLine());
			rList.add(new RotateInfo(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken())));
		}
	} // input

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

	public static int stoi(String input) {
		return Integer.parseInt(input);
	} // stoi
}
