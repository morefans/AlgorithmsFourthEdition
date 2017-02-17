package chapter_1.session_4;

import chapter_1.session_1.BinarySearch;

/**
 * 习题1.4.20，双调查找，双调数组即先递增后递减的数组，从中查找某元素，要求~lgN
 * @author ZhangYuliang
 *
 */
public class BitonicSearch {
	public static int maxIndex(int[] array, int low, int high){
		if (low == high)
			return low;
		int middle = (low + high) / 2;
		if (array[middle] > array[middle+1])
			return maxIndex(array, low, middle);
		else if (array[middle] < array[middle+1])
			return maxIndex(array,middle+1, high);
		else
			return middle;
	}
	public static int search(int[] array, int key){
		int maxIndex = maxIndex(array, 0, array.length-1);
		
		int[] left = new int[maxIndex+1];
		for (int i = 0; i <= maxIndex; i++)
			left[i] = array[i];
		int leftindex = BinarySearch.search(left, key);
		if (leftindex >= 0)
			return leftindex;
		
		int[] right = new int[array.length-maxIndex];
		for (int i = 0; i < right.length; i++)
			right[i] = array[array.length-i-1];
		int rightindex = BinarySearch.search(right, key);
		if (rightindex >= 0)
			return array.length-1-rightindex;
		
		return -1;
		
	}
	
	public static void test(){
		int[] array = {1,2,3,4,5,11,11,9,8,7,6,0};
		System.out.println(search(array,0));
	}
	public static void main(String[] args){
		BitonicSearch.test();
	}
	

}
