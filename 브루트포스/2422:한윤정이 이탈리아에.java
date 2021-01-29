import java.util.*;
import java.io.*;

/**
 * solved https://www.acmicpc.net/problem/2422
 * 한윤정이 이탈리아에 가서 아이스크림을 사먹는데
 * @author leesohee
 */
public class Main2422 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static boolean[][] noMix = new boolean[201][201];
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		input();
		for (int i = 1; i < N; i++) {
			getComb(1, -1, i);
		}
		System.out.println(answer);
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		int a,b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			noMix[a][b] = true;
			noMix[b][a] = true;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

	static void getComb(int depth, int pre, int now) {
		if (depth == 3) {
			answer++;
			return;
		}
		
		for (int i = now + 1; i <= N; i++) {
			if(noMix[i][now]) continue;
			if(pre != -1 && noMix[pre][i]) continue;
			getComb(depth + 1, now, i);
			
		}
	}

}
