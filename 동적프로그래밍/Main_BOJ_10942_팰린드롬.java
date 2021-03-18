import java.io.*;
import java.util.*;

public class Main_BOJ_10942_팰린드롬 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] num;
	static int[][] dp; // 1 true, 2 false, 0 미방문
	static int N, M;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		num = new int[N + 1];
		dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = 1;
			if (i+1 <=N && num[i] == num[i + 1])
				dp[i][i + 1] = 1;
		}

		M = Integer.parseInt(br.readLine());
		int a, b, answer;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			answer = getDP(a, b) > 1 ? 0 : 1;
			bw.append(answer + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int getDP(int a, int b) {
		if (dp[a][b] > 0) {
			return dp[a][b];
		} else if (a -b ==1) {
			return dp[a][b] = num[a] == num[b] ? 1: 2; 
		}
		else {
			return dp[a][b] = getDP(a + 1, b - 1) == 1 && num[a] == num[b] ? 1 : 2;
		}
	}
}