package sort;

public class BinarySearch {
	
	public static int serach(int[] array, int target){
		int low = 0;
		int high = array.length-1;
		int middle;
		while (low <= high){
			middle = (low + high)/2;
			if (array[middle] == target){
				return middle;
			}
			else if (target > array[middle]){
				low = middle+1;
			}
			else if (target < array[middle]){
				high = middle-1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
//		int[] array = {2,1,4,3,6,5,8,7,9,0};

		int[] array = {0,1,2,3,4,5,6,7,8,9};
		System.out.println(serach(array,9));
	}

}
