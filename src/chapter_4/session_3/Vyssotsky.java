package chapter_4.session_3;

import chapter_1.session_3.Stack;
import chapter_3.session_2.BST;

public class Vyssotsky {
	private BST<Edge, Edge> mst;
	private Stack<Edge> ring;
	private boolean[] marked;
	private EdgeWeightedGraph G;
	private boolean hasCycle;

	public Vyssotsky(EdgeWeightedGraph G){
		mst = new BST<Edge, Edge>();
		marked = new boolean[G.V()];
		ring = new Stack<Edge>();
		this.G = new EdgeWeightedGraph(G.V());

		for (Edge e : G.edges()){
			addEdge(e);
			if (hasCycle())
				removeMostWeightedEdgeInRing();
		}

	}
	private void addEdge(Edge e){
		G.addEdge(e);
		mst.put(e, null);
	}
	private void removeMostWeightedEdgeInRing(){
		Edge mostEdge = ring.pop();
		while (!ring.isEmpty()){
			Edge edge = ring.pop();
			if (mostEdge.compareTo(edge) < 0)
				mostEdge = edge;
		}
		if (mostEdge != null){
			mst.delete(mostEdge);
			G = new EdgeWeightedGraph(mst.size());
			for (Edge e : mst.keys()){
				G.addEdge(e);
			}
		}
	}
	private void dfs(){
		for (int i = 0; i < G.V(); i++){
			marked[i] = false;
		}
		for (int v = 0; v < G.V(); v++){
			dfs(v, v);
		}
	}
	private void dfs(int v, int u){
		marked[v] = true;
		for (Edge e : G.adj(v)){
			int w = e.other(v);
			if (!marked[w]){
				ring.push(e);
				dfs(w, v);
			}
			else{
				if (w != u){
					hasCycle = true;
					return ;
				}
			}
			ring.pop();
		}

	}
	private boolean hasCycle(){
		dfs();
		return hasCycle;
	}
	public Iterable<Edge> edges(){
		return mst.keys();
	}
	public double weight(){
		double weight = 0.0;
		for (Edge e : edges()){
			weight += e.weight();
		}
		return weight;
	}

	public static void test(){
		EdgeWeightedGraph G = EdgeWeightedGraph.getTestEdgeWeightedGraph();
		System.out.println(G.toString());
		Vyssotsky mst = new Vyssotsky(G);
		System.out.println("Total weight: " + mst.weight());
		for (Edge e : mst.edges()){
			int v = e.either();
			System.out.println(v + "--" + e.other(v) + "(" + e.weight() + ")");
		}
	}
	public static void main(String[] args){
		test();
	}

}
