package och10_ex01;

public class Exception04 {

	static void method1() throws Exception {
		System.out.println("method1 run");
		throw new Exception();//강제 오류 발생
	}

	static void method2() throws Exception {
		System.out.println("method2 run");
		method1();
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		method2();
		System.out.println("main End");
	}

}
