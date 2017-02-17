package chapter_4.session_4;

public class DijkstraAllPairsSP {
	private DijkstraSP[] all;
	public DijkstraAllPairsSP(EdgeWeightedDigraph G){
		all = new DijkstraSP[G.V()];
		for (int v = 0; v < G.V(); v++)
			all[v] = new DijkstraSP(G, v);
	}
	public Iterable<DirectedEdge> path(int s, int t){
		return all[s].pathTo(t);
	}
	public double dist(int s, int v){
		return all[s].distTo(v);
	}

}
