package chapter_4.session_3;

import chapter_1.session_3.Queue;
import chapter_2.session_4.IndexMinPQ;

public class PrimMST {
	private Edge[] edgeTo;// 距离树最近的边
	private double[] distTo;// distTo[w]=edgeTo.weight()
	private boolean[] marked;// 如果v在树中则为true
	private IndexMinPQ<Double> pq;// 有效的横切边

	public PrimMST(EdgeWeightedGraph G){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<Double>(G.V());
		distTo[0] = 0.0;
		pq.insert(0, 0.0);
		while (!pq.isEmpty())
			visit(G, pq.delMin());
	}
	public void visit(EdgeWeightedGraph G, int v){
		marked[v] = true;
		for (Edge e : G.adj(v)){
			int w  = e.other(v);
			if (marked[w])
				continue;
			if (e.weight() < distTo[w]){
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if (pq.contains(w))
					pq.changeKey(w,  distTo[w]);
				else
					pq.insert(w,  distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges(){
		Queue<Edge> mst = new Queue<Edge>();
		for (int v = 0; v < edgeTo.length; v++){
			if (edgeTo[v] != null)
				mst.enqueue(edgeTo[v]);
		}
		return mst;
	}
	public double weight(){
		double weight = 0.0;
		for (Edge e: edges()){
			weight += e.weight();
		}
		return weight;
	}
	public static void test(){
		EdgeWeightedGraph G = EdgeWeightedGraph.getTestEdgeWeightedGraph();
		PrimMST mst = new PrimMST(G);
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
