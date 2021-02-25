import java.util.*;
import java.io.*;

public class Main_BOJ_2304_창고다각형 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, max = 0, maxIdx;
	static int[] list = new int[1001];
	
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if(max < h) {
				max = h;
				maxIdx = x;
			}
			list[x] = h;
		}
		int total = max;
		int tempH = 0;
		for(int i=0; i<maxIdx; i++) {
			if(tempH < list[i]) {
				tempH = list[i];
			}
			total += tempH;
		}
		tempH =0;
		for(int i=list.length-1; i> maxIdx; i--) {
			if(tempH < list[i]) {
				tempH = list[i];
			}
			total += tempH;
		}
		System.out.println(total);
	}
}
