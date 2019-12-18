package ReentrantLock关键字;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    *    线程1 lock上锁了后  并未关闭锁 且线程2一直在等待线程1的锁 时  使用interrupt在使线程2 放弃等待
 * @author CTH
 *
 */
public class Interrupt {

	Lock lock = new ReentrantLock();
	public void m1() {
		System.out.println("线程t1启动了");
		try {
			lock.lock();
			TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);//无限休眠
			System.out.println("t1end");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(lock.tryLock()) {
				lock.unlock();
			}
		}
	}
	
	public void m2() {
		lock.lock();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程t2启动了");
	}
	
	public static void main(String[] args) {
		new Thread(new Interrupt()::m1,"t1").start();
		new Thread(new Interrupt()::m2,"t2").start();
	}
}
