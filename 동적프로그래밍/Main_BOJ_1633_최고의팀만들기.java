import java.io.*;
import java.util.*;

public class Main_BOJ_1633_최고의팀만들기 {
	static int[] white;
	static int[] black;
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		white = new int[1001];
		black = new int[1001];
		int i = 0;
		while(sc.hasNextInt()){
			white[i] = sc.nextInt();
			black[i] = sc.nextInt();
			i++;
		}
		dp = new int[1001][16][16];
		System.out.println(solution(0, 0, 0, i));
	}

	private static int solution(int i, int wi, int bi, int N) {
		if (wi == 15 && bi == 15)
			return 0;
		if (i == N)
			return 0;
		if (dp[i][wi][bi] != 0)
			return dp[i][wi][bi];

		int ans = solution(i + 1, wi, bi, N);
		if (wi < 15)
			ans = Math.max(ans, solution(i + 1, wi + 1, bi, N)+white[i]);
		if (bi < 15)
			ans = Math.max(ans, solution(i + 1, wi, bi + 1, N)+black[i]);

		dp[i][wi][bi] = ans;
		return dp[i][wi][bi];
	}
}
