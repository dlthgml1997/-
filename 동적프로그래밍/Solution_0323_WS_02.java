public class Solution_0323_WS_02 {
	static int[] dp;
	public static void main(String[] args) {
		dp= new int[7];
		dp[1] = 2;
		dp[2] = 5;
		for(int i=3; i<=6; i++) {
			dp[i] = dp[i-2] + dp[i-1] * 2;
		}
		System.out.println(dp[6]);
	}
}
