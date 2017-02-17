package chapter_3.session_1;

import chapter_1.session_3.Queue;

/**
 * 基于无序链表的顺序查找
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {
	private Node first;
	private int size = 0;
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node (Key key, Value value, Node next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public Value get(Key key){
		for (Node node = first; node != null; node = node.next){
			if (key.equals(node.key)){
				return node.value;
			}
		}
		return null;
	}
	public void put(Key key, Value value){
		if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
		if (value == null) {
			delete(key);
			return;
		}
		for (Node node = first; node != null; node = node.next){
			if (key.equals(node.key)){
				node.value = value;
				return ;
			}
		}
		first = new Node(key, value, first);
		size++;
	}
	public boolean contains(Key key){
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
		first = delete(first, key);
	}
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		if (key.equals(x.key)) {
			size--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
	public Iterable<Key> keys()  {
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}
	public static void test(){
		SequentialSearchST<String, String> st = new SequentialSearchST<String, String>();
		st.put("a", "a1");
		st.put("cd", "cd1");
		st.put("b", "b1");
		st.put("r", "r1");
		System.out.println(st.get("1"));
		System.out.println(st.get("cd"));
	}
	public static void main(String[] args){
		test();
	}
}
