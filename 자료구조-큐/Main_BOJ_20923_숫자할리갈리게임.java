import java.util.*;
import java.io.*;

public class Main_BOJ_20923_숫자할리갈리게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Deque<Integer> doD = new ArrayDeque<>(), suD = new ArrayDeque<>();
	static ArrayList<Integer> sGround = new ArrayList<>(), dGround = new ArrayList<>();
	static int N, M;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			doD.add(Integer.parseInt(st.nextToken()));
			suD.add(Integer.parseInt(st.nextToken()));
		}

		int dNum, sNum;
		while (M > 0) {
			if (isEmptyDeque())
				return;
			
			dNum = doD.pollLast();
			dGround.add(dNum);

			if (isEmptyDeque())
				return;

			if (dNum == 5) { // do 종
				doGet();
			} else if (sGround.size() > 0 && dNum + sGround.get(sGround.size() - 1) == 5) {
				// su종
				suGet();
			}

			M--;
			if (M == 0) {
				break;
			}

			sNum = suD.pollLast(); // su 차례
			sGround.add(sNum);
			if (sNum == 5) { // do 종
				doGet();
			} else if (dGround.size() > 0 && sNum + dGround.get(dGround.size() - 1) == 5) {
				suGet();
			}
			M--;
		}

		if (doD.size() == suD.size()) {
			System.out.println("dosu");
		} else {
			if (doD.size() > suD.size()) {
				System.out.println("do");
			} else {
				System.out.println("su");
			}
		}
	}

	private static boolean isEmptyDeque() {
		if (doD.isEmpty() || suD.isEmpty()) {
			if (doD.size() == 0)
				System.out.println("su");
			else
				System.out.println("do");
			return true;
		}
		return false;
	}

	private static void suGet() {
		while (!dGround.isEmpty()) {
			suD.addFirst(dGround.remove(0));
		}
		while (!sGround.isEmpty()) {
			suD.addFirst(sGround.remove(0));
		}
	}

	private static void doGet() {
		while (!sGround.isEmpty()) {
			doD.addFirst(sGround.remove(0));
		}
		while (!dGround.isEmpty()) {
			doD.addFirst(dGround.remove(0));
		}
	}
}
