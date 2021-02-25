import java.util.*;
import java.io.*;

public class Solution_D3_7964_부먹왕국의차원관문 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int cnt, answer, N, D;
	static int[] capital;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			cnt =0; answer =0;
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			capital = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i< N; i++) {
				capital[i] = Integer.parseInt(st.nextToken());
			}
			
			if(capital[0] == 0) {
				answer++;
				capital[0] =1;
			}
			
			if(capital[N-1] == 0) {
				answer++;
				capital[N-1] =1;
			}
			
			for(int i=0; i< N; i++) {
				if(capital[i] == 1) {
					cnt =0;
				} else {
					cnt++;
					if(cnt >= D) {
						answer++;
						cnt =0;
					}
				}
			}
			bw.append("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
}
