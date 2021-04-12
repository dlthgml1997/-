import java.util.*;
import java.io.*;

public class Solution_D4_1251_하나로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static boolean[] visited;
	static double[][] map;
	static int[][] island;
	static double[] minEdge;
	static int T, N;
	static double E, total= Double.MAX_VALUE;
	
	private static class Vertex implements Comparable<Vertex>{
		int no;
		double edge;
		
		public Vertex(int no, double edge) {
			super();
			this.no = no;
			this.edge = edge;
		}
		@Override
		public int compareTo(Vertex o) {
			if(this.edge > o.edge) return 1;
			else if(this.edge == o.edge) return 0;
			else return -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new double[N][N];
			visited = new boolean[N];
			island = new int[N][N];
			minEdge = new double[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i< N; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i< N; i++) {
				island[i][1]= Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			total = Double.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) map[i][j] = 0;
					else {
						map[i][j] = calc(island[i][0] , island[i][1], island[j][0], island[j][1]);
					}
				}
				minEdge[i] = Integer.MAX_VALUE;
			}
			
			PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
			minEdge[0] = 0;
			queue.offer(new Vertex(0, 0));
			double lineLength = 0;
			int nodeCnt = 0;
			while(!queue.isEmpty()) {
				
				Vertex minVertex = queue.poll();
				if(visited[minVertex.no]) continue;
				// 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L2)만큼 지불
				lineLength += E * Math.pow(minVertex.edge,2);
				visited[minVertex.no] = true;
				if(++nodeCnt == N) break;
				
				for(int i=0; i<N; i++) {
					if(!visited[i] && map[minVertex.no][i] != 0 && 
						minEdge[i] > map[minVertex.no][i]) {
						minEdge[i] = map[minVertex.no][i];
						queue.offer(new Vertex(i, map[minVertex.no][i]));
					}
				}
			}
			
			bw.append("#"+t+" "+Math.round(lineLength)+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static double calc(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1 - y2,2));
	}
}
