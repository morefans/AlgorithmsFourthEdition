package chapter_5.session_1;
/**
 * Least-Significant-Digit-First，低位优先字符串排序
 * @author ZhangYuliang
 *
 */
public class LSD {
	public static void sort(String[] a, int W){
		// 通过前W个字符将a[]排序
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
		for (int d = W-1; d >= 0; d--){
			// 根据第d个字符用键索引计数法排序
			int[] count = new int[R+1];// 计算出现频率
			for (int i = 0; i < N; i++)
				count[a[i].charAt(d) + 1]++;
			for (int r = 0; r < R; r++)// 将频率转换为索引
				count[r+1] += count[r];
			for (int i = 0; i < N; i++){// 将元素分类
				aux[count[a[i].charAt(d)]] = a[i];
				count[a[i].charAt(d)]++;
			}
			for (int i = 0; i < N; i++)// 回写
				a[i] = aux[i];
		}
	}
	public static void print(String[] a){
		int i;
		System.out.print("{");
		for (i = 0; i < a.length-1; i++)
			System.out.print(a[i] + "\t");
		System.out.print(a[i]);
		System.out.println("}");
	}
	public static void test(){
		String[] a = {"yu0s", "abc","123", "153", "1ad"};
		print(a);
		sort(a, 3);
		print(a);
	}
	public static void main(String[] args){
		test();
	}

}
