import java.io.*;
import java.util.*;

public class Main_BOJ_1149_RGB거리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3]; // n 번 집에 r,g,b 를 칠했을 때의 비용 (기본 값)
		int[][] dp = new int[N][3]; // n 번 집에 r,g,b 를 칠했을 때의 최솟 값
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dp[0][0] = rgb[0][0];
		dp[0][1] = rgb[0][1];
		dp[0][2] = rgb[0][2];
		for(int i=1; i< N; i++) {
			for(int j=0; j<3; j++) {
				for(int k=0; k<3; k++) {
					if(j != k) {
						dp[i][k] = Math.min(dp[i][k], dp[i - 1][j] + rgb[i][k]);
					}
				}
			}
		}
		
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}
}
