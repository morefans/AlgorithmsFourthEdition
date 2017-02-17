package chapter_4.session_1;

import chapter_1.session_3.Queue;
import chapter_1.session_3.Stack;

public class BreadthFirstPaths {

    private static final int INFINITY = Integer.MAX_VALUE;
    
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private final int s;
	public BreadthFirstPaths(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        
		this.s = sources.iterator().next();
		
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        bfs(G, sources);
    }
	private void bfs(Graph G, int s){
		Queue<Integer> queue = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
		marked[s] = true;
		System.out.print(s + "\t");
		queue.enqueue(s);
		while (!queue.isEmpty()){
			int v = queue.dequeue();
			for (int w : G.adj(v)){
				if (!marked[w]){
					edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
					marked[w] = true;
					System.out.print(w + "\t");
					queue.enqueue(w);
				}
			}
		}
	}
	private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            
    		System.out.print(s + "\t");
    		
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
            		System.out.print(w + "\t");
                    q.enqueue(w);
                }
            }
        }
    }
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public Iterable<Integer> pathTo(int v){
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]){
			path.push(x);
		}
		path.push(s);
		return path;
	}
	public Iterable<Integer> pathToVice(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
	/**
	 * distance from source to v
	 * 起点到给定顶点路径长度 
	 * @param v
	 * @return
	 */
	public int distTo(int v) {
        return distTo[v];
    }
	public static void test(){
		Graph G = Graph.getTestGraph();
		System.out.print("bfs: ");
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, 0);
		System.out.println();
		for (int v = 0; v < G.V();v++){
			System.out.println("distance from source to " + v + " : " + bfs.distTo(v));
			
		} 

		System.out.print("pathTo(4):");
		for (int v : bfs.pathTo(4))
			System.out.print(v + "\t");
	}
	public static void main(String[] args){
		test();
	}

}



