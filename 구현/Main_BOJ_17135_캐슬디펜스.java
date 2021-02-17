import java.util.*;
import java.io.*;

public class Main_BOJ_17135_캐슬디펜스 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, D;
	static int[][] castle;
	static int[][] castleClone;
	static int score = 0;
	static int[][] playerPos = new int[3][2];
	static int[] pos1 = new int[2], pos2 = new int[2], pos3 = new int[2];

	public static void main(String[] args) throws Exception {
		input();
		combination(0, 0, new int[3]);
		System.out.println(score);
	}

	private static void combination(int cnt, int start, int[] player) { // 주어진 치킨 집 개수에서 M개를 골라내는 조합
		if (cnt == 3) {
			int i = 0;
			for (int n : player) {
				playerPos[i][0] = N;
				playerPos[i++][1] = n;
			}
			cloneMap();
			int newScore = game();
			score = Math.max(score, newScore);
			return;
		}

		for (int i = start; i < M; i++) {
			player[cnt] = i;
			combination(cnt + 1, i + 1, player);
		}
	}

	private static int game() {
		int newScore = 0;
		int count = 0;
		int num1 = Integer.MAX_VALUE, num2 = Integer.MAX_VALUE, num3 = Integer.MAX_VALUE;

		pos1[0] = 0;
		pos1[1] = 0;
		pos2[0] = 0;
		pos2[1] = 0;
		pos3[0] = 0;
		pos3[1] = 0;

		while (count < N) {
			for (int i = N - 1; i >= N - D; i--) {
				for (int j = 0; j < M; j++) {
					if (castle[i][j] == 1) {
						int value = Math.abs(playerPos[0][0] - i) + Math.abs(playerPos[0][1] - j);
						if (D >= value) {
							if (value < num1) {
								num1 = value;
								pos1[0] = i;
								pos1[1] = j;
							}
						}

						value = Math.abs(playerPos[1][0] - i) + Math.abs(playerPos[1][1] - j);
						if (D >= value) {
							if (value < num2) {
								num2 = value;
								pos2[0] = i;
								pos2[1] = j;
							}
						}

						value = Math.abs(playerPos[2][0] - i) + Math.abs(playerPos[2][1] - j);
						if (D >= value) {
							if (value < num3) {
								num3 = value;
								pos3[0] = i;
								pos3[1] = j;
							}
						}

					}
				}
			}

			if (pos1[0] == 0 && pos1[1] == 0) {

			} else {
				if (castle[pos1[0]][pos1[1]] != 0) {
					newScore++;
					castle[pos1[0]][pos1[1]] = 0;
				}
			}

			if (pos2[0] == 0 && pos2[1] == 0) {

			} else {
				if (castle[pos2[0]][pos2[1]] != 0) {
					newScore++;
					castle[pos2[0]][pos2[1]] = 0;
				}
			}

			if (pos3[0] == 0 && pos3[1] == 0) {

			} else {
				if (castle[pos3[0]][pos3[1]] != 0) {
					newScore++;
					castle[pos3[0]][pos3[1]] = 0;
				}
			}

			pos1[0] = 0;
			pos1[1] = 0;
			pos2[0] = 0;
			pos2[1] = 0;
			pos3[0] = 0;
			pos3[1] = 0;
			num1 = Integer.MAX_VALUE;
			num2 = Integer.MAX_VALUE;
			num3 = Integer.MAX_VALUE;

			move();
			count++;
		}

		return newScore;
	}

	private static void move() {
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				if (castle[i - 1][j] == 1) {
					castle[i][j] = 1;
				} else {
					castle[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			castle[0][i] = 0;
		}
	}

	private static void cloneMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				castle[i][j] = 0;
				if (castleClone[i][j] == 1) {
					castle[i][j] = 1;
				}
			}
		}
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		D = stoi(st.nextToken());
		if (D > N)
			D = N;
		castle = new int[N][M];
		castleClone = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int input = stoi(st.nextToken());
				castle[i][j] = input;
				castleClone[i][j] = input;
			}
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
