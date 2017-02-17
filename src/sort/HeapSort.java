package sort;

public class HeapSort extends Sort {

	public void adjustDown(int[] array, int index, int end){
		int child = index*2 + 1;
		int temp = array[index];
		while (child <= end){
			if (child < end && array[child] < array[child+1]){
				child++;
			}
			if (temp >= array[child]){
				break;
			}
			array[(child-1)/2] = array[child];
			child = child*2 + 1;
		}
		array[(child-1)/2] = temp;
	}
	@Override
	public int[] sort(int[] array) {
		for (int i = (array.length-1)/2;i > -1;i--){
			adjustDown(array, i, array.length-1);
		}
		for (int i = array.length-1;i > 0;i--){
			swap(array, 0, i);
			adjustDown(array, 0, i-1);
		}
		return array;
	}

	public static void main(String[] args) {
		new HeapSort().test();
	}

}
