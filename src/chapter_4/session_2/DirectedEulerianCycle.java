package chapter_4.session_2;

import java.util.Iterator;

import chapter_1.session_3.Stack;

public class DirectedEulerianCycle {
	private Stack<Integer> cycle;
	
	public DirectedEulerianCycle(Digraph G){
		if (G.E() <= 0)
			return ;
		Degrees degrees = new Degrees(G);
		for (int v = 0; v < G.V(); v++)
			if (degrees.indegree(v) != degrees.outdegree(v))
				return ;
		
		@SuppressWarnings("unchecked")
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();
        
		int s = nonIsolatedVertex(G);
		Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }
            cycle.push(v);
        }

        if (cycle.size() != G.E() + 1)
            cycle = null;
	}
	public static int nonIsolatedVertex(Digraph G){
		Degrees degrees = new Degrees(G);
		for (int v = 0; v < G.V(); v++){
			if (degrees.outdegree(v) > 0)
				return v;
		}
		return -1;
	}
	public Iterable<Integer> cycle() {
        return cycle;
    }
	public boolean hasEulerianCycle() {
        return cycle != null;
    }

}
