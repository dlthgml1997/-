import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D4_1861_정사각형방_DP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N;
	static int[][] rooms;
	static Stack<Location> stack;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int[] dp;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		T = stoi(br.readLine());
		for(int t = 1; t<=T; t++) {
			int maxNum = 0;
			int roomNum = 1000000;
			input();
			for(int i =0; i< N; i++) {
				for(int j=0; j< N; j++) {
					if(dp[rooms[i][j]] ==0) {
						int num = move(i,j);
						if(num > maxNum) {
							roomNum = rooms[i][j];
							maxNum = num;
						}
						else if(num == maxNum) roomNum = Math.min(roomNum, rooms[i][j]);
					}
				}
			}
			bw.append("#"+t+" "+roomNum+" "+(maxNum+1)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static int move(int x, int y) {
			for(int dir= 0; dir<4; dir++) {
				int nx = x+ dx[dir];
				int ny = y+ dy[dir];
				if(!isRange(nx,ny) || rooms[nx][ny] -rooms[x][y] != 1) continue;
				dp[rooms[x][y]] = move(nx,ny) +1;
			}
			return dp[rooms[x][y]];
		}

	private static void input() throws Exception {
		N = stoi(br.readLine());
		rooms= new int[N][N];
		dp = new int[N*N+1];
		stack = new Stack<>();
		for(int i =0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0; j<N; j++) {
				rooms[i][j] = stoi(st.nextToken());
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y <0 || y >= N ? false : true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}

class Pos {
	int x;
	int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Pos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
}

