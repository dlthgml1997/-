import java.io.*;
import java.util.*;

public class Main_BOJ_16234_인구이동 {
	static int N,L,R;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j <N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer  = 0;
		while(getCanMove()) {
			answer++;
		}
		System.out.println(answer);
	}
	
	private static boolean getCanMove() {
		Deque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N]; 
		
		boolean answer = false;
		
		for(int i=0; i< N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]) continue;
				
				dq.add(new int[] {i,j});
				int cnt = 1; 
				int total = map[i][j];
				Deque<int[]> country = new ArrayDeque<>();
				country.add(new int[] {i, j});
				visited[i][j]= true;
				while(!dq.isEmpty()) {
					int[] pre = dq.poll();
					
					for(int d=0; d< 4; d++) {
						int nx = pre[0] + dx[d];
						int ny = pre[1] + dy[d];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						int diff = Math.abs(map[pre[0]][pre[1]] - map[nx][ny]);
						if(diff >= L && diff <= R && !visited[nx][ny]) {
							answer = true;
							cnt += 1;
							total += map[nx][ny];
							visited[nx][ny] = true;
							dq.add(new int[] {nx, ny});
							country.add(new int[] {nx, ny});
						}
					}	
				}
				
				int people = total / cnt;
				while(!country.isEmpty()) {
					int[] now = country.poll();
					map[now[0]][now[1]] = people;
				}
			}
		}
		return answer;
	}

}
