package queue.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockQueue 有界队列
 * @author CTH
 *
 */
public class ArrayBlockBQ有界 {
	
	static BlockingQueue<Integer> strs = new ArrayBlockingQueue<>(10);
	
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<10;i++) {
			try {
				strs.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//strs.put(1);//添加不进去 会阻塞
//		strs.add(1);//添加失败 会报异常
//		strs.offer(1);//添加成功返回true功返回true 失败false
		System.out.println(strs.offer(1, 3, TimeUnit.SECONDS));//3秒后 重新尝试 添加
	}
	
}
