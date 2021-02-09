import java.io.*;
import java.util.*;


public class Main_BOJ_2563_색종이 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[100][100];
		int answer =0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			answer += countBlack(x,y);
		}
		bw.append(answer+"");
		bw.flush();
		bw.close();
	}
	
	private static int countBlack(int x, int y) {
		int result =0;
		for(int i=x; i<x+10; i++) {
			for(int j=y; j<y+10; j++) {
				if(!visited[i][j]) {
					result += 1;
				}
				visited[i][j] = true;
			}
		}
		return result;
	}
}
