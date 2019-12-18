package queue.高并发加锁队列;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * CoucurrentQueue
 * @author CTH
 *
 */
//add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
//remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
//element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
//offer       添加一个元素并返回true       如果队列已满，则返回false
//poll         移除并返问队列头部的元素    如果队列为空，则返回null   拿出来并在 队列中删除
//peek       返回队列头部的元素             如果队列为空，则返回null	拿出来不删除
//put         添加一个元素                      如果队列满，则阻塞
//take        移除并返回队列头部的元素     如果队列为空，则阻塞
public class ConcurrentQueue {

	static Queue<Integer> q = new ConcurrentLinkedQueue<>();
	
	public static void main(String[] args) {
		for(int i=1;i<=10;i++) {
			q.add(i);
		}
		System.out.println(q);
//		System.out.println(q.remove());//删除第一个元素
//		System.out.println(q.element());//拿到第一个元素
//		System.out.println(q.offer(158));//在尾部添加元素成功 返回 true  如果队列已满，则返回false
//		System.out.println(q.poll());//删除第一个元素
//		System.out.println(q.peek());
	}
	
}
