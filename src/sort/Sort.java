package sort;

public abstract class Sort {
	public abstract int[] sort(int[] array);
	public void test(){
		int[] array = {1,2,7,8,3,4,5,6,0,9};
//		int[] array = {2,1};
		sort(array);
		print(array);
	}
	public static void print(int[] array){
		int i;
		System.out.print("{");
		for (i = 0; i < array.length-1; i++){
			System.out.print(array[i] + ", ");
		}
		System.out.print(array[i] + "}");
	}
	public static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
