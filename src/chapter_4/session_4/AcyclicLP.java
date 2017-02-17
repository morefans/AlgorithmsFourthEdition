package chapter_4.session_4;

import chapter_1.session_3.Stack;
import chapter_4.session_2.Topological;

public class AcyclicLP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	public AcyclicLP(EdgeWeightedDigraph G, int s){
		distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.NEGATIVE_INFINITY;
        distTo[s] = 0.0;

        Topological topological = new Topological(G);
        if (!topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic.");
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }
	}
	private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] < distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }       
    }
	public double distTo(int v){
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		return distTo[v] > Double.NEGATIVE_INFINITY;
	}
	public Iterable<DirectedEdge> pathTo(int v){
        validateVertex(v);
		if (!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
	private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	public static void test(){
		EdgeWeightedDigraph G = EdgeWeightedDigraph.getTestAcyclicEdgeWeightedDigraph();
		AcyclicLP lp = new AcyclicLP(G, 5);
		System.out.println(G.toString());
		for (int v = 0; v < G.V(); v++){
			System.out.println("sp.hasPathTo(" + v + "): " + lp.hasPathTo(v));
			if (lp.hasPathTo(v))
				for (DirectedEdge e : lp.pathTo(v))
					System.out.println(e.toString());
		}
	}
	public static void main(String[] args){
		test();
	}

}
