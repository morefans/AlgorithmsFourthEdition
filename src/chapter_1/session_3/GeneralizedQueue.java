package chapter_1.session_3;

/**
 * 习题1.3.38，删除第k个元素，链表实现
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class GeneralizedQueue<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node first;
	private Node last;
	private int size;
	public boolean isEmpty(){
		return first == null;//size == 0;
	}
	public int size(){
		return size;
	}
	public void insert(Item item){
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
	public Item delete(int k){
		if (isEmpty() || k >= size)
			return null;
		if (k == 0){
			Item item = first.item;
			first = first.next;
			size--;
			return item;
		}
		Node node = first;
		for (int i = 0;i < k-1;i++){
			node = node.next;
		}
		Item item = node.next.item;
		node.next = node.next.next;
		size--;
		return item;
	}

	public void print(){
		System.out.print("queue:\t");
		Node node = first;
		while (node != null){
			System.out.print(node.item + "\t");
			node = node.next;
		}
		System.out.println();
	}
	public static void test(){
		GeneralizedQueue<String> queue = new GeneralizedQueue<String>();
		for (int i = 0;i < 10;i++){
			queue.insert(String.valueOf(i));
		}
		queue.print();
		System.out.println(queue.delete(2));
		queue.print();
		System.out.println(queue.delete(0));
		queue.print();
		System.out.println(queue.delete(7));
		queue.print();
	}
	public static void main(String[] args){
		GeneralizedQueue.test();
	}

}
