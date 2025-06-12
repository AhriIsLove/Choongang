package och08_ex02;

class Super01 {
	int a = 7;
	int b = 7;
	
	void dispaly() {
		System.out.println("Super01.display");
	}
	void print() {
		System.out.println("Super01.print");
	}
}

class Super02 extends Super01{	
	int a = 10;
	
	@Override
	void print() {
		// TODO Auto-generated method stub
//		super.print();
//		System.out.println("Super02.print");
		
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		System.out.println("super.a : " + super.a);
	} 
}

class Super03 extends Super02 {
	@Override
	void print() {
		// TODO Auto-generated method stub
		//부모클래스의 메소드 : (조부모클래스의 메소드는 안한다.)
		super.print();
		
		System.out.println("Super03.print");
	}
}

public class Super01Ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Super03 super03 = new Super03();
		super03.print();
	}

}
