package chapter_2.session_2;

import java.util.Random;

import chapter_2.session_1.SortBase;

public class LinkedListSort extends SortBase {
	@SuppressWarnings("rawtypes")
	private class Node{
		Comparable value;
		Node next;
		public Node(Comparable value){
			this.value = value;
		}
	}

	private Node first;
	private int size;
	public LinkedListSort(){}
	public LinkedListSort(Node[] nodes){
		setLinkedList(nodes);
	}

	public void setLinkedList(Node[] nodes){
		size = nodes.length;
		for (int i = 0;i < nodes.length-1;i++){
			nodes[i].next = nodes[i+1];
		}
		first = nodes[0];
	}
	public int size(){
		return size;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {

	}
	/**
	 * 链表选择排序，每次选最大
	 * @return
	 */
	public Node selectSort() {
		if (first == null)
			return first;
		Node newFirst = null;
		Node beforeMax = null;
		Node max = first;
		Node beforeCur = null;
		Node cur = first;
		while (first.next != null){// 直到链表只剩一个最小元素
			beforeMax = null;
			max = first;
			beforeCur = null;
			cur = first;
			while (cur != null){// 每次选出最大
				if (less(max.value, cur.value)){
					max = cur;
					beforeMax = beforeCur;
				}
				beforeCur = cur;
				cur = cur.next;
			}

			if (beforeMax == null)// 最大是否是原先第一个结点
				first = first.next;
			else
				beforeMax.next = max.next;
			if (newFirst == null){// 新链表头结点是否为空
				newFirst = max;
				newFirst.next = null;
			}else{
				max.next = newFirst;
				newFirst = max;
			}
		}
		first.next = newFirst;
		return first;
	}
	public Node insertionSort(){// 链表插入排序
		if (first == null)
			return first;
		Node newFirst = null;
		Node cur = first;
		while (cur != null){
			first = first.next;
			if (newFirst == null){// 第一个结点
				newFirst = cur;
				newFirst.next = null;
			}
			else if (!less(newFirst.value, cur.value)){// 与第一个元素比较，较小则直接放在前面
				cur.next = newFirst;
				newFirst = cur;
			}else{// 与第一个元素比较，较大则插在第一个结点后面，然后开始往后移的操作
				cur.next = newFirst.next;
				newFirst.next = cur;
				Node beforeCur = newFirst;
				while (cur.next != null && less(cur.next.value, cur.value)){// 较大则往后交换
					Node temp = cur.next;
					cur.next = cur.next.next;
					temp.next = cur;
					beforeCur.next = temp;

					beforeCur = temp;
				}
			}
			cur = first;
		}
		first = newFirst;
		return first;
	}
	public Node bubbleSort(){
		if (first == null)
			return first;
		Node newFirst = null;
		Node beforeMax = null;
		Node max = null;
		while (first.next != null){
			beforeMax = null;
			max = first;
			while (max.next != null){
				if (!less(max.value, max.next.value)){
					Node temp = max.next;
					max.next = max.next.next;
					temp.next = max;

					if (beforeMax == null){
						first = temp;
						beforeMax = temp;
					}
					else{
						beforeMax.next = temp;
						beforeMax = temp;
					}
				}else{
					beforeMax = max;
					max = max.next;
				}
			}
			if (beforeMax != null)
				beforeMax.next = null;
			if (newFirst == null){
				newFirst = max;
				newFirst.next = null;
			}else{
				max.next = newFirst;
				newFirst = max;
			}
		}
		first.next = newFirst;
		return first;
	}
	public void test(){
		int N = 50;
		Node[] nodes = new Node[N];
		for (int i = 0;i < N;i++){
			Node node = new Node(i);
			nodes[i] = node;
		}
		//		shuffle(nodes);
		setLinkedList(nodes);
		show();
		//		first = shuffleRecursive(first);
		first = shuffleIterative(first);
		show();
		//		selectSort();
		//		insertionSort();
		//		bubbleSort();
		//		show();
	}
	public void show(){
		show(first);
	}
	public void show(Node root){
		Node node = root;
		System.out.print("{");
		while (node != null){
			if (node.next == null)
				break;
			System.out.print(node.value + ", ");
			node = node.next;
		}
		System.out.println(node.value + "}");
	}

	public static void main(String[] args) {
		//		for (int i = 0; i < 10; i++)
		//			System.out.println(new Random().nextInt(10));
		new LinkedListSort().test();
	}
	/**
	 * 随机打乱一个数组，O(n)时间复杂度和O(n)空间复杂度
	 * @param array
	 */
	public static void shuffleArray(Object[] array){
		for (int i = 0;i < array.length;i++){
			int random = new Random(System.currentTimeMillis()).nextInt(array.length-i);
			Object temp = array[random];
			array[random] = array[array.length-i-1];
			array[array.length-i-1] = temp;
		}
	}
	public static int lengthOfLinkedList(Node first){
		Node node = first;
		int length = 0;
		while (node != null){
			length++;
			node = node.next;
		}
		return length;
	}
	/**
	 * 随机打乱链表，直接思路，产生随机数，取那个位置的结点，放入，n平方级别的
	 * @param first
	 */
	public static Node shuffleNoArray(Node first){
		if (first == null)
			return first;
		int length = lengthOfLinkedList(first);
		Node newFirst = null;
		for (int i = 0;i < length;i++){
			int random = new Random().nextInt(length-i);
			Node beforeCur = null;
			Node cur = first;
			for (int k = 0;k < random;k++){
				beforeCur = cur;
				cur = cur.next;
			}
			if (beforeCur == null){
				first = first.next;
			}else{
				beforeCur.next = cur.next;
			}
			if (newFirst == null){
				newFirst = cur;
				newFirst.next = null;
			}else{
				cur.next = newFirst;
				newFirst = cur;
			}
		}
		first = newFirst;
		return first;
	}
	/**
	 * 随机打乱链表，借助数组，O(n)时间和O(n)空间
	 * @param first
	 */
	public static Node shuffleUseArray(Node first){
		if (first == null)
			return first;
		int length = lengthOfLinkedList(first);
		Node[] nodeArray = new Node[length];
		Node node = first;
		for (int i = 0;i < length;i++){
			nodeArray[i] = node;
			node = node.next;
		}
		shuffleArray(nodeArray);
		Node newFirst = nodeArray[length-1];
		newFirst.next = null;
		for (int i = length-2;i >= 0;i--){
			nodeArray[i].next = newFirst;
			newFirst = nodeArray[i];
		}
		return newFirst;
	}
	/**
	 * 打乱链表，递归归并， 归并左半部分，归并右半部分，然后随机合并两个子链表，即每次从一个链表中选出一个元素放入新链表
	 * O(nlgn)时间复杂度，O(1)空间复杂度
	 * @param first
	 * @return
	 */
	public static Node shuffleRecursive(Node first){
		if (first == null || first.next == null)
			return first;
		// 寻找中间结点，利用中间结点每次移动一个结点，尾结点每次移动两个结点来获取中间结点
		Node beforeMid = null;
		Node mid = first;
		Node end = first;
		while (end != null && end.next != null){
			beforeMid = mid;
			mid = mid.next;
			end = end.next.next;
		}
		// 将前半部分链表截取下来，即中间结点前一个的next置空
		if (beforeMid != null)
			beforeMid.next = null;
		// 打乱两个子链表
		first = shuffleRecursive(first);
		mid = shuffleRecursive(mid);
		first = shuffleMerge(first, mid);
		return first;
	}
	/**
	 * 随机打乱链表，迭代归并，nlgn时间和lgn空间
	 * @param first
	 */
	public static Node shuffleIterative(Node first){
		int length = lengthOfLinkedList(first);
		for (int size = 1;size < length;size += size){
			Node left = first;
			Node right = first;
			Node beforeLeft = null;
			while (left != null && left.next != null){
				Node leftEnd = left;
				for (int i = 1;i < size && leftEnd != null && leftEnd.next != null;i++){
					leftEnd = leftEnd.next;
				}
				right = leftEnd.next;
				Node rightEnd = right;
				for (int i = 1;i < size && rightEnd != null && rightEnd.next != null;i++){
					rightEnd = rightEnd.next;
				}
				Node newLeft = null;
				if (rightEnd != null){
					newLeft = rightEnd.next;
					rightEnd.next = null;
				}
				leftEnd.next = null;
				left = shuffleMerge(left, right);
				if (beforeLeft == null){
					first = left;
				}else{
					beforeLeft.next = left;
				}
				Node mergeEnd = left;
				while (mergeEnd != null && mergeEnd.next != null){
					mergeEnd = mergeEnd.next;
				}
				beforeLeft = mergeEnd;
				left = newLeft;
			}
		}
		return first;
	}
	/**
	 * 随机合并两个链表
	 * @param left
	 * @param right
	 * @return
	 */
	public static Node shuffleMerge(Node left, Node right){
		Node head = null;
		Node tail = null;
		while (left != null && right != null){
			int random = new Random().nextInt(2);
			if (random == 0){
				if (head == null){
					head = left;
					tail = head;
					left = left.next;
				}else{
					tail.next = left;
					tail = left;
					left = left.next;
				}
			}else{
				if (head == null){
					head = right;
					tail = head;
					right = right.next;
				}else{
					tail.next = right;
					tail = right;
					right = right.next;
				}
			}
		}
		while (left != null){
			if (head == null){
				head = left;
				tail = head;
				left = left.next;
			}else{
				tail.next = left;
				tail = left;
				left = left.next;
			}
		}
		while (right != null){
			if (head == null){
				head = right;
				tail = head;
				right = right.next;
			}else{
				tail.next = right;
				tail = right;
				right = right.next;
			}
		}
		return head;
	}

}

