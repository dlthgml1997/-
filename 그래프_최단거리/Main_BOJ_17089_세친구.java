import java.util.*;
import java.io.*;
// 최단 거리는 아님.
public class Main_BOJ_17089_세친구 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] friendMap = new int[N+1][N+1];
		int[] friend = new int[N+1];

		for(int i=0; i< M; i++) {
			st= new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			friendMap[A][B] = 1; // 1
			friendMap[B][A] = 1;
			friend[A]++; // 2
			friend[B]++;
		}
		
		int minNum = Integer.MAX_VALUE;
		for(int k=1; k<=N; k++) { // 3
			for(int i=1; i<=N; i++) {
				if(k == i || friendMap[k][i] != 1) continue; // 4
				for(int j=1; j<=N; j++) {
					int cnt = 0;
					if(j == i || j == k || friendMap[i][j] != 1 || friendMap[j][k] != 1) continue; // 5
					cnt += friend[i] - 2; // 6
					cnt += friend[j] - 2;
					cnt += friend[k] - 2;
					minNum = Math.min(cnt, minNum); // 7
				}
			}
		}
		if(minNum == Integer.MAX_VALUE) minNum = -1; // 8
		System.out.println(minNum); // 9
	}
}
