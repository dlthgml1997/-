import java.io.*;
import java.util.*;

public class Solution_D2_2001_파리퇴치 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, M;
	static int[][] maps;
	public static void main(String[] args) throws Exception {
		T = stoi(br.readLine());
		for(int t= 1; t<=T; t++) {
			int maxNum = 0;
			input();
			for(int i=0; i< N; i++) {
				for(int j=0; j<N; j++) {
					if(!isRange(i+M-1, j+M-1)) break;
					maxNum = Math.max(maxNum, cutAndSum(i, j));
				}
			}
			System.out.printf("#"+t+" "+maxNum+"\n");
		}
		
	}
	
	private static boolean isRange(int x, int y) {
		return x < 0 || x>=N || y <0 || y>=N ? false: true;
	}
	
	private static int cutAndSum(int i, int j) {
		int answer= 0;
		for(int x = i; x< i+M; x++) {
			for(int y = j; y< j+M; y++) {
				answer += maps[x][y];
			}
		}
		return answer;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		maps = new int[N][N];
		for(int i =0 ; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N; j++) {
				maps[i][j] = stoi(st.nextToken());
			}
		}
	}
	
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
	
	private static void print() {
		for(int i =0 ; i< N; i++) {
			for(int j = 0 ; j<N; j++) {
				System.out.print(maps[i][j]);
			}
			System.out.println();
		}
	}
}
