package chapter_3.session_1;

import chapter_1.session_3.Queue;
/**
 * 习题3.1.2，无序数组实现的基本符号表
 * @author ZhangYuliang
 *
 * @param <Key>
 * @param <Value>
 */
public class ArrayST<Key, Value> extends ST<Key, Value>{

	private int size = 0;
	private Key[] keys;
	private Value[] values;
	@SuppressWarnings("unchecked")
	public ArrayST(){
		keys = (Key[])new Object[1];
		values = (Value[])new Object[1];
	}
	@SuppressWarnings("unchecked")
	public ArrayST(int capacity){
		keys = (Key[])new Object[capacity];
		values = (Value[])new Object[capacity];
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void put(Key key, Value value) {
		delete(key);
		if (size >= values.length)
			resize(size*2);
		keys[size] = key;
		values[size] = value;
		size++;
	}

	@Override
	public Value get(Key key) {
		for (int i = 0;i < size;i++){
			if (key.equals(keys[i]))
				return values[i];
		}
		return null;
	}

	@Override
	public void delete(Key key) {
		for (int i = 0;i < size;i++){
			if (key.equals(keys[i])){
				for (int j = i;j < size-1;j++){
					keys[j] = keys[j+1];
				}
				keys[size-1] = null;
				size--;
				if (size > 0 && size <= keys.length/4)
					resize(keys.length/2);
				break;
			}
		}
	}

	@Override
	public boolean contains(Key key) {
		if (get(key) != null)
			return true;
		return false;
	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < size; i++)
			queue.enqueue(keys[i]);
		return queue;
	}
	@SuppressWarnings("unchecked")
	public void resize(int size){
		Key[] keysTemp = (Key[])new Object[size];
		Value[] valuesTemp = (Value[])new Object[size];
		for (int i = 0;i < this.size;i++){
			keysTemp[i] = keys[i];
			valuesTemp[i] = values[i];
		}
		keys = keysTemp;
		values = valuesTemp;
	}
	public static void test() {
		ArrayST<Integer, String> st = new ArrayST<Integer, String>();
		for (int i = 0;i < 10;i++)
			st.put(i, String.valueOf(i));
		System.out.println("st.get(1): " + st.get(1));
		st.put(1,"11");
		System.out.println("st.get(1): " + st.get(1));
		for (Integer key : st.keys()){
			System.out.print( key + "\t");
		}
		System.out.println();
		st.delete(1);
		System.out.println("st.get(1): " + st.get(1));
		System.out.println("st.contains(1): " + st.contains(1));
		System.out.println("st.size():" + st.size());
		for (Integer key : st.keys()){
			System.out.print( key + "\t");
		}
	}
	public static void main(String[] args) {
		test();
	}

}
