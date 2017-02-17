package chapter_4.session_2;

import chapter_1.session_3.Queue;

public class Degrees {
	private boolean[] marked;
	private int[] indegrees;
	private int[] outdegrees;

	public Degrees(Digraph G){
		marked = new boolean[G.V()];
		indegrees = new int[G.V()];
		outdegrees = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			dfs(G, v);
	}
	private void dfs(Digraph G, int v){
		marked[v] = true;
		for (int w : G.adj(v)){
			indegrees[w]++;
			outdegrees[v]++;
			if (!marked[w]){
				dfs(G, w);
			}
		}
	}
	public int indegree(int v){
		return indegrees[v];
	}
	public int outdegree(int v){
		return outdegrees[v];
	}
	
	public Iterable<Integer> sources(){
		Queue<Integer> queue = new Queue<Integer>();
		for (int v = 0; v < indegrees.length; v++){
			if (indegrees[v] == 0)
				queue.enqueue(v);
		}
		return queue;
	}
	/**
	 * �����յ�ļ���
	 * @return
	 */
	public Iterable<Integer> sinks(){
		Queue<Integer> queue = new Queue<Integer>();
		for (int v = 0; v < outdegrees.length; v++){
			if (outdegrees[v] == 0)
				queue.enqueue(v);
		}
		return queue;
	}
	/**
	 * һ����������Ի���ÿ������ĳ��Ⱦ�Ϊ1������ͼ����ӳ�䣨��0��V-1֮�����������������ĺ�����
	 * @return
	 */
	public boolean isMap(){
		for (int v = 0; v < outdegrees.length; v++){
			if (outdegrees[v] != 1)
				return false;
		}
		return true;
	}
	 
	public static void test(){
		Digraph G = Digraph.getTestDigraph();
		Degrees d = new Degrees(G);
		System.out.println(G.toString());
		System.out.println("indegree of " + 0 + " : " + d.indegree(0));
		System.out.println("outdegree of " + 0 + " : " + d.outdegree(0));
		System.out.print("source(): ");
		for (int v : d.sources())
			System.out.print(v + "\t");
		System.out.println();

		System.out.print("sinks(): ");
		for (int v : d.sinks())
			System.out.print(v + "\t");
		System.out.println();
		
		System.out.println("isMap(): " + d.isMap());
	}
	public static void main(String[] args){
		test();
	}

}
