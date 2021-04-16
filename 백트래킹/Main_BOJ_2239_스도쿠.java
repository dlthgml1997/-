import java.io.*;

public class Main_BOJ_2239_스도쿠 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] map= new int[9][9];
	static boolean[][] rows= new boolean[9][10], cols= new boolean[9][10], squares = new boolean[9][10];
	
	public static void main(String[] args) throws Exception{
		for(int i=0; i<9; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				int value = input[j] - '0';
				map[i][j] = value;
				if(value != 0) {
					rows[i][value] = true;
					cols[j][value] = true;
					squares[getSquares(i,j)][value] = true;
				}
			}
		}
		
		fillMap(0, 0);
		bw.flush();
		bw.close();
	}
	
	private static boolean fillMap(int x, int y) throws Exception {
		int nx = x, ny= y + 1;
		if(ny == 9) {
			ny = 0;
			nx = x + 1;
		}
		
		if(x == 9) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					bw.append(map[i][j]+"");
				}
				if(i < 8) 
					bw.append("\n");
			}
			return true;
		}
		
		if(map[x][y] != 0) {
			return fillMap(nx, ny);
		} else {
			for(int i=1; i<=9 ;i++) {
				int s = getSquares(x,y);
				if(rows[x][i] || cols[y][i] 
						|| squares[s][i]) continue;
				
				rows[x][i] = true;
				cols[y][i] = true;
				squares[getSquares(x,y)][i] = true;
				map[x][y] = i;
				boolean flag = fillMap(nx, ny);
				if(flag) return true;
				rows[x][i] = false;
				cols[y][i] = false;
				squares[s][i] = false;
				map[x][y] = 0;
			}
		}
		return false;
	}
	
	private static int getSquares(int i, int j) {
		if(i < 3) {
			if(j < 3) {
				return 0;
			} else if(j <6) {
				return 1;
			} else {
				return 2;
			}
		} else if(i < 6) {
			if(j < 3) {
				return 3;	
			} else if(j <6) {
				return 4;
			} else {
				return 5;
			}
		} else {
			if(j < 3) {
				return 6;	
			} else if(j <6) {
				return 7;
			} else {
				return 8;
			}
		}
	}

}
