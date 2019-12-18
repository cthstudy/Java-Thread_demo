package queue.DelayQueue延迟队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 限时队列
 * @author CTH
 *
 */
public class DelayQueue无界 {

	static BlockingQueue<MyTask> task = new DelayQueue<>();
	
	static class MyTask implements Delayed{

		long runningTime;
		
		MyTask(long rt){
			this.runningTime=rt;
		}
		
		
		@Override
		public String toString() {
			return "MyTask [runningTime=" + runningTime + "]";
		}


		@Override
		public int compareTo(Delayed arg0) {
			if(this.getDelay(TimeUnit.MILLISECONDS) < arg0.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			}
			else if(this.getDelay(TimeUnit.MILLISECONDS) > arg0.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			}
			return 0;
		}

		@Override
		public long getDelay(TimeUnit arg0) {
			return arg0.convert(runningTime -System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask(now + 1000);
		MyTask t2 = new MyTask(now + 2000);
		MyTask t3 = new MyTask(now + 3000);
		MyTask t4 = new MyTask(now + 4000);
		MyTask t5 = new MyTask(now + 500);
		
		task.put(t1);
		task.put(t2);
		task.put(t3);
		task.put(t4);
		task.put(t5);
		
		System.out.println(task);
		
		for(int i = 0; i<5; i++) {
			System.out.println(task.take());
		}
	}
}
