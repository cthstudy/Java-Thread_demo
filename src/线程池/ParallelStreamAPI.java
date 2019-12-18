package 线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author CTH
 *
 */
public class ParallelStreamAPI {

	public static void main(String[] args) {
		
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for(int i = 0; i<10000; i++) {
			nums.add(1000000 + r.nextInt(1000000));
		}
//		System.out.println(nums);
		long start = System.currentTimeMillis();
		nums.forEach(num->isPrime(num));
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(ParallelStreamAPI::isPrime);//num->isPrime(num)
		System.out.println(System.currentTimeMillis()-start);
		
	}
	
	/**
	 * 判断是否是质数
	 * @param num
	 * @return
	 */
	private static boolean isPrime(int num) {
		for(int i = 2; i<=num/2; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
