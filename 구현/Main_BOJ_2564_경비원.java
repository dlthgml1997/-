import java.io.*;
import java.util.*;

public class Main_BOJ_2564_경비원 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M; // 블록 경계 행, 열
	static int storeNum;
	static Stack<Pos> stack;
	static Pos dongPos;
	
	public static void main(String[] args) throws Exception {
			input();
			int answer =0 ;
			while(!stack.isEmpty()) {
				Pos store = stack.pop();
				if(Math.abs(store.y - dongPos.y) == M) {
					answer += Math.min(N-store.x + N- dongPos.x, store.x + dongPos.x) + M;
				} else if(Math.abs(store.x - dongPos.x) == N) {
					answer += Math.min(M-store.y + M- dongPos.y, store.y + dongPos.y) + N;
				} else {
					answer+= Math.abs(store.x-dongPos.x) + Math.abs(store.y - dongPos.y);						
				}
			}
			System.out.println(answer);
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken()); // 열
		N = stoi(st.nextToken()); // 행
		
		storeNum = stoi(br.readLine());
		stack = new Stack<Pos>();
		for(int i=0 ; i< storeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = stoi(st.nextToken());
			int pos = stoi(st.nextToken());
			stack.add(realPos(dir, pos));
		}
		st = new StringTokenizer(br.readLine());
		dongPos = realPos(stoi(st.nextToken()), stoi(st.nextToken()));
	}
	
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
	
	private static Pos realPos(int dir, int pos) {
		if(dir == 1) {
			return new Pos(0, pos);
		} else if(dir == 2) {
			return new Pos(N, pos);
		} else if(dir == 3) {
			return new Pos(pos, 0);
		} else if(dir == 4) {
			return new Pos(pos, M);
		}
		return null;
	}
}


class Pos{
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
