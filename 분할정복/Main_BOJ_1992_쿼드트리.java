import java.io.*;
import java.util.*;

public class Main_BOJ_1992_쿼드트리 {

	private static int N;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static char[][] map;
	private static char c;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		solve(0, 0, N);
		bw.flush();
		bw.close();
	}

	private static boolean check(int x, int y, int n) {
		c= map[x][y];
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (map[i][j] != map[x][y])
					return false;
			}
		}
		return true;
	}

	private static void solve(int x, int y, int n) throws Exception {
			if(check(x, y, n)) {
				bw.append(c);
			} else {
				bw.append("(");
				int max = n / 2; 
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						solve(x + max * i, y + max * j, max);
					}
				}
				bw.append(")");
			}
	}
}