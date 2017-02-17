package chapter_2.session_3;

import chapter_1.session_3.Stack;
import chapter_2.session_1.SortBase;

public class QuickSortNonRecursive extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		int low = 0;
		int high = array.length-1;
		Stack<Integer> stack = new Stack<Integer>();
		int p = QuickSort.partition(array, low, high);
		if (p-1 > low)  
		{
			stack.push(p-1);
			stack.push(low);
		}
		if (p+1 < high)  
		{
			stack.push(high);
			stack.push(p+1);
		}
		while (!stack.isEmpty()){
			low = stack.pop();
			high = stack.pop();
			p = QuickSort.partition(array, low, high);
			if (p-1 > low)
			{  
				stack.push(p-1);
				stack.push(low);
			}  
			if (p+1 < high)
			{
				stack.push(high);
				stack.push(p+1);
			}
		}

	}

	public static void main(String[] args) {
		new QuickSortNonRecursive().test();
	}

}
