package chapter_4.session_3;

import chapter_1.session_3.Bag;

public class EdgeWeightedGraph {
	private final int V;// 顶点总数
	private int E;// 边的总数
	private Bag<Edge>[] adj;// 邻接表
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++){
			adj[v] = new Bag<Edge>();
		}
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public void addEdge(Edge e){
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	public Iterable<Edge> edges(){
		Bag<Edge> b = new Bag<Edge>();
		for (int v = 0; v < V; v++){
			for (Edge e : adj[v]){
				if (e.other(v) > v)
					b.add(e);
			}
		}
		return b;
	}
	public String toString(){
		String graph = V + " vertex, " + E + " edges. \n";
		for (int v = 0; v < V; v++){
			graph += v + ": ";
			for (Edge e : adj[v]){
				graph += e.other(v) + "(" + e.weight() + ") \t";
			}
			graph += "\n";
		}
		return graph;
	}
	public static EdgeWeightedGraph getTestEdgeWeightedGraph(){
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(8);
		ewg.addEdge(new Edge(4, 5, 0.35));
		ewg.addEdge(new Edge(4, 7, 0.37));
		ewg.addEdge(new Edge(5, 7, 0.28));
		ewg.addEdge(new Edge(0, 7, 0.16));
		ewg.addEdge(new Edge(1, 5, 0.32));
		ewg.addEdge(new Edge(0, 4, 0.38));
		ewg.addEdge(new Edge(2, 3, 0.17));
		ewg.addEdge(new Edge(1, 7, 0.19));
		ewg.addEdge(new Edge(0, 2, 0.26));
		ewg.addEdge(new Edge(1, 2, 0.36));
		ewg.addEdge(new Edge(1, 3, 0.29));
		ewg.addEdge(new Edge(2, 7, 0.34));
		ewg.addEdge(new Edge(6, 2, 0.40));
		ewg.addEdge(new Edge(3, 6, 0.52));
		ewg.addEdge(new Edge(6, 0, 0.58));
		ewg.addEdge(new Edge(6, 4, 0.93));
		return ewg;
	}
	public static void test(){
		EdgeWeightedGraph ewg = getTestEdgeWeightedGraph();
		System.out.println(ewg.toString());
	}
	public static void main(String[] args){
		test();
	}
	

}
