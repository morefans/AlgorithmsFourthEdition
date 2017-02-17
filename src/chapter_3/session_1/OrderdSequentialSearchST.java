package chapter_3.session_1;

import chapter_1.session_3.Queue;
/**
 * 习题3.1.3，有序链表实现的基本符号表
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class OrderdSequentialSearchST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key key, Value value, Node next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	private Node first;
	private int size;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}
	/**
	 * 无序插入
	 * @param key
	 * @param value
	 */
	public void putUnordered(Key key, Value value) {
		delete(key);
		if (isEmpty()){
			first = new Node(key, value, null);
			size++;
			return ;
		}
		Node newFirst = new Node(key, value, first);
		first = newFirst;
		size++;
	}
	@Override
	public void put(Key key, Value value) {
		if (isEmpty()){
			first = new Node(key, value, null);
			size++;
			return ;
		}
		Node node = first;
		Node pre = null;
		while (node != null){
			if (key.compareTo(node.key) == 0){
				node.value = value;
				return;
			}
			else if (key.compareTo(node.key) < 0){
				if (pre == null){
					first = new Node(key, value, first);
				}else{
					Node newNode = new Node(key, value, node);
					pre.next = newNode;
				}
				size++;
				return ;
			}else{
				pre = node;
				node = node.next;
			}
		}
		if (pre == null){
			first = new Node(key, value, first);
		}else{
			Node newNode = new Node(key, value, node);
			pre.next = newNode;
		}
		size++;
	}

	@Override
	public Value get(Key key) {
		Node node = first;
		while (node != null){
			if (node.key.equals(key))
				return node.value;
			node = node.next;
		}
		return null;
	}

	@Override
	public void delete(Key key) {
		Node node = first;
		Node pre = null;
		while (node != null){
			if (node.key.equals(key)){
				if (pre == null){
					first = node.next;
				}else{
					pre.next = node.next;
				}
				return;
			}else{
				pre = node;
				node = node.next;
			}
		}
	}

	@Override
	public boolean contains(Key key) {
		if (get(key) != null)
			return true;
		return false;
	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		Node node = first;
		while (node != null){
			queue.enqueue(node.key);
			node = node.next;
		}
		return queue;
	}
	public static void test() {
		OrderdSequentialSearchST<Integer, String> st = new OrderdSequentialSearchST<Integer, String>();
		for (int i = 0;i < 10;i++)
			st.put(i, String.valueOf(i));
		System.out.println("st.get(1): " + st.get(1));
		st.put(1,"11");
		System.out.println("st.get(1): " + st.get(1));
		for (Integer key : st.keys()){
			System.out.print( key + "\t");
		}
		System.out.println();
		st.delete(1);
		System.out.println("st.get(1): " + st.get(1));
		System.out.println("st.contains(1): " + st.contains(1));
		System.out.println("st.size():" + st.size());
		for (Integer key : st.keys()){
			System.out.print( key + "\t");
		}
	}
	public static void main(String[] args) {
		test();
	}

}
