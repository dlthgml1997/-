import java.util.*;
import java.io.*;
// 플로이드 와샬

public class Main_BOJ_14938_서강그라운드_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 지역의 개수 
		int m = Integer.parseInt(st.nextToken()); // 수색범위
		int r = Integer.parseInt(st.nextToken()); // 길의 개수
		int[] items = new int[n+1];
		int[][] edges = new int[n+1][n+1];
		int MAX_LEN = 16;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<= n; i++) {
			items[i] = Integer.parseInt(st.nextToken()); // 각 구역에 있는 아이템 수 
		}
	
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				edges[i][j] = MAX_LEN;
			}
		}
		
		
		for(int i=0; i< r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken()); // 길의 길이
			edges[a][b] = l;
			edges[b][a] = l;
		}
		
		
		
		for(int i=1; i<=n; i++) { // 경유지
			for(int j=1; j<=n; j++) { // 출발지
				for(int k=1; k<=n; k++) { // 도착지
					if(i == j || j == k || i == k) continue;
					edges[j][k] = Math.min(edges[j][i] + edges[i][k], edges[j][k]);
				}
			}
		}
		
		int maxNum = 0;
		for(int i=1; i<=n; i++) {
			int temp = items[i];
			for(int j=1; j<=n; j++) {
				if(edges[i][j] <= m) {
					temp += items[j];
				}
			}
			maxNum = Math.max(temp, maxNum);
		}
		
		System.out.println(maxNum);
	}
}
