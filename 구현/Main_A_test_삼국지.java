import java.io.*;
import java.util.*;

public class Main_A_test_삼국지 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// 소유 병력 보충
	static int[][] map, powerMap, fillMap, tempMap;
	static int N, answer;
	static boolean[] exist = new boolean[4];
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	static ArrayList<int[]> changeList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			input();
			simulation();
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void simulation() {
		boolean end = false;
		while (true) {
			for (int i = 1; i <= 3; i++) {
				if (exist[i]) {
					attack(i);
					tempToPowerMap();
					help(i);
					tempToPowerMap();
					fillPowerMap();
					answer = 0;
					if (isOne()) {
						end = true;
						break;
					}
				}
			}
			if(end) break;
		}
	}
	
	private static void attack(int c) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				if (map[i][j] != c) {
					int sum = 0;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (!isRange(nx, ny))
							continue;
						if (map[nx][ny] == c)
							sum += powerMap[nx][ny];
					}

					if (powerMap[i][j] * 5 < sum) {
						sum = 0;
						for (int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];

							if (!isRange(nx, ny))
								continue;
							if (map[nx][ny] == c) {
								sum += powerMap[nx][ny] / 4;
								tempMap[nx][ny] -= powerMap[nx][ny] / 4;
							}
						}
						
						changeList.add(new int[] {i, j, c, sum-powerMap[i][j]});
//						powerMap[i][j] = sum - powerMap[i][j];
					}
				}
			}
		}
	}

	private static void help(int c) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				if (map[i][j] == c) {
					boolean surrounded = true;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (!isRange(nx, ny) || map[nx][ny] == 0)
							continue;
						if (map[nx][ny] != c) {
							surrounded = false;
						}
					}

					// nx ny -> 나눠주려는 곳 
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (!isRange(nx, ny))
							continue;
						if (map[nx][ny] == c) {
							if (surrounded) { 
								tempMap[nx][ny] += powerMap[i][j] / 5; // 1/5 주고 
								tempMap[i][j] -= powerMap[i][j] / 5; // 내 1/5 사라지고 
							} else {
								if (powerMap[i][j] > powerMap[nx][ny] * 5) {
									tempMap[nx][ny] += powerMap[i][j] / 5; 
									tempMap[i][j] -= powerMap[i][j] / 5; 
								}
							}
						}
					}
				}
			}
		}
	}

	private static void tempToPowerMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				powerMap[i][j] += tempMap[i][j];
				tempMap[i][j] = 0;
			}
		}
 
		for(int i=0; i<changeList.size(); i++) {
			int x =changeList.get(i)[0];
			int y =changeList.get(i)[1];
			int p = changeList.get(i)[3];
			map[x][y] = changeList.get(i)[2];
			powerMap[x][y] = p;
		}
		changeList.clear();
	}

	private static void fillPowerMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				powerMap[i][j] += fillMap[i][j];
			}
		}
	}
	
	private static boolean isOne() {
		Arrays.fill(exist, false);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				exist[map[i][j]] = true;
				answer += powerMap[i][j];
			}
		}
		int cnt = 0;
		for (int i = 1; i <= 3; i++) {
			if (exist[i])
				cnt++;
		}
		return cnt == 1 ? true : false;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= N ? false : true;
	}

	private static void input() throws Exception {
		map = new int[N][N];
		powerMap = new int[N][N];
		fillMap = new int[N][N];
		visited = new boolean[N][N];
		tempMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				exist[map[i][j]] = true;
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				powerMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				fillMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}
}
