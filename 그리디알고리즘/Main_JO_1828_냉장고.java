import java.io.*;
import java.util.*;

public class Main_JO_1828_냉장고 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int RefreNum;
	private static class Temperatures implements Comparable<Temperatures> {
		int min;
		int max;
		
		public Temperatures(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Temperatures [min=");
			builder.append(min);
			builder.append(", max=");
			builder.append(max);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int compareTo(Temperatures o) {
			int diff = this.max - o.max;
			return diff != 0 ? diff : this.min- o.min;
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = stoi(br.readLine());
		Temperatures[] temps = new Temperatures[N];
		RefreNum = N;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			temps[i] = new Temperatures(stoi(st.nextToken()), stoi(st.nextToken()));
		}
		Arrays.sort(temps);
		int maxNum = temps[0].max;
		for(int i=0 ; i < N-1 ; i++) {
			if(maxNum >= temps[i+1].min) {
				RefreNum--;
			} else {
				maxNum = temps[i+1].max;
			}
		}
		
		System.out.println(RefreNum+"");
	}
	
	static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
