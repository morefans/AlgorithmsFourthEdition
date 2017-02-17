package chapter_2.session_2;

import chapter_2.session_1.SortBase;
/**
 * ��ϰ2.2.9���鲢���򣬲�ʹ��static�������飬Ҳ����ԭ�ع鲢��ʹ�þֲ���������
 * @author ZhangYuliang
 *
 */
public class MergeSortNoStatic extends SortBase{

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
		Comparable[] aux = new Comparable[array.length];
		for (int size = 1;size < array.length;size += size){
			for (int low = 0;low < array.length-size;low += size+size){
				inPlaceMerge(array, low, low+size-1, Math.min(low+size+size-1, array.length-1), aux);
			}
		}
	}
	/**
	 * �Զ����¹鲢����
	 * @param array
	 */
	@SuppressWarnings("rawtypes")
	public void topDownSort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		topDownsort(array, 0, array.length-1, aux);
	}
	@SuppressWarnings("rawtypes")
	private void topDownsort(Comparable[] array, int low, int high, Comparable[] aux){
		if (high <= low)
			return ;
		int mid = (low + high) / 2;
		topDownsort(array, low, mid, aux);
		topDownsort(array, mid+1, high, aux);
		inPlaceMerge(array, low, mid, high, aux);
	}
	/**
	 * ԭ�ع鲢����������ĳ��󷽷���ֻ������������������Ĳ��ܵõ���ȷ���
	 * @param array
	 * @param low
	 * @param mid
	 * @param high
	 */
	@SuppressWarnings("rawtypes")
	public void inPlaceMerge(Comparable[] array, int low, int mid, int high, Comparable[] aux){
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
		new MergeSortNoStatic().test();
	}
}
