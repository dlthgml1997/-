package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 * @author leesohee
 *
 */
public class Solution1221 {
	public static void main(String[] args) throws IOException {
		String [] numbers= new String[]{"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
		
//		System.setIn(new FileInputStream("GNS_test_input.txt")); 
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		for(int t = 0; t < T; t++) {
            int [] cnt = new int[10];
			StringTokenizer st = new StringTokenizer(br.readLine());
			String testCase = st.nextToken();
			int N = stoi(st.nextToken());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i=0; i< N; i++) {
				String input = st2.nextToken();
				if (input.equals("ZRO")) cnt[0]++;
				else if (input.equals("ONE")) cnt[1]++;
				else if (input.equals("TWO")) cnt[2]++;
				else if (input.equals("THR")) cnt[3]++;
				else if (input.equals("FOR")) cnt[4]++;
				else if (input.equals("FIV")) cnt[5]++;
				else if (input.equals("SIX")) cnt[6]++;
				else if (input.equals("SVN")) cnt[7]++;
				else if (input.equals("EGT")) cnt[8]++;
				else if (input.equals("NIN")) cnt[9]++;
			}
			System.out.println(testCase+" ");
			int now = 0; // 0 부터 시작
			for (int num : cnt) {
				while(num > 0) {
					System.out.print(numbers[now]+" ");
					num--;
				}
				now++;
			}
			System.out.println();
		}
	}

	private static int stoi(String input) {
		return Integer.valueOf(input);
	}
}