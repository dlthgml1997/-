import java.util.Arrays;

// 퀵 정렬
// 연속적인 분할에 의한 정렬 방식, 하나의 피봇 (맨 뒤부터)을 정하고 이 축의 값보다 작으면 왼쪽, 크면 오른쪽에 둔다.
// 가장 많이 사용되지만 안정성이 떨어진다는 단점이 있다.
 
public class QuickSort {
	public static void main(String[] args) {
		int[] arr = {6,4,2,10,9,1,7,11,5,3,0,8};
		System.out.println(Arrays.toString(sort(arr, 0, arr.length -1)));
	}
	
	private static int[] sort(int[] arr, int left, int right) {
		if(arr == null) return null;
		int[] result = arr;
		if(left >= right) return result;
		
		int pivotPos = partition(result, left, right);
		
		result = sort(result, left, pivotPos -1);
		result = sort(result, pivotPos, right);
		
		return result;
	}

	private static int partition(int[] arr, int left, int right) {
		if(arr == null || left < 0) return -1;
		int standardOfTemp = left -1;
		int pivotValue = arr[right];
		
		for(int pos = left; pos < right; pos++) {
			if(arr[pos] < pivotValue) {
				standardOfTemp +=1;
				swap(arr, pos, standardOfTemp);
			}
		}
		standardOfTemp += 1;
		swap(arr, standardOfTemp, right);
		
		return standardOfTemp;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
