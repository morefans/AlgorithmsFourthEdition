package chapter_1.session_1;
/**
 * 习题1.1.13，矩阵的转置
 * @author ZhangYuliang
 *
 */
public class TransMatrix {
	
	public static int[][] transposition(int[][] array){
		int row = array.length;
		int col = array[0].length;
		int[][] tMatrix = new int[col][row];
		for (int  i = 0; i < col; i++){
			for (int j = 0; j < row; j++){
				tMatrix[i][j] = array[j][i];
			}
		}
		return tMatrix;
	}
	public static void print(int[][] array){
		int row = array.length;
		int col = array[0].length;
		for (int  i = 0; i < row; i++){
			int j;
			System.out.print("{");
			for (j = 0; j < col-1; j++){
				System.out.print(array[i][j] + "\t");
			}
			System.out.println(array[i][j] + "}");
		}
	}
	public static void test(){
		int[][] array = {
				{11,12,13,14,15,16},
				{21,22,23,24,25,26}
		};
		print(array);
		array = transposition(array);
		System.out.println("转置后：");
		print(array);
	}
	public static void main(String[] args){
		TransMatrix.test();
	}

}
