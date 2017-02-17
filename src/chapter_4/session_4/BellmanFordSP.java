package chapter_4.session_4;

import chapter_1.session_3.Queue;
import chapter_1.session_3.Stack;

public class BellmanFordSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private boolean[] onQ;//该顶点是否存在于队列中，防止顶点重复插入队列 
	private Queue<Integer> queue;// 正在被放松的顶点
	private int cost;// relax()的调用次数
	private Iterable<DirectedEdge> cycle;//edgeTo[]中的是否有负权重环

	public BellmanFordSP(EdgeWeightedDigraph G, int s){
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQ = new boolean[G.V()];
		queue = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		queue.enqueue(s);
		onQ[s] = true;
		while (!queue.isEmpty() && !hasNegativeCycle()){
			int v = queue.dequeue();
			onQ[v] = false;
			relax(G, v);
		}
	}
	private void relax(EdgeWeightedDigraph G, int v){
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQ[w]){
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
			if (cost++ % G.V() == 0)
				findNegativeCycle();
		}
	}
	public boolean hasNegativeCycle(){
		return cycle != null;
	}
	private void findNegativeCycle(){
		int V = edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
		for (int v = 0; v < V; v++){
			if (edgeTo[v] != null)
				spt.addEdge(edgeTo[v]);
		}
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
		cycle = finder.cycle();
	}
	public Iterable<DirectedEdge> negativeCycle(){
		return cycle;
	}
	public double distTo(int v){
		return distTo[v];
	}
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v){
		if (!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
	public static void test(){
		EdgeWeightedDigraph G = EdgeWeightedDigraph.getTestEdgeWeightedDigraph();//ContainsNegativeEdge();
		System.out.println(G.toString());
		BellmanFordSP sp = new BellmanFordSP(G, 0);
		System.out.println("hasNegativeCycle(): " + sp.hasNegativeCycle());
		if (sp.hasNegativeCycle()){
			for (DirectedEdge e : sp.negativeCycle()){
				System.out.println(e.toString());
			}
		}
		else {
			for (int v = 0; v < G.V(); v++){
				System.out.println("hasPathTo(" + v +"): " + sp.hasPathTo(v));
				if (sp.hasPathTo(v))
					for (DirectedEdge e : sp.pathTo(v))
						System.out.println(e.toString());
			}
		}
	}
	public static void main(String[] args){
		test();
	}

}
