import java.io.*;
import java.util.*;

public class Main_BOJ_19238_스타트택시 {
	static int N, M, oil, cnt = 0;
	static int[][] map, start, dest;
	static int[] taxi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행, 열 크기 
		M = Integer.parseInt(st.nextToken()); // 승객 수 
		oil = Integer.parseInt(st.nextToken()); // 연료 
		map = new int[N][N];
		
		start = new int[M+1][2]; // 승객들의 출발지 좌표 리스트 
								// (EX: 1번 손님의 출발지 x좌표는 start[1][0], y좌표는 start[1][1]) 
		dest = new int[M+1][2]; // 승객들의 도착지 좌표 리스트 
		taxi = new int[2]; // 택시의 현재 위치 
		
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) { // 벽은 -1로 바꿔준다. (0 이상이면 승객으로 처리할 것이기 때문)
					map[i][j] = -1;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi[0] = Integer.parseInt(st.nextToken()) -1; // 0~ N-1 좌표를 가져야하기 때문에 -1 해준다. 
		taxi[1] = Integer.parseInt(st.nextToken()) -1;

		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			start[i][0] = Integer.parseInt(st.nextToken()) -1;
			start[i][1] = Integer.parseInt(st.nextToken()) -1;
			map[start[i][0]][start[i][1]] = i; // 승객의 출발지 위치에 승객 번호를 지정 (겹치지 X) 
			dest[i][0] = Integer.parseInt(st.nextToken()) -1; // 다른 승객의 출발지 위치와 겹칠 수 있기 때문에 map에 지정하지 않는다.
			dest[i][1] = Integer.parseInt(st.nextToken()) -1;
		}
		
		int answer = -1;
		while(true) {
			int[] passengerInfo = bfs(taxi[0], taxi[1]); // [0]: 승객번호, [1]: 거리
			// 승객 운반 및 오일 충전
			move(passengerInfo);
			
			if(passengerInfo[0] == 0 || oil < 0) break; // 승객이 없거나 || 연료가 다 닳았으면 끝.

			cnt++;
			if(cnt == M) { // 모든 승객을 다 운반했으면
				answer = oil;
				break;
			}
		}
		System.out.println(answer);
	}

	private static void move(int[] near) {
		// 거리 구하기
		int p = near[0];
		int dir = getDistance(p);
		// oil 양 줄인 후 충전
		oil -= dir + near[1];
		if(oil < 0) return;
		oil += (dir * 2);
		// 승객 초기화
		map[start[p][0]][start[p][1]] = 0; 
		// 택시 위치 옮기기 
		taxi[0] = dest[p][0];
		taxi[1] = dest[p][1];
	}

	private static int getDistance(int p) { // p의 출발지부터 도착지까지의 최소 거리 구하기 
		int minDir = Integer.MAX_VALUE; // 최소 거리 
		boolean[][] visited = new boolean[N][N];
		Deque<int[]> deque = new ArrayDeque<>();  
		deque.add(new int[] {start[p][0], start[p][1], 0}); // x 좌표, y 좌표, 출발지부터의 거리
		visited[start[p][0]][start[p][1]] = true;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		while(!deque.isEmpty()) {
			int[] now = deque.poll();
			for(int d=0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx >= N || nx < 0 || ny >= N || ny < 0) continue;
				if(map[nx][ny] == -1 || visited[nx][ny]) continue;
				if(nx == dest[p][0] && ny == dest[p][1]) { // 도착지이면 
					if(now[2]+1 < minDir) // 최소거리 갱신 
						minDir = now[2]+1;
				} else if(!visited[nx][ny]) {
					deque.add(new int[] {nx, ny, now[2]+1});
					visited[nx][ny] = true;
				}
			}
		}
		return minDir;
	}

	// 택시와 가장 가까운 승객 정보 반환
	// param: 택시의 좌표
	// return: 가장 가까운 승객의 정보 [0]: 승객 번호, [1]: 거리 
	private static int[] bfs(int x, int y) {
		if(map[x][y] > 0) { // 승객과 택시가 같은 곳에 있는 경우 
			return new int[] {map[x][y], 0};
		}
		
		int near = 0; // 가장 가까운 승객 
		int minDir = Integer.MAX_VALUE; // 가까운 승객과의 거리
		boolean[][] visited = new boolean[N][N];
		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(new int[] {x, y, 0});
		visited[x][y] = true;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		while(!deque.isEmpty()) {
			int[] now = deque.poll();
			for(int d=0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];

				if(nx >= N || nx < 0 || ny >= N || ny < 0) continue;
				if(map[nx][ny] == -1 || visited[nx][ny]) continue;
				if(map[nx][ny] > 0) { // 승객이면 
					if(now[2]+1 < minDir) { // 거리가 최소면
						minDir = now[2]+1; // 거리 갱신 
						near = map[nx][ny]; // 내가 제일 가까운 승객 
					} else if(now[2]+1 == minDir) { // 택시로부터 최소 거리 승객과 같은 거리면 
						if(nx < start[near][0]) { // 행이 더 작으면 
							near = map[nx][ny]; // 내가 제일 가까운 승객 
						} else if(nx == start[near][0] && ny < start[near][1]) { // 행은 같은데 열이 더 작으면  
							near = map[nx][ny]; // 내가 제일 가까운 승객
						}
					}
				}
				
				if(!visited[nx][ny]) {
					deque.add(new int[] {nx, ny, now[2]+1});
					visited[nx][ny] = true;
				}
			}
		}
		return new int[] {near, minDir};
	}
}
