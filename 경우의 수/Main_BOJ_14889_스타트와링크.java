import java.util.*;
import java.io.*;

public class Main_BOJ_14889_스타트와링크 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[] starts;
	static int[] link;
	static boolean[] select;
	static int N;
	static int minNum = Integer.MAX_VALUE, startTotal = 0, linkTotal = 0;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		starts = new int[N / 2];
		select = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combination(0, 0);
		System.out.println(minNum);
	}

	private static void combination(int cnt, int start) {
		if (cnt == N / 2) { // 선택되면 스타트
			select = new boolean[N];
			for (int i = 0; i < N / 2; i++) {
				select[starts[i]] = true;
			}
			getSub();
			return;
		}

		for (int i = start; i < N; i++) {
			starts[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	private static void getSub() {
		int linkTotal = 0;
		int startTotal = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (select[i] && select[j]) {
					linkTotal += map[i][j];
					linkTotal += map[j][i];
				}
				if (!select[i] && !select[j]) {
					startTotal += map[i][j];
					startTotal += map[j][i];
				}
			}
		}
		minNum = Math.min(minNum, Math.abs(startTotal - linkTotal));
	}
}
