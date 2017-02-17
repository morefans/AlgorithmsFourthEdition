package chapter_2.session_4;

import chapter_2.session_1.SortBase;
/**
 * 堆排序，数组第0个元素为根结点，k的子结点便是2*k+1和2*k+2，父节点是(k-1)/2
 * @author ZhangYuliang
 *
 */
public class HeapSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		adjustToHeap(array);
		for (int i = array.length-1;i > 0;i--){
			exch(array, 0, i);
			sink(array, 0, i-1);
		}
	}
	@SuppressWarnings("rawtypes")
	public void swim(Comparable[] array, int k, int start){
		int parent = (k-1)/2;
		while (parent > 0 && less(array[parent], array[k])){
			exch(array, parent, k);
			k = parent;
			parent = (k-1)/2;
		}
	}
	@SuppressWarnings("rawtypes")
	public void sink(Comparable[] array, int k, int end){
		int child = 2*k+1;
		while (child <= end){
			if (child < end && less(array[child], array[child+1]))
				child++;
			if (less(array[k], array[child])){
				exch(array, k, child);
				k = child;
				child = 2*k+1;
			}
			else
				break;
		}
	}
	@SuppressWarnings("rawtypes")
	public void adjustToHeap(Comparable[] array){
		for (int i = (array.length-1)/2;i >= 0;i--){
			sink(array, i, array.length-1);
		}
	}

	public static void main(String[] args) {
		new HeapSort().test();
	}

}
