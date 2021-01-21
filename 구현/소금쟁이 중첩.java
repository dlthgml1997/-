package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  소금쟁이 중첩
 * @author leesohee
 *
 */
public class Solution22 {
	static int T, M, N;
	static int[][] water;
	static int[][] striders;
	static int answer;
	static int[] dx = {0, -1, 1, 0, 0}; // {-, 상, 하, 좌, 우}
	static int[] dy = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi(br.readLine());
		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken()); // 연못의 크기
			N = stoi(st.nextToken()); // 소금쟁이의 수
			water = new int[M][M];
			striders = new int[N][3];
			for (int j = 0; j < N; j++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				striders[j][0] = stoi(st2.nextToken()); // 행
				striders[j][1] = stoi(st2.nextToken()); // 열
				striders[j][2] = stoi(st2.nextToken()); // 방향
			}
			System.out.println("#"+i+" "+move(striders));
		}
	}
	
	static int move(int[][] striders) {
		answer = N;
		for (int[] strider : striders) { // 한 마리씩 이동
			if(water[strider[0]][strider[1]] == 1) { // 처음 위치에 소금쟁이가 있으면
				answer--;
				continue;
			}
			for(int k =3; k >= 1; k--) {
				int nx = strider[0] + dx[strider[2]] * k;
				int ny = strider[1] + dy[strider[2]] * k;
				if(!isRange(nx, ny)) { // 연못을 벗어나면
					answer--;
					break;
				}
				if(water[nx][ny] == 1) { // 이동한 곳에 소금쟁이가 있으면
					answer--;
					break;
				} else {
					strider[0] = nx; // 이동한 곳이 소금쟁이의 현재 위치가 된다. 
					strider[1] = ny;
				}
				if(k == 1) { // 마지막 이동이면
					water[nx][ny] = 1; // 소금쟁이의 최종 위치가 된다. (생존)
				}
			}
		}
		return answer;
	}

	static boolean isRange(int x, int y) {
		return x<0 || x>=M || y<0 || y>=M ? false : true;
	}
	
	static int stoi(String string) {
		return Integer.valueOf(string);
	}
}
