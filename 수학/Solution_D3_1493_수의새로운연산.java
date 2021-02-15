import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_D3_1493_수의새로운연산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static HashMap<Integer, int[]> map = new HashMap<>();
	static HashMap<int[], Integer> map2 = new HashMap<>();
	static int[][] maps = new int[301][301];

	public static void main(String[] args) throws Exception {
		int num = 1;

		int limit = 1;
		int i=1;
		int j=1;
		while (i<=300 && j <= 300) {
			j = limit;
			for (i = 1; i <= limit; i++) {
				maps[i][j--] = num++;
			}
			limit++;
		}
		
		int T = stoi(br.readLine());
		for(int t =1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int p = stoi(st.nextToken());
			int q = stoi(st.nextToken());
			System.out.println("#"+ t+ " " + star(p,q));
		}
	}

	private static int star(int p, int q) {
		int pI=0, pJ=0;
		int qI=0, qJ=0;
		boolean pFlag = false, qFlag = false;
		for(int i = 1 ; i < maps.length; i++) {
			for(int j= 1; j< maps[i].length; j++) {
				if(maps[i][j] == p) {
					pI = i;
					pJ = j;
					pFlag = true;
				} 
				if(maps[i][j] == q) {
					qI = i;
					qJ = j;
					qFlag = true;
				}
				if(pFlag & qFlag) break;
			}
			if(pFlag & qFlag) break;
		}
		int a = pI + qI;
		int b = pJ + qJ;
		return maps[a][b];
	}


	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
	
}
