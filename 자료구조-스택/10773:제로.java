package com.sohee.stack;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/10773

public class Solution10773 {

	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int answer = 0;
		int n;
		
		int N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			n = stoi(br.readLine());
			if (n == 0) {
				stack.pop();
				continue;
			}
			stack.add(n);
		}

		while (!stack.empty()) {
			answer += stack.pop();
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static int stoi(String input) {
		return Integer.parseInt(input);
	}
}