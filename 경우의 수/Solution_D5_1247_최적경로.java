import java.io.*;
import java.util.*;

public class Solution_D5_1247_최적경로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int T, C;
	
	static boolean[] isSelected;
	static Pos[] client;
	static Pos[] clientPer;
	static Pos house, company;
	static int answer;
			
	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		T = stoi(br.readLine());
		for(int t=1; t<= T; t++) {
			input();
			answer = Integer.MAX_VALUE;
			isSelected = new boolean[C];
			permutation(0);
			bw.append("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void permutation(int cnt) {
		if(cnt == C) {
			int distance = calcDistance();
			answer = Math.min(answer, distance);
		}
		
		for(int i=0; i< C; i++) {
			if(isSelected[i]) continue;
			
			clientPer[cnt] = client[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}

	private static int calcDistance() {
		int distance =0;
		
		distance+= Math.abs(company.x - clientPer[0].x) + Math.abs(company.y - clientPer[0].y);  
		for(int i=0; i<C-1 ; i++) {
			distance+= Math.abs(clientPer[i].x - clientPer[i+1].x) + Math.abs(clientPer[i].y - clientPer[i+1].y);
		}
		distance+= Math.abs(house.x - clientPer[C-1].x) + Math.abs(house.y - clientPer[C-1].y);
		
		return distance;
	}

	private static void input() throws Exception {
		C = stoi(br.readLine());
		client = new Pos[C];
		clientPer = new Pos[C];
		st = new StringTokenizer(br.readLine());
		company = new Pos(stoi(st.nextToken()), stoi(st.nextToken()));
		house = new Pos(stoi(st.nextToken()), stoi(st.nextToken()));
		for(int c=0; c<C; c++) {
			client[c] = new Pos(stoi(st.nextToken()), stoi(st.nextToken()));
		}		
	}

	static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
