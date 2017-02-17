package chapter_1.session_5;

import java.util.Random;

public class ErdosRenyi {

	public static int count(int N){
		int edges = 0;
//		int count = 0;
		UF uf = new UF(N);
		Random random;
		while (uf.count() > 1){
			//			int i = new Random(System.currentTimeMillis()).nextInt(N);
			//			int j = new Random(System.currentTimeMillis()).nextInt(N);
			random = new Random(System.currentTimeMillis());
			int i = random.nextInt(N);
			random = new Random(System.currentTimeMillis());
			int j = random.nextInt(N);
			while (i == j)
				j = new Random(System.currentTimeMillis()).nextInt(N);
//			System.out.println(i + "\t" + j);
			if (!uf.connected(i, j)){
				uf.union(i, j);
//				count++;
			}
			edges++;
		}
//		System.out.println("count:" + count);
		return edges;
	}
	public static void main(String[] args) {
//		System.out.println(count(9));
		for (int i = 0;i < 50;i++){
			if (i == 4 || i == 8||i==16||i==32)
				continue;
			System.out.println("countLink(" + i + "): " + count(i));
		}
	}
}
