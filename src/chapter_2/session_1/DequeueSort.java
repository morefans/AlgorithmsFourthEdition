package chapter_2.session_1;
/**
 * 出列排序，扑克牌排序，只能查看最上面的两张牌，交换最上面的两张牌或者将最上面的一张牌放大这摞牌的最下面
 * @author ZhangYuliang
 *
 */
public class DequeueSort extends SortBase{

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] array) {
		if (array.length < 2)
			return ;
		int tail = array.length-1;
		for (int i = array.length-1;i > 0;i--){
			for (int j = i;j > 0;j--){
				if (notMoreThan(array[tail], array[tail-1])){
					exch(array, tail, tail-1);
					moveTailToFront(array);
				}
				else{
					moveTailToFront(array);
				}
			}
			for (int j = 0;j < array.length-i;j++){
				moveTailToFront(array);
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public static void moveTailToFront(Comparable[] array){
		for (int i = array.length-1;i > 0;i--)
			exch(array, i, i-1);
	}
	public static void main(String[] args) {
//		Integer[] array = {1,2,3,4,5,0};
//		moveTailToFront(array);
//		moveTailToFront(array);
//		show(array);
		new DequeueSort().test();
	}

}
