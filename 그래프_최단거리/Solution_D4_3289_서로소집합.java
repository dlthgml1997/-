import java.util.*;
import java.io.*;

public class Solution_D4_3289_서로소집합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] parent;
	static int T, size, M;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		int oper, a, b;
		for (int t = 1; t <= T; t++) {
			bw.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[size + 1];
			for(int i=0; i<= size; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				oper = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if (oper == 0)
					union(a, b);
				if (oper == 1) {
					if (findSet(a) == findSet(b)) {
						bw.append(1 + "");
					} else {
						bw.append(0 + "");
					}
				}
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

	private static int findSet(int x) {
		if (parent[x] == x)
			return x;
		else
			return parent[x] = findSet(parent[x]);
	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);

		if (a != b) {
			if (a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
		}
	}
}
