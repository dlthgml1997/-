import java.util.*;
import java.io.*;

public class Main_BOJ_2458_키순서 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] minEdge;
	static int N, M;
	static int INF = 501;

	public static void main(String[] args) throws Exception {
		int answer = 0;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		minEdge = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(minEdge[i], INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			minEdge[from][to] = 1;
		}

		for (int k = 0; k < N; k++) { // 경유지
			for (int i = 0; i < N; i++) { // 출발지
				for (int j = 0; j < N; j++) { // 도착지
					if (k == i || i == j || k == j)
						continue;
					minEdge[i][j] = Math.min(minEdge[i][j], minEdge[i][k] + minEdge[k][j]);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < N; j++) {
				if (i == j || minEdge[i][j] != INF || minEdge[j][i] != INF)
					continue;
				flag = false;
				break;

			}
			if (flag)
				answer++;
		}
		System.out.println(answer);
	}
}
