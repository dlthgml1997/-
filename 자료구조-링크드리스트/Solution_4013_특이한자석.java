import java.io.*;
import java.util.*;

public class Solution_4013_특이한자석 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static LinkedList<Integer>[] magnets = new LinkedList[4];
	static int[] magnetDir = new int[4];

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		int[][] lotationInfo;

		for (int tc = 1; tc <= TC; tc++) {
			int K = Integer.parseInt(br.readLine());
			lotationInfo = new int[K][2];
			for (int i = 0; i < 4; i++) {
				LinkedList<Integer> temp = new LinkedList<>();
				st = new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) {
					temp.addLast(Integer.parseInt(st.nextToken()));
				}
				magnets[i] = temp;
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				lotationInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
				lotationInfo[i][1] = Integer.parseInt(st.nextToken());
			}

			int sum = 0;
			for (int[] lotation : lotationInfo) {
				int magnet = lotation[0];
				int dir = lotation[1];
				Arrays.fill(magnetDir, 0);
				fillMagnetDir(magnet, dir);
				for (int i = 0; i < 4; i++) {
					lotate(i, magnetDir[i]);
				}
			}

			for (int i = 0; i < 4; i++) {
				if (magnets[i].get(0) == 1) {
					sum += Math.pow(2, i);
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}

	private static void fillMagnetDir(int magnet, int dir) {
		magnetDir[magnet] = dir;
		for (int i = magnet; i > 0; i--) { // 왼쪽으로 살펴보자 !!
			int now = i;
			int left = i - 1;

			if (magnets[now].get(6) != magnets[left].get(2)) {
				magnetDir[left] = magnetDir[now] * -1;
			} else {
				magnetDir[left] = 0;
				break;
			}
		}
		
		magnetDir[magnet] = dir;
		for (int i = magnet; i < 3; i++) { // 오른쪽으로 살펴보자 !!
			int now = i;
			int right = i + 1;

			if (magnets[now].get(2) != magnets[right].get(6)) {
				magnetDir[right] = magnetDir[now] * -1;
			} else {
				magnetDir[right] = 0;
				break;
			}
		}
	}

	private static void lotate(int m, int dir) {
		if (dir == -1) { // 반시계 방향, 앞에서 빼서 뒤로 넣는다.
			magnets[m].addLast(magnets[m].pollFirst());
		} else if (dir == 1) { // 시계 방향, 뒤에서 빼서 앞으로 넣는다.
			magnets[m].addFirst(magnets[m].pollLast());
		}
	}
}
