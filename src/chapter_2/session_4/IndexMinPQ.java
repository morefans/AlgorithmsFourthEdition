package chapter_2.session_4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    /**
     * Initializes an empty indexed priority queue with indices between {@code 0}
     * and {@code maxN - 1}.
     * @param  maxN the keys on this priority queue are index from {@code 0}
     *         {@code maxN - 1}
     * @throws IllegalArgumentException if {@code maxN < 0}
     */
    @SuppressWarnings("unchecked")
	public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];                   // make this of length maxN??
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param  i an index
     * @return {@code true} if {@code i} is an index on this priority queue;
     *         {@code false} otherwise
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Associates key with index {@code i}.
     *
     * @param  i an index
     * @param  key the key to associate with index {@code i}
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if there already is an item associated
     *         with index {@code i}
     */
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key minKey() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n+1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        pq[n+1] = -1;        // not needed
        return min;
    }

    /**
     * Returns the key associated with index {@code i}.
     *
     * @param  i the index of the key to return
     * @return the key associated with index {@code i}
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @deprecated Replaced by {@code changeKey(int, Key)}.
     */
    @Deprecated
    public void change(int i, Key key) {
        changeKey(i, key);
    }

    /**
     * Decrease the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to decrease
     * @param  key decrease the key associated with index {@code i} to this key
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Increase the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to increase
     * @param  key increase the key associated with index {@code i} to this key
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key <= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) >= 0)
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * Remove the key associated with index {@code i}.
     *
     * @param  i the index of the key to remove
     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }


   /***************************************************************************
    * General helper functions.
    ***************************************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


   /***************************************************************************
    * Heap helper functions.
    ***************************************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


   /***************************************************************************
    * Iterators.
    ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
    
    
//	private int N;
//	private Key[] keys;
//	private int[] pq;
//	@SuppressWarnings("unchecked")
//	public IndexMinPQ(){
//		keys = (Key[])new Comparable[1];
//		pq = new int[1];
//		pq[0] = -1;
//	}
//	@SuppressWarnings("unchecked")
//	public IndexMinPQ(int maxN){
//		keys = (Key[])new Comparable[maxN + 1];
//		pq = new int[maxN + 1];
//		pq[0] = -1;
//		for (int i = 0;i < maxN + 1;i++)
//			pq[i+1] = i;
//	}
//	@SuppressWarnings("unchecked")
//	public IndexMinPQ(Key[] array){
////		keys = array;
//		N = array.length;
//		keys = (Key[])new Comparable[N];
//		for (int i = 0;i < N;i++)
//			keys[i] = array[i];
//		pq = new int[N+1];
//		pq[0] = -1;
//		for (int i = 0;i < N;i++)
//			pq[i+1] = i;
//		adjustToHeap();
//	}
//	public boolean isEmpty(){
//		return N == 0;
//	}
//	public int size(){
//		return N;
//	}
//	public boolean less(int i, int j){
//		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
//	}
//	public void exch(int i, int j){
//		int temp = pq[i];
//		pq[i] = pq[j];
//		pq[j] = temp;
//	}
//	public void swim(int k){
//		while (k > 1 && less(k/2, k)){
//			exch(k/2, k);
//			k = k/2;
//		}
//	}
//	public void sink(int k){
//		while (2*k <= N){
//			int j = 2*k;
//			if (j < N && less(j, j+1))
//				j++;
//			if (!less(k, j))
//				break;
//			exch(k, j);
//			k = j;
//		}
//	}
//	public void adjustToHeap(){
//		for (int i = N/2;i > 0;i--)
//			sink(i);
//	}
//	public Key max(){
//		if (isEmpty())
//			return null;
//		return keys[pq[1]];
//	}
//	public int maxIndex(){
//		if (isEmpty())
//			return -1;
//		return pq[1];
//	}
//	public Key delMax(){
//		if (isEmpty())
//			return null;
//		Key temp = keys[pq[1]];
//		keys[pq[1]] = null;// ½«keyÖµÖÃÎªnull
//		exch(1, N--);
//		pq[N+1] = -1;
//		sink(1);
//		return temp;
//	}
//	public void insert(Key v){
//		if (N >= pq.length-1)
//			resize(2*pq.length);
//		keys[N++] = v;
//		pq[N] = N-1;
//		swim(N);
//	}
//	@SuppressWarnings("unchecked")
//	public void resize(int size){
//		Key[] temp = (Key[])new Comparable[size-1];
//		for (int i = 0;i < N;i++){
//			temp[i] = keys[i];
//		}
//		keys = temp;
//		int[] tempPQ = new int[size];
//		for (int i = 0;i <= N;i++){
//			tempPQ[i] = pq[i];
//		}
//		pq = tempPQ;
//	}
//	public void show(){
//		System.out.print("{");
//		for (int i = 1;i < N;i++){
//			System.out.print(keys[pq[i]] + ", ");
//		}
//		System.out.println(keys[pq[N]] + "}");
//	}
//	public boolean isMaxHeap(){
//		return isMaxHeap(1);
//	}
//	public boolean isMaxHeap(int k){
//		if (k > N)
//			return true;
//		int left = 2*k;
//		int right = 2*k+1;
//		if (left <= N && less(k, left))
//			return false;
//		if (right <= N && less(k, right))
//			return false;
//		return isMaxHeap(left) && isMaxHeap(right);
//	}
//	public static void main(String[] args){
////		Character[] array = {'a','b','c','d','e','f','g','h','i','j'};
////		IndexMaxPQ<Character> minPQ = new IndexMaxPQ<>(array);
//		IndexMinPQ<Character> minPQ = new IndeinPQ<>();
//		minPQ.insert('a');
//		minPQ.insert('z');
//		minPQ.insert('c');
//		minPQ.insert('b');
//		minPQ.insert('j');
//		minPQ.insert('i');
//		minPQ.insert('g');
//		minPQ.insert('f');
//		minPQ.insert('h');
//		minPQ.insert('d');
//		minPQ.insert('e');
//		minPQ.show();
//		System.out.println(minPQ.isMaxHeap());
//		//		minPQ.insert(12);
//		//		minPQ.show();
//		//		minPQ.insert(10);
//		//		minPQ.show();
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		System.out.println(minPQ.delMax());
//		minPQ.show();
//	}
	
}
