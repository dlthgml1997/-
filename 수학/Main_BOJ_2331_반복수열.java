import java.io.*;
import java.util.*;

public class Main_BOJ_2331_반복수열 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int A;
	static int P;
	static List<Integer> D = new ArrayList();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		D.add(A);
		makeD(A, P);
		System.out.println(D.size()-1);
	}

	private static void makeD(int a, int p) {
		while (true) {
			int total = 0;
			while (a > 0) {
				total += Math.pow(a % 10, p);
				a /= 10;
			}

			if (D.contains(total)) {
				for (int i = D.size() - 1; i >= 0; i--) {
					if (D.get(i) == total) {
						break;
					}
					D.remove(i);
				}
				break;
			}
			D.add(total);
			a = total;
		}
	}
}
