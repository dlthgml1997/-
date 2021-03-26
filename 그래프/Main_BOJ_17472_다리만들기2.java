import java.util.*;
import java.io.*;

public class Main_BOJ_17472_다리만들기2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// input
	static int N, M; // 세로, 가로 
	static int[][] map; // 섬의 상태를 담는다.
	static boolean[][] setNumVisited; // 섬에 숫자 할당할 때 쓰이는 중복체크 배열 
	static int islandCnt = 0; // 섬의 번호
	// BFS
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Deque<int[]> deque = new ArrayDeque<>();
	// PRIM
	static int[][] bridges; // 섬끼리 연결할 수 있는 다리의 최소 길이, 0: 불가능
	static int[] minEdge; // 섬이 연결될 수 있는 최소 다리 길이 
	static boolean[] visited; // 중복체크 배열
	static PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(); // 섬과 다리길이 정보가 담긴다.

	private static class Vertex implements Comparable<Vertex> {
		int no; // 섬 번호
		int edge;

		public Vertex(int no, int edge) {
			this.no = no;
			this.edge = edge;
		}

		@Override
		public int compareTo(Vertex o) { // 다리길이 기준으로 오름차순 정렬
			return this.edge - o.edge;
		}
	}

	public static void main(String[] args) throws Exception {
		// 초기화
		st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		setNumVisited = new boolean[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 번호 매기기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !setNumVisited[i][j]) // 아직 체크하지 않은 섬이면 
					setIslandNum(i, j);
			}
		}
		
		// A섬이 B섬에 연결되는 다리의 최소 길이가 3 이면 bridges[A][B] = 3;
		bridges = new int[islandCnt+1][islandCnt+1];
		visited = new boolean[islandCnt+1]; // 섬에 대한 방문 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !visited[map[i][j]]) // map[i][j]가 섬이고, map[i][j]번째 섬을 살펴보지 않았으면  
					setMinBridgeLength(i, j); // map[i][j] 섬과 다리를 놓을 수 있는 섬들의 다리 길이 최솟값을 구하는 메소드
			}
		}

		
		Arrays.fill(visited, false); // 섬 번호에 따른 방문 체크 배열 초기화
		int result = 0, nodeCount = 0; // 다리길이의 합, 연결된 섬의 개수
		minEdge = new int[islandCnt+1]; // minEdge[A]: A섬이 다른 섬과 연결됐을 때 다리길이 최솟값
		Arrays.fill(minEdge, Integer.MAX_VALUE); // 최솟값으로 갱신하기 위해 MAX_VALUE 할당 
		minEdge[1] = 0; // 섬 1부터 시작
		pq.offer(new Vertex(1, 0)); // Vertex(no: 섬의 번호,edge: 다리 길이) 섬 1과 섬 1은 다리를 연결할 수 없으므로 edge는 0 
		
		// PRIM 시작
		while (!pq.isEmpty()) { // 모든 연결 방법을 다 살펴볼 동안 
			Vertex minVertex = pq.poll(); // minVertex: 다른 섬과 연결되는 다리 길이(edge)가 제일 작은 Vertex
			if (visited[minVertex.no]) // 이미 최솟값으로 연결됐으면
				continue; // 패스

			result += minVertex.edge; // 총 다리 길이에 최솟값을 더한다.
			visited[minVertex.no] = true; // 최솟값으로 연결됐으므로 true 
			
			if (++nodeCount == islandCnt) // 모든 섬이 연결됐으면 
				break;

			for (int i = 1; i <= islandCnt; i++) { // 모든 섬을 살펴보면서
				if (!visited[i] // 최솟값으로 연결되지 않았고, 
						&& bridges[minVertex.no][i] != 0 // 다른 섬과 다리를 놓을 수 있으 
						&& minEdge[i] > bridges[minVertex.no][i]) // 다른 섬과 놓을 다리 길이가 더 작은 값이면  
				{ 
					minEdge[i] = bridges[minVertex.no][i]; // 더 작은 값으로 갱신
					pq.offer(new Vertex(i, bridges[minVertex.no][i]));
				}
			}
		}
		
		// 출력
		if (nodeCount < islandCnt) { // 모든 섬이 연결되지 않았으면
			System.out.println(-1); 
		} else { // 모든 섬이 연결됐으면
			System.out.println(result);
		}
	}

	private static void setMinBridgeLength(int x, int y) { // 다른 섬과 연결될 수 있다면, 최단 길이를 구한다.
		int nx, ny, islandNum = map[x][y]; // 섬의 번호는 map[x][y]
		boolean[][] check = new boolean[N][M]; // deque에 같은 좌표가 add되는 것 방지 
		check[x][y] = true; // 현재 좌표 visit check
		deque.add(new int[] { x, y });
		while (!deque.isEmpty()) {
			int[] pos = deque.poll();
			for (int i = 0; i < 4; i++) { // 현재 좌표 기준 사방 탐색
				nx = pos[0] + dx[i];
				ny = pos[1] + dy[i];
				if (!isRange(nx, ny)) // 범위를 벗어나면
					continue;
				int count = 1; // 다리 길이 (한번 이동한 상태이기 때문에 1로 초기화)
				int nx2 = nx, ny2 = ny; // nx2, ny2 : 다리 놓기 시뮬레이션을 위한 좌표
				if (map[nx2][ny2] != map[x][y]) { // 다리 놓을 곳이 같은 섬이면 pass 
					while (true) {
						nx2 += dx[i]; // 같은 방향으로 계속 이동
						ny2 += dy[i];
						if (!isRange(nx2, ny2) || map[nx2][ny2] == map[x][y]) { // 범위를 벗어나거나, 같은 섬이면 다리 연결 실패
							count = 0; // 연결 실패면 count = 0
							break;
						}
						if (map[nx2][ny2] != 0) // 다른섬에 도달
							break;
						count++; // 다리 길이 1 증가
					}
					if (count > 1) { // 다리 길이가 2 이상이면
						// islandNum: 현재 다리놓기를 하고 있는 섬 번호, map[nx2][ny2]: 연결된 섬 번호
						if (bridges[islandNum][map[nx2][ny2]] == 0) { // 처음 할당하면
							bridges[islandNum][map[nx2][ny2]] = count; // 대소 비교 없이 바로 할당
						} else { // 처음이 아니면 
							// 더 작은 다리 길이 값으로 갱신 
							bridges[islandNum][map[nx2][ny2]] = Math.min(count, bridges[islandNum][map[nx2][ny2]]);
						}
					}
				}
				
				if (map[nx][ny] == map[x][y] && !check[nx][ny]) { // 다음 탐색할 좌표가 같은 섬 번호이고, 아직 살펴보지 않는 좌표이면
					deque.add(new int[] { nx, ny });
					check[nx][ny] = true;
				}
			}
		}
		visited[map[x][y]] = true; // 현재 섬 번호 true 
	}

	private static void setIslandNum(int x, int y) { // 섬 번호(islandNum) 매기기 
		int nx, ny;
		deque.add(new int[] { x, y });
		++islandCnt; // 섬 번호 매기기 전, 섬 번호 1 증가
		map[x][y] = islandCnt;
		while (!deque.isEmpty()) {
			int[] pos = deque.poll();
			for (int i = 0; i < 4; i++) {
				nx = pos[0] + dx[i];
				ny = pos[1] + dy[i];
				if (!isRange(nx, ny) || setNumVisited[nx][ny] || map[nx][ny] == 0) // 범위 벗어나거나, 이미 번호가 있거나, 섬이 아니면
					continue; // 패스 
				map[nx][ny] = islandCnt; // 섬 번호 매기기 
				setNumVisited[nx][ny] = true; // 섬 번호 매겼으니까 true
				deque.add(new int[] { nx, ny }); // 번호 매기기 위한 섬(같은 섬) 탐색을 위해 add
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M ? false : true;
	}
}
