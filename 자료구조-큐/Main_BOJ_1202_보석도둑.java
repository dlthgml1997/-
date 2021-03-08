import java.util.*;
import java.io.*;

public class Main_BOJ_1202_보석도둑 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static int[] bag;
	static boolean[] visited;
	static long total =0;
	static ArrayList<Dia> dias;// 무게 기준정렬
	static PriorityQueue<Dia> diaPq; // 가치 기준 정렬 
	
	private static class Dia implements Comparable<Dia>{
		int weight;
		int price;
		
		public Dia(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
		
		@Override
		public int compareTo(Dia o) { // price 기준 내림차 순 
			return o.price - this.price;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bag = new int[K];
		visited = new boolean[K]; 
		diaPq = new PriorityQueue<>();
		dias = new ArrayList<>();
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			dias.add(new Dia(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for(int i=0; i<K; i++) {
			bag[i] =  Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag); // 오름차순
		
		Collections.sort(dias, (o1, o2) -> { // 앞에서 뒤를 빼면 오름차순
			return o1.weight - o2.weight;
		}); //무게기준 오름차순
	
		int j=0;
		for(int i=0; i< K; i++) {
			while(j < N && dias.get(j).weight <= bag[i]) {
				diaPq.add(dias.get(j++));
			}
			if(diaPq.isEmpty()) continue;
			total+= diaPq.poll().price;	
		}
		
		System.out.println(total);
	}
}
