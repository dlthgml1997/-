// 제출 시 package 빼기
// 클래스 이름 Main으로 바꾸기

import java.util.*;

import java.io.*;

/**
 * solved https://www.acmicpc.net/problem/1018
 * 체스판 다시 칠하기
 * @author leesohee
 */
public class Solution1018 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static char[][] chess;
	static int minNum = 64;

	public static void main(String[] args) throws IOException {
		input();
		if (N == 8 && M == 8) {
			System.out.println(getPaintCount(0, 0));
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i + 7 < N && j + 7 < M) {
					minNum = Math.min(minNum, getPaintCount(i, j));
				}
			}
		}
		
		System.out.println(minNum);
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		chess = new char[N][M];
		for (int i = 0; i < N; i++) {
			chess[i] = br.readLine().toCharArray();
		}
	}

	static int getPaintCount(int x, int y) {
		int count = 0;
		for (int i = x; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0 && chess[i][j] == 'W')
						count++;
					else if (j % 2 != 0 && chess[i][j] == 'B')
						count++;
				} else {
					if (j % 2 == 0 && chess[i][j] == 'B')
						count++;
					else if (j % 2 != 0 && chess[i][j] == 'W')
						count++;
				}
			}
		}
		return Math.min(count, 64 - count);
	}
}
