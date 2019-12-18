package 线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    *  线程前后按顺序执行
 * @author CTH
 *
 */
public class _SingleThreadPool线程前后按顺序执行 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i = 0; i<5; i++) {
			int j = i;
			service.execute(()->{
				System.out.println(j + "," + Thread.currentThread().getName());
			});
		}
	}
}
