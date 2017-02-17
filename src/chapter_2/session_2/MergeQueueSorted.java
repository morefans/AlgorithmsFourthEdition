package chapter_2.session_2;

import chapter_1.session_3.Queue;
import chapter_2.session_1.SortBase;
/**
 * 习题2.2.14，归并两个有序的队列
 * 习题2.2.15，自底向上的归并有序队列并排序
 * @author ZhangYuliang
 *
 */
@SuppressWarnings("rawtypes")
public class MergeQueueSorted extends SortBase{

	public static Queue<Comparable> merge(Queue<Comparable> queue1, Queue<Comparable> queue2){
		Queue<Comparable> result = new Queue<Comparable>();
		while (!queue1.isEmpty() && !queue2.isEmpty()){
			if (SortBase.less(queue1.peak(), queue2.peak()))
				result.enqueue(queue1.dequeue());
			else
				result.enqueue(queue2.dequeue());
		}
		while (!queue1.isEmpty())
			result.enqueue(queue1.dequeue());
		while (!queue2.isEmpty())
			result.enqueue(queue2.dequeue());
		return result;
	}
	public static Queue<Comparable> mergeNoPeak(Queue<Comparable> queue1, Queue<Comparable> queue2){
		Queue<Comparable> result = new Queue<Comparable>();
		Comparable temp = null;
		int tempFrom = 0;
		while (!queue1.isEmpty() && !queue2.isEmpty()){
			if (tempFrom == 1){
				Comparable item2 = queue2.dequeue();
				if (SortBase.less(item2, temp)){
					result.enqueue(item2);
				}else{
					result.enqueue(temp);
					temp = item2;
					tempFrom = 2;
				}
			}
			else if (tempFrom == 2){
				Comparable item1 = queue1.dequeue();
				if (SortBase.less(item1, temp)){
					result.enqueue(item1);
				}else{
					result.enqueue(temp);
					temp = item1;
					tempFrom = 1;
				}
			}
			else{
				Comparable item1 = queue1.dequeue();
				Comparable item2 = queue2.dequeue();
				if (SortBase.less(item1, item2)){
					result.enqueue(item1);
					temp = item2;
					tempFrom = 2;
				}else{
					result.enqueue(item2);
					temp = item1;
					tempFrom = 1;
				}
			}
		}
		if (temp != null)
			result.enqueue(temp);
		while (!queue1.isEmpty())
			result.enqueue(queue1.dequeue());
		while (!queue2.isEmpty())
			result.enqueue(queue2.dequeue());
		return result;
	}

	public static void main(String[] args) {
//		Queue<Comparable> queue1 = new Queue<Comparable>();
//		for (int i = 0; i < 50;i += 2)
//			queue1.enqueue(i);
//		Queue<Comparable> queue2 = new Queue<Comparable>();
//		for (int i = 1; i < 40;i += 2)
//			queue2.enqueue(i);
////		Queue<Comparable> queue =  MergeQueueSorted.merge(queue1, queue2);
//		Queue<Comparable> queue =  MergeQueueSorted.mergeNoPeak(queue1, queue2);
//		for (Comparable item : queue)
//			System.out.print(String.valueOf(item) + "\t");
//		System.out.println();
		new MergeQueueSorted().test();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparable[] array) {
		Queue<Queue> queue = new Queue<Queue>();
		for (int i = 0;i < array.length;i++){
			Queue<Comparable> q = new Queue<Comparable>();
			q.enqueue(array[i]);
			queue.enqueue(q);
		}
		while (queue.size() > 1){
			Queue<Comparable> q1 = queue.dequeue();
			Queue<Comparable> q2 = queue.dequeue();
			Queue<Comparable> q = merge(q1, q2);
			queue.enqueue(q);
		}
		Queue<Comparable> q = queue.dequeue();
		for (int i = 0;i < array.length;i++){
			array[i] = q.dequeue();
		}
	}

}
