package chapter_1.session_3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 栈，链表实现，泛型和迭代器
 * 习题1.3.50，快速出错的迭代器
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node first;
	private int size;
	private int pushTimes;
	private int popTimes;
	
	public Stack(){}
	public Stack(Stack<Item> stack){
		size = stack.size();
		Node copyNode = stack.first;
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
	}

	public boolean isEmpty(){
		return size == 0;// 或者 first == null
	}

	public int size(){
		return size;
	}

	public void push(Item item){
		pushTimes++;
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	public Item pop(){
		popTimes++;
		if (first == null)
			return null;
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}
	public Item peak(){
		if (first != null)
			return first.item;
		else
			return null;
	}

	public static void test(){
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 1; i < 10; i++)
			stack.push(i);
		Stack<Integer> copyStack = new Stack<Integer>(stack);
		stack.first.item = 111;
		stack.push(11);
		//		while (!stack.isEmpty())
		//			System.out.println("pop:" + stack.pop() + "\t leftNum:" + stack.size());
		System.out.print("Stack:\t");
		for (int item : stack)
			System.out.print(item + "\t");
		System.out.println();

		System.out.print("CopyStack:\t");
		for (int item : copyStack)
			System.out.print(item + "\t");
		System.out.println();
	}
	public static void main(String[] args){
		Stack.test();
		@SuppressWarnings({ "unchecked" })
		//		Stack[] stacks = new Stack[10];
		//		Stack<String>[] stacks = new Stack[10];
		Stack<String>[] stacks = (Stack<String>[]) new Stack[10];//最好用这个生命Stack数组
		Stack<String> stack0 = new Stack<String>();
		stack0.push("a");
		stack0.push(null);
		stack0.push("b");
		//		stack0.first = null;//类内可以访问，类外不可以
		stacks[0] = stack0;
		for (String item : stacks[0])
			System.out.println(item);
	}


	@Override
	public Iterator<Item> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<Item>{
		private Node node = first;
		
		private int recordPush;
		private int recordPop;
		public StackIterator(){
			recordPush = pushTimes;
			recordPop = popTimes;
		}

		@Override
		public boolean hasNext() {
			if (recordPush != pushTimes || recordPop != popTimes)
				throw new ConcurrentModificationException("Pop or push in Iterator!");
			return node != null;
		}

		@Override
		public Item next() {
			if (recordPush != pushTimes || recordPop != popTimes)
				throw new ConcurrentModificationException("Pop or push in Iterator!");
			Item item = node.item;
			node = node.next;
			return item;
		}

		public void remove(){}
	}

}
