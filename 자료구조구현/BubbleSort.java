import java.util.Arrays;

// 버블 정렬
// 첫번째 원소부터 인접한 원소끼리 계속 자리를 교환하면서 맨 끝부터 정렬하는 방식
// 데이터를 하나씩 비교하기 때문에 정밀하지만, 비교 횟수가 많아지므로 성능면에서 좋지 않다.
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {2, 1, 4, 0, 3};
		System.out.println(Arrays.toString(sort(arr)));
	}

	private static int[] sort(int[] arr) {
		for(int i=0; i< arr.length -1; i++) {
			for(int j=i+1; j< arr.length; j++) {
				if(arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
}
