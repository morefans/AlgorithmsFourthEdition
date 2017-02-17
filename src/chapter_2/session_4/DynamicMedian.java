package chapter_2.session_4;

public class DynamicMedian<Key extends Comparable<Key>> {
	private MaxPQ<Key> maxPQ;// 小于中位数的
	private MinPQ<Key> minPQ;// 大于中位数的
	private Key median = null;
	public DynamicMedian(){
		maxPQ = new MaxPQ<Key>();
		minPQ = new MinPQ<Key>();
	}
	public DynamicMedian(Key[] array){
		maxPQ = new MaxPQ<Key>();
		minPQ = new MinPQ<Key>();
		for (int i = 0;i < array.length;i++)
			insert(array[i]);
	}
	public boolean isEmpty(){
		return median == null;
	}
	public Key median(){
		return median;
	}
	public Key delMedian(){
		if (median == null)
			return median;
		Key temp = median;
		if (minPQ.size() > maxPQ.size()){// minPQ里元素个数多，则中位数一定在minPQ中
			temp = minPQ.delMin();
			median = minPQ.min();
			return temp;
		}
		if (minPQ.size() < maxPQ.size()){// maxPQ里元素个数多，则中位数一定在maxPQ中
			temp = maxPQ.delMax();
			median = maxPQ.max();
			return temp;
		}
		// minPQ和maxPQ元素个数一样多，应该为两数的平均数，但这里只取一个，若要取两个的也可以，但需要稍作修改
		if (median.compareTo(maxPQ.max()) == 0){
			temp = maxPQ.delMax();
			median = minPQ.min();
			return temp;
		}
		else 
		{
			temp = minPQ.delMin();
			median = maxPQ.max();
			return temp;
		}
	}
	public void insert(Key v){
		if (minPQ.isEmpty()){
			minPQ.insert(v);
			median = v;
		}
		else if (maxPQ.isEmpty()){
			if (v.compareTo(median) < 0){
				maxPQ.insert(v);
				median = maxPQ.max();
			}else{
				maxPQ.insert(minPQ.delMin());
				minPQ.insert(v);
				median = minPQ.min();
			}
		}else if (v.compareTo(median) < 0){// 比中位数小，应该放到maxPQ
			if (minPQ.size() > maxPQ.size()){
				maxPQ.insert(v);
				median = maxPQ.max();
			}else{
				minPQ.insert(maxPQ.delMax());
				maxPQ.insert(v);
				median = maxPQ.max();
			}
		}else{// 比中位数小，应该放到minPQ
			if (minPQ.size() > maxPQ.size()){
				maxPQ.insert(minPQ.delMin());
				minPQ.insert(v);
				median = minPQ.min();
			}else{
				minPQ.insert(v);
				median = minPQ.min();
			}
		}
	}
	public void show(){
		System.out.print("minPQ: ");
		minPQ.show();
		System.out.println("median: " + median);
		System.out.print("mqxPQ: ");
		maxPQ.show();
	}
	public static void main(String[] args) {
		Integer[] array = {3,5,2,7,1,6,4,6,9};
		DynamicMedian<Integer> dMedian = new DynamicMedian<>(array);
		//		for (int i = 1; i <= 10;i++)
		//			dMedian.insert(i);
		//		for (int i = 7; i > 0;i--)
		//			dMedian.insert(i);
		
//		dMedian.insert(3);
//		dMedian.insert(5);
//		dMedian.insert(2);
//		dMedian.insert(7);
//		dMedian.insert(1);
//		dMedian.insert(6);
//		dMedian.insert(6);
//		dMedian.insert(4);
//		dMedian.insert(9);
		
		dMedian.show();
		System.out.println("\t delMedian: " + dMedian.delMedian());
		dMedian.show();
		System.out.println("\t delMedian: " + dMedian.delMedian());
		dMedian.show();
		System.out.println("\t delMedian: " + dMedian.delMedian());
		dMedian.show();
		System.out.println("\t delMedian: " + dMedian.delMedian());
		dMedian.show();
	}

}
