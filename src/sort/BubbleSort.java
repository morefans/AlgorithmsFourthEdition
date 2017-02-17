package sort;

public class BubbleSort extends Sort{
	
	public int[] sort(int[] array){
		for (int i = 0; i < array.length; i++){
			for (int j = 0;j < array.length-i-1;j++){
				if (array[j] > array[j+1]){
					swap(array,j,j+1);
				}
			}
		}
		return array;
	}
	public int[] sort2(int[] array){
		for (int i = 0; i < array.length; i++){
			for (int j = array.length-1;j > i;j--){
				if (array[j] < array[j-1]){
					swap(array,j,j-1);
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		new BubbleSort().test();
	}

}
