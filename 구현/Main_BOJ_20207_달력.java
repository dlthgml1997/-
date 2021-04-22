import java.util.*;
import java.io.*;

public class Main_BOJ_20207_달력 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] days = new int[365 + 1];
	static int maxDay = 0;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for (int d = start; d <= end; d++) {
				days[d]++;
			}
			if (end > maxDay)
				maxDay = end;
		}
		int cnt = 0;
		int sum = 0;
		int height = 0;
		for (int i = 1; i <= maxDay; i++) {
			if (days[i] == 0) { // 한 뭉텅스 끝
				sum += cnt * height;
				height = 0;
				cnt = 0;
				continue;
			}

			if (days[i] > height) {
				height = days[i];
			}
			cnt++;
		}
		sum += cnt * height;

		System.out.println(sum);
	}
}
