package chapter_1.session_5;
/**
 * 加权quick-find算法，小树连到大树上
 * @author ZhangYuliang
 *
 */
public class WeightedQuickUnionUnionFind {
	private int[] parent; // 父链接数组（由触点索引）
	private int[] size; // （由触点索引的）各个根节点所对应的分量的大小
	private int count;// 连通分量数量
	
	public WeightedQuickUnionUnionFind(int N){
		count = N;
		parent = new int[N];
		size = new int[N];
		for (int i = 0;i < N;i++){
			parent[i] = i;
			size[i] = 1;
		}
	}
	public int find(int p){
		while (p != parent[p])
			p = parent[p];
		return p;
	}
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot)
			return ;
		if (size[pRoot] < size[qRoot]){
			parent[pRoot] = qRoot;
			size[qRoot] += size[pRoot];
		}else{
			parent[qRoot] = pRoot;
			size[pRoot] += size[qRoot];
		}
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
		WeightedQuickUnionUnionFind unionFind = new WeightedQuickUnionUnionFind(10);
		for (int i = 0;i < 10;i++){
			if (unionFind.connected(pairs[i][0], pairs[i][1]))
					continue;
			unionFind.union(pairs[i][0], pairs[i][1]);
			System.out.println("(" + pairs[i][0] + ", " + pairs[i][1] + ")");
		}
		System.out.println(unionFind.count() + "条连接。");
	}
	public static void main(String[] args) {
		WeightedQuickUnionUnionFind.test();
	}
}
