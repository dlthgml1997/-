package com.sohee.stack;
import java.util.*;
import java.io.*;

import java.util.Stack;

//https://www.acmicpc.net/problem/10828

public class Solution10828 {
	
	static Stack<Integer> stack = new Stack<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String s = st.nextToken();
			if(s.equals("push")) {
				stack.push(Integer.parseInt(st.nextToken()));
			} else if(s.equals("pop")) {
				if (stack.empty()) {
					System.out.println(-1); continue;
				}
				System.out.println(stack.pop());
			} 
			else if(s.equals("size")) {
				System.out.println(stack.size());
			} 
			else if(s.equals("empty")) {
				if (stack.empty()) {
					System.out.println(1); continue;
				}
				System.out.println(0);
			} 
			else if(s.equals("top")) {
				if (stack.empty()) {
					System.out.println(-1); continue;
				}
				System.out.println(stack.peek());
			}
		}
	}

}
