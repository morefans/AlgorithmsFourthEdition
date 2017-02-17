package chapter_4.session_2;

import chapter_1.session_3.Stack;

public class DepthFirstDirectedPaths {
	private boolean[] marked;
	private int count;// 搜索路径上离源点的长度，即经过多少条边
	private final int s;// 起点
	private Digraph G;
	
	private int[] edgeTo;// 用来保存路径，edgeTo[v]为路径中v顶点的上一个顶点
	
	public DepthFirstDirectedPaths(Digraph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		this.G = G;
		dfs(G, s);
	}
	
	public void dfs(){
		dfs(this.G, s);
	}
	private void dfs(Digraph G, int v){
		marked[v] = true;
		
		System.out.print(v + "\t");
		
		count++;
		for (int w : G.adj(v)){
			if (!marked[w]){
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public boolean marked(int w){
		return marked[w];
	}
	public int count(){
		return count;
	}
	public Iterable<Integer> pathTo(int v){
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]){
			path.push(x);
		}
		path.push(s);
		return path;
	}
	public static void test(){
		Digraph G = Digraph.getTestDigraph();
		System.out.print("dfs: ");
		DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, 0);
		System.out.println();
		System.out.print("pathTo(4):");
		for (int v : dfs.pathTo(4))
			System.out.print(v + "\t");
	}
	public static void main(String[] args){
		test();
	}

}
