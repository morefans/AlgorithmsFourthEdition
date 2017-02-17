package chapter_2.session_1;

public class ShellSort extends SortBase{
	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		for (int gap = array.length/3; gap > 0; gap /= 3){
			for (int i = gap; i < array.length; i++){
				for (int j = i; j >= gap; j -= gap){
					if (less(array[j], array[j-gap]))
						exch(array, j, j-gap);
				}
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public void sortOrigin(Comparable[] array){
		int N = array.length;
		int h = 1;
		while (h < N/3)
			h = 3*h + 1;
		while (h >= 1){
			for (int i = h; i < N; i++){
				for (int j = i; j >= h && less(array[j], array[j-h]); j -= h)
					exch(array, j, j-h);
			}
			h = h/3;
		}
	}

	public static void main(String[] args) {
		new ShellSort().test();
	}
}
