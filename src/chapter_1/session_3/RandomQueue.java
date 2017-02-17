package chapter_1.session_3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 习题1.3.35，随机队列，习题1.3.36，随机迭代器，数组的shuffle
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class RandomQueue<Item> implements Iterable<Item>{

	private Item[] array;
	private int size;
	private int first;
	private int last;

	@SuppressWarnings("unchecked")
	public RandomQueue(int capacity){
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

		int r = new Random(System.currentTimeMillis()).nextInt(size);
		int real = (r+first)%array.length;
		Item item = array[real];
		array[real] = array[first];
		array[first] = item;
		
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
		RandomQueue<String> queue = new RandomQueue<String>(0);
		for (int i = 0; i < 10; i++)
			queue.enqueue(String.valueOf(i));
		System.out.print("go through:\t");
		for (String item : queue)
			System.out.print(item + "\t");
		System.out.println();
		System.out.print("dequeue:\t");
		while (!queue.isEmpty())
			System.out.print(queue.dequeue() + "\t");
		System.out.println();
	}

	public static void main(String[] args){
		RandomQueue.test();
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{
		private Item[] items;
		private int index = 0;
		
		@SuppressWarnings("unchecked")
		public QueueIterator(){
			items = (Item[]) new Object[size];
			for (int i = 0;i < size;i++){
				items[i] = array[(first+i)%array.length];
			}
			shuffle();
		}
		public void shuffle(){
			int r;
			for (int i = 0;i < size;i++){
				r = new Random(System.currentTimeMillis()).nextInt(size-i);
				Item item = items[r];
				items[r] = items[size-1-i];
				items[size-1-i] = item;
			}
			return ;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Item next() {
			return items[index++];
		}
	}

}
