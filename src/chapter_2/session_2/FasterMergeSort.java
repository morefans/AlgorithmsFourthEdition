package chapter_2.session_2;

import chapter_2.session_1.SortBase;

public class FasterMergeSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		for (int size = 1;size < array.length;size += size){
			for (int low = 0;low < array.length-size;low += size+size){
				merge(array, low, low+size-1, Math.min(low+size+size-1, array.length-1), aux);
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public static void merge(Comparable[] array, int low, int mid, int high, Comparable[] aux){
		for (int k = low;k <= mid;k++)
			aux[k] = array[k];
		for (int k = mid+1;k <= high;k++)
			aux[k] = array[high-k+mid+1];
		int i = low;
		int j = high;
		for (int k = low;k <= high;k++){
			if (less(aux[j], aux[i]))
				array[k] = aux[j--];
			else
				array[k] = aux[i++];
		}
	}
	public static void main(String[] args) {
		new FasterMergeSort().test();
	}

}
