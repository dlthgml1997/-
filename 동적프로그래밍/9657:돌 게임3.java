import java.util.*;
import java.io.*;

/**
 * solved 
 * https://www.acmicpc.net/problem/9657 
 * DP
 */

public class Solution9657 {
	static boolean[] dp;
	static BufferedReader br;
	static int rock;
	static int turn = 0;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		rock = Integer.valueOf(br.readLine());

		dp = new boolean[1001];
		dp[1] = dp[3] = dp[4] = true;
		dp[2] = false;

		for (int i = 5; i <= rock; i++) {
			dp[i] = !(dp[i - 1] && dp[i - 3] && dp[i - 4]);
		}

		if (dp[rock])
			System.out.println("SK");
		else
			System.out.println("CY");
	}
}