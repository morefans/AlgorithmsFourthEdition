package chapter_4.session_2;

public class KosarajuSCC {
	private boolean[] marked;// 已访问过的顶点
	private int[] id;// 强连通分量的标识符
	private int count;// 强连通分量的数量，并以数量为强连通分量的标识

	public KosarajuSCC(Digraph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
		for (int s : order.reversePost()){
			if (!marked[s]){
				dfs(G, s);
				count++;
			}
		}
	}
	private void dfs(Digraph G, int v){
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v)){
			if (!marked[w])
				dfs(G, w);
		}
	}
	public boolean stronglyConnected(int v, int w){
		return id[v] == id[w];
	}
	public int id(int v){
		return id[v];
	}
	public int count(){
		return count;
	}
	public static void test(){
		Digraph g = new Digraph(13);
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 2);
		g.addEdge(3, 5);
		g.addEdge(4, 2);
		g.addEdge(4, 3);
		g.addEdge(5, 4);
		g.addEdge(6, 0);
		g.addEdge(6, 4);
		g.addEdge(6, 9);
		g.addEdge(7, 6);
		g.addEdge(7, 8);
		g.addEdge(8, 7);
		g.addEdge(8, 9);
		g.addEdge(9, 10);
		g.addEdge(9, 11);
		g.addEdge(10, 12);
		g.addEdge(11, 4);
		g.addEdge(11, 12);
		g.addEdge(12, 9);
		
		KosarajuSCC scc = new KosarajuSCC(g);
		System.out.println("KosarajuSCC.stronglyConnected(0, 1): " + scc.stronglyConnected(0, 1));
		System.out.println("KosarajuSCC.stronglyConnected(7, 8): " + scc.stronglyConnected(7, 8));
		System.out.println("KosarajuSCC.stronglyConnected(0, 2): " + scc.stronglyConnected(0, 2));
		System.out.println("KosarajuSCC.stronglyConnected(9, 11): " + scc.stronglyConnected(9, 11));
		
	}
	public static void main(String[] args){
		test();
	}
}
