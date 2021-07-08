import java.util.*;
import java.io.*;

public class BOJ_Main_10211_Maximum_Subarray_DP {
	static int N;
	static int[] list, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			list = new int[N+1];
			dp = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<= N; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println(getMaxArraySum(N));
		}
		
		
	}
	private static int getMaxArraySum(int n) {
		int answer = Integer.MIN_VALUE;
		for(int i=1; i<= n; i++) {
			dp[i] = Math.max(list[i], dp[i-1] + list[i]);
			answer = Math.max(answer, dp[i]);
		}
		return answer;
	}
}

