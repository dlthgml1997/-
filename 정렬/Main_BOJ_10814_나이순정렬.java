import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main_BOJ_10814_나이순정렬 {
	private static class Customer implements Comparable<Customer>{
		int age;
		int turn;
		String name;
		
		Customer(int turn, int age, String name) {
			this.turn = turn;
			this.age = age;
			this.name = name;
		}
		
		@Override
		public int compareTo(Customer o) {
			if(o.age == this.age) {
				return this.turn - o.turn;
			} else {
				return this.age - o.age;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<Customer> customers = new ArrayList<>();
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			customers.add(new Customer(i, Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		Collections.sort(customers);
		
		for(int i=0; i< N; i++) {
			bw.append(customers.get(i).age + " " + customers.get(i).name+ "\n");
		}
		bw.flush();
		bw.close();
	}
}
