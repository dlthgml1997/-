import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapsackSudoCode {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] dp;
	static int N, K; // 배낭 개수, 최대 무게
	
	public static void main(String[] args) throws Exception {
		N = 7;
		K = 8;
		dp = new int[N+1][K+1]; // N번째 배낭에서 K무게까지 담을 수 있을 때 최대 가치 
		for(int i=1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // i번째 배낭 무게 
			int v = Integer.parseInt(st.nextToken()); // i번째 배낭 가치
			for(int j=1; j<=K; j++) { // 현재 담을 수 있는 무게
				// 담을 수 없으면 
				dp[i][j] = w > j ? dp[i-1][j] : Math.max(v + dp[i-1][j-w], dp[i-1][j]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
