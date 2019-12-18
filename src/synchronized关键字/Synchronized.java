package synchronized关键字;
/**
 * 对某个对象加锁
 * @author CTH
 * synchronized 锁定的 是一个对象
 */
public class Synchronized {

	private int count = 10;
//	private Object o = new Object();
	
	public void m() {
		synchronized (this) {//任何线程要执行下面的代码 都必须先拿到 this的锁
			count--;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
		}
	}
			
}
