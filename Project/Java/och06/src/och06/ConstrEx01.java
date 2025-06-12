package och06;

class Sonata {
	int a = 0;
	String str = "";
	public Sonata() {
		System.out.println("생성자");
	}
	public Sonata(int a) {
		System.out.println("int 생성자");
		this.a = a;
	}
	public Sonata(String str) {
		System.out.println("String 생성자");
		this.str = str;
	}
}

public class ConstrEx01 {

	public static void main(String[] args) {
		Sonata s1 = new Sonata();
		Sonata s2 = new Sonata(5);
		Sonata s3 = new Sonata("에어백");

	}

}
