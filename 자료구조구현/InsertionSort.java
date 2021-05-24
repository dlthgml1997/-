// 삽입 정렬
// 이미 정렬된 앞부분과 비교해 자신의 위치를 찾아 삽입한다.
// 버블정렬보다 좋은 성능 효율을 보여준다.
import java.util.*;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = {6,4,1,8,9,2,7,5,3};
		System.out.println(Arrays.toString(sort(arr)));
	}

	private static int[] sort(int[] arr) {
		if(arr == null) return arr;
		int temp;
		for(int i= 1; i<arr.length; i++) {
			temp = arr[i];
			int k;
			for(k=i-1; k>=0; k--) {
				if(temp >= arr[k]) break; // 내가 더 크거나 같으면 
									// 내가 더 작으면
				arr[k+1] = arr[k]; // 하나씩 뒤로 미룬다. 
			}
			arr[k+1] = temp; // 내가 들어간다. 
		}
		return arr;
	}
}
