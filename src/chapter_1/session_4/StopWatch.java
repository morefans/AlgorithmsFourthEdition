package chapter_1.session_4;
/**
 * 停表，计时用
 * @author ZhangYuliang
 *
 */
public class StopWatch {
	private final long start;
	public StopWatch(){
		start = System.currentTimeMillis();
	}
	/**
	 * 逝去的秒数
	 * @return 单位为秒
	 */
	public double elapsedTime(){
		return (System.currentTimeMillis() - start) / 1000.0;
	}

}
