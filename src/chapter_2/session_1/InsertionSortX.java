package chapter_2.session_1;

public class InsertionSortX extends SortBase{

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		int exchanges = 0;
		for (int i = array.length-1;i > 0;i--){
			if (less(array[i], array[i-1])){
				exch(array, i, i-1);
				exchanges++;
			}
		}
		if (exchanges == 0)
			return ;
		for (int i = 2;i < array.length;i++){
			Comparable temp = array[i];
			int j = i;
			while (less(temp, array[j-1])){
				array[j] = array[j-1];
				j--;
			}
			array[j] = temp;
		}
	}
	
	public static void main(String[] args){
		new InsertionSortX().test();
	}

}
