package chapter_2.session_1;

import java.util.Random;

@SuppressWarnings("rawtypes")
public abstract class SortBase {
	public abstract void sort(Comparable[] array);
	/**
	 * v�Ƿ�С��w��trueΪС�ڣ�falseΪ���ڻ����
	 * @param v
	 * @param w
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	@SuppressWarnings("unchecked")
	public static boolean notMoreThan(Comparable v, Comparable w){
		return v.compareTo(w) < 0 || v.compareTo(w) == 0;
	}
	/**
	 * ��������array��i��j����Ԫ��ֵ
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void exch(Comparable[] array, int i, int j){
		Comparable t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
	/**
	 * ��ӡ����
	 * @param array
	 */
	public static void show(Comparable[] array){
//		System.out.println("Array is sorted: " + isSorted(array));
		System.out.print("{");
		int i;
		for (i = 0; i < array.length-1; i++)
			System.out.print(array[i] + ", ");
		System.out.println(array[i] + "}");
	}
	/**
	 * �����Ƿ��������
	 * @param array
	 * @return trueΪ����falseΪ����
	 */
	public static boolean isSorted(Comparable[] array){
		for (int i = 1;i < array.length;i++){
			if (less(array[i], array[i-1]) )
				return false;
		}
		return true;
	}
	/**
	 * �����������˳��
	 * @param array
	 */
	public static void shuffle(Comparable[] array){
		for (int i = 0;i < array.length;i++){
			int index = new Random(System.currentTimeMillis()).nextInt(array.length-i);
			exch(array, array.length-i-1, index);
		}
	}
	public void test(){
		test(50);
	}
	public void test(int N){
		Integer[] array = new Integer[N];
		for (int i = 0;i < N;i++)
			array[i] = i;
		shuffle(array);
		show(array);
		sort(array);
		assert isSorted(array);
		show(array);
	}

}
