import java.util.*;
import java.io.*;

public class Main_BOJ_16198_에너지모으기 {
	static int N;
	static List<Integer> marbles = new ArrayList<Integer>();
	static int power = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i< N; i++) {
			marbles.add(Integer.parseInt(st.nextToken()));
		}
		 
		dfs(N, marbles, 0);
		System.out.println(power);
	}
	
	private static void dfs(int n, List<Integer> marbles, int sum) {
		if(n == 2) {
			power = Math.max(sum, power);
			return;
		}
		
		for(int i=1; i< marbles.size() -1; i++) {
			int s = marbles.get(i-1) * marbles.get(i+1);
			int temp = marbles.get(i); marbles.remove(i);
			dfs(n-1, marbles, sum+s);
			marbles.add(i, temp);
		}
	}
}
