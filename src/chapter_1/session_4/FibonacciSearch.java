package chapter_1.session_4;
/**
 * 习题1.4.22，仅用加减实现的二分查找，即斐波那契查找
 * @author ZhangYuliang
 *
 */
public class FibonacciSearch {
	public static int[] getFibonacci(int[] array){
		int max = array[array.length-1];
		int[] f = new int[array.length*2];
		f[0] = 0;
		f[1] = 1;
		int i = 1;
		while (f[i] < max){
			i++;
			f[i] = f[i-1] + f[i-2];
		}
		int[] fibonacci = new int[i+1];
		for (int k = 0; k <= i; k++)
			fibonacci[k] = f[k];
		return fibonacci;
	}
	//	public static int search(int[] array, int key){
	//		int[] fibonacci = getFibonacci(array);
	//		int flength = fibonacci.length;
	//		int[] temp = new int[fibonacci[flength-1]+1];
	//		for (int i = 0; i < array.length; i++)
	//			temp[i] = array[i];
	//		for (int i = array.length;i < temp.length;i++)
	//			temp[i] = array[array.length-1];
	//		int low = 0;
	//		int high = array.length-1;
	//		int middle = 0;
	//		int k = flength-1;
	//		while (low <= high){
	//			if (k > 1)
	//				middle = low + fibonacci[k-2];
	//			else{
	//				if (temp[low] == key){
	//					return low;
	//				}else{
	//					break;
	//				}
	//			}
	//			if (temp[middle] == key){
	//				if (middle > array.length-1)
	//					return array.length-1;
	//				return middle;
	//			}
	//			if (temp[middle] < key){
	//				low = middle + 1;
	//				k--;
	//			}
	//			if (temp[middle] > key){
	//				high = middle - 1;
	//				k-=2;
	//			}
	//		}
	//		return -1;
	//	}
	public static int search(int[] array, int key){
		int[] fibonacci = getFibonacci(array);
		
		int flength = fibonacci.length;
		int needlength = fibonacci[flength-1]+1;
		int[] temp = new int[needlength];
		for (int i = 0; i < array.length; i++)
			temp[i] = array[i];
		for (int i = array.length;i < temp.length;i++)
			temp[i] = array[array.length-1];
		int low = 0;
		int high = array.length-1;
		int middle = 0;
		int k = flength-1;
		while (low <= high){
			middle = low + fibonacci[k-1];
			if (temp[middle] == key){
				if (middle > array.length-1)
					return array.length-1;
				return middle;
			}
			if (temp[middle] < key){
				low = middle + 1;
				k-=2;
			}
			if (temp[middle] > key){
				high = middle - 1;
				k--;
			}
		}
		return -1;
	}
	public static void test(){
//		int[] array = {1,2,3,4,5,6,7,8,9,10,10};
		int[] array = {1};
		System.out.println(search(array,1));
		System.out.println(search(array,2));
		System.out.println(search(array,10));

		System.out.println(fibonacciSearch(array,1));
		System.out.println(fibonacciSearch(array,2));
		System.out.println(fibonacciSearch(array,10));
	}
	public static void main(String[] args){
		FibonacciSearch.test();
	}

	public static final int MAXSIZE = 20;
	public static int[] fibonacci() {  
		int[] f = new int[20];  
		int i = 0;  
		f[0] = 1;  
		f[1] = 1;  
		for (i = 2; i < MAXSIZE; i++) {  
			f[i] = f[i - 1] + f[i - 2];  
		}  
		return f;  
	} 
	public static int fibonacciSearch(int[] data, int key) {  
		int low = 0;  
		int high = data.length - 1;  
		int mid = 0;  
		// 斐波那契分割数值下标  
		int k = 0;  
		// 序列元素个数  
		int i = 0;  
		// 获取斐波那契数列  
		int[] f = fibonacci();  
		// 获取斐波那契分割数值下标  
		while (data.length > f[k] - 1) {  
			k++;  
		}  
		// 创建临时数组  
		int[] temp = new int[f[k] - 1];  
		for (int j = 0; j < data.length; j++){
			temp[j] = data[j];  
		}  
		// 序列补充至f[k]个元素  
		// 补充的元素值为最后一个元素的值  
		for (i = data.length; i < f[k] - 1; i++) {  
			temp[i] = temp[high];  
		}  
		//        for (int j : temp) {  
		//            System.out.print(j + " ");  
		//        }  
		//        System.out.println();  
		while (low <= high) {  
			// low：起始位置  
			// 前半部分有f[k-1]个元素，由于下标从0开始  
			// 则-1 获取 黄金分割位置元素的下标  
			mid = low + f[k - 1] - 1;  

			if (temp[mid] > key) {  
				// 查找前半部分，高位指针移动  
				high = mid - 1;  
				// （全部元素） = （前半部分）+（后半部分）  
				// f[k] = f[k-1] + f[k-1]  
				// 因为前半部分有f[k-1]个元素，所以 k = k-1  
				k = k - 1;  
			} else if (temp[mid] < key) {  
				// 查找后半部分，高位指针移动  
				low = mid + 1;  
				// （全部元素） = （前半部分）+（后半部分）  
				// f[k] = f[k-1] + f[k-1]  
				// 因为后半部分有f[k-1]个元素，所以 k = k-2  
				k = k - 2;  
			} else {  
				// 如果为真则找到相应的位置  
				if (mid <= high) {  
					return mid;  
				} else {  
					// 出现这种情况是查找到补充的元素  
					// 而补充的元素与high位置的元素一样  
					return high;  
				}  
			}  
		}  
		return -1;  
	}  

}
