package chapter_1.session_3;
/**
 * 可调整大小的栈，数组实现，未实现迭代器不支持foreach
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class ResizableStack<Item> {
	private Item[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ResizableStack(int capacity){
		array = (Item[]) new Object[capacity];
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public void push(Item item){
		if (size == array.length)
			resize (2 * array.length);
		array[size++] = item;
	}
	
	public Item pop(){
		if (size <= 0)
			return null;
		Item item = array[--size];
		array[size] = null;// 避免对象游离
		// 如果栈大小不足四分之一，调整为半满
		if (size > 0 && size < array.length/4)
			resize(array.length/2);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public void resize(int capacity){
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			temp[i] = array[i];
		array = temp;
	}
	
	public static void test(){
		ResizableStack<Integer> stack = new ResizableStack<Integer>(5);
		for (int i = 1; i < 10; i++)
			stack.push(i);
		System.out.println("There are " + stack.size() + " items in stack.");
		while (!stack.isEmpty())
			System.out.println("pop:" + stack.pop() + "\t leftNum:" + stack.size());
	}
	public static void main(String[] args){
		ResizableStack.test();
	}
}
