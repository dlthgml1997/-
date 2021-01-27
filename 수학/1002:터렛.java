// 클래스 이름 Main으로 바꾸기

import java.util.*;

import java.io.*;

/**
 * solved 
 * https://www.acmicpc.net/problem/1002
 * @author leesohee 
 * 원의 겹치는 부분 구하기 (내접, 외접)
 */
public class Solution1002 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T;
	static int x1, y1, x2, y2;
	static double d;
	static int r1, r2;
	static int cnt;
	static int sumValue;
	static int subValue;

	public static void main(String[] args) throws IOException {
		T = stoi(br.readLine());
		for (int t = 1; t <= T; t++) {
			cnt = 0;
			input();
			d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); // 좌표 사이 거리
			sumValue = r1 + r2;
			subValue = Math.abs(r1 - r2);

			if (x1 == x2 && y1 == y2) { // 좌표가 같을 때

				if (r1 == r2) { // 원이 일치할 때
					cnt = -1;
				} else { // 큰원이 작은 원을 포함할 때 (동심원)
					cnt = 0;
				}

			} else { // 좌표가 다를 때

				// 큰원 반지름(r1 또는 r2중 큰 값)에서 작은 반지름(작은 값을) 뺀 값이 좌표 사이 거리와 같으면 내접
				if (subValue == d)
					cnt = 1;
				else if (sumValue > d && subValue < d)
					cnt = 2; // 반지름 둘을 더한 값이 좌표사이 거리 값보다 클 때
				else if (sumValue < d)
					cnt = 0; // 아예 동떨어져 있을 때
				else if (sumValue == d)
					cnt = 1; // 외접
			}

			System.out.println(cnt);
		}
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		x1 = stoi(st.nextToken());
		y1 = stoi(st.nextToken());
		r1 = stoi(st.nextToken());
		x2 = stoi(st.nextToken());
		y2 = stoi(st.nextToken());
		r2 = stoi(st.nextToken());
	}

	public static int stoi(String input) {
		return Integer.valueOf(input);
	}
}