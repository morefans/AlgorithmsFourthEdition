package chapter_3.session_2;

/**
 * 非递归实现的二叉查找树
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
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
		if (isEmpty()){
			root = new Node(key, value, 1);
			return ;
		}
		Node node = root;
		boolean exist = false;
		while (node != null){
			int cmp = key.compareTo(node.key);
			if (cmp > 0){
				if (node.right == null){
					node.right = new Node(key, value, 1);
					break ;
				}else{
					node = node.right;
				}
			}else if (cmp < 0){
				if (node.left == null){
					node.left = new Node(key, value, 1);
					break ;
				}else{
					node = node.left;
				}
			}else{
				node.value = value;
				exist = true;
				break;
			}
		}
		if (!exist){// 新增的Key，需要更新结点计数器
			node = root;
			while (node != null){
				int cmp = key.compareTo(node.key);
				if (cmp > 0){
					node.size++;
					node = node.right;
				}else if (cmp < 0){
					node.size++;
					node = node.left;
				}else{
					break;
				}
			}
		}
	}
	public Value get(Key key){
		Node node = root;
		while (node != null){
			int cmp = key.compareTo(node.key);
			if (cmp > 0){
				node = node.right;
			}else if (cmp < 0){
				node = node.left;
			}else{
				return node.value;
			}
		}
		return null;
	}
	public Key min(){
		if (isEmpty())
			return null;
		Node node = root;
		while (node.left != null){
			node = node.left;
		}
		return node.key;
	}
	public Key max(){
		if (isEmpty())
			return null;
		Node node = root;
		while (node.right != null){
			node = node.right;
		}
		return node.key;
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
	public static void test(){
		BinarySearchTree<String, String> bst = new BinarySearchTree<String, String>();
		bst.put("E", "E");
		bst.put("B", "B");
		bst.put("A", "A");
		bst.put("S", "S");
		bst.put("D", "D");
		bst.put("R", "R");
		bst.put("Y", "Y");
		bst.put("I", "I");
		System.out.println("root: " + bst.root.key);
		bst.print();
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
		System.out.println("rank(Y): " + bst.rank("Y"));
		System.out.println("rank(1): " + bst.rank("1"));
		System.out.println("rank(a): " + bst.rank("a"));
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
