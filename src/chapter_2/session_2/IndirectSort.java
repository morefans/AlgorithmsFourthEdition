package chapter_2.session_2;

import chapter_2.session_1.SortBase;
/**
 * 习题2.2.20，间接排序，不改变数组的归并排序，返回一个int数组perm，perm[i]为第i小的元素的位置
 * @author ZhangYuliang
 *
 */
public class IndirectSort extends SortBase {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		int[] perm = new int[array.length];
		int[] permaux = new int[array.length];
		for (int i = 0;i < array.length;i++){
			perm[i] = i;
			permaux[i] = i;
		}
		for (int size = 1;size < array.length;size += size){
			for (int low = 0;low < array.length-size;low += size+size){
				merge(array, perm, permaux, low, low+size-1, Math.min(low+size+size-1, array.length-1));
			}
		}
		for (int i = 0; i < array.length;i++){
			System.out.print(array[perm[i]] + ", ");
		}
		System.out.println();
	}
	@SuppressWarnings("rawtypes")
	public static void merge(Comparable[] array, int[] perm, int[] permaux, int low, int mid, int high){
		for (int i = low;i <= high;i++)
			permaux[i] = perm[i];
		int left = low;
		int right = mid+1;
		for (int k = low;k <= high;k++){
			if (left > mid)
				perm[k] = permaux[right++];
			else if (right > high)
				perm[k] = permaux[left++];
			else if (less(array[permaux[right]], array[permaux[left]]))
				perm[k] = permaux[right++];
			else
				perm[k] = permaux[left++];
		}
	}

	public static void main(String[] args) {
		Integer[] array = {0,3,5,1,2,8,9};
		int[] perm = new int[array.length];
		int[] permaux = new int[array.length];
		for (int i = 0;i < array.length;i++){
			perm[i] = i;
			permaux[i] = i;
		}
		merge(array, perm, permaux, 0, 2, 6);

		for (int i = 0; i < array.length;i++){
			System.out.print(perm[i] + ", ");
		}
		System.out.println();
		
		new IndirectSort().test();
	}

}
