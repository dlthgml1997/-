import java.util.*;
import java.io.*;

public class Solution_D6_1263_사람네트워크2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] network;
	static final int INF = 100000000;
	static int T, N;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			network = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());
					network[i][j] = value;
					if(i!= j && network[i][j] == 0) {
						network[i][j] = INF;
					}
				}
			}
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) {
					if(i==k) continue;
					for(int j=0; j<N; j++) {
						if(i==j || k==j) continue;
						if(network[i][k] != INF && network[i][k]+network[k][j] < network[i][j]) {
							network[i][j] = network[i][k] + network[k][j];
						}
					
					}
				}
			}
			
			int minNum = INF;
			int total = 0;
			for(int i=0; i<N; i++) {
				total =0;
				for(int j=0; j<N; j++) {
					total+= network[i][j];
				}
				minNum = Math.min(total, minNum);
			}
			bw.append("#"+t+" "+Math.round(minNum)+"\n");
		}
		bw.flush();
		bw.close();
	}
}
