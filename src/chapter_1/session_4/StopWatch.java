package chapter_1.session_4;
/**
 * ͣ����ʱ��
 * @author ZhangYuliang
 *
 */
public class StopWatch {
	private final long start;
	public StopWatch(){
		start = System.currentTimeMillis();
	}
	/**
	 * ��ȥ������
	 * @return ��λΪ��
	 */
	public double elapsedTime(){
		return (System.currentTimeMillis() - start) / 1000.0;
	}

}
