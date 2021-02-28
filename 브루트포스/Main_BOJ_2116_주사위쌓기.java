import java.util.*;
import java.io.*;

public class Main_BOJ_2116_주사위쌓기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] dices;
	static boolean[] isTop = new boolean[6];
	static int maxNum = 0;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0-5 1-3 2-4
		boolean[] visited = new boolean[7];
		for (int i = 0; i < 6; i++) {
			int pair = findPair(i);
			visited[pair] = true;
			if (!visited[i]) {
				doStack(dices[0][i], dices[0][pair], 0, 0);
				doStack(dices[0][pair], dices[0][i], 0, 0);
			}
		}
		System.out.println(maxNum);

	}

	// top, bottom은 인덱스가 아니라 주사위에 적힌 숫자
	private static void doStack(int top, int bottom, int cnt, int total) {
		int sideMax = 0;
		if (cnt == N - 1) {
			for (int i = 0; i < 6; i++) {
				if (dices[cnt][i] != top && dices[cnt][i] != bottom) {
					sideMax = Math.max(sideMax, dices[cnt][i]);
				}
			}
			total += sideMax;
			maxNum = Math.max(total, maxNum);
			return;
		}

		int topIdx = -1;
		for (int i = 0; i < 6; i++) {
			if (dices[cnt][i] != top && dices[cnt][i] != bottom) 
				sideMax = Math.max(sideMax, dices[cnt][i]);

			if (dices[cnt+1][i] == top) topIdx = i;
		}

		// 다음 주사위의 bottom은 이전 주사위의 top이 되어야 한다.(같은 수가 마주봐야 하기 때문)
		doStack(dices[cnt + 1][findPair(topIdx)], dices[cnt+1][topIdx], cnt + 1, total + sideMax);
	}

	// 0-5 1-3 2-4
	private static int findPair(int i) { // top 인덱스가 넘어오면, bottom 인덱스를 반환
		int pair = -1;
		switch (i) {
			case 0: pair = 5; break;
			case 5: pair = 0; break;
		
			case 1: pair = 3; break;
			case 3: pair = 1; break;
			
			case 2: pair = 4; break;
			case 4:	pair = 2; break;
		}
		return pair;
	}
}
