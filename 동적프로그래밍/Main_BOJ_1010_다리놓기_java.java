import java.util.*;
import java.io.*;

public class Main_BOJ_1010_다리놓기_java {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M;
	static int T;
	static long[][] dp= new long[31][31];
	
	public static void main(String[] args) throws Exception{
		T = stoi(br.readLine());
		for(int t=0; t< T; t++) {
			input();
				sb.append(combination(M,N)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static long combination(int n, int r) {
        if(dp[n][r] != 0) return dp[n][r];
		if(n == r) {
			dp[n][r] = 1; return 1;
		}
		if(n==0 || r==0) {
            return 0;
		}
		if(r == 1) {
			dp[n][r] = n; return n;
		}
		dp[n][r] = combination(n-1,r-1) + combination(n-1, r);
		return dp[n][r];
	}
	
	private static void input() throws Exception{
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
	}
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
