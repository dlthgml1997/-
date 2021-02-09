import java.io.*;
import java.util.*;

public class Main_BOJ_1068_트리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] tree;
	static int N;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		int answer = 0;
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int node=0 ; node<N; node++) {
			int parent = Integer.parseInt(st.nextToken());
			while(parent>=0) {
				tree[parent]++;
				if(parent == 0) {
					break;
				}
				parent/=2;
			}
		}
		System.out.println(Arrays.toString(tree));
		
		int removeNode = Integer.parseInt(br.readLine());
		answer -= tree[removeNode];
		bw.append(answer+"");
		
		bw.flush();
		bw.close();
	}
	
	private static void remove(int node) {
		if(node > N-1) {
			return;
		}
		tree[node]--;
		if(tree[node] == 0) tree[node] = -1;
		remove(node*2+1);
		remove(node*2+2);
	}
}
