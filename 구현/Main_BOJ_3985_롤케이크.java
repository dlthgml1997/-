import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3985_롤케이크 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int L, N, maxE = 0, maxR=0;
		int expect=0, real=0;
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		int[] rollCake = new int[L+1];
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			int p =Integer.parseInt(st.nextToken());
			int k =Integer.parseInt(st.nextToken());
			
			if(maxE < k-p +1 ) {
				maxE = k-p +1;
				expect = i+1;
			}
			
			int cnt =0;
			for(int j=p; j<=k; j++) {
				if(rollCake[j] == 0) {
					rollCake[j] = i+1;
					cnt++;
				}
			}
			
			if(maxR < cnt) {
				maxR = cnt;
				real = i+1;
			}
		}
		
		System.out.println(expect);
		System.out.println(real);
		
		
	}
}
