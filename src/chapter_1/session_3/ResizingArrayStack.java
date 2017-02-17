package chapter_1.session_3;

import java.util.Iterator;
/**
 * 可调整大小数组栈，数组实现，泛型和迭代器
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ResizingArrayStack(int capacity){
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
		ResizingArrayStack<Integer> stack = new ResizingArrayStack<Integer>(5);
		for (int i = 1; i < 10; i++)
			stack.push(i);
		System.out.println("There are " + stack.size() + " items in stack.");
//		while (!stack.isEmpty())
//			System.out.println("pop:" + stack.pop() + "\t leftNum:" + stack.size());
		for (int item : stack){
			System.out.println("pop:" + item);
		}
	}
	public static void main(String[] args){
		ResizingArrayStack.test();
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = size;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Item next() {
			return array[--i];
		}
		
		public void remove(){}
	}

}
