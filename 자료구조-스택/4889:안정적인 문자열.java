package com.sohee.stack;
// 제출 시 package 빼기

// 클래스 이름 Main으로 바꾸기

import java.util.*;

import java.io.*;

public class Solution4889 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Stack<String> stack;
	static int testCase = 1;

	public static void main(String[] args) throws IOException {
		while (true) {
			String input = br.readLine();
			if (input.contains("-"))
				break;

			int answer = 0;
			stack = new Stack<>();
			StringTokenizer st = new StringTokenizer(input);

			while (st.hasMoreTokens()) {
				String[] data = st.nextToken().split("");

				for (String d : data) {
					if (d.equals("{")) {
						stack.push(d);
					}
						
					else {
						if (stack.isEmpty()) {
							answer++;
							stack.push("{");
						} else {
							stack.pop();
						}
					}
				}
			}

			answer += (stack.size() / 2);
			System.out.printf("%d. %d\n", testCase, answer);
			testCase++;
		}

	}
}