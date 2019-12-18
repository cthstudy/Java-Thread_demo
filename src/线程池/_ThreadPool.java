package 线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author CTH
 *
 */
public class _ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		//创建一个有5个线程的线程池  execute submit
		ExecutorService service = Executors.newFixedThreadPool(5);
		for(int i=0; i<6; i++) {
			service.execute(()->{
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		//查看目前线程池
		System.out.println(service);//[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
		
		service.shutdown();//关闭
		System.out.println(service.isTerminated());//关闭任务是否 已经完成
		System.out.println(service.isShutdown());//是否已经关闭
		System.out.println(service);//[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());//关闭任务是否 已经完成
		System.out.println(service.isShutdown());//是否已经关闭
		System.out.println(service);
	}
}
