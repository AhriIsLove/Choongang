package och10_ex03;

public class Ex01 {

	static void method1(boolean b) {
		try {
			System.out.println(1 + "b : " + b);
			if(b) throw new ArithmeticException();
			System.out.println(2);
		} catch (RuntimeException e) {
			System.out.println("RuntimeException : " + 3);
			return;
		} catch (Exception e) {
			System.out.println(4);
			return;
		} finally {
			System.out.println("예외발생여부에 관계없이 항상 실행되는 문장 : " + 5);
		}
		System.out.println("정상적으로 끝나야 실행되는 문장");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-true-");
		method1(true);
		System.out.println("-false-");
		method1(false);
	}

}
