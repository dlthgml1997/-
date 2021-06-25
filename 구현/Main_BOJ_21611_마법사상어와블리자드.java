
// 9시 29분 시작
// 11시 34분 정답
import java.io.*;
import java.util.*;

public class Main_BOJ_21611_마법사상어와블리자드 {
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int[] mx = { 0, 1, 0, -1 }; // 구슬 움직일 때
	static int[] my = { -1, 0, 1, 0 };
	static int N, M;
	static int[][] map, magicInfos;
	static int[] sharkLoc = new int[2];
	static int[] bombedMarbles = new int[4]; // 폭발한 1,2,3 번 구슬 개수 카운트
	static int[] cntMarbles = new int[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharkLoc[0] = (N) / 2;
		sharkLoc[1] = (N) / 2;
		 
		map = new int[N][N];
		magicInfos = new int[M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[sharkLoc[0]][sharkLoc[1]] = -2;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			magicInfos[i][0] = Integer.parseInt(st.nextToken());
			magicInfos[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int m = 0; m < M; m++) { // 마법 횟수
			// 마법
			magic(m);
			
			// 구슬 이동
			moveMarbles();
			
			// 구슬 폭발, 이동 반복
			while(bomb()) {
				moveMarbles();
			}
			
			// 구슬 변하기
			changeBomb();
		}
		System.out.println(1 * bombedMarbles[1] + 2 * bombedMarbles[2] + 3 * bombedMarbles[3]);
	}

	private static void changeBomb() {
		Deque<Integer> marbles = new ArrayDeque<>();
		Deque<int[]> nearMarbles = new ArrayDeque<>();
		int n = 1, d = 0;
		int nx = sharkLoc[0], ny = sharkLoc[1];
		int px = sharkLoc[0], py = sharkLoc[1];
		boolean out = false;
		while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < n; j++) {
					nx += mx[d % 4];
					ny += my[d % 4];
					if (nx < 0 || ny < 0) {
						out = true;
						break;
					}
					cntMarbles[map[nx][ny]] += 1;
					
					if(map[nx][ny] != map[px][py] && map[px][py] != -2) {
						marbles.add(nearMarbles.size());
						marbles.add(map[px][py]);
						while(!nearMarbles.isEmpty()) {
							nearMarbles.poll();
						}
					} 
					if(map[nx][ny] == 0) {
						out = true;
						break;
					}
					nearMarbles.add(new int[] {nx, ny});
					px = nx;
					py = ny;
				}
				if(out) break;
				d++;
			}
			if(out) break;
			n++;
		}
		fillBomb(marbles);
	}

	private static void fillBomb(Deque<Integer> marbles) {
		int n = 1, d = 0;
		int nx = sharkLoc[0], ny = sharkLoc[1];
		boolean out = false;
		while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < n; j++) {
					nx += mx[d % 4];
					ny += my[d % 4];
					if (nx < 0 || ny < 0) {
						out = true;
						break;
					}
					if(!marbles.isEmpty()) {
						map[nx][ny] = marbles.poll();	
					}
				}
				if(out) break;
				d++;
			}
			if(out) break;
			n++;
		}
	}

	private static boolean bomb() {
		// 카운트 세기
		Deque<int[]> bombMarbleList = new ArrayDeque<>();
		int n = 1, d = 0;
		int nx = sharkLoc[0], ny = sharkLoc[1];
		int px = sharkLoc[0], py = sharkLoc[1];
		boolean out = false;
		boolean isBombed = false;
		while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < n; j++) {
					nx += mx[d % 4];
					ny += my[d % 4];
					if (nx < 0 || ny < 0) {
						out = true;
						break;
					}
					cntMarbles[map[nx][ny]] += 1;
					
					if(map[nx][ny] != map[px][py]) {
						// 마블 수를 세서 4개 이상이면 -1로 바꿔
						if(bombMarbleList.size() >= 4) {
							while(!bombMarbleList.isEmpty()) {
								int[] pos = bombMarbleList.poll();
								bombedMarbles[map[pos[0]][pos[1]]]++;
								map[pos[0]][pos[1]] = -1;
								isBombed = true;
							}
						} else {
							while(!bombMarbleList.isEmpty()) {
								bombMarbleList.poll();
							}
							
						}
					} 
					bombMarbleList.add(new int[] {nx, ny});
					px = nx;
					py = ny;
				}
				if(out) break;
				d++;
			}
			if(out) break;
			n++;
		}
		return isBombed;
	}

	private static void moveMarbles() {
		int n = 1, d = 0;
		int nx = sharkLoc[0], ny = sharkLoc[1];
		int px = sharkLoc[0], py = sharkLoc[1];
		boolean out = false, flag = false;
		while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < n; j++) {
					nx += mx[d % 4];
					ny += my[d % 4];
					if (nx < 0 || ny < 0) {
						out = true;
						break;
					}
					if (flag) {
						map[px][py] = map[nx][ny];
					}
					if (map[nx][ny] == -1) {
						flag = true;
					}
					px = nx;
					py = ny;
				}
				if (out)
					break;
				d++;
			}
			n++;
			if (out) {
				if (getEmptyCnt() == 0) {
					break;
				} else {
					n = 1;
					d = 0;
					nx = sharkLoc[0];
					ny = sharkLoc[1];
					flag = false;
					out = false;
				}
			}
		}
		
	}

	private static int getEmptyCnt() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1)
					cnt++;
			}
		}
		return cnt;
	}

	private static void magic(int m) {
		int nx = sharkLoc[0], ny = sharkLoc[1];
		for (int i = 0; i < magicInfos[m][1]; i++) {
			nx += dx[magicInfos[m][0]];
			ny += dy[magicInfos[m][0]];
			if (!isRange(nx, ny))
				break;
			map[nx][ny] = -1;
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N ? false : true;
	}
}
