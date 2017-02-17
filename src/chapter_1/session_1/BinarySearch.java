package chapter_1.session_1;
/**
 * 二分查找。二分查找改进得到最小下标
 * @author ZhangYuliang
 *
 */
public class BinarySearch {
	public static int search(int[] array, int key){
		int low = 0;
		int high = array.length-1;
		while (low <= high){
			int middle = (low + high)/2;
			if (key == array[middle])
				return middle;
			else if (key > array[middle])
				low = middle + 1;
			else if (key < array[middle])
				high = middle - 1;
		}
		return -1;
	}
	public static int searchMinIndex(int[] array, int key){
		int low = 0;
		int high = array.length-1;
		int minIndex = -1;
		while (low <= high){
			int middle = (low + high)/2;
			if (key == array[middle]){
				minIndex =  middle;
				high = middle - 1;
			}
			else if (key > array[middle])
				low = middle + 1;
			else if (key < array[middle])
				high = middle - 1;
		}
		return minIndex;
	}
	
	public static int[] removeRepeated(int[] array){
		int[] temp = new int[array.length];
		int count = 0;
		for (int i = 0;i < array.length; i++){
			if (count == 0 || temp[count-1] != array[i]){
				temp[count] = array[i];
				count++;
			}
		}
		array = new int[count];
		for (int i = 0; i < count; i++){
			array[i] = temp[i];
		}
		return array;
	}
	public static void print(int[] array){
		int i;
		System.out.print("{");
		for(i = 0;i < array.length-1;i++){
			System.out.print(array[i] + "\t");
		}
		System.out.println(array[i] + "}");
	}
	
	public void test(){
		int[] array = {1,4,4,4,4,6,7,8,9};
		print(array);
		array = removeRepeated(array);
		print(array);
//		System.out.println(search(array, 10));
		System.out.println(searchMinIndex(array, 4));
	}

	public static void main(String[] args) {
		new BinarySearch().test();
	}

}
