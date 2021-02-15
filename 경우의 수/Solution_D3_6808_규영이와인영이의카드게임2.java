/**
 *규영이와 인영이는 1에서 18까지의 수가 적힌 18장의 카드로 게임을 하고 있다.

한 번의 게임에 둘은 카드를 잘 섞어 9장씩 카드를 나눈다. 그리고 아홉 라운드에 걸쳐 게임을 진행한다.

한 라운드에는 한 장씩 카드를 낸 다음 두 사람이 낸 카드에 적힌 수를 비교해서 점수를 계산한다.

높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고,

낮은 수가 적힌 카드를 낸 사람은 아무런 점수도 얻을 수 없다.

이렇게 아홉 라운드를 끝내고 총점을 따졌을 때, 총점이 더 높은 사람이 이 게임의 승자가 된다.

두 사람의 총점이 같으면 무승부이다.

이번 게임에 규영이가 받은 9장의 카드에 적힌 수가 주어진다.

규영이가 내는 카드의 순서를 고정하면, 인영이가 어떻게 카드를 내는지에 따른 9!가지 순서에 따라

규영이의 승패가 정해질 것이다.

이 때, 규영이가 이기는 경우와 지는 경우가 총 몇 가지 인지 구하는 프로그램을 작성하라.


[입력]

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 아홉 개의 정수가 공백으로 구분되어 주어진다.

각 정수는 1이상 18이하이며, 같은 정수는 없다.

규영이는 정수가 주어지는 순서대로 카드를 낸다고 생각하면 된다.


[출력]

각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고 한 칸을 띄운 후,

인영이가 카드를 내는 9! 가지의 경우에 대해 규영이가 게임을 이기는 경우의 수와 게임을 지는 경우의 수를

공백 하나로 구분하여 출력한다. 
[입력]							[결과]
4
1 3 5 7 9 11 13 15 17           #1 112097 250783
18 16 14 12 10 8 6 4 2          #2 250783 112097
13 17 9 5 18 7 11 1 15          #3 336560 26320
1 6 7 9 12 13 15 17 18          #4 346656 16224
 */

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution_D3_6808_규영이와인영이의카드게임2 {
	
	static int win, lose;
	static int[] numbers1, numbers2;
	static int N=9;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		numbers1 = new int[N]; //규영이 카드저장 배열
		numbers2 = new int[N]; //인영이 카드저장 배열
		String line;
		StringTokenizer st ;
		int[] card = new int[19];//0~18 중  1번지~18번지까지 사용
		int data;
		for (int tc = 1; tc <= T; tc++) {
			line = in.readLine();
			st = new StringTokenizer(line.trim());
			
			for (int i = 0; i <N; i++) {
				data = Integer.parseInt(st.nextToken());
				card[data] = 1;      //규영이 받은 9장의 카드를 카드배열에  1로표시
				numbers1[i] = data;  //규영이 받은 숫자 저장
			}
			data=0;
			for (int i = 1; i <card.length; i++) {
				if(card[i]==0) {
					numbers2[data++]= i;//인영이 받은 숫자 저장(전체카드 - 규영이)
				}
			}
			dfs(0, 0, 0, 0);//비교수(9), 비교확인, 규영이합, 인영이합
			System.out.println("#"+tc+" "+win+" "+lose);
			
			//다음 테스트케이스를 위한 초기화
			win=0;
			lose=0;
			Arrays.fill(card, 0);
		}
	}
	public static void dfs(int cnt, int flag, int guSum, int inSum) {
		if(cnt == N) {
			if(guSum>inSum) {
				win++;
			}else {
				lose++;
			}
			return;
		}
		
		int guCard, inCard;
		
		for (int i = 1; i <= N; i++) {
			if( (flag & 1<<i) == 0) {
			 guCard = numbers1[cnt];
			 inCard = numbers2[i-1];
			 if(guCard>inCard) {
				 dfs(cnt+1, flag | 1<<i, guSum+inCard+guCard, inSum);
			 }else {
				 dfs(cnt+1, flag | 1<<i, guSum, inSum+inCard+guCard);
			 }
			}
		}
	}
}
