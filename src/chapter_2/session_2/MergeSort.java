package chapter_2.session_2;

import chapter_2.session_1.SortBase;
/**
 * �Զ����µĹ鲢����ϸ����������ԭ�ع鲢
 * �Ե����ϵĹ鲢
 * @author ZhangYuliang
 *
 */
public class MergeSort extends SortBase {
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux; // ��������

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		bottomUpSort(array);
	}
	/**
	 * �Ե����Ϲ鲢����
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
	 * �Զ����¹鲢����
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
	 * ԭ�ع鲢����������ĳ��󷽷���ֻ������������������Ĳ��ܵõ���ȷ���
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
