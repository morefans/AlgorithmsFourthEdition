package chapter_1.session_4;
/**
 * 习题1.4.12，有序打印给定的两个有序int数组中的所有公共元素，要求最坏O(n)
 * @author ZhangYuliang
 *
 */
public class CommonInteger {

	public static int[] printCommonInteger(int[] array1, int[] array2){
		int i1 = 0;
		int i2 = 0;
		int count = 0;
		int[] common = new int[array1.length];
		while (i1 < array1.length && i2 < array2.length){
			if (array1[i1] == array2[i2]){
				if (count == 0 || (count > 0 && common[count-1] != array1[i1])){
					common[count] = array1[i1];
					count++;
					System.out.println(array1[i1]);
				}
				i1++;
				i2++;
			}else if (array1[i1] < array2[i2])
				i1++;
			else if (array1[i1] > array2[i2])
				i2++;
		}
		int[] temp = new int[count];
		for (int i = 0; i < count; i++){
			temp[i] = common[i];
		}
		return temp;
	}
	public static void test(){
		int[] array1 = {1,2,2,3,3,3,4,5,6,7,8,9};
		int[] array2 = {0,1,2,2,2,3,5,5,9,9};
		printCommonInteger(array1, array2);
	}
	public static void main(String[] args){
		CommonInteger.test();
	}

}
