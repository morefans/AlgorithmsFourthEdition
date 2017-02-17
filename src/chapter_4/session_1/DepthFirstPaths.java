package chapter_4.session_1;

import chapter_1.session_3.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int count;
	private final int s;// Æðµã
	private Graph G;
	
	private int[] edgeTo;
	
	public DepthFirstPaths(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		this.G = G;
		dfs(G, s);
	}
	
	public void dfs(){
		dfs(this.G, s);
	}
	private void dfs(Graph G, int v){
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
		Graph G = Graph.getTestGraph();
		System.out.print("dfs: ");
		DepthFirstPaths dfs = new DepthFirstPaths(G, 0);
		System.out.println();
		System.out.print("pathTo(4):");
		for (int v : dfs.pathTo(4))
			System.out.print(v + "\t");
	}
	public static void main(String[] args){
		test();
	}

}
