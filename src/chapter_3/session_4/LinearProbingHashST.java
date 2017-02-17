package chapter_3.session_4;

import chapter_1.session_3.Queue;

public class LinearProbingHashST<Key, Value>{
    private static final int INIT_CAPACITY = 4;
    
	private int N;// 符号表中键值对的总数
	private int M = 16;// 线性探测表的大小
	private Key[] keys;// 键 数组
	private Value[] values;// 值 数组
	public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }
	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int size){
		M = size;
		N = 0;
		keys = (Key[]) new Object[M];
		values = (Value[]) new Object[M];
	}
	private int hash(Key key){
		return (key.hashCode() & 0x7ffffff) % M;
	}
	public int size() {
        return N;
    }
	public boolean isEmpty() {
        return size() == 0;
    }
	private void resize(int size){
		LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(size);
		for (int i = 0; i < M; i++){
			if (keys[i] != null)
				t.put(keys[i], values[i]);
		}
		keys = t.keys;
		values = t.values;
		M = t.M;
		
	}
	public void put(Key key, Value value){
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (value == null) {
            delete(key);
            return;
        }
		if (N >= M/2)
			resize(2*M);
		int i;
		for (i = hash(key);keys[i] != null;i = (i+1)%M){
			if (keys[i].equals(key)){
				values[i] = value;
				return ;
			}
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}
	public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i+1)%M){
			if (keys[i].equals(key))
				return values[i];
		}
		return null;
	}
	public boolean contains(Key key){
		return get(key) != null;
	}
	public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key))
			return ;
		int i = hash(key);
		while (!key.equals(keys[i])){
			i = (i+1) % M;
		}
		keys[i] = null;
		values[i] = null;
		i = (i+1) % M;
		while (keys[i] != null){
			Key keyToRedo = keys[i];
			Value valueToRedo = values[i];
			keys[i] = null;
			values[i] = null;
			N--;
			put(keyToRedo, valueToRedo);
			i = (i+1) % M;
		}
		N--;
		if (N > 0 && N == M/8)
			resize(M/2);
	}
	public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
	public static void test(){
		LinearProbingHashST<String, String> st = new LinearProbingHashST<String, String>();
		st.put("A", "A");
		st.put("D", "D");
		st.put("Y", "Y");
		st.put("W", "W");
		st.put("O", "O");
		st.put("A", "A");
		
	}
	
	public static void main(String[] args){
//		double result = (21 & 0x7ffffff) % 16;
//		System.out.println(result);
		test();
	}

}
