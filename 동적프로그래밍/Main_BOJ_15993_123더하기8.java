import java.io.*;
import java.util.*;

public class Main_BOJ_15993_123더하기8 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static double[][] dp = new double[100001][2]; // 0 홀 1 짝 
	static int T, N;
	static int NUM = 1000000009;
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		dp[1][0] = 1; // 1
		dp[1][1] = 0; // 
		dp[2][0] = 1; // 2
		dp[2][1] = 1; // 1 1
		dp[3][0] = 2; // 1 1 1 / 3
		dp[3][1] = 2; // 1 2 / 2 1 

		for(int n=4; n<=100000; n++) {
			dp[n][1] = (dp[n-1][0] + dp[n-2][0] + dp[n-3][0]) % NUM;
			dp[n][0] = (dp[n-1][1] + dp[n-2][1] + dp[n-3][1]) % NUM;
		}
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			int answer1 = (int) dp[N][0];
			int answer2 = (int) dp[N][1];
			bw.append(answer1+" "+answer2+"\n");
		}
		bw.flush();
		bw.close();
	}
}