package chapter_3.session_5;

public class SparseVector {
	private ST<Integer, Double> st;
	private int d;// dimension	
	public SparseVector(int d){
		this.d = d;
		this.st = new ST<Integer, Double>();
	}
	public void put(int i, double value){
		if (i < 0 || i >= d)
			throw new IndexOutOfBoundsException("Illegal index");
		if (value == 0.0)
			st.delete(i);
		else
			st.put(i, value);
	}
	public double get(int i){
		if (i < 0 || i >= d)
			throw new IndexOutOfBoundsException("Illegal index");
		if (!st.contains(i))
			return 0.0;
		else
			return st.get(i);
	}
	public int nnz(){
		return st.size();
	}
	public int size(){
		return d;
	}
	public int dimension(){
		return d;
	}
	public double dot(double[] that){
		double sun = 0.0;
		for (int i : st.keys())
			sun += that[i] * this.get(i);
		return sun;
	}
	public double dot(SparseVector that){
		if (this.d != that.d)
			throw new IllegalArgumentException("Vector length disagree");
		double sum = 0.0;
		if (this.st.size() <= that.size()){
			for (int i : this.st.keys()){
				if (that.st.contains(i))
					sum += this.get(i) * that.get(i);
			}
		}else{
			for (int i : that.st.keys()){
				if (this.st.contains(i))
					sum += this.get(i) * that.get(i);
			}
		}
		return sum;
	}
	public double magnitude(){
		return Math.sqrt(this.dot(this));
	}
	public double norm(){
		return Math.sqrt(this.dot(this));
	}
	public SparseVector scale(double alpha){
		SparseVector c = new SparseVector(d);
		for (int i : this.st.keys())
			c.put(i, alpha * this.get(i));
		return c;
	}
	public SparseVector plus(SparseVector that){
		if (this.d != that.d)
			throw new IllegalArgumentException();
		SparseVector c = new SparseVector(d);
		for (int i : this.st.keys())
			c.put(i, this.get(i));
		for (int i : that.st.keys())
			c.put(i, that.get(i) + c.get(i));
		return c;
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		for (int i : st.keys()){
			s.append("(" + i + "," + st.get(i) + ")");
		}
		return s.toString();
	}
}
