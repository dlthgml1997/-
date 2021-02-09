import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_D4_1233_사칙연산유효성검사 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			boolean zeroFlag = false;
			N = stoi(br.readLine());
			if(N%2==0) {
				zeroFlag = true;
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String value = st.nextToken();
				if(st.hasMoreTokens()) {
					st.nextToken();
					if(st.hasMoreTokens()) st.nextToken();
					if(value.equals("+")||value.equals("/")||value.equals("*")||value.equals("-")) {
						continue;
					} else {
						zeroFlag = true;
					}
				} else {
					if(value.equals("+")||value.equals("/")||value.equals("*")||value.equals("-")) {
						zeroFlag = true;
					}
				}
			}
			if(zeroFlag) bw.append("#"+t+" 0\n");
			else bw.append("#"+t+" 1\n");
		}
		bw.flush();
		bw.close();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
