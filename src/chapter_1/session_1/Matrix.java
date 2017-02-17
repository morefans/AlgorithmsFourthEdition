package chapter_1.session_1;


public class Matrix {
	public static double dot(double[] x, double[] y) {
		double result = 0;
		if (x.length != y.length)
			throw new Error("not match");
		for (int i = 0; i < x.length; i++){
			result += x[i] * y[i];
		}
		return result;
	}

	public static double[][] mult(double[][] a, double[][] b) {
		if (a[0].length != b.length)
			throw new Error("These two Matrixes does not match!");
		int row = a.length;
		int col = b[0].length;
		double result = 0;
		double[][] mult = new double[row][col];
		for (int i = 0;i < row;i++){
			for (int j = 0;j < col;j++){
				for (int k = 0;k < a[0].length;k++){
					result += a[i][k] * b[k][j];
				}
				mult[i][j] = result;
				result = 0;
			}
		}
		return mult;
	}
	public static double[][] transpose(double[][] a){
		int row = a[0].length;
		int col = a.length;
		double[][] result = new double[row][col];
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				result[i][j] = a[j][i];
			}
		}
		return result;
	}
	public static double[] mult(double[][] a, double[] x) {
		if (a[0].length != x.length)
			throw new Error("These two Matrixes does not match!");
		int row = a.length;
		double result = 0;
		double[] mult = new double[row];
		for (int i = 0;i < row;i++){
			for (int k = 0;k < a[0].length;k++){
				result += a[i][k] * x[k];
			}
			mult[i] = result;
			result = 0;
		}
		return mult;
	}
	public static double[] mult(double[] y, double[][] a) {
		if (a.length != y.length)
			throw new Error("These two Matrixes does not match!");
		int col = a[0].length;
		double result = 0;
		double[] mult = new double[col];
		for (int i = 0;i < col;i++){
			for (int k = 0;k < y.length;k++){
				result += y[k] * a[k][i];
			}
			mult[i] = result;
			result = 0;
		}
		return mult;
	}
	public static void print(double[] array){
		int i;
		System.out.print("{");
		for (i = 0; i < array.length-1; i++){
			System.out.print(array[i] + "\t");
		}
		System.out.println(array[i] + "}");
	}

	public static void print(double[][] array){
		for (int row = 0; row < array.length; row++){
			print(array[row]);
		}
	}
	public static void test(){
		double[] x = {1,2,3,4,5};
		double[] y = {1,1,1,1,1};
		double[][] a = {
				{1,1,1,1,1},
				{1,1,1,1,1}
		};
		double[][] b = {
				{1,1},
				{1,1},
				{1,1},
				{1,1},
				{1,1}
		};
		System.out.println("µã³Ë£º");
		System.out.println(dot(x,y));
		System.out.println("¾ØÕó³Ë¾ØÕó£º");
		print(mult(a,b));
		System.out.println("×ªÖÃ¾ØÕó£º");
		print(transpose(b));
		System.out.println("¾ØÕó³ËÏòÁ¿£º");
		print(mult(a,y));
		System.out.println("ÏòÁ¿³Ë¾ØÕó£º");
		print(mult(y,b));
	}
	public static void main(String[] args){
		Matrix.test();
	}
}
