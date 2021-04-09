import java.util.*;
import java.io.*;

public class Main_BOJ_10800_컬러볼 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[] colorSum, sizeSum;
	static PriorityQueue<Ball> balls;
	
	private static class Ball implements Comparable<Ball>{
		int no;
		int color;
		int size;
		
		public Ball(int no, int c, int s) {
			this.no = no;
			this.color = c;
			this.size = s;
		}
		
		@Override
		public int compareTo(Ball o) { // 오차순
			return this.size - o.size;
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		colorSum = new int[N+1];
		sizeSum = new int[2001];
		balls = new PriorityQueue();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			Ball ball = new Ball(i,c,s);
			balls.add(ball);
		}
		
		int[] totals = new int[N];
		int total =0;
		int presize =0, precolor=0, preno = 0;
		while(!balls.isEmpty()) {
			Ball ball = balls.poll();
			colorSum[ball.color] += ball.size;
			sizeSum[ball.size] += ball.size;
			total += ball.size;
			if(ball.size == presize && ball.color == precolor) {
				totals[ball.no] = totals[preno];
			} else if(ball.color == precolor) {
				totals[ball.no] = total - colorSum[ball.color];
			} else if(ball.size == presize) {
				totals[ball.no] = total - sizeSum[ball.size];
			}
			
			presize = ball.size;
			precolor = ball.color;
			preno = ball.no;
		}
		
		for(int i=0; i<N; i++) {
			bw.append(totals[i]+"\n");
		}
		bw.flush();
		bw.close();
	}
}
