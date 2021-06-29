import java.util.*;
import java.io.*;

public class Prog_가장먼노드_BFS {
    static ArrayList<ArrayList<Integer>> list;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        list = new ArrayList<>();
        for(int i=0; i<= n; i++) {
            list.add(i, new ArrayList<>());
        }
        
        for(int i=0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        answer = bfs(1, n);
        return answer;
    }
    
    private static int bfs(int start, int n) {
        int answer = 0;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[n+1];
        deque.add(start);
        visited[start] = true;
        
        while(true) {
            Deque<Integer> temp = new ArrayDeque<Integer>();
            while(!deque.isEmpty()) {
                int value = deque.poll();
                
                for(int node: list.get(value)) { // value와 인접한 모든 노드들
                    if(!visited[node]) {
                        temp.add(node);
                        visited[node] = true;
                    }
                }
            }
            if(temp.isEmpty()) break;
            answer = temp.size();
            while(!temp.isEmpty()) {
                deque.add(temp.poll());    
            }
        }
        return answer;
    }
}