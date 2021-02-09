import java.io.*;
import java.util.*;

// 트리 + 구현
public class Main_BOJ_13116_30번 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int A, B;

	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			int minNum = Math.min(A, B);
			if(minNum == A) {
				while(true) {
					if(B - A < A) break;
					B/=2;
				}
			} else {
				while(true) {
					if(A - B < B) break;
					A/=2;
				}
			}
			
			while (A != B) {
				if(A > B) {
					A /= 2;
					if(A== B) break;
					B /= 2;
				}
				else {
					B /=2;
					if(A == B) break;
					A/=2;
				}
			}
			bw.append((A*10)+"\n");
		}
		bw.flush();
		bw.close();
	}
}
