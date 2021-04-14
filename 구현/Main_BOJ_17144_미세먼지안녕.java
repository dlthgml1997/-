import java.io.*;
import java.util.*;

public class Main_BOJ_17144_미세먼지안녕 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[][] map, spreadMap;
	static int[] tx = {-1, 0, 1, 0};
	static int[] ty = {0, 1, 0, -1};
	static int[] bx = {1, 0, -1, 0};
	static int[] by = {0, 1, 0, -1}; 
	static int N, M, TIME;
	static int[] machineTop = new int[2], machineBottom = new int[2];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		spreadMap = new int[N][M];
		TIME = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(machineTop[0] == 0) {
						machineTop[0] = i; machineTop[1] = j;
					} else {
						machineBottom[0] = i; machineBottom[1] = j;
					}
				}
			}
		}
		int time = 0;
		while(true) {
			if(time == TIME) break;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] > 0) {
						spreadDust(i, j);
					}
				}
			}
			sumDust();
			machineStartTop();
			machineStartBottom();
			time++;
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -1) continue;
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}
	
	private static void spreadDust(int x, int y) {
		int dust = map[x][y] / 5;
		int total = 0;
		for(int i=0; i<4; i++) {
			int nx = x + tx[i];
			int ny = y + ty[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == -1) continue;
			total+= dust;
			spreadMap[nx][ny] += dust;
		}
		map[x][y] -= total;
	}

	private static void machineStartTop() {
		int x = machineTop[0];
		int y = machineTop[1];
		int nx;
		int ny;
		int dir = 0;
		while(true) {
			nx = x + tx[dir%4];
			ny = y + ty[dir%4];
			if(nx < 0 || nx > machineTop[0] || ny < 0 || ny >= M ) {
				dir++;
				nx = x + tx[dir%4];
				ny = y + ty[dir%4];
			} 
			if(nx == machineTop[0] && ny == machineTop[1]) {
				map[x][y] = 0;
				break;
			}
			map[x][y] = map[nx][ny];
			x = nx;
			y = ny;
		}
		map[machineTop[0]][machineTop[1]] = -1;
	}
	
	private static void machineStartBottom() {
		int x = machineBottom[0];
		int y = machineBottom[1];
		int nx;
		int ny;
		int dir = 0;
		while(true) {
			nx = x + bx[dir%4];
			ny = y + by[dir%4];
			if(nx < machineBottom[0] || nx >= N || ny < 0 || ny >= M) {
				dir++;
				nx = x + bx[dir%4];
				ny = y + by[dir%4];
			} 
			if(nx == machineBottom[0] && ny == machineBottom[1]) {
				map[x][y] = 0;
				break;
			}
			map[x][y] = map[nx][ny];
			x = nx;
			y = ny;
		}
		map[machineBottom[0]][machineBottom[1]] = -1;
		
	}
	private static void sumDust() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] += spreadMap[i][j];
				spreadMap[i][j] = 0;
			}
		}
	}

}
