import java.util.*;
import java.io.*;

public class Solution_D3_6808_규영이와인영이의카드게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] guCards;
	static int[] inCards;
	static boolean[] isSelected;
	static int[] nums;
	static int win, lose;

	public static void main(String[] args) throws Exception {
		int T = stoi(br.readLine());
		
		for(int t=1; t<=T; t++) {
			input();
			int inIdx = 0;
			inCards = new int[9];
			for(int i=1; i<= 18; i++) {
				if(!isSelected[i]) inCards[inIdx++] = i;
			}
			isSelected = new boolean[9];
			nums = new int[9];
			win =0; lose = 0;
			permutation(0);
			bw.append("#" + t + " " + win + " " + lose + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void permutation(int cnt) {
		if(cnt == 9) {
			game();
			return;
		}
		for(int i=0; i< 9; i++) {
			if(isSelected[i]) continue;
			nums[cnt] = inCards[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}

	private static void game() {
		int guIdx = 0;
		int guTotal =0;
		int inTotal =0;
		
		for(int num: nums) {
			if(num < guCards[guIdx]) {
				guTotal += num + guCards[guIdx];
			} else {
				inTotal += num + guCards[guIdx];
			}
			guIdx++;
		}
		
		if(guTotal == inTotal) {
			return;
		}
		
		if(guTotal > inTotal) {
			win++;
		} else {
			lose++;
		}
	}

	private static void input() throws Exception {
		guCards = new int[9];
		isSelected = new boolean[19];
		isSelected[0] = true;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<9;i++) {
			int value = stoi(st.nextToken());
			guCards[i] = value;
			isSelected[value] = true;
		}
	}
	
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}