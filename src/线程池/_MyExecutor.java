package 线程池;

import java.util.concurrent.Executor;

public class _MyExecutor implements Executor{

	@Override
	public void execute(Runnable arg0) {
		arg0.run();
	}
	
	public static void main(String[] args) {
		new _MyExecutor().execute(()->{
			System.out.println("hello");
		});
	}
}
