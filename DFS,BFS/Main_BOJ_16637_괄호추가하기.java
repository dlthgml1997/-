import java.io.*;
import java.util.*;

public class Main_BOJ_16637_괄호추가하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int total;
	static char[] line;
	static int N;
	static int maxNum = Integer.MIN_VALUE;
	static boolean zeroFlag = false;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		line = br.readLine().toCharArray();
		if (N == 1) {
			System.out.println(line[0] - '0');
			return;
		}

		dfs(line[0] - '0', 1); // total , idx
		if (zeroFlag) {
			System.out.println(0);
		} else {
			System.out.println(maxNum);
		}
	}

	private static void dfs(int su1, int idx) {
		if (idx == N) {
			maxNum = Math.max(maxNum, su1);
			return;
		}
		
		int value = operation(su1, line[idx], line[idx+1]-'0');
		dfs(value, idx+2);
		if(idx+3 <= N-1) {
			int value2 = operation(line[idx+1]-'0', line[idx+2], line[idx+3] - '0');
			dfs(operation(su1, line[idx], value2), idx+4);
		}
	}

	private static int operation(int su1, char oper, int su2) {
		switch (oper) {
		case '-':
			return su1 - su2;
		case '+':
			return su1 + su2;
		case '*':
			return su1 * su2;
		default:
			return -1;
		}
	}
}
