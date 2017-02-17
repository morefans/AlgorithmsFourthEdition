package chapter_4.session_2;
/**
 * 顶点对的可达性
 * @author ZhangYuliang
 *
 */
public class TransitiveClosure {
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph G){
		all = new DirectedDFS[G.V()];
		for (int v = 0; v < G.V(); v++){
			all[v] = new DirectedDFS(G, v);
		}
	}
	public boolean reachable(int v, int w){
		return all[v].marked(w);
		
	}

}
