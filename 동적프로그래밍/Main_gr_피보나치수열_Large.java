import java.io.*;
import java.util.*;

public class Main_gr_피보나치수열_Large {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());

		int[] dp = new int[401];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i <= input; i++) {
			dp[i] = dp[i - 1] % 10009 + dp[i - 2] % 10009;
		}
		int answer = dp[input] % 10009;
		System.out.println(answer);
	}
}
