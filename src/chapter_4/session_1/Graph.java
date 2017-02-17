package chapter_4.session_1;

import chapter_1.session_3.Bag;
import chapter_1.session_3.Stack;

public class Graph {
	private final int V;// 顶点数目
	private int E;// 边的数目
	private Bag<Integer>[] adj;// 邻接表
	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++){
			adj[v] = new Bag<Integer>();
		}
	}
	public Graph(Graph G){
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public void addEdge(int v, int w){
		if (hasEdge(v, w))
			return ;
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	// 度数，即连接边数目
	public static int degree(Graph G, int v){
		int degree = 0;
		for (@SuppressWarnings("unused") int w : G.adj(v))
			degree++;
		return degree;
	}
	public static int maxDegree(Graph G){
		int max = 0;
		for (int v = 0; v < G.V(); v++){
			if (degree(G, v) > max){
				max = degree(G, v);
			}
		}
		return max;
	}
	public static double avgDegree(Graph G){
		return 2.0 * G.E() / G.V();
	}
	public static int numberOfSelfLoops(Graph G){
		int count = 0;
		for (int v = 0; v < G.V(); v++){
			for (int w : G.adj(v)){
				if (v == w)
					count++;
			}
		}
		return count/2;
	}
	public String toString(){
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++){
			s += v + " : ";
			for (int w : this.adj(v)){
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}
	public Graph copy(){
		Graph copy = new Graph(this.V());
		for (int v = 0; v < this.V(); v++){
			for (int w : this.adj(v)){
				if (w > v)
					copy.addEdge(v, w);
			}
		}
		return copy;
	}
	public boolean hasEdge(int v, int w){
		for (int i : adj(v)){
			if (i == w)
				return true;
		}
		return false;
	}

	public static Graph getTestGraph(){
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 5);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		return g;
	}
	public static void test(){
		Graph g = getTestGraph();
		for (int v = 0; v < g.V();v++){
			System.out.print("adj(" + v + "): ");
			for (int w : g.adj(v))
				System.out.print(w + "\t");
			System.out.println();
		}
		// 起点与起点的度数不对啊
		for (int v = 0; v < g.V();v++){
			System.out.println("degree from source to " + v + " : " + degree(g, v));
		}
		System.out.println("maxDegree():" + maxDegree(g));
		System.out.println("avgDegree():" + avgDegree(g));
		System.out.println("numberOfSelfLoops():" + numberOfSelfLoops(g));
		
	}
	public static void main(String[] args){
		test();
	}
}
