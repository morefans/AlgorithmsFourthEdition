package chapter_2.session_3;

import chapter_2.session_1.SortBase;
/**
 * 习题2.3.5，将已知只有两种主键值得数组排序
 * @author ZhangYuliang
 *
 */
public class Sort2Distinct extends SortBase {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void sort(Comparable[] array) {
		int low = 0;
		int high = array.length-1;
		int lt = low;
		int gt = high;
		int i = low;
		while (i <= gt){
			int cmp = array[i].compareTo(array[lt]);
			if (cmp < 0)
				exch(array, lt++, i++);
			else if (cmp > 0)
				exch(array, i, gt--);
			else
				i++;
		}
	}

	public static void main(String[] args) {
		Integer[] array = {2,1,1,2,1,1,2,2,1,2,1};
		new Sort2Distinct().sort(array);
		SortBase.show(array);
	}

}
