package chapter_1.session_3;
/**
 * 固定容量的String栈，练手，数组实现
 * @author ZhangYuliang
 *
 */
public class FixedCapacityStackOfStrings {
	private String[] array;
	private int size;
	
	public FixedCapacityStackOfStrings(int capacity){
		array = new String[capacity];
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public boolean push(String item){
		if (size == array.length)// 防止数组下标访问异常
			return false;
		array[size] = item;
		size++;
		return true;
	}
	
	public String pop(){
		if (size <= 0)// 防止数组下标访问异常
			return null;
		size--;
		return array[size];
	}
	
	public static void test(){
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(5);
		for (int i = 1; i < 10; i++)
			stack.push(String.valueOf(i));
		System.out.println("There are " + stack.size() + " items in stack.");
		while (!stack.isEmpty())
			System.out.println("pop:" + stack.pop() + "\t leftNum:" + stack.size());
	}
	public static void main(String[] args){
		FixedCapacityStackOfStrings.test();
	}
}
