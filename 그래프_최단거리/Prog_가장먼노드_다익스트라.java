import java.util.*;
import java.io.*;

class Prog_가장먼노드_다익스트라 {
    static ArrayList<Integer>[] minEdges;
    static int[] dirs;
    static boolean[] visited;
    static int maxNum = 0;

    private static class Node implements Comparable<Node> {
        int no; // 노드 번호
        int dir; // 거리

        Node(int no, int dir) {
            this.no = no;
            this.dir = dir;
        }

        public int compareTo(Node o) { // 거리 기준 오름차순
            return this.dir - o.dir;
        }
    }

    public int solution(int n, int[][] edge) {
        minEdges = new ArrayList[n+1];
        dirs = new int[n+1]; // 1로부터 다른 노드까지의 최단 거리
        visited = new boolean[n+1];

        for(int i=0; i<= n; i++) {
            minEdges[i] = new ArrayList<Integer>();
            Arrays.fill(dirs, Integer.MAX_VALUE); // 최댓값으로 초기화
        }

        for(int i=0; i< edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            minEdges[a].add(b); // 양방향 연결
            minEdges[b].add(a);
        }

        dijkstra(1);

        int answer = 0;
        int maxNum = 0;
        for(int i=2; i<=n; i++) {
            maxNum = Math.max(maxNum, dirs[i]); // maxNum: 가장 먼 거리
        }

        for(int i=2; i<=n; i++) {
            if(maxNum == dirs[i]) answer++; // 가장 먼 거리의 노드 수 세기
        }
        return answer;
    }

    // 다익스트라로 start로부터 다른 노드까지 최단 거리(dirs) 구하기
    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐
        pq.offer(new Node(start, 0)); // 나와의 거리는 0
        dirs[start] = 0; // 나와의 거리는 0

        while(!pq.isEmpty()) {
            Node value = pq.poll(); // 가장 가까운 거리를 가진 노드가 poll된다.

            if(visited[value.no]) continue; // 이미 최단 거리를 구한 경우
            visited[value.no] = true; // 최단 거리를 구했으므로 true

            for(int node: minEdges[value.no]) {
	            // 아직 최단 거리 못 구했고, value를 경유해서 start로 가는게 더 빠르다면
                if(!visited[node] && dirs[node] > dirs[value.no] + 1) {
                    dirs[node] = dirs[value.no] + 1; // 그 값으로 갱신
                    pq.offer(new Node(node, dirs[node])); // 값 pq에 offer
                }
            }
        }
    }
}