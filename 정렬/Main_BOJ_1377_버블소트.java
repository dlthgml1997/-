import java.io.*;
import java.util.*;

// 버블 정렬 몇회전만에 정렬이 되는지 (+1) 출력하는 문제이다. 
// 2회전만에 정렬이 된다면 3을 출력한다.

// 버블 정렬은 값을 뒤에서부터 고정시키는 특징이 있다.
// 뒤를 고정시키기 위해 뒤에 있던 값들은 앞으로 이동하게 된다.
// 즉, 값이 앞으로 이동한 최대 수만큼 회전을 돌았다는 것을 의미한다.
// 처음 인덱스와 정렬 후 인덱스를 비교하여 가장 차이가 큰 값 + 1을 출력하면 된다!

public class Main_BOJ_1377_버블소트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] a= new int[N][2];
		int[] temp = new int[N];
		for(int i=0; i<N; i++) {
			a[i][0] = i; 
			a[i][1] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(a, (o1, o2) -> {
			return o1[1] - o2[1];
		});
		
		
		int maxNum =0;
		for(int i=0; i< N; i++) {
			maxNum = Math.max(a[i][0] - i, maxNum);
		}
		
		System.out.println(maxNum + 1);
	}
}
