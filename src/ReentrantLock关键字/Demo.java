package ReentrantLock关键字;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock是手动上锁 手动释放 
 * @author 曹天化
 *
 */
public class Demo {

	Lock lock = new ReentrantLock();
	
	public void m1() {
		System.out.println("t1启动了");
		lock.lock();//手动上锁
		for(int i = 0;i<5;i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("t1,i="+i);
				if(i==2) {
					lock.unlock();//手动关闭锁
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if(lock.tryLock()) {
					lock.unlock();
				}
			}
		}
	}
	
	public void m2() {
		lock.lock();
		System.out.println("t2启动了");
		for(int i = 0;i<5;i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("t2,i="+i);
				if(i==2) {
					lock.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if(lock.tryLock()) {
					lock.unlock();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Demo d = new Demo();
		new Thread(d::m1,"t1").start();
		new Thread(d::m2,"t2").start();
	}
}
