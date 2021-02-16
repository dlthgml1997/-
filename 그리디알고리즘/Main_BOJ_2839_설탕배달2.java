import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_2839_설탕배달2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<=N/3; i++) {
			for(int j = 0; j<=N/5; j++) {
				if( i*3 + j* 5 == N) {
					System.out.println(i+j);
					return;
				}
			}
		}
		System.out.println("-1");
	}
}