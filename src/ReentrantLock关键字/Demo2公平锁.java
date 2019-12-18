package ReentrantLock关键字;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * @author 曹天化
 *
 */
public class Demo2公平锁 {

	ReentrantLock lock = new ReentrantLock(true);
	
	void m1() {
		System.out.println("线程1已启动");
		lock.lock();
		for(int i=0;i<3;i++) {
			try {
//				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName()+"获得锁"+i);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		lock.unlock();
		System.out.println("t1已解锁");
//		if(lock.tryLock()) {
//			lock.unlock();
//			System.out.println("t1已解锁");
//		}
	}
	
	void m2() {
		System.out.println("线程2已启动");
		lock.lock();
		for(int i=0;i<3;i++) {
			try {
//				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName()+"获得锁"+i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
		System.out.println("t2已解锁");
	}
	
	void m3() {
		System.out.println("线程3已启动");
		lock.lock();
		for(int i=0;i<3;i++) {
			try {
//				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName()+"获得锁"+i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
		System.out.println("t3已解锁");
	}
	
	public static void main(String[] args) {
		Demo2公平锁 d = new Demo2公平锁();
		new Thread(d::m1,"t1").start();
		new Thread(d::m2,"t2").start();
		new Thread(d::m3,"t3").start();
	}
}
