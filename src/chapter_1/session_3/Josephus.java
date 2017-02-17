package chapter_1.session_3;
/**
 * ϰ��1.3.37��Josephus����
 * @author ZhangYuliang
 *
 */
public class Josephus {
	/**
	 * N����Χ��һȦ��λ�ü�Ϊ0��N-1����1��ʼ����M��M���˱�ɱ������ӡ��ɱ����˳��
	 * @param N
	 * @param M
	 * @return
	 */
	public static int run(int N, int M){
		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0;i< N;i++){
			queue.enqueue(i);
		}
		System.out.print("Killed order:\t");
		int killed = -1;
		while (!queue.isEmpty()){
			for (int i = 1;i < M;i++){
				int temp = queue.dequeue();
				queue.enqueue(temp);
			}
			killed = queue.dequeue();
			System.out.print(killed + "\t");
		}
		return killed;
	}

	public static void main(String[] args) {
		Josephus.run(7, 2);
	}

}
