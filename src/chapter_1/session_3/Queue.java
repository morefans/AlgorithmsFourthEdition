package chapter_1.session_3;

import java.util.Iterator;
/**
 * 队列，链表实现，泛型和迭代器都实现了
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node first;
	private Node last;
	private int size;
	
	public Queue(){}
	public Queue(Queue<Item> queue){
		size = queue.size();
		Node copyNode = queue.first;
		first = new Node();
		first.item = copyNode.item;
		copyNode = copyNode.next;
		Node node = first;
		Node pre = first;
		while (copyNode != null){
			node = new Node();
			node.item = copyNode.item;
			pre.next = node;
			pre = node;
			copyNode = copyNode.next;
		}
		last = node;
	}
	
	public boolean isEmpty(){
		return size == 0;// 或者 first == null;
	}
	
	public int size(){
		return size;
	}
	
	public void enqueue(Item item){
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		size++;		
	}
	
	public Item dequeue(){
		if (isEmpty())
			return null;
		Item item = first.item;
		first = first.next;
		size--;
		if (isEmpty())
			last =  null;
		return item;
	}
	
	public Item peak(){
		return first.item;
	}
	
	public static void test(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("w");
		System.out.println("dequeue:" + queue.dequeue());
		System.out.println("dequeue:" + queue.dequeue());
		for (int i = 0; i < 10; i++)
			queue.enqueue(String.valueOf(i));
		Queue<String> copyQueue = new Queue<String>(queue);
		
		queue.dequeue();
		queue.first.item = "first";
		System.out.print("queue:\t");
		for (String item : queue)
			System.out.print(item + "\t");
		System.out.println();

		System.out.print("copy queue:\t");
		for (String item : copyQueue)
			System.out.print(item + "\t");
		System.out.println();

		System.out.print("copy queue:\t");
		for (String item : copyQueue)
			System.out.print(item + "\t");
		System.out.println();
	}
	
	public static void main(String[] args){
		Queue.test();
	}
	

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{
		private Node node = first;

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public Item next() {
			Item item = node.item;
			node = node.next;
			return item;
		}
		
	}

}
