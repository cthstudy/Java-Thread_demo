package volatile关键字;
/**
 * volatile的作用 是提示正在执行的线程中 如果变量发生改变了 请将变量重新赋值带入
 */

import java.util.concurrent.TimeUnit;

public class Volatile {

	/* volatile */boolean flag = true;//对比加上volatile的运行效果
	void m() {
		System.out.println("m strat");
		while(flag) {
			
		}
		System.out.println("m end!");
	}
	
	public static void main(String[] args) {
		 
		Volatile v = new Volatile();
		new Thread(v::m).start();
		
		try {
//			TimeUnit.SECONDS.sleep(1);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		v.flag = false;
	}
}
