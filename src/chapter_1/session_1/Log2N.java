package chapter_1.session_1;
/**
 * 习题1.1.14，求不大于log2N的最大整数
 * @author ZhangYuliang
 *
 */
public class Log2N {
	public static int lg(int N){
		if (N < 2)
			return 0;
		int count = 1;
		int remainder = N / 2;
		while (remainder >= 2){
			remainder = remainder / 2;
			count++;
		}
		return count;
	}
	public static void test(){
		for (int i = 0; i <= 129; i++)
			System.out.println("lg" + i + ":" + lg(i));
	}
	public static void main(String[] args){
		Log2N.test();
	}
}
