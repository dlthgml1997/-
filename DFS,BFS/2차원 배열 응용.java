package com.ssafy.ws02.step3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, N;
	static char[][] map;
	static int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
	static Queue<Node> q = new LinkedList<>();
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi(br.readLine());
		
		for(int n=1; n<= T; n++) {
			max = 0;
			N = stoi(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
					if (map[i][j] == 'B') q.add(new Node(i,j));
				}
			}
			bfs();
			System.out.println("#"+n+" "+max);
		}
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			int tx = tmp.x;
			int ty = tmp.y;
			boolean flag = true;
			
			for (int i = 0; i < 8; i++) {
				int nx = tx + dx[i];
				int ny = ty + dy[i];
				if (!isRange(nx, ny)) continue;
				if (map[nx][ny] == 'G') {
					max = Math.max(max, 2);
					flag = false;
					break;
				}
			}
			
			if (flag) {
				max = Math.max(max, countBuilding(tx, ty));
			}
		}
	}
	
	
	static int countBuilding(int x, int y) {
		int cnt = 0;
		// 세로에 있는 빌딩
		for (int i = 0; i < N; i++) {
			if (map[i][y] =='B') cnt++; 
		}
		
		// 가로에 있는 빌딩
		for (int j = 0; j < N; j++) {
			if (map[x][j] =='B') cnt++; 
		}
		return cnt - 1;
	}

	static boolean isRange(int x, int y) {
		return x<0 || x>=N || y<0 || y>=N ? false : true;
	}

	static int stoi(String input) {
		return Integer.valueOf(input); // parseInt 와 동일
	}
}

class Node {
	int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}