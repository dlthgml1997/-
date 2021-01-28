// 제출 시 package 빼기
// 클래스 이름 Main으로 바꾸기

import java.util.*;
import java.io.*;

/**
 * solved 문자열
 * https://www.acmicpc.net/problem/1120
 * @author leesohee
 *
 */
public class Solution1120 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println(MinimumDiffBetweenAB(sc.next(), sc.next()));
		sc.close();
	}

	private static int MinimumDiffBetweenAB(String A, String B) {
		if(A.length() == B.length()) {
			return getDif(A, B, 0);
		}
		int sub = B.length() - A.length();
		int aN = 0;
		int minNum = 50;
		while(aN <= sub) {
			minNum = Math.min(minNum, getDif(A, B, aN++));
		}

		return minNum; // @return: diff between A and B
	}

	private static int getDif(String A, String B, int j) {
		int cnt = 0;
		for(int i = 0 ; i< A.length(); i++, j++) {
				if(B.charAt(j) != A.charAt(i)) cnt++;
			}
		return cnt;
	}
}