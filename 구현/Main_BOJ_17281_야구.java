import java.io.*;
import java.util.*;

public class Main_BOJ_17281_야구 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int inningNum;
	static int[] order;
	static int turn, score =0;
	static int[][] player;
	static int out = 0;
	static int[] field;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		inningNum = stoi(br.readLine());
		player = new int[inningNum][9];
		
		for (int i = 0; i < inningNum; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				player[i][j] = stoi(st.nextToken());
			}
		}
		order = new int[9];
		isSelected = new boolean[9];
		field = new int[4];
		permutation(0);
		System.out.println(score);
	}
	
	private static void permutation(int cnt) {
		if(cnt == 9) {
			int inning = 0;
			turn = 0;
			int newScore=0;
			while (inning < inningNum) {
				out =0;
//				field = new int[4];
				while(out < 3) {
					newScore += game(player[inning][order[turn%9]]);
					turn++;
				}
				inning++;
			}
			score = Math.max(score, newScore);
		}

		if(cnt == 3) {
			permutation(cnt+1);
			return;
		}
		
		for(int i=1 ;i < 9; i++) {
			if(isSelected[i]) continue;
			order[cnt] = i;
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}

	private static int game(int exp) {
		int newS = 0;
		if (exp == 0) {
			out++;
		}
		switch(exp) {
		case 1:
			newS += field[3];
			field[3] = field[2];
			field[2] = field[1];
			field[1] = 1;
			field[0] = 0;
			break;
		case 2:
			newS += field[3] + field[2];
			field[3] = field[1];
			field[2] = 1;
			field[1] = 0; field[0] = 0;
			break;
		case 3:
			newS += field[3] + field[2] + field[1];
			field[3] = 1;
			field[2] = 0; field[1] = 0; field[0] = 0;
			break;
		case 4:
			newS += field[3] + field[2] + field[1] + 1;
			field[3] = 0; field[2] = 0; field[1] = 0; field[0] = 0;
			break;
		}
		
		if(out == 3) {
			field[0] = 0; field[1] = 0; field[2] = 0; field[3] =0;
		}
		return newS;
	}

	static int stoi(String input) {
		return Integer.parseInt(input);
	}
}