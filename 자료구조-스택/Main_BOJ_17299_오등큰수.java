import java.util.*;
import java.io.*;

public class Main_BOJ_17299_오등큰수 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[] num;
	static Stack<Integer> stack= new Stack<Integer>();
	static int[] f = new int[1000001];
	static int[] ngf;
	static int a,b;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		ngf = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			f[num[i]]++;
		}
		stack.add(0);
		for(int i=1 ; i<num.length; i++) {
			while(!stack.isEmpty() && f[num[stack.peek()]] < f[num[i]]) { // 오른쪽이 더 클때 자신을 다 뺸다. 
				ngf[stack.pop()] = num[i];
			}
			
			stack.add(i);
		}
		
		while(!stack.isEmpty()) {
			
			ngf[stack.pop()] = -1;
		}
		
		for(int answer: ngf) {
			bw.append(answer + " ");
		}
		bw.flush();
		bw.close();
	}
}
