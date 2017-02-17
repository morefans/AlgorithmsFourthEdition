package chapter_2.session_3;

import chapter_2.session_1.SortBase;

public class QuickSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		sort(array, 0, array.length-1);
	}
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] array, int low, int high){
		if (high <= low)
			return ;
		int index = partition(array, low, high);
		sort(array, low, index-1);
		sort(array, index+1, high);
	}
	@SuppressWarnings("rawtypes")
	public static int partition(Comparable[] array, int low, int high){
		int left = low+1;
		int right = high;
		Comparable temp = array[low];
		while (left <= right){
			if (less(temp, array[left])){
				if (less(array[right], temp)){
					exch(array, left, right);
				}else{
					right--;
				}
			}else{
				left++;
			}
		}
		exch(array, left-1, low);
		return left-1;
	}

	public static void main(String[] args) {
		Integer[] array = {3,1,1};
		QuickSort sort = new QuickSort();
		sort.sort(array);
		SortBase.show(array);
//		new QuickSort().test();
	}

}
