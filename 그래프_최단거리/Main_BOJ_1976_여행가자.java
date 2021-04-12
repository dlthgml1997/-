import java.util.*;
import java.io.*;

public class Main_BOJ_1976_여행가자 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] capitals;
	static int[] plan;
	static int N, M;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine()); // 도시의 수
		M = Integer.parseInt(br.readLine()); // 여행계획에 속한 도시들의 수
		capitals = new int[N + 1];
		plan = new int[M];
		for (int i = 0; i <= N; i++) {
			capitals[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					if (i < j) {
						union(i, j);
					} else {
						union(j, i);
					}
				}
			}
		}
		boolean answer = true;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		int from = plan[0];
		for(int i=1; i<M; i++) {
			int to = plan[i];
			if(find(from) != find(to)) answer = false;
			from = plan[i];
		}

		if (answer) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static int find(int x) {
		if (x == capitals[x])
			return x;
		else
			return capitals[x] = find(capitals[x]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			if (a < b)
				capitals[b] = a;
			else
				capitals[a] = b;
		}
	}
}
