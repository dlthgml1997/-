import java.util.*;
import java.io.*;

public class Main_BOJ_2629_양팔저울 { // class 시작
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 한줄 받아오기
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 위한 변수
	static StringTokenizer st; // 입력을 받아와서 공백 기준으로 분리하기 위한 변수
	static int N, M; 
	static int[] weights;
	static int[] check;
	static boolean[] dp = new boolean[40001];
	static int max = 0;

	public static void main(String[] args) throws Exception { // main 시작
		N = Integer.parseInt(br.readLine()); // 추의 개수를 받아온다.
		st = new StringTokenizer(br.readLine());
		int value;
		weights = new int[N];
		for (int i = 0; i < N; i++) {
			value = Integer.parseInt(st.nextToken());
			max = Math.max(value, max);
			weights[i] = value;
			boolean[] temp = new boolean[40001];
			int maxTemp = max;
			for (int n = 0; n <= max; n++) {
				if (dp[n]) {
					temp[n + value] = true;
					temp[Math.abs(value - n)] = true;
					maxTemp = Math.max(maxTemp, n + value);
				}
			}
			max = maxTemp;
			for (int j = 0; j <= max; j++) {
				if (temp[j])
					dp[j] = true;
			}
			dp[value] = true;
		}

		M = Integer.parseInt(br.readLine());
		check = new int[M];
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			if (dp[Integer.parseInt(st.nextToken())]) {
				bw.append("Y ");
			} else {
				bw.append("N ");
			}
			
		} 
		bw.flush();
		bw.close();
	} // main 끝
}// class 끝
