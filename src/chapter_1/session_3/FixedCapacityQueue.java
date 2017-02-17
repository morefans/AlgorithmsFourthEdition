package chapter_1.session_3;
/**
 * 固定容量队列，使用数组实现
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class FixedCapacityQueue<Item> {
	private Item[] array;
	private int size;
	private int first;
	private int last;
	
	@SuppressWarnings("unchecked")
	public FixedCapacityQueue(int capacity){
		array = (Item[]) new Object[capacity];
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	private boolean isFull(){
		if (size == array.length)
			return true;
		return false;
	}
	
	public boolean enqueue(Item item){
		if (isFull())
			return false;
		array[last] = item;
		last = (last+1) % array.length;
		size++;
		return true;
	}
	
	
	public Item dequeue(){
		if (isEmpty())
			return null;
		Item item = array[first];
		first = (first+1) % array.length;
		size--;
		return item;
	}
	
	public static void test(){
		FixedCapacityQueue<String> queue = new FixedCapacityQueue<String>(5);
		queue.enqueue("w");
		System.out.println("dequeue:" + queue.dequeue());
		System.out.println("dequeue:" + queue.dequeue());
		for (int i = 0; i < 10; i++)
			queue.enqueue(String.valueOf(i));

		System.out.println("dequeue:" + queue.dequeue());
		queue.enqueue("a");
		while (!queue.isEmpty())
			System.out.println("dequeue:" + queue.dequeue());
	}
	
	public static void main(String[] args){
		FixedCapacityQueue.test();
	}

}
