package chapter_2.session_4;

//import chapter_1.session_3.Queue;

public class MaxPQLinkedList<Key extends Comparable<Key>> {
	/*
	private class Node{
		Key key;
		Node parent;
		Node left;
		Node right;
		public Node(){}
		public Node(Key key){}
	}
	private Node root;
	private Node tail;
	private int size;
	public MaxPQLinkedList(Key[] array){
		root = new Node(array[0]);
		size = array.length;
		for (int i = 0;i <= (array.length-1)/2;i++){
			Node left = new Node(array[2*i+1]);
			Node right = new Node(array[2*i+2]);
		}
		adjustToHeap();
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	public void insert(Key v){
	}
	public Key max(){
		return root.key;
	}
	public Key delMax(){
		Key max = root.key;
		return max;
	}
	public boolean less(Key i, Key j){
		return i.compareTo(j) < 0;
	}
	public void exch(Node node1, Node node2){
		Key temp = node1.key;
		node1.key = node2.key;
		node2.key = temp;
	}
	public void swim(Node node){
		if (node == null)
			return;
		if (node.parent != null && less(node.parent.key, node.key)){
			exch(node, node.parent);
			swim(node.parent);
		}
	}
	public void sink(Node node){
		if (node == null)
			return ;
		if (node.left == null || node.right == null){
			if (node.left != null && less(node.key, node.left.key)){
				exch(node, node.left);
				sink(node.left);
				return ;
			}
			if (node.right != null && less(node.key, node.right.key)){
				exch(node, node.right);
				sink(node.right);
				return ;
			}
		}
		else if (less(node.key, node.left.key) && less(node.right.key, node.left.key)){
			exch(node, node.left);
			sink(node.left);
			return ;
		}else if(less(node.key, node.right.key) && less(node.left.key, node.right.key)){
			exch(node, node.right);
			sink(node.right);
			return ;
		}
	}
	public void show(){
		System.out.print("{");
		Queue<Node> queue = new Queue<Node>();
		queue.enqueue(root);
		while (!queue.isEmpty()){
			Node node = queue.dequeue();
			if (node.left != null)
				queue.enqueue(node.left);
			if (node.right != null)
				queue.enqueue(node.left);
			System.out.print(node.key + ", ");
		}
		System.out.println("}");
	}

	public void adjust(Node node){
		swim(node);
		swim(node.left);
		swim(node.right);
	}
	public void adjustToHeap(){
		adjust(root);
	}
	public boolean isMaxHeap(){
		return isMaxHeap(root);
	}
	public boolean isMaxHeap(Node node){
		if (node == null)
			return true;
		if (node.left !=null && less(node.key, node.left.key))
			return false;
		if (node.right !=null && less(node.key, node.right.key))
			return false;
		return isMaxHeap(node.left) && isMaxHeap(node.right);
	}
	public static void main(String[] args){
//		Integer[] array = {0,1,2,3,4,5,6,7,8,9};
//		MaxPQ<Integer> maxPQ = new MaxPQ<>(array);
//		maxPQ.show();
//		System.out.println(maxPQ.isMaxHeap());
////		maxPQ.insert(12);
////		maxPQ.show();
////		maxPQ.insert(10);
////		maxPQ.show();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.delMax();
//		maxPQ.show();
	}
*/
}
