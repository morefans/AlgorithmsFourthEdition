package chapter_4.session_3;

import chapter_1.session_3.Queue;
import chapter_2.session_4.MinPQ;

/**
 * Minimal Spanning Tree
 * @author ZhangYuliang
 *
 */
public class LazyPrimMST {
	private boolean[] marked;// 最小生成树的顶点
	private Queue<Edge> mst;// 最小生成树的边
	private MinPQ<Edge> pq;// 横切边（包括失效的边）
	
	private double weight;
	
	public LazyPrimMST(EdgeWeightedGraph G){
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
		weight = 0.0;
		
		visit(G, 0);// 假设G是连通的
		while (!pq.isEmpty()){
			Edge e = pq.delMin();// 从pq中得到权重最小的边
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w])
				continue;// 跳过失效的边
			mst.enqueue(e);// 将边添加到树中
			weight += e.weight();
			if (!marked[v])// 将顶点v或w添加到树中
				visit(G, v);
			if (!marked[w])
				visit(G, w);
		}
	}
	private void visit(EdgeWeightedGraph G, int v){
		// 标记顶点v并将所有连接v和未被标记顶点的边加入pq
		marked[v] = true;
		for (Edge e : G.adj(v))
			if (!marked[e.other(v)])
				pq.insert(e);
	}
	public Iterable<Edge> edges(){
		return mst;
	}
	public double weight(){
		return weight;
	}
	public double weightIntime(){
		double weight = 0.0;
		for (Edge e : mst)
			weight += e.weight();
		return weight;
	}
	
	public static void test(){
		EdgeWeightedGraph G = EdgeWeightedGraph.getTestEdgeWeightedGraph();
		LazyPrimMST mst = new LazyPrimMST(G);
		System.out.println("Total weight: " + mst.weight() + "\t" + mst.weightIntime());
		for (Edge e : mst.edges()){
			int v = e.either();
			System.out.println(v + "--" + e.other(v) + "(" + e.weight() + ")");
		}
	}
	public static void main(String[] args){
		test();
	}

}
