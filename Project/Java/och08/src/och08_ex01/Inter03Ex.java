package och08_ex01;

public class Inter03Ex {

	public static void main(String[] args) {
		//Inter03_3 객체
		Inter03_3 interface3 = new Inter03_C1();
		interface3.a();
		interface3.b();
		interface3.c();
//		interface3.d();//Inter03_3 객체가 가진 함수만 사용 가능
		
		//Inter03_C1 객체
		Inter03_C1 class3 = new Inter03_C1();
		class3.a();
		class3.b();
		class3.c();
		class3.d();
	}

}
