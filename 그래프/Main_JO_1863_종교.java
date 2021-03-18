import java.util.*;
import java.io.*;

public class Main_JO_1863_종교 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m;
	static int parent[];
	static int cnt;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			parent[i] = i;
		}
		cnt = n;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		System.out.println(n);
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
			if (a > b) {
				parent[a] = b;
			} else {
				parent[b] = a;
			}
			cnt--;
		}
	}
}
