package chapter_3.session_4;

import chapter_1.session_3.Queue;

public class SeparateChainingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	
	private int N;// 键值对总数
	private int M;// 散列表的大小
	private SequentialSearchST<Key, Value>[] st;// 存放链表对象的数组
	public SeparateChainingHashST(){
		this(INIT_CAPACITY);
	}
	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int M){
		this.M = M;
		st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
		for (int i = 0;i < M;i++){
			st[i] = new SequentialSearchST<Key, Value>();
		}
	}
	private int hash(Key key){
		return (key.hashCode() & 0x7ffffff) % M;
	}
	public Value get(Key key){
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return (Value)st[hash(key)].get(key);
	}
	public void put(Key key, Value value){
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }

        if (N >= 10*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, value);
	}
	public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    }
	public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
	public int size() {
        return N;
    }
	public boolean isEmpty() {
        return size() == 0;
    }
	private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
    }
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
	}

	public static void test(){
		SeparateChainingHashST<String, String> st = new SeparateChainingHashST<String, String>();
		st.put("A", "A");
		st.put("D", "D");
		st.put("Y", "Y");
		st.put("W", "W");
		st.put("O", "O");
		st.put("J", "J");
		st.put("Y", "Y");
		for (String key : st.keys()){
			System.out.print(key + "\t");
		}
		
	}
	public static void main(String[] args){
		test();
	}

}
