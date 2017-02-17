package chapter_3.session_1;
/**
 * ÓÐÐò·ûºÅ±í
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class SymbolTable<Key extends Comparable<Key>, Value> {
	public void put(Key key, Value value){
		
	}
	public Value get(Key key){
		return null;
	}
	public void delete(Key key){
		put(key, null);
	}
	public boolean contains(Key key){
		return get(key) != null;
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return 0;
	}
	public Key max(){
		return null;
	}
	public Key min(){
		return null;
	}
	public Key floor(Key key){
		return null;
	}
	public Key ceiling(Key key){
		return null;
	}
	public int rank(Key key){
		return -1;
	}
	public Key select(int k){
		return null;
	}
	public void deleteMax(){
		delete(max());
	}
	public void deleteMin(){
		delete(min());
	}
	public int size(Key low, Key high){
		if (high.compareTo(low) < 0)
			return 0;
		else if (contains(high))
			return rank(high) - rank(low) + 1;
		else 
			return rank(high) - rank(low);
	}
	public Iterable<Key> keys(Key low, Key high){
		return null;
	}
	public Iterable<Key> keys(){
		return keys(min(), max());
	}

	public static void test(){
		
	}
	public static void main(String[] args) {
		test();
	}

}
