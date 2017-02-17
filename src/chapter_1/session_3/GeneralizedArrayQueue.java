package chapter_1.session_3;

/**
 * 习题1.3.38，删除第k个元素，数组实现
 * @author ZhangYuliang
 *
 * @param <Item>
 */
public class GeneralizedArrayQueue<Item> {
	private Item[] array;
	private int first;
	private int last;
	private int size;
	
	@SuppressWarnings("unchecked")
	public GeneralizedArrayQueue(){
		array = (Item[])new Object[1];
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	public void resize(int capcity){
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[])new Object[capcity];
		for (int i = 0;i < size;i++){
			temp[i] = array[(first+i)%array.length];
		}
		array = temp;
		first = 0;
		last = size;
	}
	public void insert(Item item){
		if (size == array.length)
			resize(array.length*2);
		array[last] = item;
		if (last == array.length-1)
			last = 0;
		else
			last++;
		size++;
	}
	public Item dequeue(){
		if (isEmpty())
			return null;
		Item item = array[first];
		if (first == array.length-1)
			first = 0;
		else
			first++;
		size--;
		if (size > 0 && size < array.length/4)
			resize(array.length/2);
		return item;
	}

	public Item delete(int k){//第0个开始
		if (isEmpty() || k >= size)
			return null;
		Item item = array[(first+k)%array.length];
		for (int i = 0;i < size-k-1;i++){
			int index = (i+first+k)%array.length;
			array[index] = array[index+1];
		}
		if (last == 0)
			last = array.length-1;
		else
			last--;
		size--;
		if (size > 0 && size < array.length/4)
			resize(array.length/2);
		return item;
	}
	public void info(){
		System.out.println("first=" + first + "; last=" + last + "; size=" + size);
		System.out.print("array:\t");
		for (int i = 0;i < array.length;i++)
			System.out.print(array[i] + "\t");
		System.out.println();
	}
	public static void test(){
		GeneralizedArrayQueue<String> queue = new GeneralizedArrayQueue<String>();
		for (int i = 0;i < 10;i++){
			queue.insert(String.valueOf(i));
		}
		queue.info();
		System.out.println(queue.delete(2));
		queue.info();
		System.out.println(queue.delete(0));
		queue.info();
		System.out.println(queue.delete(7));
		queue.info();
	}
	public static void main(String[] args){
		GeneralizedArrayQueue.test();
	}
}
