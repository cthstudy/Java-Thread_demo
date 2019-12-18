package 生产者和消费者;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

/**
 * 面试题:写一个固定容量同步容器,拥有put和get方法, 以及getCount方法,
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * 使用wait和notify/notifyAll来实现
 * @author	CTH
 *
 */
public class Demo<T> {

	private final List list = new ArrayList<>();
	private final int max = 10;//最多10个元素
	
	private int count = 0;
	public synchronized void put(T t) {
		while(list.size() == max) {//为什么用while 不用if(随时检测)
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		list.add(t);
		++count;
		this.notifyAll();//通知消费者线程进行消费
	}
	
	public synchronized  T get(int index) {
		T t = null;
		while(list.size() == 0) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		t = (T) list.remove(index);
		count--;
		this.notifyAll();//通知生产者进行生产
		return t;
	}
	
	public static void main(String[] args) {
		Demo<String> d = new Demo<>();
		//启动消费者线程
		for(int i=0 ; i<1; i++) {
			new Thread(()->{
				for(int j=0; j<5; j++) {
					System.out.println(d.get(j));
				}
			},"生产者" + i).start();
		}
		
		//启动生产者线程
		for(int i=0;i<1; i++) {
			new Thread(()->{
				for(int j=0;j<25;j++) {
					d.put(Thread.currentThread().getName() + " " + j);
				}
			},"消费者" + i).start();
		}
	}
}
