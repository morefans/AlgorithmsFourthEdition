package chapter_4.session_4;


public class DirectedEdge implements Comparable<DirectedEdge> { 
	private final int v;// �ߵ����
	private final int w;// �ߵ��յ�
	private final double weight;// �ߵ�Ȩ��
	
	public DirectedEdge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public double weight(){
		return weight;
	}
	public int from(){
		return v;
	}
	public int to(){
		return w;
	}
	public String toString(){
		return String.format("%d->%d(%.2f)", v, w, weight);
	}
	@Override
	public int compareTo(DirectedEdge that) {
		if (this.weight() < that.weight())
			return -1;
		else if (this.weight() > that.weight())
			return 1;
		else
			return 0;
	}

}
