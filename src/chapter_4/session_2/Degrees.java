package chapter_4.session_2;

import chapter_1.session_3.Queue;

public class Degrees {
	private boolean[] marked;
	private int[] indegrees;
	private int[] outdegrees;

	public Degrees(Digraph G){
		marked = new boolean[G.V()];
		indegrees = new int[G.V()];
		outdegrees = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			dfs(G, v);
	}
	private void dfs(Digraph G, int v){
		marked[v] = true;
		for (int w : G.adj(v)){
			indegrees[w]++;
			outdegrees[v]++;
			if (!marked[w]){
				dfs(G, w);
			}
		}
	}
	public int indegree(int v){
		return indegrees[v];
	}
	public int outdegree(int v){
		return outdegrees[v];
	}
	
	public Iterable<Integer> sources(){
		Queue<Integer> queue = new Queue<Integer>();
		for (int v = 0; v < indegrees.length; v++){
			if (indegrees[v] == 0)
				queue.enqueue(v);
		}
		return queue;
	}
	/**
	 * 所有终点的集合
	 * @return
	 */
	public Iterable<Integer> sinks(){
		Queue<Integer> queue = new Queue<Integer>();
		for (int v = 0; v < outdegrees.length; v++){
			if (outdegrees[v] == 0)
				queue.enqueue(v);
		}
		return queue;
	}
	/**
	 * 一幅允许出现自环且每个顶点的出度均为1的有向图叫做映射（从0到V-1之间的整数到它们自身的函数）
	 * @return
	 */
	public boolean isMap(){
		for (int v = 0; v < outdegrees.length; v++){
			if (outdegrees[v] != 1)
				return false;
		}
		return true;
	}
	 
	public static void test(){
		Digraph G = Digraph.getTestDigraph();
		Degrees d = new Degrees(G);
		System.out.println(G.toString());
		System.out.println("indegree of " + 0 + " : " + d.indegree(0));
		System.out.println("outdegree of " + 0 + " : " + d.outdegree(0));
		System.out.print("source(): ");
		for (int v : d.sources())
			System.out.print(v + "\t");
		System.out.println();

		System.out.print("sinks(): ");
		for (int v : d.sinks())
			System.out.print(v + "\t");
		System.out.println();
		
		System.out.println("isMap(): " + d.isMap());
	}
	public static void main(String[] args){
		test();
	}

}
