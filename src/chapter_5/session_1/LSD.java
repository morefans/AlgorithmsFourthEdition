package chapter_5.session_1;
/**
 * Least-Significant-Digit-First����λ�����ַ�������
 * @author ZhangYuliang
 *
 */
public class LSD {
	public static void sort(String[] a, int W){
		// ͨ��ǰW���ַ���a[]����
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
		for (int d = W-1; d >= 0; d--){
			// ���ݵ�d���ַ��ü���������������
			int[] count = new int[R+1];// �������Ƶ��
			for (int i = 0; i < N; i++)
				count[a[i].charAt(d) + 1]++;
			for (int r = 0; r < R; r++)// ��Ƶ��ת��Ϊ����
				count[r+1] += count[r];
			for (int i = 0; i < N; i++){// ��Ԫ�ط���
				aux[count[a[i].charAt(d)]] = a[i];
				count[a[i].charAt(d)]++;
			}
			for (int i = 0; i < N; i++)// ��д
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
