package och07_ex01;

class G2{
	int k;
	
	G2() {
		System.out.println("매개변수 X");
	}
	G2(int k) {
		this();
		this.k = k;
		System.out.println("매개변수 1 : " + k);
	}
	G2(int k, int y) {
//		G2();//불가능
		this(y);
		this.k = k;
		System.out.println("매개변수 2 : " + k + ", " + y);
	}
	void Print() {
		System.out.println("k = " + k);
	}
}

public class ThisEx02 {

	public static void main(String[] args) {
		G2 g = new G2();
		G2 g2 = new G2(1);
		g2.Print();
		G2 g3 = new G2(2, 3);
		g3.Print();
	}

}
