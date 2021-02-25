import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_8320_직사각형을만드는방법 {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		int total = 0;
		for(int i=1; i<= N; i++) {
			total += getCnt(i);
		}
		System.out.println(total);
	}

	private static int getCnt(int num) {
		int count =0;
		for(int i=1; i<= Math.sqrt(num); i++) {
			for(int j=1; j<= N; j++) {
				if(i*j == num) count++;
			}
		}
		return count;
	}
}
