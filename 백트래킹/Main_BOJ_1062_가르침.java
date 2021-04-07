import java.io.*;
import java.util.*;

public class Main_BOJ_1062_가르침 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Character>[] words;
	static int N, K, maxNum = 0;
	static boolean[] canUse = new boolean['z'-'a'+1];
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		K -= 5;
		
		canUse['a'-'a'] = true;
		canUse['n'-'a'] = true;
		canUse['t'-'a'] = true;
		canUse['i'-'a'] = true;
		canUse['c'-'a'] = true;
		
		words = new ArrayList[N];
		for(int i=0; i< words.length; i++) {
			words[i]= new ArrayList<Character>();
		}
		
		ArrayList<Character> temp;
		for(int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
			temp = new ArrayList<>(); 
			for(int j=4; j< input.length-4; j++) {
				temp.add(input[j]);
			}
			words[i] = temp;
		}
		
		if(K < 0) {
			System.out.println(0);
			return;
		}
		
		if(K == 21) {
			System.out.println(N);
			return;
		}
		
		combination(0, 0);
		System.out.println(maxNum);
	}

	private static void combination(int start, int cnt) {
		if(cnt == K) {
			//비교 함수 시작
			int total = getCanReadWordCount();
			maxNum = Math.max(maxNum, total);
			return;
		}
		
		for(int i= start; i< 26; i++) {
			if(canUse[i]) {
				continue;
			}
			canUse[i] = true;
			combination(i+1, cnt+1);
			canUse[i] = false;
		}
	}
	
	private static int getCanReadWordCount() {
		int total =0;
		boolean flag; 
		for(int i=0; i<words.length; i++) {
			flag = true;
			for(char c: words[i]) {
				if(!canUse[c-'a']) {
					flag = false;
					break;
				}
			}
			if(flag) {
				total++;
			}
		}
		return total;
	}
}
