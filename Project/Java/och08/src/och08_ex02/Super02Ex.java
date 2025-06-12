package och08_ex02;

class Cons1 {
	public Cons1(){
		System.out.println("Cons1 매개변수 0 부모 생성자");
	}
	public Cons1(String str){
		System.out.println("Cons1 매개변수 1 부모 생성자 : " + str);
	}
	void cc1Method() {
		System.out.println("Cons1 Method");
	}
}

class Cons2 extends Cons1{
	public Cons2() {
		super("띠용");
		System.out.println("Cons2 매개변수 0 자식 생성자");
	}
	void cc1Method() {
		System.out.println("Cons2 Method1");
	}
	void cc2Method() {
		System.out.println("Cons2 Method2");
	}
}

public class Super02Ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cons2 cons2 = new Cons2();
		
		cons2.cc2Method();
		cons2.cc1Method();
	}

}
