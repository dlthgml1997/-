import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_D3_2805_농작물수확하기 {
	static int T, N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][] farm;
	static int answer;

	public static void main(String[] args) throws IOException {
		T = stoi(br.readLine());
		for (int t = 1; t <= T; t++) {
			func();
			bw.append("#" + t + " " + answer + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void func() throws IOException {
		int cnt = 0;
		answer = 0;
		
		N = stoi(br.readLine());
		farm = new int[N][N];
		int center = N / 2;

		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if(j >= center - cnt && j <= center + cnt) {
					answer += ch[j] - '0';
				}	
			}
			if (i < center)	cnt++;
			else cnt--;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
