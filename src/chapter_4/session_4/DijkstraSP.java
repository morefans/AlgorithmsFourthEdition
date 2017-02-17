package chapter_4.session_4;

import chapter_1.session_3.Stack;
import chapter_2.session_4.IndexMinPQ;

public class DijkstraSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s){
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq.insert(s,  0.0);
		while (!pq.isEmpty())
			relax(G, pq.delMin());
	}
	private void relax(EdgeWeightedDigraph G, int v){
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.changeKey(w, distTo[w]);
				else
					pq.insert(w,  distTo[w]);
			}
		}
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
		EdgeWeightedDigraph G = EdgeWeightedDigraph.getTestEdgeWeightedDigraph();
		DijkstraSP sp = new DijkstraSP(G, 0);
		System.out.println(G.toString());
		for (int v = 0; v < G.V(); v++){
			System.out.println("sp.hasPathTo(" + v + "): " + sp.hasPathTo(v));
			if (sp.hasPathTo(v))
				for (DirectedEdge e : sp.pathTo(v))
					System.out.println(e.toString());
		}
	}
	public static void main(String[] args){
		test();
	}

}
