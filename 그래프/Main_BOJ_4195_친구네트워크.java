import java.io.*;
import java.util.*;

public class Main_BOJ_4195_친구네트워크 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int F, R;
	static HashMap<String, Integer> idxInfo = new HashMap<>(); // 이름, 인덱스
	static ArrayList<Integer> parent = new ArrayList<>();
	static ArrayList<Integer> friend = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		F = Integer.parseInt(br.readLine());
		for (int i = 0; i < F; i++) {
			R = Integer.parseInt(br.readLine());
			int idx = 0;
			int idxa = -1;
			int idxb = -1;
			friend.clear();
			parent.clear();
			idxInfo.clear();
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();
				if (!idxInfo.containsKey(A)) {
					idxInfo.put(A, idx);
					parent.add(idx);    
					friend.add(1);
					idxa = idx++;
				} else {
					idxa = findParent(idxInfo.get(A));
				}
				if (!idxInfo.containsKey(B)) {
					idxInfo.put(B, idx);
					parent.add(idx);
					friend.add(1);
					idxb = idx++;
				} else {
					idxb = findParent(idxInfo.get(B));
				}
				System.out.println(union(idxa, idxb));
			}
		}
	}

	private static int findParent(int x) {
		if (x == parent.get(x))
			return parent.set(x, x);
		else {
			return findParent(parent.get(x));
		}
	}

	private static int union(int a, int b) { // 친구가 아니면 친구로 만들어라
		int af = friend.get(a);
		int bf = friend.get(b);

		if (a != b) {
			if (a > b) {
				parent.set(a, b);
				friend.set(a, af+bf);
				friend.set(b, af+bf);
				return friend.get(a);
			}
			else {
				parent.set(b, a);
				friend.set(a, af+bf);
				friend.set(b, af+bf);
				return friend.get(b);
			}
		}
		return friend.get(a);
	}
}
