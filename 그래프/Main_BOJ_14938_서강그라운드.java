import java.util.*;
import java.io.*;

public class Main_BOJ_14938_서강그라운드 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Vertex>[] graph;
	static int[] distance;
	static boolean[] check;
	static int[] item;
	static int areaCnt,areaLimit,loadCnt;
	
	private static class Vertex implements Comparable<Vertex>{
		int no;
		int dis;
		
		public Vertex(int no, int dis) {
			this.no = no;
			this.dis = dis;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dis - o.dis;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		areaCnt = Integer.parseInt(st.nextToken());
		areaLimit = Integer.parseInt(st.nextToken());
		loadCnt = Integer.parseInt(st.nextToken());
		graph = new ArrayList[areaCnt+1];
		item = new int[areaCnt+1];
		check = new boolean[areaCnt+1];
		distance = new int[areaCnt+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=areaCnt; i++) {
			graph[i] = new ArrayList<Vertex>(); // 초기화!!!
			item[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<loadCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			graph[a].add(new Vertex(b,l));
			graph[b].add(new Vertex(a,l));
		}
		
		int maxNum = 0;
		for(int i=1; i<= areaCnt; i++) {
			maxNum = Math.max(maxNum, djikstra(i));
		}
		System.out.println(maxNum);
	}

	private static int djikstra(int start) {
		int itemTotal =0;
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(check, false);
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.offer(new Vertex(start, 0));
		distance[start] = 0; // 자기 자신은 거리가 0
		
		while(!queue.isEmpty()) {
			Vertex current = queue.poll();
			int no = current.no;
			
			if(check[no]) continue;
			check[no] = true;
			
			for(Vertex vertex: graph[no]) {
				if(!check[vertex.no] && distance[vertex.no] > distance[no] + vertex.dis) {
					distance[vertex.no] = distance[no]+ vertex.dis;
					queue.offer(new Vertex(vertex.no, distance[vertex.no]));
				}
			}
		}
		
		
		for(int i=1; i<=areaCnt; i++) {
			if(distance[i] <= areaLimit) {
				itemTotal+= item[i];
			}
		}
		return itemTotal;
	}
}
