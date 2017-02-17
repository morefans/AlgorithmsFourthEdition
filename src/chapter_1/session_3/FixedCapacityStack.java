package chapter_1.session_3;
/**
 * 固定容量栈，使用数组实现
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class FixedCapacityStack<Item> {
	private Item[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int capacity){
		array = (Item[]) new Object[capacity];
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public boolean push(Item item){
		if (size == array.length)
			return false;
		array[size++] = item;
		return true;
	}
	
	public Item pop(){
		if (size <= 0)
			return null;
		return array[--size];
	}
	
	
	public static void test(){
		FixedCapacityStack<Integer> stack = new FixedCapacityStack<Integer>(5);
		for (int i = 1; i < 10; i++)
			stack.push(i);
		System.out.println("There are " + stack.size() + " items in stack.");
		while (!stack.isEmpty())
			System.out.println("pop:" + stack.pop() + "\t leftNum:" + stack.size());
	}
	public static void main(String[] args){
		FixedCapacityStack.test();
	}

}
