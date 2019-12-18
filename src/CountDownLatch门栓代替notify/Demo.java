package CountDownLatch门栓代替notify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 用CountDownLatch/semaphore代替notify和wait
 * @author CTH
 *
 */
public class Demo {

	volatile static List<Integer> list = new ArrayList<Integer>();
	CountDownLatch latch = new CountDownLatch(1);//1变成0的时候 门栓打开  latch.countDown()执行一次 -1  即1-1=0
	
	void add() {
		System.out.println("t1 启动了");
		for(int i=0; i<10; i++) {
			try {
				list.add(i);
				TimeUnit.SECONDS.sleep(1);
				if(list.size()==5) {
					//打开门栓让t2 执行
					latch.countDown();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println(list.size());
		}
	}
	void size() {
		System.out.println("t2 启动了");
		while(true) {
			if(list.size()==5) {
				System.out.println("线程1长度为5  t2停止");
				break;
			} else {
				try {
					latch.await();//门栓关闭 t2关闭  等着门栓打开
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Demo d = new Demo();
		new Thread(d::add,"t1").start();
		new Thread(d::size,"t2").start();
		
		/*new Thread(()-> {
			for(int i=0; i<10; i++) {
				try {
					list.add(i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(list.size());
			}
		},"t1").start();
		
		new Thread(()-> {
			while(true) {
				if(list.size()==5) {
					new Thread("t1").stop();
					System.out.println("线程1长度为5  停止");
					break;
				}
			}
		},"t2").start();*/
		
	}
	
}

