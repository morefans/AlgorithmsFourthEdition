package chapter_1.session_4;
/**
 * ϰ��1.4.34���Ȼ�����
 * @author ZhangYuliang
 *
 */
public class HotOrCold {
	private int N;
	private int key;
	public HotOrCold(int N, int key){
		this.N = N;
		this.key = key;
	}
	public void setKey(int key){
		this.key = key;
	}
	/**
	 * ���ַ���~2lgN�������Σ��ڶ��ν������Ҫ
	 * @param N
	 * @param key
	 * @return
	 */
	public int guess2lgN(){
		int last = N;
		int current = 0;
		int next = 0;
		while (current != key){
			int hotOrCold = hotOrCold(last, current);
			next = (last + current) / 2;
			if (hotOrCold == 1){// ����
				hotOrCold = hotOrCold(current, next);
				last = next;
			}else{// Զ��
				hotOrCold = hotOrCold(current, next);
				current = last;
				last = next;
			}
		}
		return current;
	}
	/**
	 * ���ַ���~lgN
	 * @param N
	 * @param key
	 * @return
	 */
	public int guesslgN(){
		int last = 0;
		int current = N;
		int middle = (last + current) / 2;
		int distance = (last + current) / 2;
		while (distance > 1){
			distance = (distance+1) / 2;
			int hotOrCold = hotOrCold(last, current);
			if (hotOrCold == 1){// ����
				if (current > middle)
					middle = middle + distance;
				else
					middle = middle - distance;
				last = current;
				current = 2 * middle - current;
			}else if (hotOrCold == -1){// Զ��
				if (current > middle)
					middle = middle - distance;
				else
					middle = middle + distance;
				last = current;
				current = 2 * middle - current;
			}
			else{
				return current;
			}
		}
		if (hotOrCold(last, middle+1) == 0)
			return middle+1;
		if (hotOrCold(middle+1, middle-1) == 0)
			return middle-1;
		return middle;
	}
	public int hotOrCold(int last, int current){
		if (current == key)
			return 0;// ������
		if (Math.abs(current - key) < Math.abs(last - key))
			return 1;// ��ʾHot�����ӽ���
		if (Math.abs(current - key) > Math.abs(last - key))
			return -1;// ��ʾCold����Զ����
		else
			return 1;// ������ȣ���Ϊ����Hot
	}
	public static void test(){
		int N = 100;
		HotOrCold hotOrCold = new HotOrCold(N, 0);
		for(int key = 0;key <= 100;key++){
			hotOrCold.setKey(key);
			System.out.println(hotOrCold.guess2lgN());
		}
		for(int key = 0;key <= N;key++){
			hotOrCold.setKey(key);
			System.out.println(hotOrCold.guesslgN());
		}
	}
	public static void main(String[] args){
		HotOrCold.test();
	}
}
