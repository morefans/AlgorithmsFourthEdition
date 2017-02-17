package chapter_1.session_3;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 习题1.3.33，数组实现的双向队列
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class ResizingArrayDeque<Item> implements Iterable<Item> {
	private Item[] array;
	private int size = 0;
	private int first = 0;
	private int last = 0;

	@SuppressWarnings("unchecked")
	public ResizingArrayDeque(int capacity){
		if (capacity < 1)
			capacity = 1;
		array = (Item[]) new Object[capacity];
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){
		return size;
	}

	public void pushRight(Item item){
		if (size == array.length)
			resize(2*array.length);
		array[last] = item;
		last = (last+1) % array.length;
		size++;
	}
	public void pushLeft(Item item){
		if (size == array.length)
			resize(2*array.length);
		if (first == 0)
			first = array.length-1;
		else
			first--;
		array[first] = item;
		size++;
	}

	public Item popLeft(){
		if (isEmpty())
			//			return null;
			throw new NoSuchElementException("Underflow");
		Item item = array[first];
		first = (first+1) % array.length;
		size--;
		// 如果栈大小不足四分之一，调整为半满
		if (size > 0 && size < array.length/4)
			resize(array.length/2);
		return item;
	}
	public Item popRight(){
		if (isEmpty())
			//			return null;
			throw new NoSuchElementException("Underflow");
		if (last == 0)
			last = array.length-1;
		else
			last--;
		Item item = array[last];
		size--;
		// 如果栈大小不足四分之一，调整为半满
		if (size > 0 && size < array.length/4)
			resize(array.length/2);
		return item;
	}
	public Item peak(){
		return array[first];
	}
	public Item tail(){
		if (last == 0)
			return array[array.length-1];
		else
			return array[last-1];
	}

	@SuppressWarnings("unchecked")
	public void resize(int capacity){
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			temp[i] = array[(first+i)%array.length];
		array = temp;
		first = 0;
		last = size;
	}
	public void print(){
		//		System.out.println("first=" + first + "; last=" + last);
		//		for (int i = 0; i < array.length; i++){
		//			System.out.print(array[i] + "\t");
		//		}
		//		System.out.println();
		if (isEmpty()){
			System.out.println("ResizingArrayDeque is empty!");
			return ;
		}
		int i;
		for (i = 0;i < size-1; i++){
			System.out.print(array[(first+i)%array.length] + "->");
		}
		System.out.println(array[(first+i)%array.length] + ";");
	}

	public static void test(){
		ResizingArrayDeque<String> deque = new ResizingArrayDeque<String>(0);
		for (int i = 0; i < 0; i++)
			deque.pushRight(String.valueOf(i));
		deque.print();
		deque.pushLeft("first");
		deque.print();
		deque.pushRight("last");
		deque.print();
		deque.popLeft();
		deque.print();
		deque.popRight();
		deque.print();

		//		deque.pop();
		//		deque.enqueue("enqueue");
		//		deque.pop();
		//		for (String item : deque)
		//			System.out.println("pop:" + item);
	}

	public static void main(String[] args){
		ResizingArrayDeque.test();
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item>{
		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Item next() {
			Item item = array[(first+i)%array.length];
			i++;
			return item;
		}

	}

}
