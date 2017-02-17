package chapter_2.session_4;

public class MinPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	private Key maxKey = null;
	@SuppressWarnings("unchecked")
	public MinPQ(){
		pq = (Key[])new Comparable[1];
	}
	@SuppressWarnings("unchecked")
	public MinPQ(int maxN){
		pq = (Key[])new Comparable[maxN+1];
	}
	@SuppressWarnings("unchecked")
	public MinPQ(Key[] array){
		pq = (Key[])new Comparable[array.length+1];
		N = array.length;
		for (int i = 0;i < array.length;i++)
			pq[i+1] = array[i];
		adjustToHeap();
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public int size(){
		return N;
	}
	public void insert(Key v){
		if (maxKey == null)
			maxKey = v;
		else if (maxKey.compareTo(v) < 0)
			maxKey = v;
		if (N >= pq.length-1)
			resize(2*pq.length);
		pq[++N] = v;
		swim(N);
	}
	private Key max(){
		return maxKey;
	}
	public Key min(){
		if (isEmpty())
			return null;
		return pq[1];
	}
	public Key delMin(){
		if (isEmpty())
			return null;
		Key min = pq[1];
		exch(1, N--);
		pq[N+1] = null;
		sink(1);
		if (N > 1 && N <= pq.length/4)
			resize(pq.length/2);
		return min;
	}
	public boolean more(int i, int j){
		return pq[i].compareTo(pq[j]) > 0;
	}
	public void exch(int i, int j){
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	public void swim(int k){
		while (k > 1 && more(k/2, k)){
			exch(k/2, k);
			k = k/2;
		}
	}
	public void sink(int k){
		while (2*k <= N){
			int j = 2*k;
			if (j < N && more(j, j+1))
				j++;
			if (!more(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	public void show(){
		System.out.print("{");
		int i;
		for (i = 0;i < pq.length-1;i++)
			System.out.print(pq[i] + ", ");
		System.out.println(pq[i] + "}");
	}
	public void adjustToHeap(){
		for (int i = N/2;i > 0;i--)
			sink(i);
		maxKey = pq[N/2];
		for (int i = N/2;i <= N;i++){
			if (pq[i].compareTo(maxKey) > 0)
				maxKey = pq[i];
		}
	}
	@SuppressWarnings("unchecked")
	public void resize(int size){
		Key[] temp = (Key[])new Comparable[size];
		for (int i = 0;i <= N;i++){
			temp[i] = pq[i];
		}
		pq = temp;
	}
	public boolean isMinHeap(){
		return isMinHeap(1);
	}
	public boolean isMinHeap(int k){
		if (k > N)
			return true;
		int left = 2*k;
		int right = 2*k+1;
		if (left <= N && more(k, left))
			return false;
		if (right <= N && more(k, right))
			return false;
		return isMinHeap(left) && isMinHeap(right);
	}
	public void clear(){
		for (int i = 0;i < pq.length;i++)
			pq[i] = null;
		N = 0;
		maxKey = null;
	}
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] array){
		
	}
	public static void main(String[] args){
		Integer[] array = {9,8,7,6,5,4,3,2,1,0};
		MinPQ<Integer> minPQ = new MinPQ<>(array);
		minPQ.show();
		System.out.println(minPQ.isMinHeap() + "   max: " + minPQ.max());
//		minPQ.insert(12);
//		minPQ.show();
//		minPQ.insert(10);
//		minPQ.show();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.delMin();
		minPQ.show();
	}
}