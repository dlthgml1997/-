import java.util.*;
import java.io.*;

public class Main_BOJ_12865_평범한배낭 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] dp;
	static int N, K;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][K + 1];
		int w, v;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= K; j++) {
				if (j < w) {
					if (i == 1) {
						continue;
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				} else {
					if(i == 1) {
							dp[i][j] = v;
							continue;
					}
					
					if (v + dp[i-1][j - w] > dp[i - 1][j]) {
						dp[i][j] = v + dp[i-1][j-w];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
