import java.io.*;
import java.util.*;

public class Solution_D4_5604_구간합_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken()); // 5 A <= B
			long B = Long.parseLong(st.nextToken()); // 32

			long[] ans = new long[10];

			long point = 1; // 자리수에 따라 10씩 증가
			while (A <= B) {
				while (B % 10 != 9 && A <= B) { // 32 -> 29
					cal(B, ans, point); // ans[32], ans[31], ans[30] 에 point를 더한다.
					B--;
				}

				if (B < A) {
					break;
				}

				while (A % 10 != 0 && A <= B) { // 5 -> 10
					cal(A, ans, point); // ans[5], ans[6], ans[7], ans[8],ans[9] 에 point를 더한다.
					A++;
				}

				A /= 10; // 1
				B /= 10; // 2
				for (int i = 0; i < 10; i++) {
					ans[i] += (B - A + 1) * point;
				}
				point *= 10;
			}
			
			long sum = 0;
			for(int i=0; i<10; i++) {
				sum += (ans[i] * i);
			}
			
			System.out.println("#"+tc+" "+sum);
		}
	}

	private static void cal(long x, long[] ans, long point) {
		// 각 자리수를 point 만큼 더해줍니다.
		while (x > 0) {
			String s = String.valueOf(x);
			int xx = s.charAt(s.length() - 1) - '0';
			ans[xx] += point;
			x /= 10;
		}
	}
}
