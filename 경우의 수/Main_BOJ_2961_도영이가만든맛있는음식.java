import java.util.*;
import java.io.*;

public class Main_BOJ_2961_도영이가만든맛있는음식 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] foodInfo;
	static boolean[] isSelected;
	static long divOfScore;

	public static void main(String[] args) throws Exception {
		int N = stoi(br.readLine());
		foodInfo = new int[N][2];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			foodInfo[i][0] = stoi(st.nextToken());
			foodInfo[i][1] = stoi(st.nextToken());
		}
		divOfScore = Long.MAX_VALUE;
		generateSubset(0);
		bw.append(divOfScore + "");
		bw.flush();
		bw.close();
	}

	private static void generateSubset(int cnt) {
		boolean isZeroSelected = false;
		if (cnt == foodInfo.length) { 
			long sTotal = 1;
			long bTotal = 0;

			for (int i = 0; i < foodInfo.length; i++) {
				if (isSelected[i]) { // 선택 되어있으면
					sTotal *= foodInfo[i][0];
					bTotal += foodInfo[i][1];
					isZeroSelected = true;
				}
			}
			if(!isZeroSelected) return;
			divOfScore = Math.min(divOfScore, Math.abs(sTotal - bTotal));
			return;
		}
		isSelected[cnt] = true; // 선택
		generateSubset(cnt + 1);
		isSelected[cnt] = false; // 비선택
		generateSubset(cnt + 1);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}