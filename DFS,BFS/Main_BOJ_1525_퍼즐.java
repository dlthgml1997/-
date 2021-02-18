import java.util.*;
import java.io.*;

public class Main_BOJ_1525_퍼즐 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static String table;
	static Deque<String> deque;
	static HashMap<String, Integer> visited;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		
		table = "";
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				String input = st.nextToken();
				table += input;
			}
		}

		deque = new ArrayDeque<String>();
		visited = new HashMap<>();
		
		deque.add(table);
		visited.put(table, 0);
		
		answer = bfs();
		System.out.println(answer);
	}

	private static int bfs() {
		while (!deque.isEmpty()) {
			String tableStr = deque.pollFirst();
			int step = visited.get(tableStr);
			int zeroIdx = tableStr.indexOf('0');
			
			if(tableStr.equals("123456780")) {
				return step;
			}
			
			int i = zeroIdx /3;
			int j = zeroIdx - 3 * (zeroIdx /3);
			
			step += 1;
			char[] tableList = tableStr.toCharArray();
			if(i>0) { // 위 
				char temp = tableList[zeroIdx];
				tableList[zeroIdx] = tableList[zeroIdx -3];
				tableList[zeroIdx-3] = temp;
				String newTableStr= String.valueOf(tableList);
				if(visited.get(newTableStr) == null) {
					visited.put(newTableStr, step);
					deque.add(newTableStr);
				}
				// 다시 돌려놓기
				tableList[zeroIdx- 3] = tableList[zeroIdx];
				tableList[zeroIdx] = temp;
			}
			
			if(i<2) { // 아래
				char temp = tableList[zeroIdx];
				tableList[zeroIdx] = tableList[zeroIdx +3];
				tableList[zeroIdx+3] = temp;
				String newTableStr= String.valueOf(tableList);
				if(visited.get(newTableStr) == null) {
					visited.put(newTableStr, step);
					deque.add(newTableStr);
				}
				// 다시 돌려놓기
				tableList[zeroIdx+ 3] = tableList[zeroIdx];
				tableList[zeroIdx] = temp;
			}
			
			if(j>0) { // 왼쪽
				char temp = tableList[zeroIdx];
				tableList[zeroIdx] = tableList[zeroIdx -1];
				tableList[zeroIdx-1] = temp;
				String newTableStr= String.valueOf(tableList);
				if(visited.get(newTableStr) == null) {
					visited.put(newTableStr, step);
					deque.add(newTableStr);
				}
				// 다시 돌려놓기
				tableList[zeroIdx-1] = tableList[zeroIdx];
				tableList[zeroIdx] = temp;
			}
			
			if(j<2) { // 오른쪽 
				char temp = tableList[zeroIdx];
				tableList[zeroIdx] = tableList[zeroIdx +1];
				tableList[zeroIdx+1] = temp;
				String newTableStr= String.valueOf(tableList);
				if(visited.get(newTableStr) == null) {
					visited.put(newTableStr, step);
					deque.add(newTableStr);
				}
				// 다시 돌려놓기
				tableList[zeroIdx+1] = tableList[zeroIdx];
				tableList[zeroIdx] = temp;
			}
		}
		return -1;
	}
}
