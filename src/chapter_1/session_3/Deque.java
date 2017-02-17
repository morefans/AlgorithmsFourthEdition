package chapter_1.session_3;

import java.util.Iterator;
/**
 * 双向队列，习题1.3.33，用双向链表实现
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item>{
	private class Node{
		Item item;
		Node previous;
		Node next;
	}
	private Node first;
	private Node last;
	private int size;

	public boolean isEmpty(){
		return size == 0;// 或者 first == null;
	}

	public int size(){
		return size;
	}

	public void pushRight(Item item){
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else{
			oldlast.next = last;
			last.previous = oldlast;
		}
		size++;		
	}
	public void pushLeft(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (isEmpty())
			last = first;
		else
			first.next.previous = first;
		size++;
	}
	public Item popLeft(){
		if (isEmpty())
			return null;
		Item item = first.item;
		first = first.next;
		size--;
		if (isEmpty()){
			last =  null;
		}else{
			first.previous = null;
		}
		return item;
	}
	public Item popRight(){
		if (isEmpty())
			return null;
		Item item = last.item;
		last = last.previous;
		size--;
		if (isEmpty())
			first =  null;
		else{
			last.next = null;
		}
		return item;
	}
	public Item peak(){
		if (first != null)
			return first.item;
		else
			return null;
	}
	public Item tail(){
		if (last != null)
			return last.item;
		else
			return null;
	}

	public static void test(){
		Deque<String> deque = new Deque<String>();
		for (int i = 0; i < 0; i++)
			deque.pushRight(String.valueOf(i));
		deque.print();
		deque.pushLeft("first");
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
	public void print(){
		if (isEmpty()){
			System.out.println("Deque is empty!");
			return ;
		}
		Node node = first;
		while (node != null && node.next != null){
			System.out.print(node.item + "->");
			node = node.next;
		}
		System.out.println(node.item + ";");
	}

	public static void main(String[] args){
		Deque.test();
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item>{
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

		public void remove(){}
	}
}
