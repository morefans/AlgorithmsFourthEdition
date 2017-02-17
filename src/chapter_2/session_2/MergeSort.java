package chapter_2.session_2;

import chapter_2.session_1.SortBase;
/**
 * 自顶向下的归并，配合辅助数组进行原地归并
 * 自底向上的归并
 * @author ZhangYuliang
 *
 */
public class MergeSort extends SortBase {
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux; // 辅助数组

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		bottomUpSort(array);
	}
	/**
	 * 自底向上归并排序
	 * @param array
	 */
	@SuppressWarnings("rawtypes")
	public void bottomUpSort(Comparable[] array) {
		aux = new Comparable[array.length];
		for (int size = 1;size < array.length;size += size){
			for (int low = 0;low < array.length-size;low += size+size){
				inPlaceMerge(array, low, low+size-1, Math.min(low+size+size-1, array.length-1));
			}
		}
	}
	/**
	 * 自顶向下归并排序
	 * @param array
	 */
	@SuppressWarnings("rawtypes")
	public void topDownSort(Comparable[] array) {
		aux = new Comparable[array.length];
		topDownsort(array, 0, array.length-1);
	}
	@SuppressWarnings("rawtypes")
	private void topDownsort(Comparable[] array, int low, int high){
		if (high <= low)
			return ;
		int mid = (low + high) / 2;
		topDownsort(array, low, mid);
		topDownsort(array, mid+1, high);
		inPlaceMerge(array, low, mid, high);
	}
	/**
	 * 原地归并左右子数组的抽象方法，只有两个子数组是有序的才能得到正确结果
	 * @param array
	 * @param low
	 * @param mid
	 * @param high
	 */
	@SuppressWarnings("rawtypes")
	public void inPlaceMerge(Comparable[] array, int low, int mid, int high){
		int i = low;
		int j = mid+1;
		for (int k = low;k <= high;k++)
			aux[k] = array[k];
		for (int k = low;k <= high;k++){
			if (i > mid)
				array[k] = aux[j++];
			else if (j > high)
				array[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				array[k] = aux[j++];
			else
				array[k] = aux[i++];
		}
	}

	public static void main(String[] args) {
		new MergeSort().test();
	}

}
