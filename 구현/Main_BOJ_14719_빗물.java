import java.util.*;
import java.io.*;

public class Main_BOJ_14719_빗물 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int h, w;
	static int maxNum = 0, maxIdx = -1;
	static int total = 0;
	static int[] block;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		block = new int[w];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < w; i++) {
			block[i] = Integer.parseInt(st.nextToken());
			if(block[i] > maxNum) {
				maxNum = block[i];
				maxIdx = i;
			}
		}
		
		if(maxIdx == -1) {
			System.out.println(0);
			return;
		}

		maxNum = block[0];
		for (int i = 1; i <= maxIdx; i++) {
			if (maxNum != 0 && block[i] < maxNum) {
				total += maxNum - block[i];
			}

			if(maxNum < block[i]) {
				maxNum = block[i];
			}
		}
		
		maxNum = block[w-1];
		for(int i= w-2; i>= maxIdx; i--) {
			if (maxNum != 0 && block[i] < maxNum) {
				total += maxNum - block[i];
			}
			if(maxNum < block[i]) {
				maxNum = block[i];
			}
		}
		
		System.out.println(total);
	}
}