import java.util.*;
import java.io.*;

public class Solution_2115_벌꿀채취 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, C;
	static int[][] map;
	static boolean[][] visited;
	static int maxNum = 0;

	private static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#"+tc+" "+combination());
		}
	}

	private static int combination() {
		int result = 0;
		int max1 = 0;
		int max2 = 0;
		for(int i=0; i<N; i++) {
			for(int j =0; j<= N-M; j++) {
				maxNum = 0;
				getMaxHoney(i, j, 0, 0, 0); // x좌표, y좌표, 채취한 꿀의 양, 벌통의 개수
				max1 = maxNum;
				maxNum = 0;
				max2 = 0;
				for(int j2= j+M; j2<=N-M; j2++) {
					getMaxHoney(i, j2, 0, 0, 0);
					max2 = Math.max(max2, maxNum);
				}
				
				maxNum = 0;
				for(int i2= i+1; i2<N; i2++) {
					for(int j2 = 0; j2<=N-M; j2++) {
						getMaxHoney(i2,j2,0,0,0);
						max2 = Math.max(max2, maxNum);
					}
				}
				result = Math.max(result, max1 + max2);
			}
		}
		return result;
	}

	private static void getMaxHoney(int i, int j, int cnt, int sum, int benefit) {
		if (sum > C) return;
		if(cnt == M) {
			if(maxNum < benefit) maxNum = benefit;
			return;
		}
		
		getMaxHoney(i, j+1, cnt+1, sum+ map[i][j], benefit + map[i][j] * map[i][j]);
		getMaxHoney(i, j+1, cnt+1, sum, benefit);
		
	}
}
