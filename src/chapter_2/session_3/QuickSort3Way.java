package chapter_2.session_3;

import chapter_2.session_1.SortBase;
/**
 * 三向切分的快速排序
 * @author ZhangYuliang
 *
 */
public class QuickSort3Way extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		sort(array, 0, array.length-1);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sort(Comparable[] array, int low, int high){
		if (high <= low)
			return;
		Comparable temp = array[low];
		int lt = low;
		int i = low + 1;
		int gt = high;
		while (i <= gt){
			int cmp = array[i].compareTo(temp);
			if (cmp < 0)
				exch(array, lt++, i++);
			else if (cmp > 0)
				exch(array, i, gt--);
			else
				i++;
		}
		sort(array, low, lt-1);
		sort(array, gt+1, high);
	}

	public static void main(String[] args) {
		new QuickSort3Way().test();
	}

}
