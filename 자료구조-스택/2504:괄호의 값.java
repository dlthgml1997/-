package com.sohee.stack;
// 제출 시 package 빼기
// 클래스 이름 Main으로 바꾸기

import java.util.*;

import java.io.*;
 /**
  * unsolved
  * https://www.acmicpc.net/problem/2504
  * @author leesohee
  *
  */
public class Solution2504 { //unsolved
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Stack<Character> stack = new Stack<>();
	static String string; 
	static char[] chars;
	
	static int cntX = 0;
	static int cntY = 0;
	static int mX = 0;
	static int mY = 0;
	static int answer = 0;
	static int x = 0;
	static int y= 0;
	public static void main(String[] args) throws IOException {
		input();
		for(char c :chars) {
			if(c == '(') {
				stack.add(c);
				cntX++;
			} else if (c== ')') {
				if(stack.isEmpty()) {
					System.out.println(0);return;
				}
				stack.pop();
				mX = cntX;
				cntX--;
				if(cntX == 0) {
					x = mX * 2;
					if(cntY > 0) continue;
					answer+= (mX * 2);
				}
			} else if(c == '[') {
				stack.add(c);
				cntY++;
			} else if(c == ']') {
				if(stack.isEmpty()) {
					System.out.println(0);return;
				}
				stack.pop();
				mY = cntY;
				cntY--;
				if(cntY == 0) {
					if(cntX > 0) continue;
					answer+= (mY * 3) + mX;
				}
			}
		}
		
		if(!stack.isEmpty()) answer = 0;
		System.out.println(answer);
	}
	
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		chars = st.nextToken().toCharArray();
	}
}