import java.util.*;
import java.io.*;

public class Main_BOJ_1912_연속합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[] X;
	static int maxNum = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
			N = stoi(br.readLine());
			X = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = stoi(st.nextToken());
			}
			maxNum = Integer.MIN_VALUE;
			maxNum = getMaxSubArr(0, N);
			bw.append(maxNum + "\n");
		bw.flush();
		bw.close();
	}

	private static int getMaxSubArr(int start, int end) {
		if (start == end-1) {
			return X[start];
		}
		int mid = (start + end) / 2;
		int sum = 0;
		int maxL = X[mid-1];
		int maxR = X[mid];
		for (int i = mid-1; i >= start; i--) {
			sum += X[i];
			maxL = Math.max(sum, maxL);
		}

		sum =0;
		for (int j = mid; j < end; j++) {
			sum += X[j];
			maxR = Math.max(maxR, sum);
		}

		int L = getMaxSubArr(start, mid);
		int R = getMaxSubArr(mid, end);
		return Math.max(L > R ? L : R, maxL + maxR);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
