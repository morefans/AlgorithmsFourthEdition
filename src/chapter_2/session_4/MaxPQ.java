package chapter_2.session_4;
/**
 * Max Priority Queue最大优先队列，基于堆，第0个元素不使用
 * 习题2.4.27，找出最小元素，新增一个变量为minKey，每次插入的时候判断更改即可，但是初始化时需要N/2的查找
 * @author ZhangYuliang
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	private Key minKey = null;
	@SuppressWarnings("unchecked")
	public MaxPQ(){
		pq = (Key[])new Comparable[1];
	}
	@SuppressWarnings("unchecked")
	public MaxPQ(int maxN){
		pq = (Key[])new Comparable[maxN+1];
	}
	@SuppressWarnings("unchecked")
	public MaxPQ(Key[] array){
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
		if (minKey == null)
			minKey = v;
		else if (v.compareTo(minKey) < 0)
			minKey = v;
		if (N >= pq.length-1)
			resize(2*pq.length);
		pq[++N] = v;
		swim(N);
	}
	public Key max(){
		if (isEmpty())
			return null;
		return pq[1];
	}
	private Key min(){
		return minKey;
	}
	public Key delMax(){
		if (isEmpty())
			return null;
		Key max = pq[1];
		exch(1, N--);
		pq[N+1] = null;
		sink(1);
		if (N > 1 && N <= pq.length/4)
			resize(pq.length/2);
		return max;
	}
	public boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	public void exch(int i, int j){
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	public void swim(int k){
		while (k > 1 && less(k/2, k)){
			exch(k/2, k);
			k = k/2;
		}
	}
	public void sink(int k){
		while (2*k <= N){
			int j = 2*k;
			if (j < N && less(j, j+1))
				j++;
			if (!less(k, j))
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
		minKey = pq[N/2];
		for (int i = N/2;i <= N;i++){
			if (pq[i].compareTo(minKey) < 0)
				minKey = pq[i];
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
	public boolean isMaxHeap(){
		return isMaxHeap(1);
	}
	public boolean isMaxHeap(int k){
		if (k > N)
			return true;
		int left = 2*k;
		int right = 2*k+1;
		if (left <= N && less(k, left))
			return false;
		if (right <= N && less(k, right))
			return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}
	public void clear(){
		for (int i = 0;i < pq.length;i++)
			pq[i] = null;
		N = 0;
		minKey = null;
	}
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] array){

	}
	public static void main(String[] args){
		Integer[] array = {0,1,2,3,4,5,6,7,8,9};
		MaxPQ<Integer> maxPQ = new MaxPQ<>(array);
		maxPQ.show();
		System.out.println(maxPQ.isMaxHeap() + "   min: " + maxPQ.min());
		//		maxPQ.insert(12);
		//		maxPQ.show();
		//		maxPQ.insert(10);
		//		maxPQ.show();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.delMax();
		maxPQ.show();
	}
}
