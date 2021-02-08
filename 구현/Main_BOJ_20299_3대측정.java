import java.io.*;
import java.util.*;
/**
 * test case
5 5000 1600
1621 1928 1809
2300 2300 1499
1805 1211 1699
1600 1700 1800
1792 1617 1830

 */
public class Main {
	static BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<Integer> queue;
	public static void main(String[] args) throws Exception {
			st = new StringTokenizer(br.readLine());
			int N =Integer.parseInt(st.nextToken()); // 신청한 동아리의 수 
			int S =Integer.parseInt(st.nextToken()); // 팀원 3명의 능력 합에 대한 스마트 클럽 가입 조건 
			int M =Integer.parseInt(st.nextToken()); // 개인 능력치에 대한 스마트 클럽 가입 조건
			queue = new LinkedList();
			int cnt = 0;
			for(int n = 0; n< N; n++) { // 동아리의 수만큼 반복문을 돈다.
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					int student1 = Integer.parseInt(st.nextToken()); // 첫번째 학생의 개인 능력치
					int student2 = Integer.parseInt(st.nextToken()); // 두번째 학생의 개인 능력치
					int student3 = Integer.parseInt(st.nextToken()); // 세번째 학생의 개인 능력치
					int sum = student1 + student2 + student3; // 세 학생의 능력치의 합
					if(student1 < M || student2 < M || student3 < M)  // 셋 중 한명이라도 개인 능력치 기준보다 낮은 능력치를 가지고 있다면, 
						break; // while문을 빠져나간다.
					if(sum >= S) { // 개인 모두가 개인 능력치 기준보다 높은 상태에서, 합이 스마트 클럽 가입 조건에 맞으면
						cnt++; // 총 경우의 수 개수를 1 늘리고,
						queue.add(student1); // 큐에 세 학생의 능력치를 삽입한다. 
						queue.add(student2);
						queue.add(student3);
					}
				}
			}
			System.out.println(cnt); // 경우의 수 출력
			while(!queue.isEmpty()) { // 모든 학생을 출력하기 위해 큐가 빌때까지,
				System.out.print(queue.poll() + " "); // 큐에 있는 학생의 능력치를 print한다. (선입 선출)
			}
			
	}
}
