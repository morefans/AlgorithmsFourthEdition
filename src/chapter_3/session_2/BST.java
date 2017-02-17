package chapter_3.session_2;

import chapter_1.session_3.Queue;

public class BST<Key extends Comparable<Key>, Value> {
	private class Node{
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int N;
		public Node(Key key, Value value, int N){
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	private Node root;
	private int size(Node node){
		if (node == null)
			return 0;
		return node.N;
	}
	public int size(){
		return size(root);
	}
	private Value get(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return get(node.left, key);
		else if (cmp > 0)
			return get(node.right, key);
		else
			return node.value;
	}
	public Value get(Key key){
		return get(root, key);
	}
	private Node put(Node node, Key key, Value value){
		if (node == null)
			return new Node(key, value, 1);
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			node.left = put(node.left, key, value);
		else if (cmp > 0)
			node.right = put(node.right, key, value);
		else
			node.value = value;
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	public void put(Key key, Value value){
		root =  put(root, key, value);
	}
	private Node min(Node node){
		if (node.left == null)
			return node;
		return min(node.left);
	}
	public Key min(){
		Node n = min(root);
		if (n == null)
			return null;
		return min(root).key;
	}
	private Key max(Node node){
		if (node.right == null)
			return node.key;
		else return max(node.right);
	}
	public Key max(){
		return max(root);
	}
	private Node floor(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp == 0)
			return node;
		if (cmp < 0)
			return floor(node.left, key);
		Node temp = floor(node.right, key);
		if (temp != null)
			return temp;
		else return node;
	}
	public Key floor(Key key){
		Node node = floor(root, key);
		if (node == null)
			return null;
		return node.key;
	}
	private Node ceiling(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp == 0)
			return node;
		if (cmp > 0)
			return ceiling(node.right, key);
		Node temp = ceiling(node.left, key);
		if (temp != null)
			return temp;
		else return node;
	}
	public Key ceiling(Key key){
		Node node = ceiling(root, key);
		if (node == null)
			return null;
		return node.key;
	}
	private Node select(Node node, int k){
		if (node == null)
			return null;
		int t = size(node.left);
		if (t > k)
			return select(node.left, k);
		else if (t < k)
			return select(node.right, k-t-1);
		else
			return node;
	}
	public Key select(int k){
		Node node = select(root, k);
		if (node == null)
			return null;
		return node.key;
	}
	private int rank(Key key, Node node){
		if (node == null)
			return 0;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return rank(key, node.left);
		else if (cmp > 0)
			return 1 + size(node.left) + rank(key, node.right);
		else return size(node.left);
	}
	public int rank(Key key){
		return rank(key, root);
	}
	public void deleteMin(){
		root = deleteMin(root);
	}
	private Node deleteMin(Node node){
		if (node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	public void deleteMax(){
		root = deleteMax(root);
	}
	private Node deleteMax(Node node){
		if (node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	public void delete(Key key){
		root = delete(root, key);
	}
	private Node delete(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			node.left = delete(node.left, key);
		else if (cmp > 0)
			node.right = delete(node.right, key);
		else{
			if (node.right == null)
				return node.left;
			if (node.left == null)
				return node.right;
//			if (size(node) == size()){//node.equals(root)){
//				Node temp = node;
//				node = deleteMin(node.right);
//				node.right = 
//				node.N = size(node.left) + size(node.right) + 1;
//				return root;
//			}
			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left; 
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	private void print(Node node){
		if (node == null)
			return ;
		print(node.left);
		System.out.print(node.key + "\t");
		print(node.right);
	}
	public void print(){
		print(root);
	}
	public void show(){
		for (Key key : keys())
			System.out.print(key + "\t");
		System.out.println();
	}
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	public Iterable<Key> keys(Key low, Key high){
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, low, high);
		return queue;
	}
	private void keys(Node node, Queue<Key> queue, Key low, Key high){
		if (node == null)
			return ;
		int cmplow = low.compareTo(node.key);
		int cmphigh = high.compareTo(node.key);
		if (cmplow < 0)
			keys(node.left, queue, low, high);
		if (cmplow <= 0 && cmphigh >= 0)
			queue.enqueue(node.key);
		if (cmphigh > 0)
			keys(node.right, queue, low, high);
	}
	public static void test(){
		BST<String, String> bst = new BST<String, String>();
		bst.put("E", "E");
		bst.put("B", "B");
		bst.put("A", "A");
		bst.put("S", "S");
		bst.put("D", "D");
		bst.put("R", "R");
		bst.put("Y", "Y");
		bst.put("I", "I");
//		bst.put("C", "C");
		System.out.println("root: " + bst.root.key);
		bst.show();
		System.out.println("size(): " + bst.size());
		System.out.println("get(D): " + bst.get("D"));
		System.out.println("floor(P): " + bst.floor("P"));
		System.out.println("floor(E): " + bst.floor("E"));
		System.out.println("floor(C): " + bst.floor("C"));
		System.out.println("floor(1): " + bst.floor("1"));
		System.out.println("floor(a): " + bst.floor("a"));
		System.out.println("ceiling(P): " + bst.ceiling("P"));
		System.out.println("ceiling(E): " + bst.ceiling("E"));
		System.out.println("ceiling(C): " + bst.ceiling("C"));
		System.out.println("ceiling(1): " + bst.ceiling("1"));
		System.out.println("ceiling(a): " + bst.ceiling("a"));
		System.out.println("rank(A): " + bst.rank("A"));
		System.out.println("rank(E): " + bst.rank("E"));
		System.out.println("rank(R): " + bst.rank("R"));
		System.out.println("rank(I): " + bst.rank("I"));
		System.out.println("rank(C): " + bst.rank("C"));
		System.out.println("rank(D): " + bst.rank("D"));
		System.out.println("select(0): " + bst.select(0));
		System.out.println("select(1): " + bst.select(1));
		System.out.println("select(2): " + bst.select(2));
		System.out.println("select(3): " + bst.select(3));
		System.out.println("select(4): " + bst.select(4));
		System.out.println("select(5): " + bst.select(5));
		System.out.println("select(6): " + bst.select(6));
		System.out.println("select(7): " + bst.select(7));
		System.out.println("select(8): " + bst.select(8));
		System.out.println("min(): " + bst.min());
		System.out.println("max(): " + bst.max());
		bst.deleteMin();
		bst.deleteMax();
		bst.delete("E");
		System.out.println("root: " + bst.root.key);
		bst.show();
	}
	public static void main(String[] args){
		test();
	}
}
