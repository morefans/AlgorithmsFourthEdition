package chapter_1.session_3;

public class MoveToFront<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node first;
	private int size;

	public boolean isEmpty(){
		return first == null;// size == 0;// 
	}

	public int size(){
		return size;
	}

	public void insert(Item item){
		delete(String.valueOf(item));
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	public void delete(String key){
		if (first != null && key.equals(String.valueOf(first.item))){
			first = first.next;
			return ;
		}
		Node node = first;
		Node pre = first;
		while (node != null){
			if (key.equals(String.valueOf(node.item))){
				pre.next = node.next;
				break;
			}
			pre = node;
			node = node.next;
				
		}
	}
	public Item peak(){
		if (first != null)
			return first.item;
		else
			return null;
	}
	public void print(){
		Node node = first;
		System.out.print("LinkedList:\t");
		while (node != null){
			System.out.print(node.item + "\t");
			node = node.next;
		}
		System.out.println();
	}

	public static void test(){
		MoveToFront<Integer> moveToFront = new MoveToFront<Integer>();
		for (int i = 1; i < 10; i++)
			moveToFront.insert(i);
		moveToFront.insert(3);
		moveToFront.print();
		moveToFront.insert(1);
		moveToFront.print();
		moveToFront.insert(1);
		moveToFront.print();
	}
	public static void main(String[] args){
		MoveToFront.test();
	}
}
