package chapter_4.session_4;

import java.util.Comparator;

import chapter_1.session_3.Stack;
import chapter_2.session_4.MinPQ;
/**
 * 似乎直接用边权重值实现MinPQ与用起点到顶点总权重实现MinPQ得到的结果相同？
 * @author ZhangYuliang
 *
 */
public class LazyDijkstraSP {
	private boolean[] marked;
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private MinPQ<DirectedEdge> pq;
	
	@SuppressWarnings("unused")
	private class ByDistanceFromSource implements Comparator<DirectedEdge> {
        public int compare(DirectedEdge e, DirectedEdge f) {
            double dist1 = distTo[e.from()] + e.weight();
            double dist2 = distTo[f.from()] + f.weight();
            return Double.compare(dist1, dist2);
        }
    }
	
	public LazyDijkstraSP(EdgeWeightedDigraph G, int s){
		for (DirectedEdge e : G.edges()){
			if (e.weight() < 0)
				throw new IllegalArgumentException("edge " + e + " has negative weight");
		}
//		pq = new MinPQ<DirectedEdge>(new ByDistanceFromSource());
		pq = new MinPQ<DirectedEdge>();
		marked = new boolean[G.V()];
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		relax(G, s);
		while (!pq.isEmpty()){
			DirectedEdge e = pq.delMin();
			int w = e.to();
			if (!marked[w])// 延时
				relax(G, w);
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v){
		marked[v] = true;
		for (DirectedEdge e : G.adj(v)){
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				pq.insert(e);
			}
		}
	}
	public double distTo(int v) {
        return distTo[v];
    }
	public boolean hasPathTo(int v){
		return marked[v];
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
		LazyDijkstraSP sp = new LazyDijkstraSP(G, 0);
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
