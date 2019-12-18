package ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 管理局部变量  是使用空间换时间, synchronized是时间换空间
 * @author 曹天化
 *
 */
public class Demo {

	//volatile static PerSon p = new PerSon();
	static ThreadLocal<String> p = new ThreadLocal<>();
	
	public static void main(String[] args) {
		
		for(int i=0;i<20;i++) {
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(2);
				}catch (Exception e) {
					e.printStackTrace();
				}
//				p.set("CTH");//ThreadLocal 只修饰当前线程  线程安全
				System.out.println(p.get());
			},"线程1").start();
		}
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			}catch (Exception e) {
				e.printStackTrace();
			}
			p.set("CTH");
		},"线程2").start();
	}
	
}
class PerSon {
	String name = "李四";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

