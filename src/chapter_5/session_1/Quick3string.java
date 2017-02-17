package chapter_5.session_1;

public class Quick3string {
	private static int charAt(String s, int d){
		if (d < s.length())
			return s.charAt(d);
		else
			return -1;
	}
	public static void sort(String[] a){
		sort(a, 0, a.length-1, 0);
	}
	public static void sort(String[] a, int lo, int hi, int d){
		if (hi <= lo)
			return ;
		int lt = lo;
		int gt = hi;
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while (i <= gt){
			int t = charAt(a[i], d);
			if (t < v)
				exch(a, lt++, i++);
			else if (t > v)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt-1, d);
		if (v >= 0)
			sort(a, lt, gt, d+1);
		sort(a, gt+1, hi, d);
	}
	private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
