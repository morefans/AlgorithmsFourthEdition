package chapter_4.session_3;
/**
 * 带权重的边
 * @author ZhangYuliang
 *
 */
public class Edge implements Comparable<Edge>{
	private final int v;// 顶点之一
	private final int w;// 另一个顶点
	private final double weight;// 边的权重
	
	public Edge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public double weight(){
		return weight;
	}
	public int either(){
		return v;
	}
	public int other(int vertex){
		if (vertex == v)
			return w;
		else if (vertex == w)
			return v;
		else throw new RuntimeException("Inconsistent edge");
	}
	@Override
	public int compareTo(Edge that) {
		if (this.weight() < that.weight())
			return -1;
		else if (this.weight() > that.weight())
			return 1;
		else
			return 0;
	}

}
