package chapter_1.session_3;

import java.util.Iterator;

/**
 * 以栈为目标的队列，习题1.3.32
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class Steque<Item> implements Iterable<Item>{
	private class Node{
		Item item;
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
	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (isEmpty())
			last = first;
		size++;
	}
	public Item peak(){
		if (first != null)
			return first.item;
		else
			return null;
	}

	public Item pop(){
		if (isEmpty())
			return null;
		Item item = first.item;
		first = first.next;
		size--;
		if (isEmpty())
			last =  null;
		return item;
	}

	public static void test(){
		Steque<String> steque = new Steque<String>();
		for (int i = 0; i < 10; i++)
			steque.push(String.valueOf(i));
		steque.pop();
		steque.enqueue("enqueue");
		steque.pop();
		for (String item : steque)
			System.out.println("pop:" + item);
	}

	public static void main(String[] args){
		Steque.test();
	}

	@Override
	public Iterator<Item> iterator() {
		return new StequeIterator();
	}
	
	private class StequeIterator implements Iterator<Item>{
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
