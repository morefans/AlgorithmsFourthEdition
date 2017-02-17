package chapter_4.session_4;

import chapter_1.session_3.Stack;

/**
 * Shortest Path
 * @author ZhangYuliang
 *
 */
public class SP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;

	public SP(EdgeWeightedDigraph G, int s){
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}

	}

	public void relax(DirectedEdge e){
		int v = e.from();
		int w = e.to();
		if (distTo[w] > distTo[v] + e.weight()){
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
	public void relax(EdgeWeightedDigraph G, int v){
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
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
	
	
}
