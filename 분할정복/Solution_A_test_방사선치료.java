import java.io.*;
import java.util.*;
/*
 * 방사선 치료 문제
 * 직사각형의 종양을 최소 M개만 남겨두고 치료해야한다.
 * 이때 이들을 한번에 치료할 수 있는 정사각형의 한 변의 길이 K를 구하는 문제
 */
class Solution_A_test_방사선치료 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, N, M, answer =0;
	static ArrayList<Pos> squares = new ArrayList<Pos>();
	static int maxX, maxY, K;

	private static class Pos {
		int x1;
		int y1;
		int x2;
		int y2;

		public Pos(int y1, int x1, int y2, int x2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	public static void main(String args[]) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			squares.clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			maxX = 0;
			maxY = 0;
			for(int i=0; i< N; i++) {
				st = new StringTokenizer(br.readLine());
				Pos pos = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				squares.add(pos);
				maxX = Math.max(maxX, Math.max(pos.x1, pos.x2));
				maxY = Math.max(maxY, Math.max(pos.y1, pos.y2));
			}
			int answer = Math.max(maxX, maxY);
			K = answer;
			while(check(answer) && answer > 0) {
				answer/=2;
			}
			
			while(!check(answer) && answer < K) {
				answer++;
			}
			bw.append("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static boolean check(int n) {
		boolean flag = false;
		for(int i=0; i+n <= K; i++) {
			for(int j=0 ; j+n <= K; j++) {
				int cnt = 0;
				int x1 = i;
				int y1 = j;
				int x2 = i+n;
				int y2 = j+n;
				
				for(int k=0; k< squares.size(); k++) {
					if(isCare(squares.get(k), new Pos(y1, x1, y2, x2))) {
						cnt++;
					}
				}
				if(cnt >= N-M) {
					flag = true;
					break;
				}
			}
			if(flag) return true;
		}
		return false;
	}

	private static boolean isCare(Pos square, Pos careZone) {
		if(square.x1 < careZone.x1 || square.y1 < careZone.y1 || square.x2 < careZone.x1 || square.y2 < careZone.y1) 
			return false;
		if(square.x1 > careZone.x2 || square.y1 > careZone.y2 || square.x2 > careZone.x2 || square.y2 > careZone.y2)
			return false;
		return true;
	}

	
}