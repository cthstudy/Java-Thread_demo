package ReentrantLock关键字;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * @author 曹天化
 *
 */
public class Demo1 extends Thread {

	ReentrantLock lock = new ReentrantLock();//公平锁 true  非公平锁默认不写
	@Override
	public void run() {
		lock.lock();
		for(int i=0;i<5;i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName()+"获得锁"+i);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		lock.unlock();
	}
	
	public static void main(String[] args) {
		Demo1 d = new Demo1();
		new Thread(d,"t1").start();
		new Thread(d,"t2").start();
		new Thread(d,"t3").start();
	}
}
