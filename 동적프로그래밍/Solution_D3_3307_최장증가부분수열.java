import java.util.*;
import java.io.*;

public class Solution_D3_3307_최장증가부분수열 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] nums, dp;
	static int T, N;
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			dp = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			int maxLen = 0;
			for(int i=0; i<N; i++) {
				dp[i] = 1;
				for(int j=0; j<i;j++) {
					if(nums[i] > nums[j] && dp[i] < dp[j]+1) {
						dp[i] = dp[j] +1;
					}
				}
				maxLen = Math.max(maxLen, dp[i]);
			}
			bw.append("#"+t+" "+maxLen+"\n");
		}
		bw.flush();
		bw.close();
	}
}
