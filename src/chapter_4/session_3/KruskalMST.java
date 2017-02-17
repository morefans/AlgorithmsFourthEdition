package chapter_4.session_3;

import chapter_1.session_3.Queue;
import chapter_1.session_5.UF;
import chapter_2.session_4.MinPQ;
/**
 * 需要用UnionFind，否则可能得到森林而不是树
 * @author ZhangYuliang
 *
 */
public class KruskalMST {
	private Queue<Edge> mst;
	
	private double weight;
	
	public KruskalMST(EdgeWeightedGraph G){
		mst = new Queue<Edge>();
		
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for (Edge e : G.edges())
			pq.insert(e);
		UF uf = new UF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V() -1){
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (uf.connected(v, w))
				continue;
			uf.union(v, w);
			weight += e.weight();
			mst.enqueue(e);
		}
	}
	public Iterable<Edge> edges(){
		return mst;
	}
	public double weight(){
		return weight;
	}
	public static void test(){
		EdgeWeightedGraph G = EdgeWeightedGraph.getTestEdgeWeightedGraph();
		KruskalMST mst = new KruskalMST(G);
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
