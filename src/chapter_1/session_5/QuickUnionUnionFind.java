package chapter_1.session_5;
/**
 * quick-union算法，id数组存的是分量重另一个触点的名称（如果存的是自己则是根触点），但并不保证比quick-find算法快
 * @author ZhangYuliang
 *
 */
public class QuickUnionUnionFind {
	private int[] id; // 连通分量id（以触点作为索引）
	private int count;// 连通分量数量
	
	public QuickUnionUnionFind(int N){
		count = N;
		id = new int[N];
		for (int i = 0;i < N;i++)
			id[i] = i;
	}
	
	public int find(int p){
		while (p != id[p])
			p = id[p];
		return p;
	}
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot)
			return ;
		id[pRoot] = qRoot;
		count--;
		
	}
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	public int count(){
		return count;
	}

	public static void test(){
		int[][] pairs = new int[10][2];
		pairs[0][0] = 4;pairs[0][1] = 3;
		pairs[1][0] = 3;pairs[1][1] = 8;
		pairs[2][0] = 6;pairs[2][1] = 5;
		pairs[3][0] = 9;pairs[3][1] = 4;
		pairs[4][0] = 2;pairs[4][1] = 1;
		pairs[5][0] = 8;pairs[5][1] = 9;
		pairs[6][0] = 5;pairs[6][1] = 0;
		pairs[7][0] = 7;pairs[7][1] = 2;
		pairs[8][0] = 6;pairs[8][1] = 1;
		pairs[9][0] = 1;pairs[9][1] = 0;
		QuickUnionUnionFind unionFind = new QuickUnionUnionFind(10);
		for (int i = 0;i < 10;i++){
			if (unionFind.connected(pairs[i][0], pairs[i][1]))
					continue;
			unionFind.union(pairs[i][0], pairs[i][1]);
			System.out.println("(" + pairs[i][0] + ", " + pairs[i][1] + ")");
		}
		System.out.println(unionFind.count() + "条连接。");
	}
	public static void main(String[] args) {
		QuickUnionUnionFind.test();
	}
}
