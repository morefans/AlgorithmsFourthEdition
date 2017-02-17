package chapter_1.session_4;
/**
 * ϰ��1.4.22�����üӼ�ʵ�ֵĶ��ֲ��ң���쳲���������
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
		// 쳲������ָ���ֵ�±�  
		int k = 0;  
		// ����Ԫ�ظ���  
		int i = 0;  
		// ��ȡ쳲���������  
		int[] f = fibonacci();  
		// ��ȡ쳲������ָ���ֵ�±�  
		while (data.length > f[k] - 1) {  
			k++;  
		}  
		// ������ʱ����  
		int[] temp = new int[f[k] - 1];  
		for (int j = 0; j < data.length; j++){
			temp[j] = data[j];  
		}  
		// ���в�����f[k]��Ԫ��  
		// �����Ԫ��ֵΪ���һ��Ԫ�ص�ֵ  
		for (i = data.length; i < f[k] - 1; i++) {  
			temp[i] = temp[high];  
		}  
		//        for (int j : temp) {  
		//            System.out.print(j + " ");  
		//        }  
		//        System.out.println();  
		while (low <= high) {  
			// low����ʼλ��  
			// ǰ�벿����f[k-1]��Ԫ�أ������±��0��ʼ  
			// ��-1 ��ȡ �ƽ�ָ�λ��Ԫ�ص��±�  
			mid = low + f[k - 1] - 1;  

			if (temp[mid] > key) {  
				// ����ǰ�벿�֣���λָ���ƶ�  
				high = mid - 1;  
				// ��ȫ��Ԫ�أ� = ��ǰ�벿�֣�+����벿�֣�  
				// f[k] = f[k-1] + f[k-1]  
				// ��Ϊǰ�벿����f[k-1]��Ԫ�أ����� k = k-1  
				k = k - 1;  
			} else if (temp[mid] < key) {  
				// ���Һ�벿�֣���λָ���ƶ�  
				low = mid + 1;  
				// ��ȫ��Ԫ�أ� = ��ǰ�벿�֣�+����벿�֣�  
				// f[k] = f[k-1] + f[k-1]  
				// ��Ϊ��벿����f[k-1]��Ԫ�أ����� k = k-2  
				k = k - 2;  
			} else {  
				// ���Ϊ�����ҵ���Ӧ��λ��  
				if (mid <= high) {  
					return mid;  
				} else {  
					// ������������ǲ��ҵ������Ԫ��  
					// �������Ԫ����highλ�õ�Ԫ��һ��  
					return high;  
				}  
			}  
		}  
		return -1;  
	}  

}
