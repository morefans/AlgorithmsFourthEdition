package chapter_2.session_1;

public class InsertionSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		for (int i = 0;i < array.length;i++){
			for (int j = i;j > 0;j--){
				if (less(array[j], array[j-1]))
					exch(array, j, j-1);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

	public static void main(String[] args) {
		new InsertionSort().test();
	}

}
