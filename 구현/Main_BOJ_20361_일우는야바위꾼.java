import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine()); // T 받아오기
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N =Integer.parseInt(st.nextToken()); // 종이컵의 수 
			int X =Integer.parseInt(st.nextToken()); // 간식이 왼쪽에서 몇번째 컵에 있는지 알려주는 수 
			int K =Integer.parseInt(st.nextToken()); // 컵의 위치를 맞바꾸는 횟수
			
			boolean[] cups= new boolean[N+1]; // 간식의 위치 여부를 나타낼 cups boolean 배열
			cups[X] = true;
			for(int k =0; k<K; k++) {
				st  = new StringTokenizer(br.readLine()); 
				int A =Integer.parseInt(st.nextToken()); // from A
				int B =Integer.parseInt(st.nextToken()); // to B
				if(cups[A] || cups[B]) { // 둘 다 false인 경우엔 위치를 바꿔도 false -> true 변화가 없기 때문에 위치 바꾸기를 실행하지 않는다.
					boolean temp = cups[A]; // temp에 A컵의 간식 여부를 담아 둔다.
					cups[A] = cups[B]; // A컵에 B컵의 간식 여부를 담고,
					cups[B] = temp; // B컵에 temp에 담아 둔 기존 A컵의 간식여부를 담는다.
				}
			}
			int answer = 0;
			for(int i=0; i<cups.length; i++) {
				if(cups[i]) // i번 째 컵에 간식이 들어있으면, cups[i] == true
					answer = i;
			}
			System.out.printf("#%d %d\n",t, answer);
		}
		
		
	}
}
