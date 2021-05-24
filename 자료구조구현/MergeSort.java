import java.util.Arrays;

// 합병정렬
// 작은 단위로 잘게 쪼개어 작은 단위부터 정렬해서 정렬된 단위들을 계속 병합해가는 정렬 방식
// 간단, 쉬움, 안정성, 좋은 성능 / 공간이 많이 필요함
import java.util.*;

public class MergeSort {
	static int[] sortedArr;

	public static void main(String[] args) {
		int[] arr = { 2, 3, 1, 5, 4, 6, 8, 7, 9 };
		sortedArr = new int[arr.length];
		sort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(sortedArr));
	}

	private static void sort(int[] arr, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			sort(arr, start, middle);
			sort(arr, middle + 1, end);
			merge(arr, start, middle, end);
		}
	}

	private static void merge(int[] arr, int start, int middle, int end) {
		int firstIdx = start; // 첫번째 배열 시작 인덱스
		int secondIdx = middle + 1; // 두번째 배열 시작 인덱스
		int sArrIdx = start;
		while (firstIdx <= middle && secondIdx <= end) {
			if (arr[firstIdx] > arr[secondIdx]) {
				sortedArr[sArrIdx++] = arr[secondIdx++];
			} else {
				sortedArr[sArrIdx++] = arr[firstIdx++];
			}
		}

		if (firstIdx > middle) {
			for (int i = secondIdx; i <= end; sArrIdx++, i++) {
				sortedArr[sArrIdx] = arr[i];
			}
		} else {
			for (int i = firstIdx; i <= middle; sArrIdx++, i++) {
				sortedArr[sArrIdx] = arr[i];
			}
		}
		
		for(int i= start; i<= end; i++) {
			arr[i] = sortedArr[i]; 
		}
		System.out.println(Arrays.toString(arr));
	}
}
