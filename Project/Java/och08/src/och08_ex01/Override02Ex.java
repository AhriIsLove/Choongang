package och08_ex01;

class Override02_1
{
	void print1() {
		System.out.println("부모클래스.메소드 : 바꾸기 가능");
	}
	final void print2() {
		System.out.println("부모클래스.final메소드 : 변경 금지");
	}
}

class Override02_2 extends Override02_1{
	@Override
	void print1() {
		// TODO Auto-generated method stub
//		super.print1();
		System.out.println("자식클래스.메소드 : 바꾸었음");
	}

	//final메소드 오버라이드 불가능
//	@Override
//	void print2() {
//		// TODO Auto-generated method stub
//		super.print2();
//		System.out.println("자식클래스.메소드 : 바꾸기 가능");
//	}
	
	void print3() {
		System.out.println("자식클래스.고유메소드");
	}
}

public class Override02Ex {

	public static void main(String[] args) {
		Override02_1 or1 = new Override02_1();
		Override02_2 or2 = new Override02_2();
		
		or1.print1();
		or1.print2();
		
		or2.print1();
		or2.print2();

		Override02_1 or1_2 = new Override02_2();
		or1_2.print1();
		or1_2.print2();
//		or1_2.print3();//불가능
	}

}
