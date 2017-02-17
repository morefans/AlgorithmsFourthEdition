package chapter_4.session_4;

import chapter_1.session_3.Stack;

public class EdgeWeightedDirectedCycle {
	private boolean[] marked;
	private DirectedEdge[] edgeTo;
	private boolean[] onStack;
	private Stack<DirectedEdge> cycle;
	
	public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G){
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++){
			if (!marked[v])
				dfs(G, v);
		}
	}
	private void dfs(EdgeWeightedDigraph G, int v){
		onStack[v] = true;
		marked[v] = true;
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if (hasCycle())
				return ;
			else if (!marked[w]){
				edgeTo[w] = e;
				dfs(G, w);
			}
			else if (onStack[w]){
				cycle = new Stack<DirectedEdge>();
				DirectedEdge f = e;
				while (f.from() != w){
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				cycle.push(f);
				return ;
			}
		}

		onStack[v] = false;
	}
	
	public boolean hasCycle(){
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}
	
	public static void test(){
		EdgeWeightedDigraph G = EdgeWeightedDigraph.getTestEdgeWeightedDigraph();
		System.out.println(G.toString());
		EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(G);
		System.out.println("hasCycle: " + cycleFinder.hasCycle());
		if (cycleFinder.hasCycle())
			for (DirectedEdge e : cycleFinder.cycle())
				System.out.println(e.toString());
	}
	public static void main(String[] args){
		test();
	}

}
