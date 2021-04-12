import java.util.*;
import java.io.*;

// 플로이드 와샬
public class Main_BOJ_9205_맥주마시면서걸어가기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static Pos[] dirs;
	static boolean[][] isHappy;
	static int T, N;
	
	static private class Pos {
		int x;
		int y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			dirs = new Pos[N+2];
			isHappy = new boolean[N+2][N+2];
			dirs[0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); // 집
			for(int i=1; i<N+1; i++) { // 편의점
				st = new StringTokenizer(br.readLine());
				dirs[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine()); 
			dirs[N+1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); // 페스티벌
			
			for(int i=0; i<N+2; i++) {
				for(int j=0; j<N+2; j++) {
					if(i==j) {
						isHappy[i][j] = true;
						continue;
					}
					Pos pos1 = dirs[i];
					Pos pos2 = dirs[j];
					int dir = Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y- pos2.y);
					if(dir <= 1000) 
						isHappy[i][j] = true;
				}
			}
			
			floyd();
			
			String answer = isHappy[0][N+1] ? "happy" : "sad";
			bw.append(answer+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static void floyd() {
		for(int k=0; k<N+2; k++) { // 경유지 
			for(int i=0; i<N+2; i++) { // 출발지
				for(int j=0; j<N+2; j++) { // 도착지
					if(isHappy[i][k] && isHappy[k][j]) isHappy[i][j] = true;
				}
			}
		}
	}
}
