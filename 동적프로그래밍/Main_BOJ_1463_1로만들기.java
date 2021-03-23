import java.util.*;
import java.io.*;

public class Main_BOJ_1463_1로만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] dp = new int[1000001];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		dp[0] = 0;
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		int value;
		for (int i = 4; i <= N; i++) {
			value = i - 1;
			dp[i] = dp[value] + 1;
			if (i % 3 == 0) {
				value = i / 3;
				dp[i] = Math.min(dp[value] + 1, dp[i]);
			}
			if (i % 2 == 0) {
				value = i / 2;
				dp[i] = Math.min(dp[value] + 1, dp[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
