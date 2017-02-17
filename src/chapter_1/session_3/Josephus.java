package chapter_1.session_3;
/**
 * 习题1.3.37，Josephus问题
 * @author ZhangYuliang
 *
 */
public class Josephus {
	/**
	 * N个人围成一圈，位置记为0到N-1，从1开始数到M，M的人被杀死，打印被杀死的顺序
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
