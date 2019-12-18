package 火车票抢购;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Queue队列不加锁 {
//	add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
//	remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
//	element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
//	offer       添加一个元素并返回true       如果队列已满，则返回false
//	poll         移除并返问队列头部的元素    如果队列为空，则返回null   拿出来并在 队列中删除
//	peek       返回队列头部的元素             如果队列为空，则返回null	拿出来不删除
//	put         添加一个元素                      如果队列满，则阻塞
//	take        移除并返回队列头部的元素     如果队列为空，则阻塞

	static Queue<String> que = new ConcurrentLinkedDeque<>();
	
	public static void main(String[] args) {
		
		for(int i=1;i<=1000;i++) {
			que.add("票编号:"+i);//先产生1000张票
		}
		
		for(int i=0;i<10;i++) {
			new Thread(()->{
				while(true) {
					String s = que.poll();//用remove会报NoSuchElementException异常
					if(s == null) {
						break;
					}else {
						try {
							Thread.sleep(10);
							System.out.println(Thread.currentThread().getName() + "---购买了" + s + "---余票" + que.size());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			},"线程"+i).start();
		}
	}
}
