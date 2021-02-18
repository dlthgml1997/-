import java.util.*;
import java.io.*;

public class Main_BOJ_3109_빵집 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int lineCnt;
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			setPipe(i, 0);
		}

		System.out.println(lineCnt);
	}

	private static boolean setPipe(int x, int y) {
		if (y == C-1) {
			lineCnt++;
			return true;
		}

		for (int j = 0; j < 3; j++) {
			int nx = x + dx[j];
			int ny = y + 1;
			if (isCheking(nx, ny)) {
				visited[nx][ny] = true; // 반드시 visited 체크 먼저
				if(setPipe(nx,ny)) { // 만약 끝까지 가서 파이프 연결에 성공하면 ! for문을 빠져나가고 
									// 중간에 파이프 연결 끊어지면 for문 계속 실행
					return true; 
				}
			}
		}
		return false; // 중간에 파이프 연결 실패
	}

	private static boolean isCheking(int nx, int ny) {
		return !isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 'x' ? false : true;
	}

	private static boolean isRange(int nx, int ny) {
		return nx < 0 || nx >= R || ny < 0 || ny >= C ? false : true;
	}
}
