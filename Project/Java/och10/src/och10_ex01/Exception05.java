package och10_ex01;

public class Exception05 {

	static void method1() throws Exception {
		try {
			System.out.println("method1 run");
			throw new Exception();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("method1 예외처리");
			throw e;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main run");
		
		try {
			method1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("main 예외처리");
		}

	}

}
