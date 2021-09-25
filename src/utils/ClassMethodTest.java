package utils;

import java.util.Arrays;
import java.util.List;

public class ClassMethodTest {

	//String.format
	public void TestStringFormat() {
		for(int i = 0; i < 20000; i += ++i) {
			String str1 = String.format("%04d", i);
			log(str1);
		}
		
	}
	
	public static void log(String msg) {
		System.out.println("TAG--" + msg + "--");
	}
	
	public static void lambdaTest() {
		List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6);
		l.stream().map(x -> x *x).forEach(System.out::println);
	}
}
