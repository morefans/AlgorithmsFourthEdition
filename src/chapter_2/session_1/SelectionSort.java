package chapter_2.session_1;
public class SelectionSort extends SortBase{

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		for (int i = 0;i < array.length;i++){
			int min = i;
			for (int j = i;j < array.length;j++){
				if (less(array[j], array[min]))
					min = j;
			}
			exch(array, i, min);
		}
	}
	public static void main(String[] args){
		new SelectionSort().test();
	}

}
