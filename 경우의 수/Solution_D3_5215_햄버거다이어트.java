import java.io.*;
import java.util.*;

// 부분집합 구현

public class Solution_D3_5215_햄버거다이어트 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int limit =0;
	static int numOfFood=0;
	static int[][] foodInfo;
	static boolean[] isSelected;
	static Queue<int[]> queue;
	static int maxNum;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			input();
			maxNum = 0;
			generateSubset(0);
			System.out.printf("#%d %d\n", t, maxNum);
		}
	}
	private static void generateSubset(int cnt) {
		if(cnt == numOfFood) {
			int total =0;
			int sumOfScore =0;
			for(int i=0; i<numOfFood; i++) {
				if(isSelected[i]) {
					sumOfScore += foodInfo[i][0];
					total += foodInfo[i][1];
					if(total>limit) return;
				}
			}
			maxNum = Math.max(maxNum, sumOfScore);
			return;
		}
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}
	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		numOfFood = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		foodInfo = new int[numOfFood][2];
		isSelected = new boolean[numOfFood];
		for(int i=0; i<numOfFood; i++) {
			st = new StringTokenizer(br.readLine());
			foodInfo[i][0] = Integer.parseInt(st.nextToken());
			foodInfo[i][1] = Integer.parseInt(st.nextToken());
		}
	}
}
