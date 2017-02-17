package chapter_2.session_5;

import chapter_2.session_1.SortBase;
import chapter_2.session_3.QuickSort;

public class NoKElement {
	/**
	 * 找出数组中第k小的元素，第0小为最小
	 * @param array
	 * @param k
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Comparable select(Comparable[] array, int k){
		int low = 0;
		int high = array.length-1;
		while (high > low){
			int j = QuickSort.partition(array, low, high);
			if (j == k)
				return array[k];
			else if (j > k)
				high = j-1;
			else if (j < k)
				low = j+1;
		}
		return array[k];
	}
	@SuppressWarnings("rawtypes")
	public static void test(){
		int N = 10;
		Comparable[] array = new Comparable[N];
		for (int i = 0;i < N;i++){
			array[i] = i+1;
		}
		SortBase.shuffle(array);
		SortBase.show(array);
		System.out.println(select(array, 4));
	}

	public static void main(String[] args) {
		NoKElement.test();
	}

}
