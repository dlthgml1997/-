import java.util.*;
import java.io.*;

public class Main_BOJ_3107_IPv6 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		char[] pressedStr = br.readLine().toCharArray();
		int cnt = 0;
		String temp = "";
		String str = "";
		String answer = "";
		// 조건 1 복원
		for (int i = 0; i < pressedStr.length; i++) {
			char value = pressedStr[i];
			if (value == ':') {
				if (i == 0) {
					answer += ":";
					continue;
				}

				if (i > 0 && pressedStr[i - 1] == ':') {
					answer += ":";
					continue;
				}

				for (int j = 0; j < 4 - temp.length(); j++) {
					str += '0';
				}

				answer += str;
				answer += temp + ":";
				cnt = 0;
				str = "";
				temp = "";
			} else {
				temp += value;
				cnt++;
			}
		}
		// 조건 1 마지막 그룹 복원
		if (pressedStr[pressedStr.length - 1] != ':') { // 마지막이 : 이라는것은 :: 으로 끝난다는 것을 의미한다.
			for (int j = 0; j < 4 - temp.length(); j++) {
				str += '0';
			}
			answer += str;
			answer += temp;
		}

		// 조건 2 복원
		temp = "";
		if (answer.length() < 39) { // 콜론 7개 + 16진수 32자리 = 39, 완성된 주소가 아니면 길이가 39보다 작다.
			for (int i = 0; i < 41 - answer.length(); i++) {
				if (answer.indexOf("::") == 0 && i == 0) // :: 로 시작하면 앞에 :를 추가하지 않는다.
					continue;
				if (i % 5 == 0) {
					temp += ":";
				} else {
					temp += "0";
				}
			}
			if (answer.indexOf("::") == 0) // :: 로 시작하면 끝에 :를 추가해준다. 
				temp += ":";
			answer = answer.replaceAll("[:]{2,}", temp); // :: 로 변경
		}
		System.out.println(answer);
	}
}
