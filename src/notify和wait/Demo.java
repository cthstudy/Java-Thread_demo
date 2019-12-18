package notify和wait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程2 监听 线程1（添加10个元素 当添加第五个的时候停止）
 * 需要加上volatile 保证 可见性
 * notify();不释放锁  唤醒等待的线程 并继续执行  wait()释放锁 让线程等待
 * @author CTH
 *
 */
public class Demo {

	volatile static List<Integer> list = new ArrayList<Integer>();
	synchronized void add() {
		System.out.println("t1 启动了");
		for(int i=0; i<10; i++) {
			try {
				list.add(i);
				TimeUnit.SECONDS.sleep(1);
				if(list.size() == 5) {
					//释放锁 并让t1进入等待
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println(list.size());
		}
	}
	synchronized void size() {
		System.out.println("t2 启动了");
		while(true) {
			if(list.size()==5) {
				System.out.println("线程1长度为5  t2停止");
				//唤醒线程t1 不释放锁 继续执行
				notify();
				break;
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
