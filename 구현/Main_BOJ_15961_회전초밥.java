import java.io.*;
import java.util.*;

public class Main_BOJ_15961_회전초밥 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, typeCnt, maxEatCnt, coupon;
	static int cnt= 0, maxNum = 0;
	static int[] belt, type;
	static boolean containCoupon;
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		typeCnt = Integer.parseInt(st.nextToken());
		maxEatCnt = Integer.parseInt(st.nextToken());
		coupon = Integer.parseInt(st.nextToken());
		
		belt = new int[N];
		type = new int[typeCnt+1];
		
		for(int i=0; i<N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<maxEatCnt; i++) {
			int t = belt[i%N];
			if(t == coupon) containCoupon = true;
			if(type[t] == 0) cnt++;
			type[t] += 1;
		}

		int i = 0;
		int start = 0;
		int end = (maxEatCnt - 1) % N;
		while(true) {
			type[belt[start]] -= 1;
			if(type[belt[start]]== 0) cnt--;
			
			i++;
			start = i % N;
			end = (i + maxEatCnt -1) % N;
			if(start == 0 && end == (maxEatCnt - 1) % N) break;
			
			if(type[belt[end]] == 0) cnt++;
			type[belt[end]] += 1;
			
			if(type[coupon] == 0) containCoupon = false;
			else containCoupon = true;
			
			if(cnt >= maxNum) {
				if(containCoupon) {
					maxNum = cnt;
				} else {
					maxNum = cnt+1;
				}
			}
		}
		System.out.println(maxNum);
	}
}
