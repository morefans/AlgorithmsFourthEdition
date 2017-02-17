package chapter_2.session_4;
/**
 * 习题2.4.29，同时面向最大和最小元素的优先队列
 * 使用两个堆
 * @author ZhangYuliang
 *
 */
public class MaxMinPQ<Key extends Comparable<Key>> {
	private MaxPQ<Key> maxPQ;
	private MinPQ<Key> minPQ;
	public MaxMinPQ(){
		maxPQ = new MaxPQ<Key>();
		minPQ = new MinPQ<Key>();
	}
	public MaxMinPQ(Key[] array){
		maxPQ = new MaxPQ<Key>(array);
		minPQ = new MinPQ<Key>(array);
	}
	public void clear(){
		maxPQ.clear();
		minPQ.clear();
	}
	public boolean isEmpty(){
		Key max = maxPQ.max();
		Key min = minPQ.min();
		if (max == null || min == null || max.compareTo(min) < 0){
			clear();
			return true;
		}else{
			return false;
		}
	}
	public Key delMax(){
		if (isEmpty())
			return null;
		return (Key) maxPQ.delMax();
	}
	public Key max(){
		if (isEmpty())
			return null;
		return (Key) maxPQ.max();
	}
	public Key delMin(){
		if (isEmpty())
			return null;
		return (Key) minPQ.delMin();
	}
	public Key min(){
		if (isEmpty())
			return null;
		return (Key) minPQ.min();
	}
	public void insert(Key v){
		maxPQ.insert(v);
		minPQ.insert(v);
	}
	public void show(){
		System.out.print("MaxPQ: ");
		maxPQ.show();
		System.out.print("MinPQ: ");
		minPQ.show();
	}
	public static void main(String[] args) {
		Integer[] array = {1,4};//,6,9,2,3,6,7,8,5,0};
		MaxMinPQ<Integer> pq = new MaxMinPQ<>(array);
		//		pq.show();
		pq.delMax();
		//		pq.show();
		pq.delMin();
		//		pq.show();
		pq.delMin();
		pq.show();

		pq.insert(0);
		pq.show();

		pq.delMin();
		pq.show();
		System.out.println(pq.isEmpty());
		pq.show();
	}

}
