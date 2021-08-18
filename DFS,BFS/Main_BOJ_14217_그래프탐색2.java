import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BOJ_14217_그래프탐색2 {
	static int[][] map;
	static int n, m;
	static BufferedWriter bw; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		
		for(int i=0; i<m; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			st= new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
			
			bfs();
			bw.append("\n");
		}
		bw.flush();
		bw.close();
		
	}
	
	private static void bfs() throws IOException {
		Deque<int[]> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];

		for(int i=1; i<=n; i++) {
			if(i == 1) {
				bw.append(0+" ");
				continue;
			}
			dq.add(new int[] {i, 0});	
			Arrays.fill(visited, false);
			int minNum = Integer.MAX_VALUE;
			
			flag: while(!dq.isEmpty()) {
				int[] city = dq.poll();
				for(int j=1;j<=n; j++) {
					if(map[city[0]][j] == 1) {
						if(j == 1) {
							minNum = Math.min(minNum, city[1]+1);
							dq.clear();
							break flag;
						} else {
							if(!visited[j] && minNum > city[1]+1)
								dq.add(new int[] {j, city[1]+1});
							visited[j] = true;
						}
					}
				}
			}
			if(minNum == Integer.MAX_VALUE) {
				bw.append(-1+" ");
			} else {
				bw.append(minNum+" ");
			}
			
		}
	}
}
