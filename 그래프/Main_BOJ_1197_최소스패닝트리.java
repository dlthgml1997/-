import java.util.*;
import java.io.*;

public class Main_BOJ_1197_최소스패닝트리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Vertex>[] vertexList;
	static int[] minEdge;
	static boolean[] visited;
	static int V, E; // 정점 , 간선

	private static class Vertex implements Comparable<Vertex> {
		int no;
		int edge;

		public Vertex(int no, int edge) {
			this.no = no;
			this.edge = edge;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.edge - o.edge;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		vertexList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			vertexList[i] = new ArrayList<>();
		}
		int from, to, edge;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			vertexList[from].add(new Vertex(to, edge));
			vertexList[to].add(new Vertex(from, edge));
		}

		int result = 0, connectedNode = 0;
		minEdge = new int[V + 1];
		visited = new boolean[V + 1];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		pq.offer(new Vertex(1, 0));
		while (!pq.isEmpty()) {
			Vertex minVertex = pq.poll();

			if (visited[minVertex.no])
				continue;
			result += minVertex.edge;
			visited[minVertex.no] = true;

			if (++connectedNode == V)
				break;

			for (int i = 0; i < vertexList[minVertex.no].size(); i++) {
				Vertex next = vertexList[minVertex.no].get(i);
				if (!visited[next.no] && minEdge[next.no] > next.edge) {
					minEdge[next.no] = next.edge;
					pq.offer(next);
				}
			}
		}
		System.out.println(result);
	}
}
