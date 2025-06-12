package och02_ex01;

public class VariableScopeEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i1 = 15;
		
		if(true) {
			int i2;
			
			i1 = 10;
			i2 = 20;
			
			System.out.println("1.i1:" + i1);
			System.out.println("2.i2:" + i2);
		}

		System.out.println("3.i1:" + i1);
//		System.out.println("4.i2:" + i2);	//선언되지 않은 변수 사용
	}

}
