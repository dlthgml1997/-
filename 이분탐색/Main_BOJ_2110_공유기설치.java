import java.io.*;
import java.util.*;

public class Main_BOJ_2110_공유기설치 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static int[] xi;
	static int maxNum = 0;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		xi = new int[N];
		for (int i = 0; i < N; i++) {
			xi[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(xi);
		int answer = 0;
		int min = 1; // 공유기 간 최소 거리
		int max = xi[N - 1] - 1; // 공유기 간 최대 거리

		while (min <= max) {
			int mid = (min + max) / 2;
			int prev = xi[0];
			int cnt = 1;

			for (int i = 0; i < N; i++) {
				if (xi[i] - prev >= mid) {
					cnt++;
					prev = xi[i];
				}
			}

			if (cnt < K) {
				max = mid - 1;
			} else {
				min = mid + 1;
				answer = mid;
			}
		}

		System.out.println(answer);
	}
}
