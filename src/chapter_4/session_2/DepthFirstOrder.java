package chapter_4.session_2;

import chapter_1.session_3.Queue;
import chapter_1.session_3.Stack;
import chapter_4.session_4.DirectedEdge;
import chapter_4.session_4.EdgeWeightedDigraph;

public class DepthFirstOrder {
	private boolean[] marked;
	private int[] pre;// pre[v]Ϊǰ��������v������
	private int[] post;// post[v]Ϊ����������v������
	private Queue<Integer> preOrder;// ���ж����ǰ������
	private Queue<Integer> postOrder;// ���ж���ĺ�������
	private Stack<Integer> reversePost;// ���ж�������������
	private int preCounter;
	private int postCounter;
	
	public DepthFirstOrder(Digraph G){
		pre = new int[G.V()];
		post = new int[G.V()];
		preOrder = new Queue<Integer>();
		postOrder = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for (int v= 0; v < G.V(); v ++)
			if (!marked[v])
				dfs(G, v);
	}
	public DepthFirstOrder(EdgeWeightedDigraph G) {
        pre    = new int[G.V()];
        post   = new int[G.V()];
        postOrder = new Queue<Integer>();
        preOrder  = new Queue<Integer>();
		reversePost = new Stack<Integer>();
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }
	
	private void dfs(Digraph G, int v){
		preOrder.enqueue(v);
		marked[v] = true;
		pre[v] = preCounter++;
		for (int w : G.adj(v)){
			if (!marked[w])
				dfs(G, w);
		}
		postOrder.enqueue(v);
		reversePost.push(v);
		post[v] = postCounter++;
	}
	private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        postOrder.enqueue(v);
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postOrder.enqueue(v);
		reversePost.push(v);
        post[v] = postCounter++;
    }
	public int pre(int v) {
        validateVertex(v);
        return pre[v];
    }
	public int post(int v) {
        validateVertex(v);
        return post[v];
    }
	// dfs�ĵ���˳��
	public Iterable<Integer> preOrder(){
		return preOrder;
	}
	// dfs���������ɵ�˳��
	public Iterable<Integer> postOrder(){
		return postOrder;
	}
	// �����
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
	private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	public static void test(){
		DepthFirstOrder dfo = new DepthFirstOrder(Digraph.getTestDigraph());
		System.out.print("DepthFirstOrder preOrder order: ");
		for (int v : dfo.preOrder())
			System.out.print(v + "\t");
		System.out.println();

		System.out.print("DepthFirstOrder postOrder order: ");
		for (int v : dfo.postOrder())
			System.out.print(v + "\t");
		System.out.println();

		System.out.print("DepthFirstOrder reverse postOrder order: ");
		for (int v : dfo.reversePost())
			System.out.print(v + "\t");
		System.out.println();
	}
	public static void main(String[] args){
		test();
	}

}
