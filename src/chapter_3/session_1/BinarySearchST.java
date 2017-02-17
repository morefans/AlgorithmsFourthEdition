package chapter_3.session_1;

import chapter_1.session_3.Queue;

/**
 * 基于二分查找的有序符号表
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> extends SymbolTable<Key, Value>
{

	private Key[] keys;
	private Value[] values;
	private int N;
	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity){
		keys = (Key[])new Comparable[capacity];
		values = (Value[])new Object[capacity];
	}
	public int size(){
		return N;
	}
	public Value get(Key key){
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return values[i];
		else
			return null;
	}
	public int rank(Key key){
		int low = 0;
		int high = N-1;
		while (low <= high){
			int mid = (low + high)/2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0)
				high = mid-1;
			else if (cmp > 0)
				low = mid+1;
			else
				return mid;
		}
		return low;
	}
	public void put(Key key, Value value){
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0){
			values[i] = value;
			return ;
		}
		for (int j = N; j > i; j++){
			keys[j] = keys[j-1];
			values[j] = values[j-1];
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}
	public Key min(){
		return keys[0];
	}
	public Key max(){
		return keys[N-1];
	}
	public Key select(int k){
		return keys[k];
	}
	public Key ceiling(Key key){
		int i = rank(key);
		return keys[i];
	}
	public Key floor(Key key){
		return null;
	}
	public Iterable<Key> keys(Key low, Key high){
		Queue<Key> q = new Queue<Key>();
		for (int i = rank(low); i < rank(high); i++){
			q.enqueue(keys[i]);
		}
		if (contains(high))
			q.enqueue(keys[rank(high)]);
		return q;
	}
	public static void main(String[] args) {

	}

}
