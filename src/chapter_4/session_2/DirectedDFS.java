package chapter_4.session_2;

public class DirectedDFS {
	private boolean[] marked;
	public  DirectedDFS(Digraph G, int s){
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	public DirectedDFS(Digraph G, Iterable<Integer> sources){
		marked = new boolean[G.V()];
		for (int s : sources){
			if (!marked[s])
				dfs(G, s);
		}
	}
	private void dfs(Digraph G, int v){
		marked[v] = true;
		
		System.out.print(v + "\t");
		
		for (int w : G.adj(v)){
			if (!marked[w])
				dfs(G, w);
		}
	}
	public boolean marked(int v){
		return marked[v];
	}
	public static void test(){
		System.out.print("dfs: ");
		new DirectedDFS(Digraph.getTestDigraph(), 0);
		System.out.println();
	}
	public static void main(String[] args){
		test();
	}

}
