package och08_ex01;

class Override01_1 {
	public int k;
	
	public void print() {
		System.out.println("Override01_1 print");
	}
}
class Override01_2 extends Override01_1 {
	public int kk;
	
	//오버라이드
//	public void print() {
//		System.out.println("Override01_2 print");
//	}
	
	public void print3() {
		System.out.println("Override01_2 print3");
	}
}



public class Override01 {

	public static void main(String[] args) {
		Override01_1 or1 = new Override01_1();
		Override01_2 or2 = new Override01_2();
		
		or1.print();
		or2.print();
		or2.print3();
		
		//
		Override01_1 or1_2 = new Override01_2();
		or1_2.k = 1;
//		or1_2.kk = 1;//불가능
		
//		Override01_2 or2_1 = new Override01_1();//불가능
	}

}
