package 线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 支持定时及周期性任务执行——延迟执行
 * @author CTH
 *
 */
public class _ScheduledPool {

	public static void main(String[] args) {
		for(int i=0; i<10;i++) {
			a();
			System.out.println(i);
		}
//		ScheduledExecutorService service = Executors.newScheduledThreadPool(7);
//		service.scheduleAtFixedRate(()->{
//			try {
//				TimeUnit.MICROSECONDS.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName());
//		}, 0, 500, TimeUnit.MICROSECONDS);//每隔500毫秒 重复执行
		
	}
	
	public static int a() {
		return 0;
	}
}
