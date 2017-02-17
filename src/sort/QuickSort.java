package sort;

public class QuickSort extends Sort {

	@Override
	public int[] sort(int[] array) {
		return sort(array,0,array.length-1);
	}
	public int[] sort(int[] array, int start, int end) {
		if (end - start < 1)
			return array;
		int left = start+1;
		int right = end;
		while (left <= right){
			if (array[right] < array[start]){
				if (array[left] > array[start]){
					swap(array,left,right);
					left++;
				}else{
					left++;
					continue;
				}
			}
			else{
				right--;
			}
		}
		swap(array,start,left-1);
		sort(array,start,left-2);
		sort(array,left,end);
		return array;
	}
	public static int[] shuffle(int[] array){
		return array;
	}

	public static void main(String[] args) {
		new QuickSort().test();
	}

}
