package synchronized关键字;

public class Demo implements Runnable {

	private int count =  10;
	
	public synchronized void run() {//一个线程执行完了 其他线程才执行 一次执行一个线程
		count--;
		System.out.println(Thread.currentThread().getName() + "=" + count);
	}

	public static void main(String[] args) {
		Demo d = new Demo();
		for(int i = 0; i<5; i++) {
			new Thread(d).start();
		}
	}
}
