package chapter_5.session_1;

/**
 * Most-Significant-Digit-First����λ�����ַ�������
 * @author ZhangYuliang
 *
 */
public class MSD {
	private static int R = 256;// ����
	private static final int M = 15;// С������л���ֵ
	private static String[] aux;// ���ݷ���ĸ�������
	private static int charAt(String s, int d){
		if (d < s.length())
			return s.charAt(d);
		else
			return -1;
	}
	public static void sort(String[] a){
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N-1, 0);
	}
	private static void sort(String[] a, int low, int high, int d){
		if (high <= low + M){
			insertion(a, low, high, d);
			return;
		}
		int[] count = new int[R+2];// ����Ƶ��
		for (int i = low; i <= high; i++)
			count[charAt(a[i], d) + 2]++;
		for (int r = 0; r < R+1; r++)// ��Ƶ��ת��Ϊ����
			count[r+1] += count[r];
		for (int i = low; i <= high; i++)// ���ݷ���
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		for (int i = low; i <= high; i++)// ��д
			a[i] = aux[i - low];
		// �ݹ����ÿ���ַ�Ϊ����������
		for (int r = 0; r < R; r++)
			sort(a, low + count[r], low + count[r+1] - 1, d+1);
	}
	private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }
	private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
	private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

	public static void test(){
//		String[] a = {"yu0s", "abc","123", "153", "1ad"};
		String[] a = {"yu0s", "abc","123", "153", "1ad",
				"yu0syu0syu0syu0syu0s", "abc1abc1abc1abc1abc1","12311231123112311231d", "15311531153115311531f", "1ad11ad11ad11ad11ad145abc1abc1abc1abc1abc1",
				"yu0sr", "abct","123t", "153h", "1adss",
				"yu0ssdfg", "abc1234","123xcvb", "153hjm", "1ad54"};
		LSD.print(a);
		sort(a);
		LSD.print(a);
	}
	public static void main(String[] args){
		test();
	}

}
