package queue.阻塞队列;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 一个由链接节点支持的可选有界队列。
 * @author CTH
 *
 */
public class LinkedBQ无界 {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
	static Random r = new Random();
	
	public static void main(String[] agrs) {
		//1个 生产者
		new Thread(()->{
			for(int i=0 ;i<100;i++) {
				try {
					strs.put("p"+i);//如果队列满了 就会等待   形成阻塞  
					System.out.println("生产者添加了p"+i);
					TimeUnit.MILLISECONDS.sleep(5000);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"p1").start();
		
		//10个消费者线程
		for(int i=1;i<=5;i++) {
			new Thread(()->{
				while(true) {
					try {
						System.out.println(Thread.currentThread().getName()+" --- 消费了 ---"+strs.take());//队列如果空了 就会等待 形成阻塞
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			},"线程"+i).start();
		}
//		System.out.println(strs);
	}
}
