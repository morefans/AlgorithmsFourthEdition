package chapter_4.session_2;

import chapter_1.session_3.Bag;
import chapter_1.session_3.Stack;

public class Digraph {
	private final int V;
	private int E;
	private Bag<Integer> [] adj;

	@SuppressWarnings("unchecked")
	public Digraph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[])new Bag[V];
		for (int v = 0; v < V; v++){
			adj[v] = new Bag<Integer>();
		}
	}
	public Digraph(Digraph G){
		this(G.V());
		this.E = G.V();
		for (int v = 0; v < G.V(); v++){
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj(v)){
				reverse.push(w);
			}
			for (int w : reverse){
				adj[v].add(w);
			}
		}
	}
	public void addEdge(int v, int w){
		// 检测平行边和自环
		if (v == w || hasEdge(v, w) || hasEdge(w, v))
			return ;
		adj[v].add(w);
		E++;
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	public Digraph reverse(){
		Digraph R = new Digraph(V);
		for (int v = 0; v < V;v++){
			for (int w : adj(v)){
				R.addEdge(w, v);
			}
		}
		return R;
	}
	public boolean hasEdge(int v, int w){
		for (int i : adj(v)){
			if (i == w)
				return true;
		}
		return false;
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
	public static Digraph getTestDigraph(){
		Digraph g = new Digraph(13);
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(0, 6);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 5);
		g.addEdge(5, 4);
		g.addEdge(6, 4);
		g.addEdge(6, 9);
		g.addEdge(7, 6);
		g.addEdge(8, 7);
		g.addEdge(9, 10);
		g.addEdge(9, 11);
		g.addEdge(9, 12);
		g.addEdge(11, 12);
		return g;
	}
	public static void test(){
		Digraph g = getTestDigraph();
		for (int v = 0; v < g.V();v++){
			System.out.print("adj(" + v + "): ");
			for (int w : g.adj(v))
				System.out.print(w + "\t");
			System.out.println();
		}
		System.out.println("Digraph.hasEdge(9, 12): " + g.hasEdge(9, 12));
		System.out.println("Digraph.hasEdge(12, 9): " + g.hasEdge(12, 9));
		
		Digraph reserve = g.reverse();
		for (int v = 0; v <reserve.V();v++){
			System.out.print("adj(" + v + "): ");
			for (int w : reserve.adj(v))
				System.out.print(w + "\t");
			System.out.println();
		}

		System.out.println("reserve.hasEdge(9, 12): " + reserve.hasEdge(9, 12));
		System.out.println("reserve.hasEdge(12, 9): " + reserve.hasEdge(12, 9));
		// 起点与起点的度数不对啊
//		for (int v = 0; v < g.V();v++){
//			System.out.println("degree from source to " + v + " : " + degree(g, v));
//		}
//		System.out.println("maxDegree():" + maxDegree(g));
//		System.out.println("avgDegree():" + avgDegree(g));
//		System.out.println("numberOfSelfLoops():" + numberOfSelfLoops(g));
		
	}
	public static void main(String[] args){
		test();
	}

}
