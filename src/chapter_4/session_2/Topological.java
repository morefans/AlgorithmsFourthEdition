package chapter_4.session_2;

import chapter_4.session_4.EdgeWeightedDigraph;
import chapter_4.session_4.EdgeWeightedDirectedCycle;

public class Topological {
	private Iterable<Integer> order;
	private int[] rank;// rank[v]为拓扑排序中v的排名
	
	public Topological(Digraph G){
		DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
 	}
	public Topological(EdgeWeightedDigraph G) {
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
	public Iterable<Integer> order(){
		return order;
	}
	public boolean hasOrder() {
        return order != null;
    }
	public boolean isDAG(){
		return hasOrder();
	}
	public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return rank[v];
        else            return -1;
    }
	private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	public static void test(){
		System.out.println("Test directed graph isDAG():" + new Topological(Digraph.getTestDigraph()).isDAG());
		
	}
	public static void main(String[] args){
		test();
	}

}
