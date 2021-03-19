import java.io.*;
import java.util.*;

public class Main_BOJ_9177_단어섞기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static char[] word1, word2, word3;
	static int len1, len2, len3;
	static int N;
	static boolean answer;
	static String answerString;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int t = 1; t <= N; t++) {
			st = new StringTokenizer(br.readLine());
			word1 = st.nextToken().toCharArray();
			word2 = st.nextToken().toCharArray();
			word3 = st.nextToken().toCharArray();
			len1 = word1.length;
			len2 = word2.length;
			len3 = len1 + len2;
			answer = false;

			Set<Character> set = new HashSet<>();
			for (int i = 0; i < Math.max(len1, len2); i++) {
				if(i < len1) set.add(word1[i]);
				if(i < len2) set.add(word2[i]);
			}
			boolean flag = true;
			for (int i = 0; i < len3; i++) {
				if (!set.contains(word3[i])) {
					flag = false;
					break;
				}
			}
			if (!flag) {
				bw.append("Data set " + t + ": no\n");
				continue;
			}

			func(0, 0, 0);

			answerString = answer ? "yes" : "no";
			bw.append("Data set " + t + ": " + answerString + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void func(int idx1, int idx2, int cnt) {
		if(answer) return; 
		
		if (idx1 + idx2 == len3) {
			answer = true;
			return;
		}

		if (idx1 < len1 && word3[cnt] == word1[idx1])
			func(idx1 + 1, idx2, cnt + 1);
		if (idx2 < len2 && word3[cnt] == word2[idx2])
			func(idx1, idx2 + 1, cnt + 1);
	}
}
