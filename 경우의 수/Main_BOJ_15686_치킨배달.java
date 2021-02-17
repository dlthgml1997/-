import java.util.*;
import java.io.*;

public class Main_BOJ_15686_치킨배달 {
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static List<int[]> chicken, house;
	static int total =0, capitalChickenDir = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		input();
		combination(0, 0, new int[M][2]);
		System.out.println(capitalChickenDir);
	}
	
	private static void combination(int cnt, int start, int[][] nums) { // 주어진 치킨 집 개수에서 M개를 골라내는 조합
		if(cnt == M) {
			calcDiff(nums); // 조합이 생성될 때 마다 거리 계산을 실행한다.
			capitalChickenDir = Math.min(capitalChickenDir, total); // 조합이 생성될 때마다 도시의 치킨 거리를 최솟값으로 갱신
			total = 0; // 도시의 치킨 거리 초기화
			return;
		}
		
		for(int i= start; i< chicken.size(); i++) {
			nums[cnt] = chicken.get(i);
			combination(cnt+1, i+1, nums);
		}
	}

	private static void calcDiff(int[][] nums) {
		int chickenDir = Integer.MAX_VALUE;  // 한 집의 치킨 거리 초기화
		for(int i=0 ; i < house.size(); i++) {
			chickenDir = Integer.MAX_VALUE;
			int hx = house.get(i)[0];
			int hy = house.get(i)[1];
			for(int[] chickenPos : nums) { // 치킨집의 좌표를 하나씩 빼오면서
				chickenDir = Math.min(chickenDir, Math.abs(hx-chickenPos[0])+Math.abs(hy - chickenPos[1])); // 한 집의 치킨 거리 최솟값으로 갱신
			}
			total+= chickenDir; // 도시의 치킨 거리에 한 집의 치킨 거리를 더한다. 
		}
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		chicken = new ArrayList<int[]>();
		house = new ArrayList<int[]>();
		for(int i=1; i<=N; i++) { // 좌표 인덱스는 1부터 N까지 (행)
			st =new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) { // 좌표 인덱스는 1부터 N까지 (열)
				int input = stoi(st.nextToken()); 
				if(input == 1) house.add(new int[] {i,j}); // 1이면 house 배열에 add
				else if(input == 2) chicken.add(new int[] {i,j}); // 2면 chicken 배열에 add
			}
		}
	}
	
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
