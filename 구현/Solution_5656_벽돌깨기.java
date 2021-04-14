import java.io.*;
import java.util.*;

public class Solution_5656_벽돌깨기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map, mapCopy;
	static int[] dx = { 0, -1, 0, 1 }; // 왼, 상 , 우, 하
	static int[] dy = { -1, 0, 1, 0 };
	static int[] gameTurn;
	static boolean[] visited;
	static int balls, M, N, minNum = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			balls = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			mapCopy = new int[N][M];

			gameTurn = new int[balls];
			visited = new boolean[M];

			minNum = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int value = Integer.parseInt(st.nextToken());
					map[i][j] = value;
					mapCopy[i][j] = value;
				}
			}

			permutation(0);
			System.out.println("#" + tc + " " + minNum);
		}
	}

	private static void permutation(int cnt) {
		if (cnt == balls) {
			// 공 게임시작
			gameStart();
			// 최소 남은개수 갱신
			minNum = Math.min(minNum, cntBox());
			// 맵 돌려놓기
			copyMap();
			return;
		}

		for (int i = 0; i < M; i++) {
			gameTurn[cnt] = i;
			permutation(cnt + 1);
		}
	}

	private static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapCopy[i][j];
			}
		}
	}

	private static int cntBox() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static void gameStart() {
		int h = N - 1;
		for (int i = 0; i < balls; i++) {
			int turn = gameTurn[i];
			for (int j = 0; j < N; j++) { // W는 고정 H만 변한다.
				if (map[j][turn] > 0) { // 타격할 벽돌 높이 정해짐.
					h = j;
					break;
				}
			}
			shotBall(h, turn);
			dropBoxToEmptyArea();
		}

	}

	private static void shotBall(int x, int y) {
		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(new int[] { x, y, map[x][y] });
		int nx, ny;

		while (!deque.isEmpty()) { // p <= power
			int[] pos = deque.poll();
			int power = pos[2];
			map[pos[0]][pos[1]] = 0;
			for (int p = 1; p < power; p++) {
				for (int i = 0; i < 4; i++) { // 왼상우하
					nx = pos[0] + dx[i] * p;
					ny = pos[1] + dy[i] * p;
					if (!isRange(nx, ny) || map[nx][ny] == 0)
						continue;
					if (map[nx][ny] > 0) {
						deque.add(new int[] { nx, ny, map[nx][ny] });
						map[nx][ny]= 0;
						continue;
					}
				}
			}
		}
	}

	private static void dropBoxToEmptyArea() {
		// 1. 큐
		// 2. bottom
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for (int j = 0; j < M; j++) { // 열
			for (int i = 0; i < N; i++) { // 행 채우고 
				if(map[i][j] > 0) {
					deque.add(map[i][j]);	
				}
			} 
			for(int i= N-1; i>= 0; i--) { // 비우고 
				if(deque.isEmpty()) {
					map[i][j] = 0;
				} else {
					map[i][j] = deque.pollLast();	
				}
			}
		}
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M ? false : true;

	}
}
