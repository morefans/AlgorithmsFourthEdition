package chapter_1.session_3;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 可调整大小数组队列，数组实现，泛型和迭代器
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
	private Item[] array;
	private int size;
	private int first;
	private int last;

	@SuppressWarnings("unchecked")
	public ResizingArrayQueue(int capacity){
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

	public void enqueue(Item item){
		if (size == array.length)
			resize(2*array.length);
		array[last] = item;
		last = (last+1) % array.length;
		size++;
	}


	public Item dequeue(){
		if (isEmpty())
//			return null;
			throw new NoSuchElementException("Queue underflow");
		Item item = array[first];
		first = (first+1) % array.length;
		size--;
		// 如果栈大小不足四分之一，调整为半满
		if (size > 0 && size < array.length/4)
			resize(array.length/2);
		return item;
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

	public static void test(){
		ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>(0);
		queue.enqueue("w");
		System.out.println("dequeue:" + queue.dequeue());
//		System.out.println("dequeue:" + queue.dequeue());
		for (int i = 0; i < 10; i++)
			queue.enqueue(String.valueOf(i));

		System.out.println("dequeue:" + queue.dequeue());
		queue.enqueue("a");
//		while (!queue.isEmpty())
//			System.out.println("dequeue:" + queue.dequeue());
		for (String item : queue)
			System.out.println("dequeue:" + item);
	}

	public static void main(String[] args){
		ResizingArrayQueue.test();
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{
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
