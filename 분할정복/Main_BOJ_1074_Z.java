import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1074_Z {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int num = 0;
	static int[][] maps;
	static int N;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		maps = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
		int i = Integer.parseInt(st.nextToken());
		int j = Integer.parseInt(st.nextToken());
		int x = (int) Math.pow(2, N) -2;
		int y = (int) Math.pow(2, N) -2;
		
		while (x >= 2 && y >= 2) {
			divide(x - 2, y);
			divide(x, y - 2);
			divide(x - 2, y - 2);
			divide(x, y);
			x -= 2;
			y -= 2;
		}
		System.out.println(maps[i][j]);
	}

	public static void divide(int x, int y) {
		
		maps[x][y++] = num++;
		maps[x++][y--] = num++;
		maps[x][y++] = num++;
		maps[x][y] = num++;
	}

}
