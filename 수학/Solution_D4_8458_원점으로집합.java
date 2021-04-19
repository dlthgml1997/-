import java.io.*;
import java.util.*;

public class Solution_D4_8458_원점으로집합 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dots;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			dots = new int[N];
			boolean evenF = false, oodF = false;
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int sum = Math.abs(a)+Math.abs(b);
				if(sum % 2 == 0) {
					evenF = true;
				} else {
					oodF = true;
				}
				dots[n] = sum;
			}
			
			if(evenF && oodF) {
				System.out.println("#"+tc+" -1");
				continue;
			}
			
			int num = 0; // 몇번째 이동인지 
			int move = 0; // 몇번 이동인지
			while(true) {
				move += num;
				boolean flag = true;
				for(int i=0; i<N; i++) {
					if(move >= dots[i]  && (move - dots[i]) % 2 == 0) {
						continue;
					}
					flag = false;
					if(!flag) {
						num++;
						break;
					}
				}
				if(flag) break;
			}
			System.out.println("#"+tc+" "+num);
		}
	}
}
