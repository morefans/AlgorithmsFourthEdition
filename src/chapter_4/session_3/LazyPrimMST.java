package chapter_4.session_3;

import chapter_1.session_3.Queue;
import chapter_2.session_4.MinPQ;

/**
 * Minimal Spanning Tree
 * @author ZhangYuliang
 *
 */
public class LazyPrimMST {
	private boolean[] marked;// ��С�������Ķ���
	private Queue<Edge> mst;// ��С�������ı�
	private MinPQ<Edge> pq;// ���бߣ�����ʧЧ�ıߣ�
	
	private double weight;
	
	public LazyPrimMST(EdgeWeightedGraph G){
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
		weight = 0.0;
		
		visit(G, 0);// ����G����ͨ��
		while (!pq.isEmpty()){
			Edge e = pq.delMin();// ��pq�еõ�Ȩ����С�ı�
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w])
				continue;// ����ʧЧ�ı�
			mst.enqueue(e);// ������ӵ�����
			weight += e.weight();
			if (!marked[v])// ������v��w��ӵ�����
				visit(G, v);
			if (!marked[w])
				visit(G, w);
		}
	}
	private void visit(EdgeWeightedGraph G, int v){
		// ��Ƕ���v������������v��δ����Ƕ���ı߼���pq
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
