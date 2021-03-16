import java.io.*;
import java.util.*;

public class Main_BOJ_1759_암호만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int L,C;
	static char[] line;
	static char[] result;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		result = new char[L];
		line = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			line[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(line);
		
		combination(0,0,0);
		bw.flush();
		bw.close();
	}
	
	private static void combination(int cnt, int start, int vowelCnt) throws Exception {
		if(cnt == L) {
			if(L - vowelCnt < 2|| vowelCnt < 1) {
				return;
			}
			printLine(result);
			return;
		}
		
		for(int i = start; i<C; i++) {
			if(cnt == 0 && i >= C-L+1) {
				return;
			}
			result[cnt] = line[i];
			if(line[i] == 'a' ||line[i] == 'e' ||line[i] == 'i' ||line[i] == 'o' ||line[i] == 'u') {
				combination(cnt+1, i+1, vowelCnt+1);
			}
			else {
				combination(cnt+1, i+1, vowelCnt);
			}
		}
	}

	private static void printLine(char[] chars) throws Exception{
		for(char c: chars) {
			bw.append(c+"");	
		}
		bw.append("\n");
	}
}
