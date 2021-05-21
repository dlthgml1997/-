import java.util.Arrays;

// 선택 정렬 구현하기
// 현재 위치에 들어갈 데이터를 찾아 선택하는 알고리
// 비교 정렬, 제자리 정렬, 불안정 정렬

public class SelectionSort {
	public static void main(String[] args) {
		int[] a = {2,4,1,7,5,9};
		sort(a, a.length);
		System.out.println(Arrays.toString(a));
	}
	
	private static void sort(int[] a, int size) {
		for(int i=0; i< size -1; i++) {
			int minIdx = i;
			
			for(int j= i+1; j< size; j++) {
				if(a[j] < a[minIdx]) {
					minIdx = j;
				}
			}
			
			swap(a, minIdx, i);
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
}
