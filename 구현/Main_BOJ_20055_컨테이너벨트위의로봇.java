import java.io.*;
import java.util.*;

public class Main_BOJ_20055_컨테이너벨트위의로봇 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static Belt[] belt;

	private static class Belt {
		int power;
		boolean isRobotHere;

		Belt(int power, boolean isRobotHere) {
			this.power = power;
			this.isRobotHere = isRobotHere;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		belt = new Belt[N * 2];
		for (int i = 0; i < N * 2; i++) {
			belt[i] = new Belt(Integer.parseInt(st.nextToken()), false);
		}

		System.out.println(move());
	}

	private static int move() {
		int Dan = 0;
		while (true) {
			Dan++;
			rotate();
			moveRobot();
			robotStart();
			if (isK()) {
				break;
			}
		}
		return Dan;
	}

	private static boolean isK() {
		int count = 0;
		for (int i = 0; i < N * 2; i++) {
			if (belt[i].power == 0) {
				count++;
			}
		}
		return count >= K ? true : false;
	}

	private static void robotStart() {
		if (!belt[0].isRobotHere && belt[0].power > 0) {
			belt[0].power--;
			belt[0].isRobotHere = true;
		}
	}

	private static void moveRobot() {
		for (int i = N - 2; i >= 0; i--) {
			if (belt[i].isRobotHere) {
				if (i + 1 == N-1) {
					if (belt[i + 1].power > 0) {
						belt[i].isRobotHere = false;
						belt[i+1].power--;
						continue;
					}
				}
				if (belt[i + 1].isRobotHere || belt[i + 1].power == 0)
					continue;
				belt[i + 1].isRobotHere = true;
				belt[i + 1].power--;
				belt[i].isRobotHere = false;
			}
		}
	}

	private static void rotate() {
		boolean isRobotTemp = belt[2 * N - 1].isRobotHere;
		int powerTemp = belt[2 * N - 1].power;
		for (int i = N * 2 - 1; i >= 1; i--) {
			belt[i].isRobotHere = belt[i - 1].isRobotHere;
			belt[i].power = belt[i - 1].power;
			if (i == N - 1) {
				belt[i].isRobotHere = false;
			}
		}
		belt[0].isRobotHere = isRobotTemp;
		belt[0].power = powerTemp;
	}
}