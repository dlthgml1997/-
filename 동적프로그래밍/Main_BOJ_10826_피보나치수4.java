import java.io.*;
import java.math.*;

public class Main_BOJ_10826_피보나치수4 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BigInteger[] dp;
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		dp = new BigInteger[n+1];
		if(n == 0 || n == 1) {
			System.out.println(n);
			return;
		}
		dp[0]=BigInteger.ZERO;
		dp[1]=BigInteger.ONE;
		dp[2]=BigInteger.ONE;
		for(int i=3; i<=n; i++) {
			dp[i] = dp[i-1].add(dp[i-2]);
		}
		System.out.println(dp[n]);
	}
}
