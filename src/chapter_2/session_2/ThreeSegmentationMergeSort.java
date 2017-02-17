package chapter_2.session_2;

import chapter_2.session_1.SortBase;

public class ThreeSegmentationMergeSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		Comparable[] aux = new Comparable[array.length];
		for (int size = 1; size < array.length; size *= 3){
			for (int low = 0;low < array.length-size;low += size*3){
				int firstSeg = low+size-1;
				int secondSeg = Math.min(low+size+size-1, array.length-1);// low+size+size-1 = firstSeg+size
				int high = Math.min(low+size+size+size-1, array.length-1);// low+size+size+size-1 = secondSeg+size
				merge(array, aux, low, firstSeg, secondSeg, high);
			}
		}

	}
	@SuppressWarnings("rawtypes")
	public static void merge(Comparable[] array, Comparable[] aux, int low, int firstSeg, int secondSeg, int high){
		for (int i = low;i <= high;i++){
			aux[i] = array[i];
		}
		int first = low;
		int second = firstSeg+1;
		int third = secondSeg+1;
		for (int i = low;i <= high;i++){
			if (first > firstSeg){
				if (second > secondSeg){
					array[i] = aux[third++];
				}else if (third > high){
					array[i] = aux[second++];
				}else if (less(aux[second], aux[third])){
					array[i] = aux[second++];
				}else{
					array[i] = aux[third++];
				}
			}
			else if (second > secondSeg){
				if (third > high){
					array[i] = aux[first++];
				}else if (less(aux[first], aux[third])){
					array[i] = aux[first++];
				}else{
					array[i] = aux[third++];
				}
			}else if (third > high){
				if (less(aux[first], aux[second])){
					array[i] = aux[first++];
				}else{
					array[i] = aux[second++];
				}
			}else {
				if (less(aux[first], aux[second]) && less(aux[first], aux[third])){
					array[i] = aux[first++];
				}else if (less(aux[second], aux[first]) && less(aux[second], aux[third])){
					array[i] = aux[second++];
				}else{
					array[i] = aux[third++];
				}
			}
		}
		return;
	}

	//	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		//				Integer[] array = {4,1,3};
		//				Comparable[] aux = new Comparable[array.length];
		//				merge(array, aux, 0, 0, 1, 2);
		//				for (int i = 0;i < array.length;i++)
		//					System.out.print(array[i] + "\t");

		//		ThreeSegmentationMergeSort sort = new ThreeSegmentationMergeSort();
		//		for (int i = 1;i < 20;i++)
		//			sort.test(i);

		new ThreeSegmentationMergeSort().test();

		//		ThreeSegmentationMergeSort sort = new ThreeSegmentationMergeSort();
		//		Integer[] array = {5,3,1,2,0,4};
		//		SortBase.show(array);
		//		sort.sort(array);
		//		SortBase.show(array);

	}

}
