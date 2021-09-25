import java.io.*;
import java.util.*;

public class Main_BOJ_5430_AC {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static Deque<Integer> deque;
	static char[] func;
	static int T;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			deque = new ArrayDeque<>();
			func = br.readLine().toCharArray();
			int L = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(),",");
			for (int i = 0; i < L; i++) {
				if(i != 0 && i != L-1) {
					deque.add(Integer.parseInt(st.nextToken()));
					continue;
				}
				
				char[] temp = st.nextToken().toCharArray();
				String str = "";
				for(int s=0; s< temp.length; s++) {
					if(temp[s] != '[' && temp[s] != ']') {
						str += temp[s];
					}
				}
				deque.add(Integer.parseInt(str));
			}
			
			int pointer = startfunc();
			if (pointer == 2){ // error
				bw.append("error");
			} else if(pointer == 0) { // 왼쪽 
				bw.append("[");
				while(!deque.isEmpty()) {
					bw.append(deque.poll()+"");
					if(deque.size() != 0)
						bw.append(",");
				}
				bw.append("]");
			} else if (pointer == 1){ // 오른쪽
				bw.append("[");
				while(!deque.isEmpty()) {
					bw.append(deque.pollLast()+"");
					if(deque.size() != 0)
						bw.append(",");
				}
				bw.append("]");
			}
			if(t != T-1)
				bw.append("\n");
		}
		bw.flush();
		bw.close();

	}

	private static int startfunc() { // pointer 반환 , 2: error
		int pointer = 0; // 0: 왼쪽, 1 : 오른
		for (int i = 0; i < func.length; i++) {
			if (func[i] == 'R') {
				pointer = pointer == 0 ? 1 : 0;
			} else { // 'D'
				if (deque.isEmpty())
					return 2;
				if (pointer == 0)
					deque.pollFirst();
				else
					deque.pollLast();
			}
		}
		return pointer;
	}
}
