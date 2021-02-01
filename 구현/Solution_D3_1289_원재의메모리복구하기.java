// https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
import java.io.*;
import java.util.StringTokenizer;

public class Solution_D3_1289_원재의메모리복구하기 {
	static int T;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int count = 0;
			char pre = '0';
			for (char n: br.readLine().toCharArray()) {
				if (n != pre) {
					count++;
					pre = n;
				}
			}
			System.out.printf("#%d %d\n", t, count);
		}
	}
}
