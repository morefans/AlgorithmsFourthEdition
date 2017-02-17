package chapter_3.session_2;

import java.util.Random;

import chapter_1.session_3.Queue;

/**
 * 递归实现的二叉查找树
 * 非递归的keys()方法 似乎 要用栈和队列辅助来实现
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class BSTRecursive<Key extends Comparable<Key>, Value> {
	private class Node{
		Key key;
		Value value;
		Node left;
		Node right;
		int size;// 以该结点为根的子树中的结点总数
		public Node(Key key, Value value, int size){
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}
	private Node root;
	private Node present;// 缓存最近访问的结点
	private int size(Node node){
		if (node == null)
			return 0;
		return node.size;
	}
	public int size(){
		return size(root);
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public void put(Key key, Value value){
		root = put(root, key, value);
	}
	private Node put(Node node, Key key, Value value){
		if (node == null)
			node = new Node(key, value, 1);
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			node.left = put(node.left, key, value);
		else if (cmp > 0)
			node.right = put(node.right, key, value);
		else
			node.value = value;
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	public Value get(Key key){
		if (present != null && present.key.compareTo(key) == 0)
			return present.value;
		return get(root, key);
	}
	private Value get(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return get(node.left, key);
		else if (cmp > 0)
			return get(node.right, key);
		else{
			present = node;
			return node.value;
		}
	}
	public boolean contains(Key key) {
        return get(key) != null;
    }
	public Key min(){
		if (isEmpty())
			return null;
		return min(root).key;
	}
	private Node min(Node node){ 
		if (node.left == null)
			return node;
		else return min(node.left);
	}
	public Key max(){
		if (isEmpty())
			return null;
		return max(root).key;
	}
	private Node max(Node node){ 
		if (node.right == null)
			return node;
		else return max(node.right);
	}
	public Key floor(Key key){
		return floor(root, key);
	}
	private Key floor(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0)
			return floor(node.left, key);
		else if (cmp == 0)
			return node.key;
		else {
			Key temp = floor(node.right, key);
			if (temp == null)
				return node.key;
			return temp;
		}
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
	public Key select(int k){
		return select(root, k);
	}
	private Key select(Node node, int k){
		if (node == null)
			return null;
		int count = size(node.left);
		if (count < k){
			return select(node.right, k-count-1);
		}else if (count > k){
			return select(node.left, k);
		}else{
			return node.key;
		}
	}
	private int rank(Node node, Key key){
		if (node == null)
			return 0;
		int cmp = key.compareTo(node.key);
		if (cmp < 0){
			return rank(node.left, key);
		}else if (cmp > 0){
			return 1 + size(node.left) + rank(node.right, key);
		}else{
			return size(node.left);
		}
	}
	public int rank(Key key){
		return rank(root, key);
	}
	private Node deleteMin(Node node){
		if (node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	public void deleteMin(){
		root = deleteMin(root);
	}

	private Node deleteMax(Node node){
		if (node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	public void deleteMax(){
		root = deleteMax(root);
	}
	private Node delete(Node node, Key key){
		if (node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0){
			node.left = delete(node.left, key);
		}else if (cmp > 0){
			node.right = delete(node.right, key);
		}else{
			if (node.right == null)
				return node.left;
			if (node.left == null)
				return node.right;
			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	public void delete(Key key){
		root = delete(root, key);
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
	public int height(){
		return height(root);
	}
	private int height(Node node){
		if (node == null)
			return 0;
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		if (leftHeight >= rightHeight){
			return leftHeight + 1;
		}else{
			return rightHeight + 1;
		}
	}
	public Key randomKey(){
		int size = size();
		int random = new Random().nextInt(size);
		return select(random);
	}
	public boolean isBinaryTree(){
		return isBinaryTree(root);
	}
	private boolean isBinaryTree(Node node){
		if (nodeCount(node) == node.size)
			return true;
		return false;
	}
	private int nodeCount(Node node){
		if (node == null)
			return 0;
		return 1 + nodeCount(node.left) + nodeCount(node.right);
	}
	public boolean isOrdered(){
		return isOrdered(root, min(), max());
	}
	private boolean isOrdered(Node node, Key min, Key max){
		if (min.compareTo(min(node).key) != 0 || max.compareTo(max(node).key) != 0)
			return false;
		return isOrdered(node);
	}
	private boolean isOrdered(Node node){
		if (node == null)
			return true;
		if (node.left == null && node.right == null)
				return true;
		if (node.left == null && node.right != null){
			if (node.key.compareTo(node.right.key) <= 0)
				return isOrdered(node.right);
			else return false;
		}
		if (node.right == null && node.left != null){
			if (node.key.compareTo(node.left.key) >= 0)
				return isOrdered(node.left);
			else return false;
		}
		if (node.left.key.compareTo(node.key) <= 0 && node.key.compareTo(node.right.key) <= 0)
			return isOrdered(node.left) && isOrdered(node.right);
		return false;
	}
	@SuppressWarnings("unused")
	private boolean isOrderedAndInRange(Node node, Key min, Key max){
		if (node == null)
			return true;
		if (node.left == null && node.right == null){
			if (node.key.compareTo(min) >= 0 && node.key.compareTo(max) <= 0)
				return true;
			else return false;
		}
		if (node.left == null && node.right != null){
			if (node.key.compareTo(node.right.key) <= 0 && node.key.compareTo(min) >= 0)
				return isOrdered(node.right, min, max);
			else return false;
		}
		if (node.right == null && node.left != null && node.key.compareTo(max) <= 0){
			if (node.key.compareTo(node.left.key) >= 0)
				return isOrdered(node.left, min, max);
			else return false;
		}
		if (node.left.key.compareTo(node.key) <= 0 && node.key.compareTo(node.right.key) <= 0)
			return isOrdered(node.left, min, max) && isOrdered(node.right, min, max);
		return false;
	}
	public boolean hasNoDuplicates(){
		return hasNoDuplicates(root);
	}
	private boolean hasNoDuplicates(Node node){
		if (node == null)
			return true;
		if (node.left != null && node.left.key.compareTo(node.key) == 0)
			return false;
		if (node.right != null && node.right.key.compareTo(node.key) == 0)
			return false;
		return true;
	}
	public boolean isBST(){
		if (!isBinaryTree(root))
			return false;
		if (!isOrdered(root, min(), max()))
			return false;
		if (!hasNoDuplicates(root))
			return false;
		return true;
	}
	public static void test(){
		BSTRecursive<String, String> bst = new BSTRecursive<String, String>();
		bst.put("E", "E");
		bst.put("B", "B");
		bst.put("A", "A");
		bst.put("S", "S");
		bst.put("D", "D");
		bst.put("R", "R");
		bst.put("Y", "Y");
		bst.put("I", "I");
		bst.put("I", "I");
//		bst.put("C", "C");
		System.out.println("root: " + bst.root.key);
		bst.print();
		System.out.println("size(): " + bst.size());
		System.out.println("isBinaryTree(): " + bst.isBinaryTree());
		System.out.println("isOrdered(): " + bst.isOrdered());
		System.out.println("hasNoDuplicates(): " + bst.hasNoDuplicates());
		System.out.println("get(D): " + bst.get("D"));
		System.out.println("get(E): " + bst.get("E"));
		System.out.println("get(E): " + bst.get("E"));
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
		System.out.println("height(): " + bst.height());
		bst.deleteMin();
		bst.deleteMax();
		bst.delete("E");
		System.out.println("deleteMin(), deleteMax(),delete(\"E\")");
		System.out.println("root: " + bst.root.key);
		bst.print();
		System.out.println("height(): " + bst.height());
		System.out.println("isBinaryTree(): " + bst.isBinaryTree());
	}
	public static void main(String[] args){
		test();
	}
}
