import java.io.*;

public class Main_BOJ_9663_NQueen {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] col;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		col = new int[N+1];
		setQueen(0);
		System.out.println(answer);
	}

	private static void setQueen(int row) {
		if(!isValid(row)) {
			return;
		}
		if(row == N) {
			answer++;
			return;
		}
		for(int i=1; i<=N; i++) {
			col[row+1] = i;
			setQueen(row+1);
		}
	}

	private static boolean isValid(int row) {
		for(int i=1; i< row; i++) {
			if(col[row] == col[i] || Math.abs(col[row]-col[i]) == row-i) return false;
		}
		return true;
	}
}