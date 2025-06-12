package och08_ex02;

class C01 {
	public C01() {
		System.out.println("C01 매개변수 0 조부모 생성자");
	}
	public C01(int x) {
		this();
		System.out.println("C01 매개변수 1 조부모 생성자:" + x);
	}
}
class C02 extends C01{
	public C02(int x) {
		super(x);
		System.out.println("C02 매개변수 1 부모 생성자 : " + x);
	}
	public C02(int x, int y) {
		this(x);
		System.out.println("C02 매개변수 2 부모 생성자 : x=" + x + ", y=" + y);
	}
}
class C03 extends C02{
	public C03(int x, int y) {
		super(x, y);
		System.out.println("C03 매개변수 2 자식 생성자 : x=" + x + ", y=" + y);
	}
	void print() {
		System.out.println("C03 print");
	}
}

public class Super03Ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		C03 c03 = new C03(7, 15);
		c03.print();
	}

}
