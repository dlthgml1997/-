// 제출 시 package 빼기
// 클래스 이름 Main으로 바꾸기

import java.util.*;
import java.io.*;

/**
 * solved 크로아티아 알파벳
 * https://www.acmicpc.net/problem/2941
 * @author leesohee
 *
 */
public class Solution2941 {
	static String[] kroatia = new String[] {"c=","c-","dz=","d-","lj","nj","s=","z="};
	public static void main(String[] args) throws Exception {
		printResult(getLengthOfKroatiaWord(makeInput()));
	}

	private static int getLengthOfKroatiaWord(String word) {
		// @param word: Make algorithm using variable `word`
		
		for(String c: kroatia) {
			word = word.replace(c,".");
		}
		return word.length();
	}

	private static String makeInput() { return new Scanner(System.in).next();}
	private static void printResult(int lengthOfKroatiaWord) { System.out.println(lengthOfKroatiaWord);
	}
}