import java.util.*;
import java.io.*;

public class Solution_D3_4789_성공적인공연기획 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int cnt, answer;
	static char[] people;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			people = br.readLine().toCharArray();
			cnt =0; answer =0;
			
			for(int i=0; i< people.length; i++) {
				if(cnt < i && people[i] - '0' > 0) {
					while(cnt < i) {
						answer++;
						cnt++;
					}
				}
				cnt += people[i] - '0';
			}
			System.out.println("#"+t+" "+answer);
		}
	}
}
