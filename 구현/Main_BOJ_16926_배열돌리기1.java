import java.util.*;
import java.io.*;

public class Main_BOJ_16926_배열돌리기1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int[][] map;
	static boolean[][] visited;
	static int N, M, R;
	
	public static void main(String[] args) throws Exception{
		input();
		for(int r= 0; r<R; r++) {
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				for(int j =0 ; j<M; j++) {
					if(!visited[i][j]) {
						rotate(i,j);
					}
				}
			}
		}
		print();
	}
	
	
	private static void rotate(int x, int y) {
		int i = 0;
		int firstX = x;
		int firstY = y;
		int temp = map[x][y];
		while(true) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isRange(nx, ny) || visited[nx][ny]) {
				i++; continue;
			}
			int t = map[nx][ny];
			map[nx][ny] = temp;
			temp = t;
			visited[nx][ny] = true;
			x = nx;
			y = ny;
			
			if(nx == firstX && ny == firstY) return;
		}
	}

	private static boolean isRange(int x, int y) {
		return x<0 || x >= N || y <0|| y >= M ? false: true;
	}


	public static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
	}
	
	public static void print() throws Exception {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				bw.append(map[i][j]+ " ");
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
