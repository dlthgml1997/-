class Solution {
	static char[] kakaos;
	static char[] lineUp;
	static boolean[] visited;
	static int total;
	static String[] checkData;
	static boolean flag;

	public int solution(int n, String[] data) {
		kakaos = new char[] { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
		lineUp = new char[8];
		visited = new boolean[8];
		total = 0;
		checkData = data;
		flag = true;

		permutation(0, lineUp);
		return total;
	}

	private static void permutation(int cnt, char[] lineUp) {
		if (cnt == 8) {
			if (checkCondition(lineUp))
				total++;
			return;
		}

		for (int i = 0; i < 8; i++) {
			if (visited[i])
				continue;

			lineUp[cnt] = kakaos[i];
			visited[i] = true;
			permutation(cnt + 1, lineUp);
			visited[i] = false;
		}
	}

	private static boolean checkCondition(char[] lineUp) {
		int idx1 = 0, idx2 = 0;
		for (String check2 : checkData) {
			char[] charData = check2.toCharArray();

			for (int i = 0; i < 8; i++) {
				if (lineUp[i] == charData[0]) {
					idx1 = i;
				} else if (lineUp[i] == charData[2]) {
					idx2 = i;
				}
			}

			switch (charData[3]) {
			case '=':
				if (Math.abs(idx1 - idx2) - 1 != (charData[4] - '0'))
					return false;
				break;
			case '>':
				if (Math.abs(idx1 - idx2) - 1 <= (charData[4] - '0'))
					return false;
				break;
			case '<':
				if (Math.abs(idx1 - idx2) - 1 >= (charData[4] - '0'))
					return false;
				break;
			}
		}
		return true;
	}
}