package chapter_1.session_3;

public class CircularLinkedListQueue<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node last;//如果是Node双向链表那么和普通的链表队列没什么区别了，只不过存为last.next和last.pre
	private int size = 0;
	public CircularLinkedListQueue(){
		last = new Node();
	}
	public boolean isEmpty(){
		return size == 0;// 或者 last.next == null;
	}
	
	public int size(){
		return size;
	}
	public void enqueue(Item item){
		Node newNode = new Node();
		newNode.item = item;
		if (isEmpty()){
			newNode.next = last;
			last.next = newNode;
			size++;
			return ;
		}
		Node node = last.next;
		while (node.next != last){
			node = node.next;
		}
		node.next = newNode;
		newNode.next = last;
		size++;		
	}
	
	public Item dequeue(){
		if (isEmpty())
			return null;
		Item item = last.next.item;
		last.next = last.next.next;
		size--;
		return item;
	}
	
	public void print(){
		if (isEmpty()){
			System.out.println("Queue is empty!");
			return ;
		}
		Node node = last.next;
		while (node != null && node.next != last){
			System.out.print(node.item + " -> ");
			node = node.next;
		}
		System.out.println(node.item + ";");
	}
	public static void test() {
		CircularLinkedListQueue<Integer> queue = new CircularLinkedListQueue<Integer>();
		queue.enqueue(0);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.print();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();
		queue.dequeue();
		queue.print();
	}
	public static void main(String[] args) {
		CircularLinkedListQueue.test();
	}

}
