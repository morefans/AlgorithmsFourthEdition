 package chapter_1.session_3;

import java.util.Iterator;
/**
 * 背包，使用链表实现
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node first;
	private int size;
	
	public void add(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return size;
	}
	
	public static void test(){
		Bag<String> bag = new Bag<String>();
		for (int i = 0; i < 10; i++)
			bag.add(String.valueOf(i));
		for (String item : bag)
			System.out.println("bag:" + item);
	}


	public static void main(String[] args) {
		Bag.test();
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
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
