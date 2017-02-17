package chapter_2.session_2;

import chapter_2.session_1.SortBase;
/**
 * 习题2.2.16，自然的归并排序，讯早
 * @author ZhangYuliang
 *
 */
public class NativeMergeSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		while (findOrdered(array, 0) != array.length-1){
			int low = 0;
			while (low < array.length){
				int mid = findOrdered(array, low);
				int high = mid;
				if (mid+1 < array.length)
					high = findOrdered(array, mid+1);
				merge(array, aux, low, mid, high);
				low = high+1;
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public static int findOrdered(Comparable[] array, int start){
//		if (start >= array.length)
//			return start-1;
		int i = start;
		for (;i < array.length-1;i++){
			if (less(array[i], array[i+1]))
				continue;
			else
				return i;
		}
		return i;
	}
	@SuppressWarnings("rawtypes")
	public void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high){
		for (int i = low;i <= mid;i++)
			aux[i] = array[i];
		for (int i = mid+1;i <= high;i++)
			aux[i] = array[high-i+mid+1];
		int left = low;
		int right = high;
		for (int i = low;i <= high;i++){
			if (less(aux[right], aux[left]))
				array[i] = aux[right--];
			else
				array[i] = aux[left++];
		}
	}

	public static void main(String[] args) {
		new NativeMergeSort().test();
	}

}
