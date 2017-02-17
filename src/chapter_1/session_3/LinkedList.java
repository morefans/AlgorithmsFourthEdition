package chapter_1.session_3;
/**
 * 链表，习题1.3.18到习题1.3.30
 * @author ZhangYuliang
 *
 */
class Node<Item>{
	Item item;
	Node<Item> next;
	public Node(Item item){
		this.item = item;
	}
}
public class LinkedList<Item> {

	private Node<Item> first;
	public LinkedList(){
	}
	public LinkedList(Node<Item> first){
		this.first = first;
	}
	public LinkedList(Node<Item>[] nodes){
		for (int i = 1;i < nodes.length;i++){
			nodes[i-1].next = nodes[i];
		}
		this.first = nodes[0];
	}

	public boolean isEmpty(){
		return first == null;
	}
	public Node<Item> getFirst(){
		return first;
	}
	public int length(){
		Node<Item> node = first;
		int length = 0;
		while (node != null){
			node = node.next;
			length++;
		}
		return length;
	}
	public Node<Item> nodeAt(int index){
		Node<Item> node = first;
		int i = 0;
		while (node != null && i < index){
			node = node.next;
			i++;
		}
		return node;
	}

	public boolean deleteTail(){
		if (first == null)
			return false;
		if (first.next == null){
			first = null;
			return true;
		}
		Node<Item> node = first;
		while (node.next.next != null){
			node = node.next;
		}
		node.next = null;
		return true;
	}
	public boolean delete(int index){
		if (first == null)
			return false;
		if (index == 0){
			first = first.next;
			return true;
		}
		Node<Item> preNode = first;
		Node<Item> node = first.next;
		int i = 1;
		while (node != null && i < index){
			preNode = node;
			node = node.next;
			i++;
		}
		if (node == null)
			return false;
		else{
			preNode.next = node.next;
			return true;
		}
	}
	public boolean removeAfter(Node<Item> node){
		if (node == null)
			return true;
		node.next = null;
		return true;
	}
	public boolean insertAfter(Node<Item> node, Node<Item> insertNode){
		if (node == null || insertNode == null)
			return false;
		insertNode.next = node.next;
		node.next = insertNode;
		return true;
	}
	public boolean find(String key){
		Node<Item> node = first;
		while (node != null){
			if (key.equals(String.valueOf(node.item)))
				return true;
			node = node.next;
		}
		return false;
	}
	public Item max(){
		if (first == null)
			return null;
		if (first.item instanceof Integer
				|| first.item instanceof Double 
				|| first.item instanceof Float 
				|| first.item instanceof Short
				|| first.item instanceof Long){
			long max = Long.parseLong(String.valueOf(first.item));
			Node<Item> node = first;
			Item item = null;
			while (node != null){
				long temp = Long.parseLong(String.valueOf(node.item));
				if (max < temp){
					max = temp;
					item = node.item;
				}
				node = node.next;
			}
			return item;
		}else
			return null;
	}

	public boolean remove(String key){
		if (first == null)
			return false;
		while (first != null && key.equals(String.valueOf(first.item))){
			first = first.next;
		}
		Node<Item> preNode = first;
		Node<Item> node = first.next;
		while (node != null){
			if (key.equals(String.valueOf(node.item))){
				preNode.next = node.next;
				node = node.next;
			}else{
				preNode = node;
				node = node.next;
			}
		}
		return true;
	}
	public boolean reverse(){
		if (first == null || first.next == null)
			return true;
		Node<Item> pre = first;
		Node<Item> cur = first.next;
		Node<Item> next = cur.next;
		if (pre.next == cur)
			pre.next = null;
		while (next != null){
			cur.next = pre;
			pre = cur;
			cur = next;
			next = next.next;
		}
		cur.next = pre;
		first = cur;
		return true;
	}
	public Node<Item> reverse(Node<Item> first){
		if (first == null)
			return null;
		if (first.next == null)
			return first;
		Node<Item> second = first.next;
		Node<Item> rest = reverse(second);
		second.next = first;
		first.next = null;
		return rest;
	}
	public boolean reverseRecursion(){
		first = reverse(first);
		return true;
	}
	public void print(){
		if (isEmpty())
			return ;
		Node<Item> node = first;
		while (node != null && node.next != null){
			System.out.print(node.item + " -> ");
			node = node.next;
		}
		System.out.println(node.item + ";");
	}
	public static void test(){
		@SuppressWarnings("unchecked")
		Node<Integer>[] nodes = (Node<Integer>[])new Node[10];
		for (int i = 0; i < 10; i++){
			nodes[i] = new Node<Integer>(i);
		}
		LinkedList<Integer> chain = new LinkedList<Integer>(nodes);
		System.out.println("isEmpty:" + chain.isEmpty());
		System.out.println("length:" + chain.length());
		chain.print();

		System.out.println("deleteTail:" + chain.deleteTail());
		System.out.println("length:" + chain.length());
		chain.print();

		System.out.println("delete:" + chain.delete(7));
		System.out.println("length:" + chain.length());
		chain.print();

		System.out.println("find:" + chain.find("2"));
		Node<Integer> tempNode = chain.nodeAt(3);
		System.out.println("nodeAt:" + tempNode.item);

		System.out.println("removeAfter:" + chain.removeAfter(nodes[3]));
		chain.print();

		System.out.println("insertAfter:" + chain.insertAfter(nodes[2],new Node<Integer>(0)));
		chain.print();

		System.out.println("remove(key):" + chain.remove("0"));
		chain.print();

		System.out.println("insertAfter:" + chain.insertAfter(nodes[2],new Node<Integer>(9)));
		chain.print();
		System.out.println("max:" + chain.max());
		chain.delete(3);
//		chain.delete(2);
//		chain.delete(1);
		chain.print();
		System.out.println("reverse:" + chain.reverse());
		chain.print();
		System.out.println("reverse:" + chain.reverseRecursion());
		chain.print();

	}

	public static void main(String[] args) {
		LinkedList.test();
	}

}

