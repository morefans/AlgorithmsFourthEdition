package sort;

public class SelectSort extends Sort {

	public int[] sort2(int[] array) {//选出最大放到最后面
		for (int i = 0;i < array.length;i++){
			int select = 0;
			for (int j = 0;j < array.length-i;j++){
				if(array[select] < array[j]){
					select = j;
				}
			}
			swap(array,select,array.length-i-1);
		}
		return array;
	}
	public int[] sort(int[] array) {//选出最小放到最前面
		for (int i = 0;i < array.length;i++){
			int select = array.length-1;
			for (int j = select;j >= i;j--){
				if(array[select] > array[j]){
					select = j;
				}
			}
			swap(array,select,i);
		}
		return array;
	}

	public static void main(String[] args) {
		new SelectSort().test();
	}

}
