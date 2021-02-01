import java.io.*;
import java.util.*;

public class Main_BOJ_1244_스위치켜고끄기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N;
	static int[] switches;

	public static void main(String[] args) throws Exception {
		input();
		N = stoi(br.readLine());
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int sex = stoi(st.nextToken());
			int switchNum = stoi(st.nextToken());
			reverseSwitch(switchNum, sex);
		}
		printAnswer();
	}

	private static void reverseSwitch(int sn, int s) {
		int tempSn = sn;
		int num = 1;
		if (s == 1) { // 남
			while (sn <= T) {
				switches[sn] = switches[sn] == 1 ? 0 : 1;
				num++;
				sn = tempSn * num;
			}
		} else { // 여
			switches[sn] = switches[sn] == 1 ? 0 : 1;
			while (sn + num <= T && sn - num > 0 && switches[sn + num] == switches[sn - num]) {
				switches[sn + num] = switches[sn + num] == 1 ? 0 : 1;
				switches[sn - num] = switches[sn - num] == 1 ? 0 : 1;
				num++;
			}
		}
	}

	private static void input() throws Exception {
		T = stoi(br.readLine());
		switches = new int[T + 1];
		st = new StringTokenizer(br.readLine());
		for (int t = 1; t <= T; t++) {
			switches[t] = stoi(st.nextToken());
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

	private static void printAnswer() {
		int cnt = 1;
		for (int t = 1; t <T; t++) {
			if (cnt >= 20) {
				System.out.println(switches[t]);
				cnt = 1;
			} else {
				System.out.print(switches[t] + " ");
				cnt++;
			}
		}
		System.out.print(switches[T]);
	}
}
