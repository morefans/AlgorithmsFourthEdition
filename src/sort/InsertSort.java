package sort;

public class InsertSort extends Sort {

	public int[] sort(int[] array) {
		for (int i = 0; i < array.length;i++){
			for (int j = i;j > 0; j--){
				if (array[j] < array[j-1]){
					swap(array,j,j-1);
				}
			}
		}
		return array;
	}
	public static void main(String[] args) {
		new InsertSort().test();
	}

}
