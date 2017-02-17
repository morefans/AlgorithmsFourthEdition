package chapter_3.session_3;

/**
 * 红黑二叉树，仅put()和delete方法不同且涉及颜色，其余方法与二叉查找树相同
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private class Node{
		Key key;
		Value value;
		Node left;
		Node right;
		int size;
		boolean color;
		public Node(Key key, Value value, int size, boolean color){
			this.key = key;
			this.value = value;
			this.size = size;
			this.color = color;
		}
	}
	private Node root;
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
	private boolean isRed(Node node){
		if (node == null)
			return false;
		return node.color == RED;
	}
	private Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}
	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}
	private void flipColors(Node h){
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}
	@SuppressWarnings("unused")
	private void flipColorsOld(Node h){
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	public void put(Key key, Value value){
		root = put(root, key, value);
		root.color = BLACK;
	}
	private Node put(Node h, Key key, Value value){
		if (h == null)
			return new Node(key, value, 1, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, value);
		else if (cmp > 0)
			h.right = put(h.right, key, value);
		else
			h.value = value;
		// 旋转
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	public Value get(Key key){
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
			return node.value;
		}
	}
	public boolean contains(Key key) {
        return get(key) != null;
    }
	public void deleteMin(){
		if (root == null)
			return;
		// 置为RED是为了根结点参与旋转时的情况
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if (!isEmpty())
			root.color = BLACK;
	}
	private Node deleteMin(Node h){
		if (h.left == null)
			return null;
		if (!isRed(h.left) && !isRed(h.left.left))// 如果当前结点是2-结点，则需要变成3-结点或临时的4-结点
			h = moveRedLeft(h);
		h.left = deleteMin(h.left);
		return balance(h);// 沿路径往上进行配平成完美平衡树
	}
	private Node moveRedLeft(Node h){
		flipColors(h);
		if (isRed(h.right.left)){// 从右子结点借一个结点过来
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}
	private Node balance(Node h){
		if (isRed(h.right))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
	public void deleteMax(){
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if (!isEmpty())
			root.color = BLACK;
	}
	private Node deleteMax(Node h){
		if (isRed(h.left))
			h = rotateRight(h);
		if (h.right == null)
			return null;
		if (!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);
		h.right = deleteMax(h.right);
		return balance(h);
	}
	private Node moveRedRight(Node h){
		flipColors(h);
		if (isRed(h.left.left)){
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}
	public void delete(Key key){
		if (!contains(key))
			return;
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root, key);
		if (!isEmpty())
			root.color = BLACK;
	}
	private Node delete(Node h, Key key){
		if (key.compareTo(h.key) < 0){
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}else{
			if (isRed(h.left))
				h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && h.right == null)
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0){
				Node x = min(h.right);
				h.value = x.value;
				h.key = x.key;
				h.right = deleteMin(h.right);
			}else{
				h.right = delete(h.right, key);
			}
		}
		return balance(h);
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
		if (isEmpty())
			return null;
		Node node = root;
		Node parentFloor = null;// 上一个Floor并且一定是结点祖先
		while (node != null){
			int cmp = key.compareTo(node.key);
			if (cmp > 0){
				if (node.right == null){
					return node.key;
				}else{
					parentFloor = node;
					node = node.right;
				}
			}else if (cmp < 0){
				if (node.left == null){
					if (parentFloor == null)
						return null;
					else return parentFloor.key;
				}else{
					node = node.left;
				}
			}else{
				return node.key;
			}
		}
		return null;
	}
	public Key ceiling(Key key){
		if (isEmpty())
			return null;
		Node node = root;
		Node parentCeiling = null;// 上一个Ceiling并且一定是结点祖先
		while (node != null){
			int cmp = key.compareTo(node.key);
			if (cmp > 0){
				if (node.right == null){
					if (parentCeiling == null)
						return null;
					else return parentCeiling.key;
				}else{
					node = node.right;
				}
			}else if (cmp < 0){
				if (node.left == null){
					return node.key;
				}else{
					parentCeiling = node;
					node = node.left;
				}
			}else{
				return node.key;
			}
		}
		return null;
	}
	public int rank(Key key){
		int rank = 0;
		Node node = root;
		while (node != null){
			int cmp = key.compareTo(node.key);
			if (cmp > 0){
				rank += size(node.left) + 1;
				node = node.right;
			}else if (cmp < 0){
				node = node.left;
			}else{
				rank += size(node.left);
				return rank;
			}
		}
		return rank;
	}
	public Key select(int k){
		Node node = root;
		Key key = null;
		int rank = 0;
		while (node != null){
			key = node.key;
			rank += size(node.left);
			if (rank == k){
				return node.key;
			}else if (rank < k){
				rank++;
				node = node.right;
			}else {
				rank -= size(node.left);
				node = node.left;
			}
		}
		if (rank >= size(root))
			return null;
		return key;
	}
	public int height(){
		return height(root);
	}
	private int height(Node node){
		if (node == null){
			return 0;
		}
		int left = height(node.left);
		int right = height(node.right);
		// 红链接
		if (isRed(node.left))
			left--;
		return 1 + (left > right ? left : right); 
	}
	public boolean is23Tree(){
		return is23Tree(root);
	}
	private boolean is23Tree(Node node){
		if (node == null)
			return true;
		if (isRed(node.right))
			return false;
		if (node != null && isRed(node) && isRed(node.left))
			return false;
		return is23Tree(node.left) && is23Tree(node.right);
	}
	public boolean isBalanced() { 
		int black = 0;
		Node x = root;
		while (x != null) {
			if (!isRed(x)) black++;
			x = x.left;
		}
		return isBalanced(root, black);
	}

	private boolean isBalanced(Node x, int black) {
		if (x == null) return black == 0;
		if (!isRed(x)) black--;
		return isBalanced(x.left, black) && isBalanced(x.right, black);
	}
	public boolean isBST() {
		return isBST(root, null, null);
	}

	private boolean isBST(Node x, Key min, Key max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	} 
	public boolean isRedBlackTree() {
		if (!isBST())
			return false;
		if (!is23Tree())
			return false;
		if (!isBalanced())
			return false;
		return true;
	}

	private void print(Node node){
		if (node == null)
			return ;
		print(node.left);
		System.out.print(node.key + "\t");
		print(node.right);
	}
	public void print(){
		if (isEmpty()){
			System.out.println("RedBlackTree is empty!");
			return ;
		}
		print(root);
		System.out.println();
	}
	public static void test(){
		RedBlackBST<String, String> bst = new RedBlackBST<String, String>();
		//		bst.put("S", "S");
		//		bst.put("E", "E");
		//		bst.put("A", "A");
		//		bst.put("R", "R");
		//		bst.put("C", "C");
		//		bst.put("H", "H");
		//		bst.put("X", "X");
		//		bst.put("M", "M");
		//		bst.put("P", "P");
		//		bst.put("L", "L");

				bst.put("A", "A");
				bst.put("C", "C");
				bst.put("E", "E");
				bst.put("H", "H");
				bst.put("L", "L");
				bst.put("M", "M");
				bst.put("P", "P");
				bst.put("R", "R");
				bst.put("S", "S");
				bst.put("X", "X");
				
				

//				bst.put("E", "E");
//				bst.put("A", "A");
//				bst.put("S", "S");
//				bst.put("Y", "Y");
//				bst.put("R", "R");
//				bst.put("U", "U");
//				bst.put("T", "T");
//				bst.put("I", "I");
//				bst.put("O", "O");
//				bst.put("N", "N");


//		bst.put("O", "O");
//		bst.put("S", "S");
//		bst.put("U", "U");
//		bst.put("E", "E");
//		bst.put("T", "T");
//		bst.put("Y", "Y");
//		bst.put("R", "R");

		System.out.println("is23Tree():" + bst.is23Tree());
		System.out.println("isBalanced():" + bst.isBalanced());
		System.out.println("isRedBlackTree():" + bst.isRedBlackTree());
		System.out.println("root: " + bst.root.key);
		bst.print();
		System.out.println("size(): " + bst.size());
		

//		bst.deleteMin();
//		System.out.println("after deleteMin, isRedBlackTree():" + bst.isRedBlackTree());
//		System.out.println("root: " + (bst.root == null ? null : bst.root.key));
//		bst.print();

		//		for (int i = 0; i < 10; i++){
		//			bst.deleteMin();
		//			System.out.println("after deleteMin, isRedBlackTree():" + bst.isRedBlackTree());
		//			System.out.println("root: " + (bst.root == null ? null : bst.root.key));
		//			bst.print();
		//		}

//		while (!bst.isEmpty()){
//			System.out.print("after deleteMax : " + bst.max() + ", isRedBlackTree():");
//			bst.deleteMax();
//			System.out.println(bst.isRedBlackTree());
//			System.out.println("root: " + (bst.root == null ? null : bst.root.key));
//			bst.print();
//		}
		
		String[] deleteKeys = {"L","M","H","P","R","E","C","S","X","E","A"};
		for (int i = 0;i < deleteKeys.length;i++){
			System.out.print("after delete : " + deleteKeys[i] + ", isRedBlackTree():");
			bst.delete(deleteKeys[i]);
			System.out.println(bst.isRedBlackTree());
			System.out.println("root: " + (bst.root == null ? null : bst.root.key));
			bst.print();
		}

		System.out.println("isRedBlackTree():" + bst.isRedBlackTree());
		//		System.out.println("get(H): " + bst.get("H"));
		//		System.out.println("height(): " + bst.height());
		//		bst.deleteMin();
		//		bst.deleteMax();
		//		bst.delete("E");
		//		System.out.println("deleteMin(), deleteMax(),delete(\"E\")");
		//		System.out.println("root: " + bst.root.key);
		//		bst.print();
		//		System.out.println("height(): " + bst.height());
	}
	public static void main(String[] args){
		test();
	}
}
