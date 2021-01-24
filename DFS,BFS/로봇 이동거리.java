package com.ssafy.algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @since 2021/01/24
 * @author leesohee
 * 1월 월말평가 대비 - 로봇 이동거리 
 */

public class Solution0124 {
	static int T;
	static int N;
	static char[][] space;
	static Queue<Node> a = new LinkedList<>();
	static Queue<Node> b = new LinkedList<>();
	static Queue<Node> c = new LinkedList<>();
	static int[] dx = {0, 0, -1, 1}; // 우, 좌, 상, 하
	static int[] dy = {1, -1, 0, 0}; 
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			answer = 0;
			N = sc.nextInt();
			space = new char[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					space[i][j] = sc.next().charAt(0);
					switch (space[i][j]) {
						case 'A':
							a.add(new Node(i, j));
							break;
						case 'B':
							b.add(new Node(i, j));
							break;
						case 'C':
							c.add(new Node(i, j));
							break;
						default:
							break;
					}
				}
			}
			moveA(a);
			moveB(b);
			moveC(c);

			System.out.printf("#%d %d", t, answer);
			System.out.println();
		}
	} // main
	
	static void moveA(Queue<Node> a) { // 우
		while (!a.isEmpty()) {
			Node node = a.poll();
			int x = node.x;
			int y = node.y;
			for(int i =0; i <1; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				while (isRange(nx, ny) && space[nx][ny] == 'S') {
					nx = nx + dx[i];
					ny = ny + dy[i];
					answer += 1;
				}
			}
		}
	} // moveA

	static void moveB(Queue<Node> b) { // 우, 좌
		while (!b.isEmpty()) {
			Node node = b.poll();
			int x = node.x;
			int y = node.y;
			for(int i =0; i <2; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				while (isRange(nx, ny) && space[nx][ny] == 'S') {
					nx = nx + dx[i];
					ny = ny + dy[i];
					answer += 1;
				}
			}
		}
	} // moveB

	static void moveC(Queue<Node> c) { // 우, 좌
		while (!c.isEmpty()) {
			Node node = c.poll();
			int x = node.x;
			int y = node.y;
			for(int i =0; i <4; i++) {
				int nx = x + dx[i]; // (*)
				int ny = y + dy[i];
				
				while (isRange(nx, ny) && space[nx][ny] == 'S') {
					nx = nx + dx[i];
					ny = ny + dy[i];
					answer += 1;
				}
			}
		}
	} // moveC

	static boolean isRange(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N ? true : false;
	} // isRange

} // Main

class Node {
	int x; // 오답 원인 : static으로 선언하면 다른 메소드들과 변수명이 겹쳤을 때,  
	int y; // 			(*) 부분과 같은 곳에서 마지막 add된 x, y 값이 쓰임 주의!!

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
