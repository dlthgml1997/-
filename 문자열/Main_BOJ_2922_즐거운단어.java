import java.io.*;
import java.util.*;

public class Main_BOJ_2922_즐거운단어 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] word;
	static long total = 0,N;
	public static void main(String[] args) throws Exception {
		word = br.readLine().toCharArray();
		N = word.length;
		
		for(int i=0; i<N; i++) {
			char c = word[i];
			if( c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
				word[i] = 'V';
			} else if( c != 'L' && c!= '_') {
				word[i] = 'C';
			}
		}
		
		System.out.println(makeJoyfulWord(0));
	}

	private static long makeJoyfulWord(int cnt) {
		while(true) {
			if(cnt == N) {
				int vowel =0, constant = 0; 
				boolean flagL = false;
				for(int i=0; i< N; i++) {
					if(word[i] == 'V') {
						vowel++;
						constant = 0;
					} else if(word[i] == 'C') {
						vowel = 0;
						constant++;
					} else if(word[i] == 'L') {
						vowel = 0;
						constant++;
						flagL = true;
					}
					
					if(vowel >= 3 || constant >= 3) return 0;
				}
				
				if(flagL) return 1;
				else return 0;
			}
			
			if(word[cnt] == '_') {
				total = 0;
				word[cnt] = 'C'; total += 20 * makeJoyfulWord(cnt+1);
				word[cnt] = 'V'; total += 5 * makeJoyfulWord(cnt+1);
				word[cnt] = 'L'; total += 1 * makeJoyfulWord(cnt+1);
				word[cnt] = '_';
				return total;
			}
			cnt++;
		}
		
	}
}
