package 火车票抢购;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用synchronized加锁
 * @author CTH
 *
 */
public class 使用synchronized加锁 {

	public static  List<String> list = new ArrayList<String>();
	
	public static void main(String[] args) {
		for(int i=1;i<100;i++) {
			list.add("票 编号:"+i);
		}
		for(int i=0;i<10;i++) {
			new Thread(()->{
				while(true) {
					synchronized (list) {
						if(list.size() <= 0) {
							System.out.println(Thread.currentThread().getName()+"票已卖完");
							break;
						}
						try {
							TimeUnit.MILLISECONDS.sleep(10);
							System.out.println(Thread.currentThread().getName()+"购买了----"+list.remove(0));
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			},"线程"+(i+1)).start();
		}
	}
}
