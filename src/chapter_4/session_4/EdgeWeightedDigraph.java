package chapter_4.session_4;

import chapter_1.session_3.Bag;

public class EdgeWeightedDigraph {
	private final int V;// 顶点总数
	private int E;// 边的总数
	private Bag<DirectedEdge>[] adj;//邻接表
	
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++){
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public void addEdge(DirectedEdge e){
		adj[e.from()].add(e);
		E++;
	}
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for (int v = 0; v < V; v++){
			for (DirectedEdge e : adj[v])
				bag.add(e);
		}
		return bag;
	}
	public String toString(){
		String graph = V() + " vertex, " + E() + "edges.\n";
		for (int v = 0; v < V(); v++){
			graph += v + ": ";
			for (DirectedEdge e : adj(v))
				graph += e.toString() + "\t";
			graph += "\n";
		}
		return graph;
	}
	public static EdgeWeightedDigraph getTestEdgeWeightedDigraph(){
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
		G.addEdge(new DirectedEdge(0, 2, 0.26));
		G.addEdge(new DirectedEdge(0, 4, 0.38));
		G.addEdge(new DirectedEdge(1, 3, 0.29));
		G.addEdge(new DirectedEdge(2, 7, 0.34));
		G.addEdge(new DirectedEdge(3, 6, 0.52));
		G.addEdge(new DirectedEdge(4, 5, 0.35));
		G.addEdge(new DirectedEdge(4, 7, 0.37));
		G.addEdge(new DirectedEdge(5, 1, 0.32));
		G.addEdge(new DirectedEdge(5, 4, 0.35));
		G.addEdge(new DirectedEdge(5, 7, 0.28));
		G.addEdge(new DirectedEdge(6, 0, 0.58));
		G.addEdge(new DirectedEdge(6, 2, 0.40));
		G.addEdge(new DirectedEdge(6, 4, 0.93));
		G.addEdge(new DirectedEdge(7, 3, 0.97));
		G.addEdge(new DirectedEdge(7, 5, 0.28));
		return G;
	}
	public static EdgeWeightedDigraph getTestAcyclicEdgeWeightedDigraph(){
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
		G.addEdge(new DirectedEdge(0, 2, 0.26));
		G.addEdge(new DirectedEdge(1, 3, 0.29));
		G.addEdge(new DirectedEdge(3, 6, 0.52));
		G.addEdge(new DirectedEdge(3, 7, 0.39));
		G.addEdge(new DirectedEdge(4, 0, 0.38));
		G.addEdge(new DirectedEdge(4, 7, 0.37));
		G.addEdge(new DirectedEdge(5, 1, 0.32));
		G.addEdge(new DirectedEdge(5, 4, 0.35));
		G.addEdge(new DirectedEdge(5, 7, 0.28));
		G.addEdge(new DirectedEdge(6, 0, 0.58));
		G.addEdge(new DirectedEdge(6, 2, 0.40));
		G.addEdge(new DirectedEdge(6, 4, 0.93));
		G.addEdge(new DirectedEdge(7, 2, 0.34));
		return G;
	}
	public static EdgeWeightedDigraph getTestEdgeWeightedDigraphContainsNegativeEdge(){
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
		G.addEdge(new DirectedEdge(0, 2, 5.26));
		G.addEdge(new DirectedEdge(0, 4, 5.38));
		G.addEdge(new DirectedEdge(1, 3, 5.29));
		G.addEdge(new DirectedEdge(1, 5, -0.1));
		G.addEdge(new DirectedEdge(2, 7, 5.34));
		G.addEdge(new DirectedEdge(3, 6, 5.52));
		G.addEdge(new DirectedEdge(4, 5, 5.35));
		G.addEdge(new DirectedEdge(4, 7, 5.37));
		G.addEdge(new DirectedEdge(5, 1, -0.1));
		G.addEdge(new DirectedEdge(5, 4, 5.35));
//		G.addEdge(new DirectedEdge(5, 7, 0.28));
		G.addEdge(new DirectedEdge(5, 7, -0.01));
		G.addEdge(new DirectedEdge(6, 0, -1.40));
		G.addEdge(new DirectedEdge(6, 2, -1.20));
		G.addEdge(new DirectedEdge(6, 4, -1.25));
		G.addEdge(new DirectedEdge(7, 3, 5.97));
//		G.addEdge(new DirectedEdge(7, 5, 0.28));
		G.addEdge(new DirectedEdge(7, 5, -0.01));
		return G;
	}
	public static void test(){
		System.out.println(getTestEdgeWeightedDigraph().toString());
	}
	public static void main(String[] args){
		test();
	}

}
