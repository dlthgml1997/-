import java.io.*;
import java.util.*;

public class Solution_1767_프로세서연결하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static ArrayList<int[]> list; // 가장자리 코어는 담지 않는다. (isBorder 로 확인)
	static int[][] map;
	static int max, min, totalCnt, N;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input();
			go(0, 0);
			System.out.println("#" + t + " " + min);
		}
	}

	private static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<int[]>();
		max = 0;
		min = Integer.MAX_VALUE;
		totalCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (isBorder(i, j)) continue;
				if (map[i][j] == 1) {
					list.add(new int[] { i, j });
					totalCnt++;
				}
			}
		}
	}

	private static void go(int index, int cCnt) { // index: 부분집합에 고려할 코어 인덱스, cCnt : 라인개수
		if (index == totalCnt) {
			int res = getLength();
			if (max < cCnt) {
				max = cCnt;
				min = res;
			} else if (max == cCnt) {
				if (res < min)
					min = res;
			}
			return;
		}

		// 코어 선택 전선 놓아보기
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		for (int d = 0; d < 4; d++) {
			if (createLine(r, c, d)) {
				// 전선 놓기
				setStatus(r, c, d, 2);
				// 다음 코어로 넘어가기
				go(index + 1, cCnt + 1);
				// 놓았던 전선 되돌려 놓기
				setStatus(r, c, d, 0);
			}
		}
		// 코어 비선택
		go(index + 1, cCnt);

	}

	private static int getLength() {
		int lCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					lCnt++;
				}
			}
		}
		return lCnt;
	}

	private static void setStatus(int r, int c, int d, int s) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dx[d];
			nc += dy[d];
			if(!isRange(nr, nc)) break;
			map[nr][nc] = s;
		}
	}

	private static boolean createLine(int r, int c, int d) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dx[d];
			nc += dy[d];
			if(!isRange(nr, nc)) break;
			if (map[nr][nc] >= 1)
				return false;
		}
		return true;
	}

	private static boolean isRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N ? false : true;
	}

	static boolean isBorder(int x, int y) {
		return x == 0 || y == 0 || x == N - 1 || y == N - 1 ? true : false;
	}
}
