package synchronizedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 加了锁的list
 * @author 曹天化
 *
 */
public class SynchronizedList {

	private static List<Integer> list = new ArrayList<>();
	private static List<Integer> list_sync = Collections.synchronizedList(list);
	
	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		for(int i=1;i<=10;i++) {
			new Thread(()->{
				synchronized (list) {
					for(int j = 0;j<10000;j++) {
//						list_sync.add(j);
						list.add(j);
					}
					System.out.println(Thread.currentThread().getName()+"---耗时:"+(System.currentTimeMillis()-t)+"---长度:"+list.size());
				}
//				System.out.println(Thread.currentThread().getName()+"---耗时:"+(System.currentTimeMillis()-t)+"---长度:"+list_sync.size());
			},"线程"+i).start();
		}
	}
}
