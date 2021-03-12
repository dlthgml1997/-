import java.util.*;
import java.io.*;

public class Main_BOJ_14502_연구소 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map, mapClone;
	static int N, M, maxNum= 0;
	static int[] combList = new int[3]; // 조합으로 생성할 safe의 좌표 3개 (0을 1로 바꿀 후보)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<Pos> safe = new ArrayList<Pos>(); // 0의 좌표
	static Deque<Pos> virus = new ArrayDeque<Pos>(); // 2의 좌표
	static boolean flag = false;
	private static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		mapClone = new int[N][M];
		int value;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				mapClone[i][j] = value;
				if(map[i][j] == 0) {
					safe.add(new Pos(i, j));
				}
			}
		}
		
		combination(0, 0);
		System.out.println(maxNum);
	}

	private static void combination(int cnt, int start) {
		if(cnt == 3) {
			mapReset(); // 배열 초기화
			for(int i: combList) { 
				map[safe.get(i).x][safe.get(i).y]= 1; 
			}
			spreadVirus();
			maxNum = Math.max(maxNum, cntZero());
			return;
		}

		for(int i= start; i< safe.size(); i++) {
			combList[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	private static void mapReset() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = mapClone[i][j];
				if(map[i][j] == 2) {
					virus.add(new Pos(i, j));
				}
			}
		}
	}
	
	private static void spreadVirus() {
		int nx, ny;
		Pos pos;
		while(!virus.isEmpty()) {
			pos = virus.poll();
			for(int i=0; i<4; i++) {
				nx = pos.x + dx[i];
				ny = pos.y + dy[i];
				if(!isRange(nx, ny) || map[nx][ny] > 0) continue;
				map[nx][ny] = 2;
				virus.add(new Pos(nx, ny));
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
	
	private static int cntZero() {
		int cnt =0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}
