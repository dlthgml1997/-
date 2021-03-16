import java.io.*;
import java.util.*;

public class Main_BOJ_2023_신기한소수 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, limit;
	static char[] startNums = { '2', '3', '5', '7' };
	static char[] oddNums = { '1', '3', '5', '7', '9' };

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		limit = (int) Math.pow(10, N);
		for (int i = 0; i < startNums.length; i++) {
			dfs(String.valueOf(startNums[i]), 1);
		}
		bw.flush();
		bw.close();
	}

	private static void dfs(String num, int cnt) throws Exception {
		if (cnt == N) {
			bw.append(num + "\n");
			return;
		}
		for (int i = 0; i < oddNums.length; i++) {
			if (!isPrime(Integer.parseInt(num + oddNums[i])))
				continue;
			dfs(num + oddNums[i], cnt + 1);
		}
	}

	private static boolean isPrime(int num) {
		for (int j = 2; j*2 < num; j++) {
			if (num % j == 0)
				return false;
		}
		return true;
	}

}
